package heroku_demo.api.controllers;

import heroku_demo.api.dto.GameStatistic;
import heroku_demo.api.dto.HeroDto;
import heroku_demo.api.dto.UserDtoViewAllUsers;
import heroku_demo.api.services.RestRequestServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller

public class StatisticController {

    @Value("${applicationHost}")
    private String applicationHost;

    private final RestRequestServices restRequestServices;

    public StatisticController(RestRequestServices restRequestServices) {
        this.restRequestServices = restRequestServices;
    }

    @RequestMapping(value = "/statistic", method = RequestMethod.POST)
    public ModelAndView statistic() {

        return null;
    }

    @RequestMapping(value = "/view/statistic", method = RequestMethod.GET)
    public ModelAndView viewStatistic(HttpServletRequest request,Map<String, Object> model) {

        model.put("link",applicationHost+"/get_stats_html");
        List<HeroDto> heroDtos = restRequestServices.getAllHeroes(request);
        List<UserDtoViewAllUsers> listAllUsers = restRequestServices.getAllUsers(request);
        model.put("users",listAllUsers);
        model.put("heroes", heroDtos);
        Cookie[] cookies = request.getCookies();
        Cookie cookie = restRequestServices.getCookieByName(cookies,"token");
        model.put("token",cookie.getValue());
        return new ModelAndView("statistic",model);
    }

    @RequestMapping(value = "/get_stats_html", method = RequestMethod.GET)
    public ModelAndView getStats(@RequestParam(name = "userId",required = false) Integer userId,
                                 @RequestParam(name = "heroId",required = false) Integer heroId,
                                 HttpServletRequest request,
                                 Map<String, Object> model) {
        GameStatistic gameStatistic = restRequestServices.getStatistic(request,userId,heroId);
        model.put("gamesQuantity",gameStatistic.getGamesCount().toString());
        model.put("winsQuantity",gameStatistic.getWinsCount().toString());
        model.put("looseQuantity",gameStatistic.getLooseCount().toString());
        model.put("victoryPercent",gameStatistic.getWinPercent().toString());

        return new ModelAndView("templates/stats",model);
    }

}

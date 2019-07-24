package heroku_demo.api.controllers;

import heroku_demo.api.dto.GameStatistic;
import heroku_demo.api.dto.HeroDto;
import heroku_demo.api.services.RestRequestServices;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class StatisticController {

    private final RestRequestServices restRequestServices;

    @RequestMapping(value = "/statistic", method = RequestMethod.POST)
    public ModelAndView statistic() {

        return null;
    }

    @RequestMapping(value = "/view/statistic", method = RequestMethod.GET)
    public ModelAndView viewStatistic(HttpServletRequest request,Map<String, Object> model) {

        model.put("host","http://localhost:8085");
        List<HeroDto> heroDtos = restRequestServices.getAllHeroes(request);
        model.put("heroes", heroDtos);
        Cookie[] cookies = request.getCookies();
        Cookie cookie = restRequestServices.getCookieByName(cookies,"token");
        model.put("token",cookie.getValue());
        return new ModelAndView("statistic",model);
    }

    @RequestMapping(value = "/get_stats_html", method = RequestMethod.GET)
    public ModelAndView getStats(@RequestParam(name = "userEmail",required = false) String userEmail,
                                 @RequestParam(name = "heroId",required = false) Integer heroId,
                                 HttpServletRequest request,
                                 Map<String, Object> model) {
        GameStatistic gameStatistic = restRequestServices.getStatistic(request,userEmail,heroId);
        model.put("gamesQuantity",gameStatistic.getGamesCount().toString());
        model.put("winsQuantity",gameStatistic.getWinsCount().toString());
        model.put("looseQuantity",gameStatistic.getLooseCount().toString());
        model.put("victoryPercent",gameStatistic.getWinPercent().toString());

        return new ModelAndView("templates/stats",model);
    }

}

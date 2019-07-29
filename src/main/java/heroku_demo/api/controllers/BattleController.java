package heroku_demo.api.controllers;

import heroku_demo.api.dto.HeroDto;
import heroku_demo.api.dto.UserDtoViewAllUsers;
import heroku_demo.api.exceptions.ForbiddenException;
import heroku_demo.api.services.RestRequestServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller

public class BattleController {
    private final RestRequestServices restRequestServices;

    @Value("${restApplicationHost}")
    private String restApplicationHost;

    @Value("${applicationHost}")
    private String applicationHost;

    public BattleController(RestRequestServices restRequestServices) {
        this.restRequestServices = restRequestServices;
    }


    @RequestMapping(value = "/view/add_battle", method = RequestMethod.GET)
    public ModelAndView addBattle(HttpServletRequest request, Map<String, Object> model) {

        try {
            model.put("victory", "Victory");
            model.put("loose", "Loose");
            model.put("halfVictory", "Half Victory");
            model.put("applicationHost",applicationHost);
            model.put("restApplicationHost",restApplicationHost);
            Integer loggedUserId  = restRequestServices.getLoggedUserId(request);
            model.put("youId",loggedUserId);
            List<HeroDto> heroDtos = restRequestServices.getAllHeroes(request);
            model.put("heroes", heroDtos);
        } catch (ForbiddenException f) {
            return new ModelAndView("forbidden", model);
        }
        return new ModelAndView("add_battle", model);
    }


    @RequestMapping(value = "/get_enemy_html", method = RequestMethod.GET)
    public ModelAndView getEnemyHtml(HttpServletRequest request, Map<String, Object> model,@RequestParam(value = "enemiesQuantity") int enemiesQuantity) {
        int enemyNumber = enemiesQuantity+1;
        model.put("enemyNumber",enemyNumber);
        model.put("victory", "Victory");
        model.put("loose", "Loose");
        model.put("halfVictory", "Half Victory");

        List<HeroDto> heroDtoList = restRequestServices.getAllHeroes(request);
        model.put("heroes",heroDtoList);
        List<UserDtoViewAllUsers> listAllUsers = restRequestServices.getAllUsers(request);
        model.put("users",listAllUsers);
        return new ModelAndView("templates/enemy", model);
    }
}

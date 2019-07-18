package heroku_demo.api.controllers;

import heroku_demo.api.exceptions.ForbiddenException;
import heroku_demo.api.exceptions.RestApiException;
import heroku_demo.api.services.RestRequestServices;
import heroku_demo.api.services.ViewServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@AllArgsConstructor
public class BattleController {
    private final RestRequestServices restRequestServices;
    private final ViewServices viewServices;


    @RequestMapping(value = "/view/add_battle", method = RequestMethod.GET)
    public ModelAndView addBattle(HttpServletRequest request, Map<String, Object> model, @RequestParam(value = "message", required = false) String message) {

        try {
            viewServices.viewBattle(request, model, 1,message);
        } catch (ForbiddenException f) {
            return new ModelAndView("forbidden", model);
        }
        return new ModelAndView("add_battle", model);
    }

    @RequestMapping(value = "/add_battle", method = RequestMethod.POST)
    public ModelAndView addBattle(@RequestParam Map<String, String> allRequestParams, HttpServletRequest request, Map<String, Object> model) {
        try {
            restRequestServices.saveBattleResults(allRequestParams, request);
        } catch (RestApiException restException) {
            int enemiesQuantity =  Integer.valueOf(allRequestParams.get("enemiesQuantity"));
            model.put("reducingEnemy","");
            return viewEnemies(allRequestParams, model, request, enemiesQuantity,"Error - "+restException.getMessage());
        } catch (ForbiddenException f) {
            return new ModelAndView("forbidden",model);
        }
        model.put("message","Game Successfully Added");
        return new ModelAndView("redirect:" + "/view/add_battle",model);
    }

    @RequestMapping(value = "/add_battle/add_enemy", method = RequestMethod.POST)
    public ModelAndView addEnemy(@RequestParam Map<String, String> allRequestParams, Map<String, Object> model, HttpServletRequest request) {
        int enemiesQuantity = Integer.valueOf(allRequestParams.get("enemiesQuantity")) + 1;
        return viewEnemies(allRequestParams, model, request, enemiesQuantity,null);
    }

    @RequestMapping(value = "/add_battle/reduce_enemy", method = RequestMethod.POST)
    public ModelAndView reduceEnemy(@RequestParam Map<String, String> allRequestParams, Map<String, Object> model, HttpServletRequest request) {
        int enemiesQuantity =  Integer.valueOf(allRequestParams.get("enemiesQuantity"))-1;
        model.put("reducingEnemy","");
        return viewEnemies(allRequestParams, model, request, enemiesQuantity,null);
    }

    private ModelAndView viewEnemies(@RequestParam Map<String, String> allRequestParams, Map<String, Object> model, HttpServletRequest request, int enemiesQuantity,String message) {
        try {
            viewServices.viewBattleWithAddedEnemy(request, model, enemiesQuantity,message,allRequestParams);
        } catch (RestApiException restException) {
            System.out.println("exceprion catched");
        } catch (ForbiddenException f) {
            return new ModelAndView("forbidden", model);
        }
        return new ModelAndView("add_battle", model);
    }
}

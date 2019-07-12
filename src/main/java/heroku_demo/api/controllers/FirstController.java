package heroku_demo.api.controllers;

import heroku_demo.api.dto.GameDto;
import heroku_demo.api.dto.UnapprovedGamesDto;
import heroku_demo.api.exceptions.ForbiddenException;
import heroku_demo.api.exceptions.RestApiException;
import heroku_demo.api.services.ViewServices;
import heroku_demo.api.services.RestRequestServices;
import lombok.AllArgsConstructor;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class FirstController {

    private final RestTemplate restTemplate;
    private final RestRequestServices restRequestServices;
    private final ViewServices viewServices;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response) {
        restRequestServices.addTokenToResponse(email, password, response);
        return new ModelAndView("redirect:" + "/view/add_battle");
    }

    @RequestMapping(value = "/view/login", method = RequestMethod.GET)
    public String viewLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/view/add_battle", method = RequestMethod.GET)
    public ModelAndView addBattle(HttpServletRequest request, Map<String, Object> model,@RequestParam (value = "successMessage", required = false) String successMessage) {

        try {
            viewServices.viewBattle(request, model, 1,successMessage);
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
            return new ModelAndView("forbidden");
        }
        model.put("successMessage","Game Successfully Added");
        return new ModelAndView("redirect:" + "/view/add_battle",model);
    }

    @RequestMapping(value = "/view_unapproved_games", method = RequestMethod.GET)
    public ModelAndView viewUnapprovedGames(HttpServletRequest request, Map<String, Object> model) {
        UnapprovedGamesDto unapprovedGames = restRequestServices.getUnapprovedGames(request);
        List<GameDto> gameDtoList = unapprovedGames.getGameDtos();
        model.put("gamesQuantity",gameDtoList.size());
        int gameNumber = 0;
        for(GameDto gameDto:gameDtoList){
            gameNumber++;
            if(gameNumber ==1){
                model.put()
            }
            model.put("date"+gameNumber,gameDto.getDate());
            model.put()
        }
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

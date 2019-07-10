package heroku_demo.api.controllers;

import heroku_demo.api.dto.*;
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

import javax.servlet.http.Cookie;
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
        restRequestServices.addTokenToResponse(email,password,response);
        return new ModelAndView("redirect:" + "/view/add_battle");
    }

    @RequestMapping(value = "/view/login", method = RequestMethod.GET)
    public String viewLoginPage(){
        return "login";
    }


    @RequestMapping(value = "/view/add_battle",method = RequestMethod.GET)
    public ModelAndView addBattle(HttpServletRequest request,Map<String, Object> model){

        viewServices.viewBattle( request, model,1);
        return new ModelAndView("add_battle",model);
    }

    @RequestMapping(value = "/add_battle",method = RequestMethod.POST)
    public String addBattle(@RequestParam Map<String,String> allRequestParams,HttpServletRequest request){
        restRequestServices.saveBattleResults( allRequestParams, request);
        return "s";
    }

    @RequestMapping(value = "/add_battle/add_enemy",method = RequestMethod.POST)
    public ModelAndView addEnemy(@RequestParam Map<String,String> allRequestParams,Map<String, Object> model,HttpServletRequest request){
        int enemiesQuantity = Integer.valueOf(allRequestParams.get("enemiesQuantity"))+1;
        viewServices.viewBattle( request, model,enemiesQuantity);
        return new ModelAndView("add_battle",model);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(HttpServletRequest request){
        HttpEntity entity = new HttpEntity(new HttpHeaders());
        ResponseEntity<Integer> exchange = restTemplate.exchange("http://localhost:8080/users/logged_user_id", HttpMethod.GET, entity, new ParameterizedTypeReference<Integer>() {});
        return "s";
    }

}

package heroku_demo.api.controllers;

import heroku_demo.api.dto.AccountCredentials;
import heroku_demo.api.dto.CastleDto;
import heroku_demo.api.dto.HeroDto;
import heroku_demo.api.dto.TokenDto;
import lombok.AllArgsConstructor;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response) {
        AccountCredentials accountCredentials = new AccountCredentials();
        accountCredentials.setEmail(email);
        accountCredentials.setPassword(password);
        HttpEntity<AccountCredentials> request = new HttpEntity<>(accountCredentials);

        ResponseEntity<TokenDto> exchange = restTemplate.exchange("http://localhost:8080/login", HttpMethod.POST, request, new ParameterizedTypeReference<TokenDto>() {
        });

        response.addCookie(new Cookie("token",exchange.getBody().getToken()));

        return "main";
    }

    @RequestMapping(value = "/view/login", method = RequestMethod.GET)
    public String viewLoginPage(){
        return "login";
    }


    @RequestMapping(value = "/castles/get_all", method = RequestMethod.GET)
    public ModelAndView getCastles( HttpServletRequest request, Map<String, Object> model){
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = null;
        if (cookies != null) {
            for (Cookie cookieInList : cookies) {
                if (cookieInList.getName().equals("token")) {
                    tokenCookie = cookieInList;
                }
            }
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+tokenCookie.getValue());

        //Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<List<CastleDto>> exchange = restTemplate.exchange("http://localhost:8080/castles/get_all", HttpMethod.GET, entity, new ParameterizedTypeReference<List<CastleDto>>() {
        });

        List<CastleDto> castleDtoList = exchange.getBody();


        model.put("castles", castleDtoList);

        return new ModelAndView("view_castle_list",model);


    }

    @RequestMapping(value = "/view/add_battle",method = RequestMethod.GET)
    public ModelAndView addBattle(HttpServletRequest request,Map<String, Object> model){

        model.put("victory","Victory");
        model.put("loose","Loose");
        model.put("halfVictory","Half Victory");

        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = null;
        if (cookies != null) {
            for (Cookie cookieInList : cookies) {
                if (cookieInList.getName().equals("token")) {
                    tokenCookie = cookieInList;
                }
            }
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+tokenCookie.getValue());

        //Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<List<HeroDto>> exchange = restTemplate.exchange("http://localhost:8080/heroes", HttpMethod.GET, entity, new ParameterizedTypeReference<List<HeroDto>>() {
        });

        List<HeroDto> heroDtos = exchange.getBody();


        model.put("heroes", heroDtos);

        return new ModelAndView("add_battle",model);
    }

    @RequestMapping(value = "/add_battle",method = RequestMethod.GET)
    public String addBattle(@RequestParam("name") String name, @RequestParam("result") String result, @RequestParam("heroName") String hero){
        return "s";
    }

}

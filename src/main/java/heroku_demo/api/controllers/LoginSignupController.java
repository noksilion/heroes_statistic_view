package heroku_demo.api.controllers;

import heroku_demo.api.exceptions.RestApiException;
import heroku_demo.api.services.RestRequestServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@AllArgsConstructor
public class LoginSignupController {
    private final RestRequestServices restRequestServices;

    @RequestMapping(value = "/view/login", method = RequestMethod.GET)
    public String viewLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/view/signup", method = RequestMethod.GET)
    public String viewSignUpPage() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("userName") String userName, HttpServletResponse response, Map<String, Object> model) {
        try {
            restRequestServices.addTokenToResponse(email, password, userName,response);
        } catch (RestApiException restException) {
            model.put("message",restException.getMessage());
            return new ModelAndView("signup", model);
        }
        model.put("message","Verify you account by click on link in email");
        return new ModelAndView("main", model);

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response,Map<String, Object> model) {
        try {
            restRequestServices.addTokenToResponse(email, password, response);
        } catch (RestApiException restException) {
            model.put("message",restException.getMessage());
            return new ModelAndView("login", model);
        }
        return new ModelAndView("main", model);
    }
}

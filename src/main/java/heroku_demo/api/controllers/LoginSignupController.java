package heroku_demo.api.controllers;

import heroku_demo.api.exceptions.RestApiException;
import heroku_demo.api.services.RestRequestServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller

public class LoginSignupController {
    private final RestRequestServices restRequestServices;
    @Value("${restApplicationHost}")
    private String restApplicationHost;
    @Value("${applicationHost}")
    private String applicationHost;

    public LoginSignupController(RestRequestServices restRequestServices) {
        this.restRequestServices = restRequestServices;
    }

    @RequestMapping(value = "/view/login", method = RequestMethod.GET)
    public ModelAndView viewLoginPage(Map<String, Object> model) {
        model.put("restHost", restApplicationHost);
        model.put("appHost",applicationHost);
        return new ModelAndView("login", model);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("userName") String userName, HttpServletResponse response, Map<String, Object> model) {
        try {
            restRequestServices.addTokenToResponse(email, password, userName,response);
        } catch (RestApiException restException) {
            model.put("applicationHost", restApplicationHost);
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
            model.put("applicationHost", restApplicationHost);
            return new ModelAndView("login", model);
        }
        return new ModelAndView("main", model);
    }
}

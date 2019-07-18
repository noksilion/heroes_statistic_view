package heroku_demo.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class StatisticController {

    @RequestMapping(value = "/statistic", method = RequestMethod.POST)
    public ModelAndView statistic() {

        return null;
    }

    @RequestMapping(value = "/view/statistic", method = RequestMethod.GET)
    public ModelAndView viewStatistic(Map<String, Object> model) {

        return new ModelAndView("statistic",model);
    }

}

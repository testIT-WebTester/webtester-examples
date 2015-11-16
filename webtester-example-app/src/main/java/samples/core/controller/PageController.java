package samples.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    @RequestMapping ( "/empty" )
    public String empty () {
        return "empty";
    }

    @RequestMapping ( "/login" )
    public String login () {
        return "login";
    }

    @RequestMapping ( "/welcome" )
    public String mainPage () {
        return "welcome";
    }

    @RequestMapping ( "/widgets" )
    public String widgets () {
        return "widgets";
    }

    @RequestMapping ( "/random" )
    public String random () {
        return "random";
    }

}

package samples.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("showcase")
public class ShowcaseController {

    @RequestMapping("empty")
    public String empty() {
        return "showcase/empty";
    }

    @RequestMapping("widgets")
    public String widgets() {
        return "showcase/widgets";
    }

    @RequestMapping("random")
    public String random() {
        return "showcase/random";
    }

}

package dimulski.areas.homepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping
    public String getHomePage() {
        return "item";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "error";
    }
}

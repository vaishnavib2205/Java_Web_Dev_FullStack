package project.vbb.SpringStarter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model){

        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){

        return "about";
    }

    @GetMapping("/book")
    public String book(Model model){

        return "book";
    }
    
}

package project.vbb.SpringStarter.Controller;

import org.springframework.stereotype.Controller;

@Controller
public class AccountController {
    
    public String register(Model model){
        Account account = new Account();
        model.addAttribute(attributeName:"account", account);
        return "register";
    }
}

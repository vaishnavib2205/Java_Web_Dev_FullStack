package project.vbb.SpringStarter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import project.vbb.SpringStarter.models.Account;
import project.vbb.SpringStarter.services.AccountService;

@Controller
public class AccountController{

    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String register(Model model){

        Account account = new Account();
        model.addAttribute("account", account);

        return "register";
    }

    @PostMapping("/register")
    public String register_user(@ModelAttribute Account account){
        accountService.save(account);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model)
    {
        return "login";
    }

    
}

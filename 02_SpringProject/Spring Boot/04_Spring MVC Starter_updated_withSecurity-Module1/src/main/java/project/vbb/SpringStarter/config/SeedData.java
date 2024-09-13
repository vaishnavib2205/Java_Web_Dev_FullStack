package project.vbb.SpringStarter.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import project.vbb.SpringStarter.models.Account;
import project.vbb.SpringStarter.models.Post;
import project.vbb.SpringStarter.services.AccountService;
import project.vbb.SpringStarter.services.PostService;

@Component
public class SeedData implements CommandLineRunner{

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        
        Account account01 = new Account();
        Account account02 = new Account();
      
        account01.setEmail("account01@ViDev.com");
        account01.setPassword("password");
        account01.setFirstname("user01");
        account01.setLastName("lastName");

        account02.setEmail("account02@ViDev.com");
        account02.setPassword("password");
        account02.setFirstname("user02");
        account02.setLastName("lastName");


        accountService.save(account01);
        accountService.save(account02);

        List<Post> posts = postService.getAll();
       if (posts.isEmpty()){
            Post post01 = new Post();
            post01.setTitle("Post01");
            post01.setBody("Database Connection Success for post01");
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("Post02");
            post02.setBody("Database Connection Success for post02");
            post02.setAccount(account02);
            postService.save(post02);

       }
        
    }
    
}
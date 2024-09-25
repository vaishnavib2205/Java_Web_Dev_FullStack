package project.vbb.SpringStarter.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;  // Make sure PasswordEncoder is used if applicable
import org.springframework.stereotype.Component;

import project.vbb.SpringStarter.models.Account;
import project.vbb.SpringStarter.models.Authority;
import project.vbb.SpringStarter.models.Post;
import project.vbb.SpringStarter.services.AccountService;
import project.vbb.SpringStarter.services.AuthorityService;
import project.vbb.SpringStarter.services.PostService;
import project.vbb.SpringStarter.util.constants.Privillages;
import project.vbb.SpringStarter.util.constants.Roles;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @SuppressWarnings("unused")
    @Autowired
    private PasswordEncoder passwordEncoder;  // Inject PasswordEncoder

    @Override
    public void run(String... args) throws Exception {

        // Adding Privillages (Authorities)
        for (Privillages auth : Privillages.values()) {
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);
        }

        // Creating and Saving Accounts
        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();

        account01.setEmail("user@user.com");
        account01.setPassword("password");  // Encode password
        account01.setFirstname("user01");
        account01.setLastName("lastName");
        account01.setRole(Roles.USER);  // Set role as enum

        account02.setEmail("admin@admin.com");
        account02.setPassword("password");  // Encode password
        account02.setFirstname("Admin");
        account02.setLastName("lastName");
        account02.setRole(Roles.ADMIN);  // Set role as enum

        account03.setEmail("editor@editor.com");
        account03.setPassword("password");  // Encode password
        account03.setFirstname("Editor");
        account03.setLastName("lastName");
        account03.setRole(Roles.EDITOR);  // Set role as enum

        account04.setEmail("super_editor@editor.com");
        account04.setPassword("password");  // Encode password
        account04.setFirstname("Editor@Super");
        account04.setLastName("lastname");
        account04.setRole(Roles.EDITOR);  // Set role as enum
        Set<Authority> authorities = new HashSet<>();
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
        authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
        account04.setAuthorities(authorities);

        // Saving Accounts to DB
        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);

        // Creating Posts if they don't exist
        List<Post> posts = postService.getAll();
        if (posts.isEmpty()) {
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

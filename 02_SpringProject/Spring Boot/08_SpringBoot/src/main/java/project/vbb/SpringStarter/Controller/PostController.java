package project.vbb.SpringStarter.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import project.vbb.SpringStarter.models.Post;
import project.vbb.SpringStarter.services.PostService;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.getById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "post_views/post"; // Make sure this matches your directory structure
        } else {
            return "404"; // Or your error page
        }
    }
    
}
package wooteco.helloworld.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wooteco.helloworld.domain.User;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    @ResponseBody
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/welcome-page")
    public String welcomePage(
            @RequestParam String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "welcome";
    }

    @GetMapping("/welcome-user")
    public ResponseEntity<?> welcomeUser() {
        return ResponseEntity.ok(new User("브라운", "brown@email.com", 20));
    }

    @GetMapping("/post-form")
    public String postForm() {
        return "post_form";
    }
}
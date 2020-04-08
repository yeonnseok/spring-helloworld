package wooteco.helloworld.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wooteco.helloworld.domain.User;

public class WelcomeController {
    /**
     * GET /welcome
     */
    public String welcome() {
        return "welcome";
    }

    /**
     * GET /welcome-page
     */
    public String welcomePage() {
        return "welcome";
    }

    /**
     * GET /welcome-user
     */
    public ResponseEntity welcomeUser() {
        return ResponseEntity.ok(new User("브라운", "brown@email.com", 20));
    }

    @GetMapping("/post-form")
    public String postForm() {
        return "post_form";
    }
}
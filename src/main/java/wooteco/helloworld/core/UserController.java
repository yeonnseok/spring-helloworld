package wooteco.helloworld.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class UserController {
    private UserService userService;

    @GetMapping("/users")
    public String retrieveUsers() {
        List<String> users = userService.findAll();
        return "found it";
    }

}

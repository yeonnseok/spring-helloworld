package wooteco.helloworld.controller;

import org.springframework.web.bind.annotation.*;
import wooteco.helloworld.domain.User;

import java.util.Map;

@RestController
public class UserController {
    /**
     * GET /users
     */
    @GetMapping("/users")
    public String sayHi(@RequestParam(defaultValue = "") String name) {
        return "Hi " + name;
    }

    /**
     * GET /users/{id}
     */
    @GetMapping("/users/{id}")
    public String retrieveUser(@PathVariable Long id) {
        return "user's id is " + id;
    }

    /**
     * POST /users
     */
    @PostMapping("/users")
    @ResponseBody
    public String createUserWithJson(@RequestBody User user) {
        return "Hello " + user.getName();
    }

    /**
     * POST /users/form
     */
    @PostMapping("/users/form")
    @ResponseBody
    public String createUserWithForm(@RequestParam Map<String, String> paramMap) {
        return "Hello " + paramMap.get("name");
    }

}
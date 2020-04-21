package wooteco.helloworld.controller;

import org.springframework.web.bind.annotation.*;
import wooteco.helloworld.domain.User;

import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/users")
    public String sayHi(
            @RequestParam String name) {
        return "Hi " + name;
    }

    @GetMapping("/users/{id}")
    public String retrieveUser(
            @PathVariable("id") Long id) {
        return "user's id is " + id;
    }

    @PostMapping("/users")
    public String createUserWithJson(
            @RequestBody User user) {
        return "Hello " + user.getName();
    }

    @PostMapping("/users/form")
    public String createUserWithForm(
            @RequestParam Map<String, String> paramMap) {
        return "Hello " + paramMap.get("name");
    }

}
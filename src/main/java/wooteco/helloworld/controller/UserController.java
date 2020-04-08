package wooteco.helloworld.controller;

import org.springframework.web.bind.annotation.RestController;
import wooteco.helloworld.domain.User;

import java.util.Map;

@RestController
public class UserController {
    /**
     * GET /users
     */
    public String sayHi(String name) {
        return "Hi " + name;
    }

    /**
     * GET /users/{id}
     */
    public String retrieveUser(Long id) {
        return "user's id is " + id;
    }

    /**
     * POST /users
     */
    public String createUserWithJson(User user) {
        return "Hello " + user.getName();
    }

    /**
     * POST /users/form
     */
    public String createUserWithForm(Map<String, String> paramMap) {
        return "Hello " + paramMap.get("name");
    }

}
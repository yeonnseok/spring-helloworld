package wooteco.helloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wooteco.helloworld.domain.Hello;
import wooteco.helloworld.domain.HelloRepository;

@RestController
public class HelloController {

    @Autowired
    private HelloRepository helloRepository;

    @GetMapping("/hello")
    public String hello(
            @RequestParam(defaultValue = "") String name
    ) {
        if (name.isEmpty()) {
            return "HelloWorld!!!";
        }
        helloRepository.save(new Hello(name));
        int count = helloRepository.countByName(name);
        return "Hello " + name + " " + count + "번째 방문입니다.";
    }
}

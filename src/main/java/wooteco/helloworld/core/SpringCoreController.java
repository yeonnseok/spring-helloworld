package wooteco.helloworld.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wooteco.helloworld.SpringCoreService;

@RestController
public class SpringCoreController {

    private SpringCoreService springCoreService;

    @GetMapping("/my-name")
    public String myName(@RequestParam String name) throws InterruptedException {
        return springCoreService.sayName(name);
    }
}

package wooteco.helloworld.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringCoreController {

    private SpringCoreService springCoreService;

    public SpringCoreController(SpringCoreService springCoreService) {
        this.springCoreService = springCoreService;
    }

    @GetMapping("/my-name")
    public String myName(@RequestParam String name) throws InterruptedException {
        return springCoreService.sayName(name);
    }
}

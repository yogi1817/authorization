package com.spj.authorization.register.user.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @RequestMapping("/test")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}

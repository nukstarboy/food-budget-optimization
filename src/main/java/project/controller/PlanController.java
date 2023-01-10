package project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController {
    @GetMapping("/")
    public String getString() {
        return "Hello";
    }
}

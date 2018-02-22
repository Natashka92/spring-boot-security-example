package simple.java.spring.example.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
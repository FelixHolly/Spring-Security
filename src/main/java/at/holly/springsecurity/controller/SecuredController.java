package at.holly.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

    @GetMapping("/secure")
    public String secure() {
        return "Secure";
    }

    @PutMapping("/admin")
    public String admin() {
        return "Admin";
    }

    @PostMapping("/me")
    public String me() {
        return "Me";
    }

}

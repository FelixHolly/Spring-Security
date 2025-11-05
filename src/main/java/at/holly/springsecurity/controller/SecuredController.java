package at.holly.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

    @GetMapping("/secure-get")
    public String get() {
        return "GET";
    }

    @PutMapping("/secure-put")
    public String put() {
        return "PUT";
    }

    @PostMapping("/secure-post")
    public String post() {
        return "POST";
    }

}

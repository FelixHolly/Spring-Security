package at.holly.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/bye")
    public ResponseEntity<String> getBye() {
        return ResponseEntity.ok("Bye");
    }

    @GetMapping("/me")
    public String currentUser(Authentication authentication) {
        return "Hello, " + authentication.getName();
    }

}

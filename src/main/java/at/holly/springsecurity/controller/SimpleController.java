package at.holly.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/bye")
    public ResponseEntity<String> getBye() {
        return ResponseEntity.ok("Bye");
    }

    @PutMapping("/me-save")
    public String currentUser(Authentication authentication) {
        return "Hello, " + authentication.getName();
    }

}

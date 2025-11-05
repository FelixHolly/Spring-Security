package at.holly.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/public-get")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/public-post")
    public ResponseEntity<String> post() {
        return ResponseEntity.ok("Bye");
    }

    @PutMapping("/public-put")
    public ResponseEntity<String> put() {
        return ResponseEntity.ok("Bye");
    }
}

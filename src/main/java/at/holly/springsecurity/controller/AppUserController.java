package at.holly.springsecurity.controller;

import at.holly.springsecurity.model.dto.AppUserDto;
import at.holly.springsecurity.serivce.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/user")
    public String user(Authentication authentication) {
        return "Hello, " + authentication.getName();
    }

    //create user endpoint
    @PostMapping("/register")
    public String user(@RequestBody AppUserDto user) {
        return appUserService.createUser(user);
    }



}

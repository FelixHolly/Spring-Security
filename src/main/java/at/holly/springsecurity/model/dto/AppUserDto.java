package at.holly.springsecurity.model.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AppUserDto {

    private String username;
    private String password;
    private String role;

}

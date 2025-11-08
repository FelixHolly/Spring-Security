package at.holly.springsecurity.serivce;

import at.holly.springsecurity.model.AppUser;
import at.holly.springsecurity.model.Role;
import at.holly.springsecurity.model.dto.AppUserDto;
import at.holly.springsecurity.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public String createUser(AppUserDto appUser) {
        if (appUserRepository.findByUsername(appUser.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        AppUser appUserEntity = new AppUser(
                appUser.getUsername(),
                passwordEncoder.encode(appUser.getPassword()),
                new HashSet<>(Collections.singleton(Role.USER)),
                new HashSet<>());

        AppUser savedUser = appUserRepository.save(appUserEntity);

        if (savedUser.getId() > 0) {
            return "Success";
        }
        return "Failed";
    }

}

package at.holly.springsecurity.serivce;

import at.holly.springsecurity.model.AppUser;
import at.holly.springsecurity.model.dto.AppUserDto;
import at.holly.springsecurity.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public String createUser(AppUserDto appUser) {
        AppUser appUserEntity = new AppUser(appUser.getUsername(), passwordEncoder.encode(appUser.getPassword()), "ROLE_USER");
        AppUser savedUser = appUserRepository.save(appUserEntity);

        if (savedUser.getId() > 0) {
            return "Success";
        }
        return "Failed";
    }

}

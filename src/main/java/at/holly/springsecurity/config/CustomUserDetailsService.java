package at.holly.springsecurity.config;

import at.holly.springsecurity.model.AppUser;
import at.holly.springsecurity.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Convert role to GrantedAuthority with ROLE_ prefix
        String authority = appUser.getRole().startsWith("ROLE_")
            ? appUser.getRole()
            : "ROLE_" + appUser.getRole();
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(authority));

        return new org.springframework.security.core.userdetails.User(
            appUser.getUsername(),
            appUser.getPassword(),
            authorities
        );
    }
}

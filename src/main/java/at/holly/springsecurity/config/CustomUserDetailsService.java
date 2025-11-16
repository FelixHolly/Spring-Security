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

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        Set<GrantedAuthority> authorities = buildAuthorities(user);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    private Set<GrantedAuthority> buildAuthorities(AppUser user) {
        return user.getRoles().stream()
                .flatMap(role -> Stream.concat(
                        // Role itself (ROLE_USER, ROLE_ADMIN)
                        Stream.of(new SimpleGrantedAuthority(role.getRoleName())),
                        // Fine-grained authorities from the role
                        role.getAuthorities().stream()
                                .map(auth -> new SimpleGrantedAuthority(auth.getValue()))
                ))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}


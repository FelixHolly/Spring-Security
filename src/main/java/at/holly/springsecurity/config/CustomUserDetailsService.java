package at.holly.springsecurity.config;

import at.holly.springsecurity.model.AppUser;
import at.holly.springsecurity.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
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

        UserBuilder builder = User.withUsername(user.getUsername()) // or user.getId().toString() for stable ID
                .password(user.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false);

        return builder.build();
    }

    /**
     * Combine roles (ROLE_*) and fine-grained authorities (RESOURCE:ACTION)
     * into a single immutable set.
     */
    private Set<GrantedAuthority> buildAuthorities(AppUser user) {
        List<GrantedAuthority> roleAuthorities = user.getRoles() == null ? List.of() :
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getAuthority())) // e.g., ROLE_ADMIN
                        .collect(Collectors.toList());

        List<GrantedAuthority> permissionAuthorities = user.getAuthorities() == null ? List.of() :
                user.getAuthorities().stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.getAuthority())) // e.g., ACCOUNT:READ
                        .collect(Collectors.toList());

        return Stream.concat(roleAuthorities.stream(), permissionAuthorities.stream())
                .collect(Collectors.toCollection(LinkedHashSet::new)); // preserves order, removes duplicates
    }
}

package at.holly.springsecurity.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers("/hello", "/bye", "/me-save").permitAll()
                        .requestMatchers("/me", "/admin", "/secure").authenticated()
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))  // Allows access to /h2-console frameset/frame pages
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //plain text
        UserDetails user = User
                .withUsername("user")
                .password("{noop}password")
                .authorities("read")
                .build();
        //bcrypt encoded
        UserDetails admin = User
                .withUsername("admin")
                .password("{bcrypt}$2a$12$hDyuAfI/9Tioxd8pvbF9heYun7G8jsvj2TPayo0YNhZMSqLzfnxHW")
                .authorities("admin")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    /*
    Spring v.6.3
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
     */


}

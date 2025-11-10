package at.holly.springsecurity.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Slf4j
public class AuthorityLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        SecurityContext context = SecurityContextHolder.getContext();
        log.info("Username: {}", context.getAuthentication().getName());
        context.getAuthentication().getAuthorities().forEach(authority ->
                log.info("Authority: {}", authority.getAuthority())
        );

        chain.doFilter(request, response);


    }
}

package at.holly.springsecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestValidationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("AUTHORIZATION");
        if(header != null){
            header= header.trim();
            if(StringUtils.startsWithIgnoreCase(header, "Basic")){
                byte[] base64Credentials = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decoded;
                try {
                    decoded = Base64.getDecoder().decode(base64Credentials);
                    String token = new String(decoded, StandardCharsets.UTF_8);
                    int delim = token.indexOf(":");
                    if(delim == -1){
                        throw new ServletException("Invalid Authorization header");
                    }
                    String username = token.substring(0, delim);

                    logger.info("Username: " + username);

                    if(username.toLowerCase().contains("test")){
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;  // Stop the filter chain - request will not proceed
                    }
                } catch (Exception e) {
                    throw new ServletException("Invalid Authorization header");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}

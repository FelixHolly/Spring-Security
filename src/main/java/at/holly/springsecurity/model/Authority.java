package at.holly.springsecurity.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Enum representing fine-grained authorities (permissions) in the system.
 * Authorities use the pattern RESOURCE:ACTION for clarity.
 */

@Getter
@ToString
@RequiredArgsConstructor
public enum Authority {

    SECURE_GET("SECURE:READ"),
    SECURE_POST("SECURE:WRITE"),
    SECURE_PUT("SECURE:UPDATE");

    private final String authority;

}

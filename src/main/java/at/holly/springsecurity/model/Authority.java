package at.holly.springsecurity.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {

    SECURE_READ("SECURE:READ"),
    SECURE_WRITE("SECURE:WRITE"),
    SECURE_UPDATE("SECURE:UPDATE");

    private final String value;
}

package at.holly.springsecurity.model;

import lombok.Getter;

import java.util.Set;

@Getter
public enum Role {

    USER(Set.of(Authority.SECURE_READ)),
    ADMIN(Set.of(Authority.SECURE_READ, Authority.SECURE_WRITE, Authority.SECURE_UPDATE));

    private final Set<Authority> authorities;

    Role(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getRoleName() {
        return "ROLE_" + name();
    }
}

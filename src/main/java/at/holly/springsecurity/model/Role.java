package at.holly.springsecurity.model;

public enum Role {

    USER,
    ADMIN;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }

}

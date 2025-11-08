package at.holly.springsecurity.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;
    private String username;
    private String password;

    /**
     * Customer roles stored in a separate table (customer_roles).
     * Using @ElementCollection creates a one-to-many relationship.
     * Using Set prevents duplicate roles.
     * Using @Enumerated(STRING) stores the enum name as string for readability.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    /**
     * Customer authorities (fine-grained permissions) stored in a separate table (customer_authorities).
     * Authorities provide specific operation-level permissions (e.g., ACCOUNT:READ, TRANSACTION:WRITE).
     * Roles provide high-level access, authorities provide specific permissions.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Set<Authority> authorities = new HashSet<>();

    public AppUser() {}

    public AppUser(String username, String password, Set<Role> roles, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.authorities = authorities;
    }



}

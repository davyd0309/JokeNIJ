package pl.dawydiuk.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Data
@Builder
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter

    private String email;

    @Getter @Setter

    private String password;

    @Getter @Setter

    private String name;

    @Getter @Setter

    private String lastName;

    @Getter @Setter
    private boolean active;

    @Builder.Default
    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "User_Role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<Role>();


}

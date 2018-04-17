package pl.dawydiuk.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name = "user_id")
    @NotNull
    private Long id;

    @Getter @Setter
    @Email(message = "Podaj prawidłowy adres email.")
    @NotEmpty(message = "Adres email jest wymagany")
    @NotNull
    private String email;

    @Getter @Setter
    @Length(min = 6,message = "Hasło musi zawierać miniumum 6 znaków.")
    @NotEmpty(message = "Hasło jest wymagane")
    @NotNull
    private String password;

    @Getter @Setter
    @NotEmpty(message = "Imię jest wymagane")
    @NotNull
    private String name;

    @Getter @Setter
    @NotEmpty(message = "Nazwisko jest wymagane")
    @NotNull
    private String lastName;

    @Getter @Setter
    private int active;

    @Getter @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<Role>();

}

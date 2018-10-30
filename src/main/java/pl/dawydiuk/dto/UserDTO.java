package pl.dawydiuk.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@NoArgsConstructor
public class UserDTO {

    @Getter
    @Setter
    @Email(message = "Podaj prawidłowy adres email.")
    @NotEmpty(message = "Adres email jest wymagany")
    @NotNull
    private String email;

    @Getter
    @Setter
    @NotEmpty(message = "Imię jest wymagane")
    @NotNull
    private String name;

    @Getter
    @Setter
    @NotEmpty(message = "Nazwisko jest wymagane")
    @NotNull
    private String lastName;

    @Getter
    @Setter
    @Length(min = 6,message = "Hasło musi zawierać miniumum 6 znaków.")
    @NotEmpty(message = "Hasło jest wymagane")
    @NotNull
    private String password;

    @Getter
    @Setter
    private boolean active;

    public UserDTO(@Email(message = "Podaj prawidłowy adres email.") @NotEmpty(message = "Adres email jest wymagany") @NotNull String email, @NotEmpty(message = "Imię jest wymagane") @NotNull String name, @NotEmpty(message = "Nazwisko jest wymagane") @NotNull String lastName, @Length(min = 6, message = "Hasło musi zawierać miniumum 6 znaków.") @NotEmpty(message = "Hasło jest wymagane") @NotNull String password, boolean active) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.active = active;
    }
}

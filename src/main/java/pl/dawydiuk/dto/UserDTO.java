package pl.dawydiuk.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


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

}

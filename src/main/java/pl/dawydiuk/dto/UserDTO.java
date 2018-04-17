package pl.dawydiuk.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDTO {

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String password;
}

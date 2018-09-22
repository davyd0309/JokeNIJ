package pl.dawydiuk.domain;


import lombok.*;

import javax.persistence.*;


@Entity
@Data
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String role;

}

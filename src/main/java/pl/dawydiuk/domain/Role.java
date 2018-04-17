package pl.dawydiuk.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name = "role_id")
    private Long id;

    @Getter @Setter
    private String role;

}

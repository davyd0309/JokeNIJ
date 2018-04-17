package pl.dawydiuk.domain;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Data
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name = "role_id")
    private Long id;

    @Getter @Setter
    private String role;

}

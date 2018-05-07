package pl.dawydiuk.domain;


import lombok.Getter;
import lombok.Setter;
import pl.dawydiuk.enums.JokeCategoryEnum;
import pl.dawydiuk.enums.VoteLevelEnum;

import javax.persistence.*;

@Entity
public class Vote {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;


    @Column(name = "level")
    @Getter
    @Setter
    private VoteLevelEnum level;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @Getter
    @Setter
    private User user;


    @ManyToOne
    @JoinColumn(name = "jokeId",referencedColumnName = "id")
    @Getter
    @Setter
    private Joke joke;



}

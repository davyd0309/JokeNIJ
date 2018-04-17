package pl.dawydiuk.dto;

import lombok.Getter;
import lombok.Setter;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.enums.JokeCategoryEnum;

import java.time.LocalDate;

public class JokeDTO {

    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String content;
    @Getter
    @Setter
    private LocalDate addDate;
    @Getter
    @Setter
    private String category;
    @Getter
    @Setter
    private Long userId;


}

package pl.dawydiuk.service;

import pl.dawydiuk.domain.Joke;
import pl.dawydiuk.dto.JokeDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface JokeService {

    Joke addJoke(JokeDTO joke);
    List<Joke> getAllUserJokes(Long userId);

}

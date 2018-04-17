package pl.dawydiuk.service;

import pl.dawydiuk.domain.Joke;
import pl.dawydiuk.dto.JokeDTO;

public interface JokeService {

    Joke addJoke(JokeDTO jokeDTO);
    Joke getJoke(Long idJoke);
}

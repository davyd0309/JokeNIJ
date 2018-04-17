package pl.dawydiuk.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dawydiuk.domain.Joke;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.dto.JokeDTO;
import pl.dawydiuk.enums.JokeCategoryEnum;
import pl.dawydiuk.repository.UserRepository;

public class JokeServiceImpl implements JokeService{


    private UserRepository userRepository;

    @Autowired
    public JokeServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Joke addJoke(JokeDTO jokeDTO) {

        User user = userRepository.getOne(jokeDTO.getUserId());

        Joke newJoke = new Joke();
        newJoke.setTitle(jokeDTO.getTitle());
        newJoke.setContent(jokeDTO.getContent());
        newJoke.setAddDate(jokeDTO.getAddDate());
        newJoke.setCategory(JokeCategoryEnum.valueOf(jokeDTO.getCategory()));
        newJoke.setUser(user);
        return newJoke;
    }

    @Override
    public Joke getJoke(Long idJoke) {
        return null;
    }
}

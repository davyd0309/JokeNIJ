package pl.dawydiuk.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dawydiuk.domain.Joke;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.dto.JokeDTO;
import pl.dawydiuk.enums.JokeCategoryEnum;
import pl.dawydiuk.repository.JokeRepsitory;
import pl.dawydiuk.repository.UserRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
public class JokeServiceImpl implements JokeService{


    private UserRepository userRepository;

    @Autowired
    public JokeServiceImpl(UserRepository userRepository, JokeRepsitory jokeRepsitory) {
        this.userRepository = userRepository;
        this.jokeRepsitory = jokeRepsitory;
    }

    private JokeRepsitory jokeRepsitory;


    @Override
    public Joke addJoke(JokeDTO jokeDTO) {
        Joke joke = transformJokeDTOToJoke(jokeDTO);
        joke.setUser(findUserWhoAddJoke(jokeDTO.getUserId()));
        jokeRepsitory.save(joke);
        return joke;
    }

    private User findUserWhoAddJoke(Long userId){
        return userRepository.getOne(userId);
    }


    private Joke transformJokeDTOToJoke(JokeDTO jokeDTO) {
//        ModelMapper mapper = new ModelMapper();
        Joke newJoke = Joke.builder().build();
//        mapper.map(jokeDTO, newJoke);
        newJoke.setTitle(jokeDTO.getTitle());
        newJoke.setContent(jokeDTO.getContent());
        newJoke.setAddDate(jokeDTO.getAddDate());
        newJoke.setCategory(JokeCategoryEnum.valueOf(jokeDTO.getCategory()));

        return newJoke;
    }

    @Override
    public List<Joke> getAllUserJokes(Long userId) {

        return null;
    }
}

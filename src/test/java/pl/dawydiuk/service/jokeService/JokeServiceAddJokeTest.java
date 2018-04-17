package pl.dawydiuk.service.jokeService;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.dawydiuk.domain.Joke;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.dto.JokeDTO;
import pl.dawydiuk.enums.JokeCategoryEnum;
import pl.dawydiuk.repository.UserRepository;
import pl.dawydiuk.service.JokeService;
import pl.dawydiuk.service.JokeServiceImpl;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class JokeServiceAddJokeTest {

    private JokeService jokeService;

    @Mock
    private UserRepository userRepository;

    private JokeDTO jokeDTO;
    private Joke joke;

    @Before
    public void setUp(){
        jokeService = new JokeServiceImpl(userRepository);

        jokeDTO = new JokeDTO();
        jokeDTO.setTitle("This is joke title");
        jokeDTO.setContent("This is joke content");
        jokeDTO.setAddDate(LocalDate.now(Clock.systemDefaultZone()));
        jokeDTO.setCategory(JokeCategoryEnum.CATEGORY_1.name());
        jokeDTO.setUserId(55L);
    }


    @Test
    public void jokeShouldServiceNotNull() {
        Assert.assertNotNull(jokeService);
    }

    @Test
    public void jokeShouldHaveTitle(){
        addJoke();
        assertThat(joke.getTitle()).isEqualTo("This is joke title");
    }

    @Test
    public void jokeShouldHaveContent(){
        addJoke();
        assertThat(joke.getContent()).isEqualTo("This is joke content");
    }

    @Test
    public void jokeShouldHaveAddDate(){
        addJoke();
        assertThat(joke.getAddDate()).isEqualTo(LocalDate.now(Clock.systemDefaultZone()));
    }

    @Test
    public void jokeShouldHaveCategory(){
        addJoke();
        assertThat(joke.getCategory().name()).isEqualTo(JokeCategoryEnum.CATEGORY_1.name());
    }

    @Test
    public void jokeShouldHaveUser(){
        Mockito.when(userRepository.getOne(55L)).thenReturn(getUserById());
        addJoke();
        assertThat(joke.getUser().getId()).isEqualTo(55);
    }

    private User getUserById(){
        User user = new User();
        user.setId(55L);
        return user;
    }

    private void addJoke(){

        joke = jokeService.addJoke(jokeDTO);
    }

}

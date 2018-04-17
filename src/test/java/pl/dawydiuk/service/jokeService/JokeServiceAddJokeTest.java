package pl.dawydiuk.service.jokeService;


import org.assertj.core.util.Sets;
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
import pl.dawydiuk.repository.JokeRepsitory;
import pl.dawydiuk.repository.UserRepository;
import pl.dawydiuk.service.JokeService;
import pl.dawydiuk.service.JokeServiceImpl;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class JokeServiceAddJokeTest {

    private JokeService jokeService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JokeRepsitory jokeRepsitory;

    private Joke joke;

    private Set<Joke> savedJokes = Sets.newHashSet();

    @Before
    public void setUp() {
        jokeService = new JokeServiceImpl(userRepository, jokeRepsitory);

    }


    @Test
    public void jokeShouldServiceNotNull() {
        Assert.assertNotNull(jokeService);
    }

    @Test
    public void jokeShouldHaveTitle() {
        //given
        JokeDTO jokeDTO = givenJokeDTO("This is joke title", "This is joke content", LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name(), 55L);
        //when
        joke = jokeService.addJoke(jokeDTO);
        //then
        assertThat(joke.getTitle()).isEqualTo("This is joke title");
    }

    @Test
    public void jokeShouldHaveContent() {

        //given
        JokeDTO jokeDTO = givenJokeDTO("This is joke title", "This is joke content", LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name(), 55L);
        //when
        joke = jokeService.addJoke(jokeDTO);
        //then
        assertThat(joke.getContent()).isEqualTo("This is joke content");
    }

    @Test
    public void jokeShouldHaveAddDate() {

        //given
        JokeDTO jokeDTO = givenJokeDTO("This is joke title", "This is joke content", LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name(), 55L);
        //when
        joke = jokeService.addJoke(jokeDTO);
        //then
        assertThat(joke.getAddDate()).isEqualTo(LocalDate.now(Clock.systemDefaultZone()));
    }

    @Test
    public void jokeShouldHaveCategory() {
        //given
        JokeDTO jokeDTO = givenJokeDTO("This is joke title", "This is joke content", LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name(), 55L);
        //when
        joke = jokeService.addJoke(jokeDTO);
        //then
        assertThat(joke.getCategory().name()).isEqualTo(JokeCategoryEnum.CATEGORY_1.name());
    }

    @Test
    public void jokeShouldHaveUser() {
        //given
        JokeDTO jokeDTO = givenJokeDTO("This is joke title", "This is joke content", LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name(), 55L);
        Mockito.when(userRepository.getOne(55L)).thenReturn(User.builder().id(55L).build());
        //when
        joke = jokeService.addJoke(jokeDTO);
        //then
        assertThat(joke.getUser().getId()).isEqualTo(55);
    }

    @Test
    public void jokeShouldBeSaveWhenRunAdd() {
        //given
        JokeDTO jokeDTO = givenJokeDTO("This is joke title", "This is joke content", LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name(), 55L);
        Mockito.when(jokeRepsitory.save(any(Joke.class))).thenAnswer(invocationOnMock -> {
            Joke joke = invocationOnMock.getArgument(0);
            savedJokes.add(joke);
            return joke;
        });
        //when
        joke = jokeService.addJoke(jokeDTO);
        //then
        assertThat(savedJokes).containsOnly(joke);
    }

    private JokeDTO givenJokeDTO(String title, String content, LocalDate addDate, String category, Long userId) {
        return JokeDTO.builder()
                .title(title)
                .content(content)
                .addDate(addDate)
                .category(category)
                .userId(userId)
                .build();
    }

}

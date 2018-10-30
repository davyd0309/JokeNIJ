package pl.dawydiuk.service;


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

import java.time.Clock;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class JokeServiceImplTest {

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
    public void addJoke_shouldHasTitle() {
        //given
        JokeDTO jokeDTO = givenJokeDTO(LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name());
        //when
        joke = jokeService.addJoke(jokeDTO);
        //then
        assertThat(joke.getTitle()).isEqualTo("This is joke title");
    }

    @Test
    public void addJoke_shouldHasContent() {
        //given
        JokeDTO jokeDTO = givenJokeDTO(LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name());
        //when
        joke = jokeService.addJoke(jokeDTO);
        //then
        assertThat(joke.getContent()).isEqualTo("This is joke content");

    }

    @Test
    public void addJoke_shouldHasAddDate() {
        //given
        JokeDTO jokeDTO = givenJokeDTO(LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name());
        //when
        joke = jokeService.addJoke(jokeDTO);
        //then
        assertThat(joke.getAddDate()).isEqualTo(LocalDate.now(Clock.systemDefaultZone()));
    }

    @Test
    public void addJoke_shouldHasCategory() {
        //given
        JokeDTO jokeDTO = givenJokeDTO(LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name());
        //when
        joke = jokeService.addJoke(jokeDTO);
        //then
        assertThat(joke.getCategory().name()).isEqualTo(JokeCategoryEnum.CATEGORY_1.name());
    }

    @Test
    public void addJoke_shouldHasUser() {
        //given
        JokeDTO jokeDTO = givenJokeDTO(LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name());
        Mockito.when(userRepository.getOne(55L)).thenReturn(User.builder().id(55L).build());
        //when
        joke = jokeService.addJoke(jokeDTO);
        //then
        assertThat(joke.getUser().getId()).isEqualTo(55);
    }

    @Test
    public void addJoke_shouldBeSaveWhenRunAdd() {
        //given
        JokeDTO jokeDTO = givenJokeDTO(LocalDate.now(Clock.systemDefaultZone()), JokeCategoryEnum.CATEGORY_1.name());
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

    private JokeDTO givenJokeDTO(LocalDate addDate, String category) {
        return JokeDTO.builder()
                .title("This is joke title")
                .content("This is joke content")
                .addDate(addDate)
                .category(category)
                .userId(55L)
                .build();
    }

}

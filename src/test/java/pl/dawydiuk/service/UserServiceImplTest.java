package pl.dawydiuk.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.dawydiuk.domain.Role;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.dto.UserDTO;
import pl.dawydiuk.repository.RoleRepository;
import pl.dawydiuk.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    private UserService userService;
    private BCryptPasswordEncoder encoder;

    @Before
    public void setUp() {
        encoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, roleRepository, encoder);
    }

    @Test
    public void addUser_shouldReturnUser() {
        //given
        UserDTO userDTO = givenUserDto();
        Role roleUser = Role.builder().id(1L).role("ROLE_USER").build();
        when(roleRepository.findByRole("USER")).thenReturn(roleUser);
        //when
        User user = userService.addUser(userDTO);
        //then
        assertThat(user).hasFieldOrPropertyWithValue("email", "konrad@nowy.pl")
                .hasFieldOrPropertyWithValue("name", "Konrad")
                .hasFieldOrPropertyWithValue("lastName", "Dawydiuk")
                .hasFieldOrProperty("password")
                .hasFieldOrPropertyWithValue("active", true)
                .hasFieldOrProperty("roles");
    }

    @Test
    public void addUser_shouldThrowExceptionIfUserDtoIsNull() {

    }

    @Test
    public void addUser_shouldThrowExceptionIfUserDtoSomeValueIsNull() {

    }

    @Test
    public void addUser_shouldThrowExceptionIfUserDtoSomeValueIsEmpty() {

    }

    @Test
    public void addUser_shouldHaveEncodePasswordWhenAdd() {

        //given
        UserDTO userDTO = givenUserDto();
        //when
        User user = userService.addUser(userDTO);
        //then
        boolean isSame = userDTO.getPassword().equals(user.getPassword());
        boolean isMatches = encoder.matches("Haslo12345", user.getPassword());
        assertThat(isSame).isFalse();
        assertThat(isMatches).isTrue();
    }

    @Test
    public void addUser_shouldBeActiveWhenAdd() {
        //given
        UserDTO userDTO = givenUserDto();
        //when
        User user = userService.addUser(userDTO);
        //then
        assertThat(user.isActive()).isEqualTo(true);
    }

    @Test
    public void addUser_shouldHaveSetRoleWhenAdd() {
        //given
        UserDTO userDTO = givenUserDto();
        Role roleUser = Role.builder().id(1L).role("ROLE_USER").build();
        when(roleRepository.findByRole("USER")).thenReturn(roleUser);
        //when
        User user = userService.addUser(userDTO);
        //then
        assertThat(user.getRoles()).containsOnly(roleUser);
    }

    @Test
    public void addUser_userShouldBeSaveWhenRunAdd() {
        //given
        UserDTO userDTO = givenUserDto();
        Set<User> savedUsers = Sets.newHashSet();
        when(userRepository.save(any(User.class))).thenAnswer((Answer<User>) invocationOnMock -> {
            User user = invocationOnMock.getArgument(0);
            savedUsers.add(user);
            return user;
        });
        //when
        User user = userService.addUser(userDTO);
        //then
        assertThat(savedUsers).containsOnly(user);

    }

    @Test
    public void getAllUsers_shouldReturnListWithAllUsersDto() {

    }

    @Test
    public void getAllUsers_shouldReturnEmptyListWhenNoUsers() {

    }

    @Test
    public void getUserById_shouldReturnUserDTO() {

    }

    @Test
    public void getUserById_shouldReturnNewUserDTOIfUserNotExist() {

    }

    @Test
    public void getUserById_shouldThrowExceptionIfParameterIsOtherThenLong() {

    }

    @Test
    public void deleteUser_shouldDeleteUser() {

    }

    @Test
    public void deleteUser_shouldThrowExceptionIfUserNotExist() {

    }

    private UserDTO givenUserDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("konrad@nowy.pl");
        userDTO.setName("Konrad");
        userDTO.setLastName("Dawydiuk");
        userDTO.setPassword("Haslo12345");
        return userDTO;
    }

}

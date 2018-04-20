package pl.dawydiuk.service.userService;


import org.assertj.core.util.Sets;
import org.junit.Assert;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.dawydiuk.domain.Role;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.dto.UserDTO;
import pl.dawydiuk.repository.RoleRepsitory;
import pl.dawydiuk.repository.UserRepository;
import pl.dawydiuk.service.UserService;
import pl.dawydiuk.service.UserServiceImpl;

import java.util.Set;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceAddUserTest extends UserServiceSetUp {


    @Test
    public void shouldServiceNotNull() {
        Assert.assertNotNull(userService);
    }


    @Test
    public void userShouldHaveEmailNameLastNameWhenAdd() {

        //given
        UserDTO userDTO = givenUserDto("konrad@nowy.pl", "Konrad", "Dawydiuk", "Haslo12345");
        //when
        user = userService.addUser(userDTO);
        //then
        assertThat(user.getEmail()).isEqualTo("konrad@nowy.pl");
        assertThat(user.getName()).isEqualTo("Konrad");
        assertThat(user.getLastName()).isEqualTo("Dawydiuk");
    }

    @Test
    public void userShouldHaveEncodePasswordWhenAdd() {

        //given
        UserDTO userDTO = givenUserDto("konrad@nowy.pl", "Konrad", "Dawydiuk", "Haslo12345");
        //when
        user = userService.addUser(userDTO);
        //then
        boolean isSame = userDTO.getPassword().equals(user.getPassword());
        boolean isMatches = encoder.matches("Haslo12345", user.getPassword());
        assertThat(isSame).isFalse();
        assertThat(isMatches).isTrue();
    }

    @Test
    public void userShouldBeActiveWhenAdd() {
        //given
        UserDTO userDTO = givenUserDto("konrad@nowy.pl", "Konrad", "Dawydiuk", "Haslo12345");
        //when
        user = userService.addUser(userDTO);
        //then
        assertThat(user.isActive()).isEqualTo(true);
    }

    @Test
    public void userShouldHaveSetRoleUser() {
        //given
        UserDTO userDTO = givenUserDto("konrad@nowy.pl", "Konrad", "Dawydiuk", "Haslo12345");
        Role roleUser = Role.builder().id(1L).role("ROLE_USER").build();
        when(roleRepsitory.findByRole("USER")).thenReturn(roleUser);
        //when
        user = userService.addUser(userDTO);
        //then
        assertThat(user.getRoles()).containsOnly(roleUser);
    }


    @Test
    public void userShouldBeSaveWhenRunAdd() {
        //given
        UserDTO userDTO = givenUserDto("konrad@nowy.pl", "Konrad", "Dawydiuk", "Haslo12345");
        Set<User> savedUsers = Sets.newHashSet();
        when(userRepository.save(any(User.class))).thenAnswer((Answer<User>) invocationOnMock -> {
            User user = invocationOnMock.getArgument(0);
            savedUsers.add(user);
            return user;
        });
        //when
        user = userService.addUser(userDTO);
        //then
        assertThat(savedUsers).containsOnly(user);

    }

    @Test
    public void userShouldNotBeNull() {
//        //given
//        UserDTO userDTO = null;
//        //when
//        user = userService.addUser(userDTO);
//        //then
        //TODO: nalezy sprawdziź
    }

    @Test
    public void userShouldHaveIdAfterAddAndSaveInDatabase() {
        //TODO: nalezy sprawdziź
    }

    private UserDTO givenUserDto(String email, String name, String lastName, String password) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setName(name);
        userDTO.setLastName(lastName);
        userDTO.setPassword(password);
        return userDTO;
    }

}

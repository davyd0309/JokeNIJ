package pl.dawydiuk.service.userService;


import org.junit.Assert;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dawydiuk.domain.Role;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.dto.UserDTO;
import pl.dawydiuk.repository.RoleRepsitory;
import pl.dawydiuk.repository.UserRepository;
import pl.dawydiuk.service.UserService;
import pl.dawydiuk.service.UserServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceAddUserTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepsitory roleRepsitory;

    private BCryptPasswordEncoder encoder;

    private UserService userService;
    private UserDTO userDTO;
    private User user;
    private Role userRole;

    @Before
    public void setUp() {
        encoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, roleRepsitory, encoder);

        userDTO = new UserDTO();
        userDTO.setEmail("konrad@nowy.pl");
        userDTO.setName("Konrad");
        userDTO.setLastName("Dawydiuk");
        userDTO.setPassword("Haslo12345");


        userRole = new Role();
        userRole.setId(1L);
        userRole.setRole("USER");


    }


    @Test
    public void shouldServiceNotNull() {
        Assert.assertNotNull(userService);
    }


    @Test
    public void userShouldHaveEmailNameLastNameWhenAdd() {

        addUser();
        assertThat(user.getEmail()).isEqualTo("konrad@nowy.pl");
        assertThat(user.getName()).isEqualTo("Konrad");
        assertThat(user.getLastName()).isEqualTo("Dawydiuk");
    }

    @Test
    public void userShouldHaveEncodePasswordWhenAdd() {

        addUser();
        boolean isMatches = encoder.matches("Haslo12345", user.getPassword());
        assertThat(isMatches).isTrue();

    }

    @Test
    public void userShouldBeActiveWhenAdd() {

        addUser();
        assertThat(user.getActive()).isEqualTo(1);

    }

    @Test
    public void userShouldHaveRoleUserWhenAdd() {

        Mockito.when(roleRepsitory.findByRole("USER")).thenReturn(userRole);
        addUser();
        assertThat(user.getRoles()).contains(userRole);
    }


    @Test
    public void userSaveMethodShouldBeRunWhenAdd(){

        addUser();
        Mockito.verify(userRepository,Mockito.atLeastOnce()).save(user);

    }

    @Test
    public void userShouldHaveIdAfterAddAndSaveInDatabase(){
        //TODO: nalezy sprawdziź
    }

    private void addUser() {
        user = userService.addUser(userDTO);
    }







}

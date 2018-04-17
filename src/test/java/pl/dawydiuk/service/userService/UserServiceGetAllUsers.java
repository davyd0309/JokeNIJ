package pl.dawydiuk.service.userService;


import org.assertj.core.internal.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.dawydiuk.domain.User;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceGetAllUsers extends UserServiceSetUp {


    @Test
    public void shouldGetAllUsers() {
        //given
        List<User> savedUsers = com.google.common.collect.Lists.newArrayList();
        savedUsers.add(User.builder().build());
        savedUsers.add(User.builder().build());
        savedUsers.add(User.builder().build());
        savedUsers.add(User.builder().build());
        //when
        Mockito.when(userRepository.findAll()).thenReturn(savedUsers);
        //then
        assertThat(userService.getAllUsers().size()).isEqualTo(4);
    }


}

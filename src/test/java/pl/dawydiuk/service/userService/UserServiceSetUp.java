package pl.dawydiuk.service.userService;

import org.assertj.core.util.Sets;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.repository.RoleRepsitory;
import pl.dawydiuk.repository.UserRepository;
import pl.dawydiuk.service.UserService;
import pl.dawydiuk.service.UserServiceImpl;

import java.util.Set;


public class UserServiceSetUp {

    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepsitory roleRepsitory;

    BCryptPasswordEncoder encoder;

    UserService userService;
    User user;


    @Before
    public void setUp() {
        user = User.builder().build();
        encoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, roleRepsitory, encoder);

    }


}

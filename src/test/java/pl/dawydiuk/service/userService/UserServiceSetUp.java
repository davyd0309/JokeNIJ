package pl.dawydiuk.service.userService;

import org.junit.Before;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.repository.RoleRepository;
import pl.dawydiuk.repository.UserRepository;
import pl.dawydiuk.service.UserService;
import pl.dawydiuk.service.UserServiceImpl;


public class UserServiceSetUp {

    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;

    BCryptPasswordEncoder encoder;

    UserService userService;
    User user;


    @Before
    public void setUp() {
        user = User.builder().build();
        encoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, roleRepository, encoder);

    }


}

package pl.dawydiuk.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dawydiuk.domain.Role;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.repository.RoleRepsitory;
import pl.dawydiuk.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private RoleRepsitory roleRepsitory;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepsitory roleRepsitory, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepsitory = roleRepsitory;
        this.encoder = encoder;
    }

    @Override
    public void addUser(User newUser) {
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        newUser.setActive(1);
        Role roleUser = roleRepsitory.findByRole("USER");
        newUser.getRoles().add(roleUser);
        userRepository.save(newUser);
    }
}

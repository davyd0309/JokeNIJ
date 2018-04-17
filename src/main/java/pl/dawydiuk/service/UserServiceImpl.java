package pl.dawydiuk.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dawydiuk.domain.Role;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.dto.UserDTO;
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
    public User addUser(UserDTO newUserDTO) {

        User newUser = new User();
        newUser.setEmail(newUserDTO.getEmail());
        newUser.setLastName(newUserDTO.getLastName());
        newUser.setName(newUserDTO.getName());
        newUser.setPassword(encoder.encode(newUserDTO.getPassword()));
        newUser.setActive(1);
        Role roleUser = roleRepsitory.findByRole("USER");
        newUser.getRoles().add(roleUser);
        userRepository.save(newUser);

        return newUser;
    }
}

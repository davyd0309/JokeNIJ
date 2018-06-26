package pl.dawydiuk.service;


import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dawydiuk.domain.Role;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.dto.UserDTO;
import pl.dawydiuk.repository.RoleRepsitory;
import pl.dawydiuk.repository.UserRepository;

import java.util.List;

@Service
@Transactional
@Qualifier("userServiceImpl")
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

        User user = transformUserDTOToUser(newUserDTO);
        codeUserPassword(user);
        setActiveForUser(user);
        setRoleForUser(user);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        //return userRepository.findAll();
        return Lists.newArrayList();
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    private void setActiveForUser(User user) {
        user.setActive(true);
    }


    private void codeUserPassword(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
    }


    private User transformUserDTOToUser(UserDTO userDTO) {
        ModelMapper mapper = new ModelMapper();
        User newUser = User.builder().build();
        mapper.map(userDTO, newUser);
        return newUser;
    }


    private void setRoleForUser(User user) {
        Role roleUser = roleRepsitory.findByRole("USER");
        user.getRoles().add(roleUser);

    }


}

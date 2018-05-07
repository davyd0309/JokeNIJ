package pl.dawydiuk.service;

import pl.dawydiuk.domain.User;
import pl.dawydiuk.dto.UserDTO;

import java.util.List;

public interface UserService {

    User addUser(UserDTO newUserDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUser(Long userId);
    void deleteUser(Long userId);

}

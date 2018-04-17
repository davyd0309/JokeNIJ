package pl.dawydiuk.service;

import pl.dawydiuk.domain.User;
import pl.dawydiuk.dto.UserDTO;

public interface UserService {

    User addUser(UserDTO newUserDTO);


}

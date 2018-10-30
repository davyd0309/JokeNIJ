package pl.dawydiuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dawydiuk.dto.JokeDTO;
import pl.dawydiuk.dto.UserDTO;
import pl.dawydiuk.dto.VoteDTO;
import pl.dawydiuk.service.JokeService;
import pl.dawydiuk.service.UserService;
import pl.dawydiuk.service.VoteService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserDTO user, BindingResult result) {
        userService.addUser(user);
        HttpStatus httpStatus = user != null ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<UserDTO>(httpStatus);
    }

    @DeleteMapping(value = "/user")
    @Secured(value = { "ROLE_USER" })
    public ResponseEntity<UserDTO> deleteUser() {

        // userService.deleteUser(SIEBIE);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<UserDTO>(httpStatus);

    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    //    @Secured(value = {"ROLE_ADMIN"})
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        UserDTO userDTO = new UserDTO("email1@pl", "Konrad", "Dawydiuk", "Haslo1", true);
        UserDTO userDTO1 = new UserDTO("email1@p2", "Micha", "Mart", "Haslo2", true);
        UserDTO userDTO2 = new UserDTO("email1@p3", "Tomasz", "Alok", "Haslo3", true);
        UserDTO userDTO3 = new UserDTO("email1@p4", "Daru", "Kwai", "Haslo4", true);
        //        List<UserDTO> users = userService.getAllUsers();
        List<UserDTO> users = new ArrayList<>();
        users.add(userDTO);
        users.add(userDTO1);
        users.add(userDTO2);
        users.add(userDTO3);
        HttpStatus httpStatus = !users.isEmpty() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<UserDTO>>(users, httpStatus);
    }

    @DeleteMapping(value = "/user/{id}")
    @Secured(value = { "ROLE_ADMIN" })
    public ResponseEntity<UserDTO> deleteUser(@RequestParam Long userId) {
        UserDTO user = userService.getUserById(userId);
        HttpStatus httpStatus;
        if (user != null) {
            userService.deleteUser(userId);
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<UserDTO>(httpStatus);
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<UserDTO>(httpStatus);
        }
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(value = { "ROLE_ADMIN" })
    public ResponseEntity<UserDTO> findUserById(@RequestParam Long userId) {
        UserDTO user = userService.getUserById(userId);
        HttpStatus httpStatus;
        if (user != null) {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<UserDTO>(user, httpStatus);
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<UserDTO>(httpStatus);
        }
    }

}

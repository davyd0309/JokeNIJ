package pl.dawydiuk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dawydiuk.domain.User;
import pl.dawydiuk.dto.UserDTO;
import pl.dawydiuk.service.UserService;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

@RestController
public class GuestController {


    private UserService userService;

    @Autowired
    public GuestController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "addUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addAndSaveUser(@RequestBody @Valid UserDTO user,BindingResult result){
        userService.addUser(user);
        HttpStatus httpStatus = user !=null ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<UserDTO>(httpStatus);
    }



}

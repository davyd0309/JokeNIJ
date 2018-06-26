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

@RestController
public class UserController {

    private UserService userService;
    private JokeService jokeService;
    private VoteService voteService;

    @Autowired
    public UserController(UserService userService, JokeService jokeService, VoteService voteService) {
        this.userService = userService;
        this.jokeService = jokeService;
        this.voteService = voteService;
    }

    @DeleteMapping(value = "/user/deleteuser")
    @Secured(value = {"ROLE_USER"})
    public ResponseEntity<UserDTO> deleteUser() {

       // userService.deleteUser(SIEBIE);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<UserDTO>(httpStatus);

    }

    @PostMapping(value = "addJoke",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JokeDTO> addAndSaveJoke(@RequestBody JokeDTO jokeDTO){
        jokeService.addJoke(jokeDTO);
        HttpStatus httpStatus = jokeDTO !=null ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<JokeDTO>(httpStatus);
    }

    @DeleteMapping(value = "deleteJoke/{id}")
    public ResponseEntity<JokeDTO> deleteJoke(@RequestParam Long jokeId){
        JokeDTO jokeById = jokeService.getJokeById(jokeId);
        HttpStatus httpStatus;
        if(jokeById!=null){
            jokeService.deleteJoke(jokeId);
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<JokeDTO>(httpStatus);
        }else {
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<JokeDTO>(httpStatus);
        }
    }


    @GetMapping(value = "findJokeById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JokeDTO> findJokeById(@PathVariable Long jokeId){
        JokeDTO jokeById = jokeService.getJokeById(jokeId);
        HttpStatus httpStatus;
        if(jokeById!=null){
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<JokeDTO>(jokeById,httpStatus);
        }else {
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<JokeDTO>(httpStatus);
        }
    }

    @PostMapping(value = "/user/addvote",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteDTO> addVote(@RequestBody @Valid VoteDTO vote, BindingResult result){
        voteService.addVote(null,null,null);
        HttpStatus httpStatus = vote !=null ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<VoteDTO>(httpStatus);
    }


}

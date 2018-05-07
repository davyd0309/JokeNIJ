package pl.dawydiuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.dawydiuk.domain.Joke;
import pl.dawydiuk.domain.Vote;
import pl.dawydiuk.dto.UserDTO;
import pl.dawydiuk.dto.VoteDTO;
import pl.dawydiuk.service.UserService;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping(value = "/user/deleteuser")
    @Secured(value = {"ROLE_USER"})
    public ResponseEntity<UserDTO> deleteUser() {

       // userService.deleteUser(SIEBIE);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<UserDTO>(httpStatus);

    }

    @PostMapping(value = "addJoke",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Joke> addAndSaveJoke(@RequestBody Joke joke){
        jokeService.saveOrUpdateJoke(joke);
        HttpStatus httpStatus = joke !=null ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<Joke>(httpStatus);
    }

    @DeleteMapping(value = "deleteJoke/{id}")
    public ResponseEntity<Joke> deleteJoke(@PathVariable Integer id){
        Joke joke = jokeService.findJokeById(id);
        HttpStatus httpStatus;
        if(joke!=null){
            jokeService.deleteJoke(id);
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<Joke>(httpStatus);
        }else {
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<Joke>(httpStatus);
        }
    }


    @GetMapping(value = "findJokeById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Joke> findJokeById(@PathVariable Integer id){
        Joke joke = jokeService.findJokeById(id);
        HttpStatus httpStatus;
        if(joke!=null){
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<Joke>(joke,httpStatus);
        }else {
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<Joke>(httpStatus);
        }
    }

    @PostMapping(value = "/user/addvote",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteDTO> addVote(){
        voteService.saveOrUpdateVote(vote);
        HttpStatus httpStatus = vote !=null ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<VoteDTO>(httpStatus);
    }


}

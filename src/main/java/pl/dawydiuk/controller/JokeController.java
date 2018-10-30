package pl.dawydiuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.dto.JokeDTO;
import pl.dawydiuk.service.JokeService;
import pl.dawydiuk.service.UserService;
import pl.dawydiuk.service.VoteService;

@RestController
public class JokeController {

    private JokeService jokeService;

    @Autowired
    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @PostMapping(value = "/joke", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured(value = { "ROLE_USER" })
    public ResponseEntity<JokeDTO> addAndSaveJoke(@RequestBody JokeDTO jokeDTO) {
        jokeService.addJoke(jokeDTO);
        HttpStatus httpStatus = jokeDTO != null ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<JokeDTO>(httpStatus);
    }

    @DeleteMapping(value = "/joke/{id}")
    @Secured(value = { "ROLE_USER" })
    public ResponseEntity<JokeDTO> deleteJoke(@RequestParam Long jokeId) {
        JokeDTO jokeById = jokeService.getJokeById(jokeId);
        HttpStatus httpStatus;
        if (jokeById != null) {
            jokeService.deleteJoke(jokeId);
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<JokeDTO>(httpStatus);
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<JokeDTO>(httpStatus);
        }
    }

    @GetMapping(value = "joke/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(value = { "ROLE_USER" })
    public ResponseEntity<JokeDTO> findJokeById(@PathVariable Long jokeId) {
        JokeDTO jokeById = jokeService.getJokeById(jokeId);
        HttpStatus httpStatus;
        if (jokeById != null) {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<JokeDTO>(jokeById, httpStatus);
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<JokeDTO>(httpStatus);
        }
    }

}

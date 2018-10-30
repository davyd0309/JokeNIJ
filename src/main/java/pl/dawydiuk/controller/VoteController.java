package pl.dawydiuk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.dto.UserDTO;
import pl.dawydiuk.dto.VoteDTO;
import pl.dawydiuk.service.JokeService;
import pl.dawydiuk.service.UserService;
import pl.dawydiuk.service.VoteService;

@RestController
@RequestMapping("votes")
public class VoteController {

    private VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }


    @PostMapping(value = "/addVote",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured(value = {"ROLE_USER"})
    public ResponseEntity<VoteDTO> addVote(@RequestBody @Valid VoteDTO vote, BindingResult result){
        voteService.addVote(null,null,null);
        HttpStatus httpStatus = vote !=null ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return new ResponseEntity<VoteDTO>(httpStatus);
    }


}

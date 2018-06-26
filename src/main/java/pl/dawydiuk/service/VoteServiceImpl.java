package pl.dawydiuk.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VoteServiceImpl implements VoteService{


    @Override
    public void addVote(Long jokeId, Long userId, String voteLevel) {

    }
}

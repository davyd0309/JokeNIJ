package pl.dawydiuk.service;

public interface VoteService {

    void addVote(Long jokeId,Long userId,String voteLevel);
}

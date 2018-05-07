package pl.dawydiuk.service;

public interface VoteService {

    void addVoteOnJoke(Long jokeId,Long userId,String voteLevel);
}

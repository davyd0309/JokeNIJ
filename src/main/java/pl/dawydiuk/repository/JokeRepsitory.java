package pl.dawydiuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawydiuk.domain.Joke;
import pl.dawydiuk.domain.Role;

@Repository
public interface JokeRepsitory extends JpaRepository<Joke, Long> {



}

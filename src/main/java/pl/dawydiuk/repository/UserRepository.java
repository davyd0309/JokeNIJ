package pl.dawydiuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawydiuk.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}

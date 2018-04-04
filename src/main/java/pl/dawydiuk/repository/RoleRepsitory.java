package pl.dawydiuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawydiuk.domain.Role;

@Repository
public interface RoleRepsitory extends JpaRepository<Role, Integer> {


    Role findByRole(String role);

}

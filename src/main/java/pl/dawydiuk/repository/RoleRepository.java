package pl.dawydiuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawydiuk.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findByRole(String role);

}

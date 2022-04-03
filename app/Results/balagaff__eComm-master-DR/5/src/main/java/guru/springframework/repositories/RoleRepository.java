package guru.springframework.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import guru.springframework.domain.Role;
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{


public Role findByRole(String role)
;

}
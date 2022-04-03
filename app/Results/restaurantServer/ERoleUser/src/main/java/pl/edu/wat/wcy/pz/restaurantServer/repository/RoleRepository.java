package pl.edu.wat.wcy.pz.restaurantServer.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Role;
import java.util.Optional;
public interface RoleRepository extends JpaRepository<Role, Long>{


public Optional<Role> findByRoleName(String roleName)
;

}
package upce.semprace.eshop.repository;
 import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upce.semprace.eshop.entity.Role;
import upce.semprace.eshop.entity.RoleName;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{


public Optional<Role> findByName(RoleName roleName)
;

}
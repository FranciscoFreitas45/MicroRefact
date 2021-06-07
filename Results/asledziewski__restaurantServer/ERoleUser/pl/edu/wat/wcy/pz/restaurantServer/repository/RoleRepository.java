import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Role;
import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


public Optional<Role> findByRoleName(String roleName)


}
package switchtwentytwenty.project.autentication;
 import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<RoleJPA, Long>{


public RoleJPA save(RoleJPA role)
;

public Optional<RoleJPA> findByName(ERole name)
;

}
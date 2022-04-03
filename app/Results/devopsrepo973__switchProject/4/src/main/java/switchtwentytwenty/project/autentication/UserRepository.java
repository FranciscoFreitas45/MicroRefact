package switchtwentytwenty.project.autentication;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserJPA, Long>{


public Optional<UserJPA> findByUsername(String username)
;

@Override
public void deleteAll()
;

public Boolean existsByEmail(String email)
;

public Boolean existsByUsername(String username)
;

}
package ink.champ.repository;
 import ink.champ.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface UserRepository extends JpaRepository<User, Long>{


public User findByUsername(String username)
;

public List<User> findUserByNameContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(String search1,String search2,String search3,Sort sort)
;

public boolean isAdmin(Long id);

}
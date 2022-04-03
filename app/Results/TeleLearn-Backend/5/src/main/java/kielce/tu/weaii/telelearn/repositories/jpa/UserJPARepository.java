package kielce.tu.weaii.telelearn.repositories.jpa;
 import kielce.tu.weaii.telelearn.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserJPARepository extends JpaRepository<User, Long>{


public Optional<User> findByUsernameOrEmail(String username,String email)
;

public Optional<User> findByUsername(String username)
;

public Optional<User> findByEmail(String email)
;

}
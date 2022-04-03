package pl.szymanski.sharelibrary.repositories.jpa;
 import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymanski.sharelibrary.entity.User;
import java.util.Optional;
public interface UserJPARepository extends JpaRepository<User, Long>{


public Optional<User> findUserByUsernameOrEmail(String username,String email)
;

public Optional<User> findUserByUsername(String username)
;

public Optional<User> findUserByEmail(String email)
;

}
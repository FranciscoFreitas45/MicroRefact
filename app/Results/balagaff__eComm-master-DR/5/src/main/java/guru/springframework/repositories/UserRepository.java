package guru.springframework.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import guru.springframework.domain.User;
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{


public User findByEmail(String email)
;

}
package ar.com.veterinaria.app.entities.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.veterinaria.app.entities.user.User;
public interface UserRepository extends JpaRepository<User, Integer>{


@SuppressWarnings("unchecked")
public User save(User user)
;

public User findUserById(Integer id)
;

public List<User> findAll()
;

public void delete(User user)
;

}
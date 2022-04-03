package io.swagger.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import io.swagger.model.User;
@Transactional(isolation = Isolation.SERIALIZABLE)
public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long>{


public List<User> findByUsername(String username)
;

public User findByEmail(String email)
;

public User getUser(Long id);

public void setUser(Long id,User user);

public User getUser(Long id);

public void setUser(Long id,User user);

}
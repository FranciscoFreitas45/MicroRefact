package pl.edu.wat.wcy.pz.restaurantServer.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.restaurantServer.entity.User;
import javax.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long>{


public Boolean existsByMail(String mail)
;

public Optional<User> findByMail(String mail)
;

@Modifying
@Query(value = "update user u set u.password = ?2 where u.user_id = ?1", nativeQuery = true)
public void setPassword(Long id,String password);


@Modifying
@Query(value = "update user u set u.user_id = ?2 where u.user_id = ?1", nativeQuery = true)
public void setUserId(Long id,Long userId);

}
package co.edu.uniquindio.gri.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.gri.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{


@Query("FROM co.edu.uniquindio.gri.model.User WHERE username = :username")
public User findOne(String username)
;

}
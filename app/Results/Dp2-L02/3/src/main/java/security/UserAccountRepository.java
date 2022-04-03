package security;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer>{


@Query("select ua from UserAccount ua where ua.username = ?1")
public UserAccount findByUsername(String username)
;

}
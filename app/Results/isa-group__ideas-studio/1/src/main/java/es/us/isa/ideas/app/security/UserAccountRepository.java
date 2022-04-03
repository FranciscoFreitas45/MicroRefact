package es.us.isa.ideas.app.security;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer>{


public UserAccount findByUsername(String username)
;

}
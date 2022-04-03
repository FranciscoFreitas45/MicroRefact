package es.us.isa.ideas.app.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import es.us.isa.ideas.app.entities.Researcher;
import es.us.isa.ideas.app.security.UserAccount;
@Repository
public interface ResearcherRepository extends JpaRepository<Researcher, Integer>{


@Query(value = "SELECT * FROM Researcher  WHERE userAccount_id = ?", nativeQuery = true)
public Researcher findByUserAccountId(int id)
;

@Query("SELECT r FROM Researcher r WHERE r.userAccount.username=?")
public Researcher findByUsername(String username)
;

public Researcher findByUserAccount(UserAccount ua)
;

public Researcher findByName(String name)
;

public Researcher findByEmail(String email)
;

}
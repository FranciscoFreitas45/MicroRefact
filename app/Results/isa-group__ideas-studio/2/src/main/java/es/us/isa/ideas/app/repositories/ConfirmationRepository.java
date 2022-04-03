package es.us.isa.ideas.app.repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import es.us.isa.ideas.app.entities.Confirmation;
@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Integer>{


public Confirmation getByConfirmationCode(String code)
;

@Query("SELECT c FROM Confirmation c WHERE c.researcher.id=?")
public Confirmation getByResearcherId(int researcherId)
;

@Query("SELECT c FROM Confirmation c WHERE c.researcher.userAccount.id=?")
public Confirmation getByUserAccountId(int userAccountId)
;

}
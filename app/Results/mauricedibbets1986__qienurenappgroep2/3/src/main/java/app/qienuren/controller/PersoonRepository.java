package app.qienuren.controller;
 import app.qienuren.model.Persoon;
import app.qienuren.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
public interface PersoonRepository extends JpaRepository<Persoon, Long>{


public Optional<Persoon> findByUserName(String username)
;

public Persoon findByEmail(String email)
;

public void setMedewerker(long idN8E9,Persoon medewerker);

public Persoon getMedewerker(long idN8E9);

}
package app.qienuren.controller;
 import app.qienuren.model.Bedrijf;
import app.qienuren.model.KlantContactPersoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface KlantContactPersoonRepository extends JpaRepository<KlantContactPersoon, Long>{


public Optional<KlantContactPersoon> findByEmail(String email)
;

public void setLeidingGevende(long id0KCA,KlantContactPersoon leidingGevende);

public KlantContactPersoon getLeidingGevende(long id0KCA);

public void koppelKlantContactPersoon(long id0KCA,KlantContactPersoon kcp);

public void koppelTrainee(long id,Trainee trainee);

}
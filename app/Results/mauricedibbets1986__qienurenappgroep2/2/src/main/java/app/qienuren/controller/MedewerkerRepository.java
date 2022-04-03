package app.qienuren.controller;
 import app.qienuren.model.Medewerker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MedewerkerRepository extends JpaRepository<Medewerker, Long>{


public void verwijderFormulierUitTijdelijkeLijst(long id,Formulier tf);

}
package app.qienuren.controller;
 import app.qienuren.model.Bedrijf;
import app.qienuren.model.InterneMedewerker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface InterneMedewerkerRepository extends JpaRepository<InterneMedewerker, Long>{


public Optional<InterneMedewerker> findByEmail(String email)
;

}
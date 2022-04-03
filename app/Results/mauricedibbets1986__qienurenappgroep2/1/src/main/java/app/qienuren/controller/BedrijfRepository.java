package app.qienuren.controller;
 import app.qienuren.model.Bedrijf;
import app.qienuren.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface BedrijfRepository extends JpaRepository<Bedrijf, Long>{


public Optional<Bedrijf> findByEmail(String email)
;

}
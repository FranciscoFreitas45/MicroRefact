package app.qienuren.controller;
 import app.qienuren.model.TijdelijkeInterneMedewerker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TijdelijkeInterneMedewerkerRepository extends JpaRepository<TijdelijkeInterneMedewerker, Long>{


}
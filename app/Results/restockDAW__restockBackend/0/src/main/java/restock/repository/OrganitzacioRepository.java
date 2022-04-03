package restock.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import restock.entities.Organitzacio;
public interface OrganitzacioRepository extends JpaRepository<Organitzacio, Integer>{


public Organitzacio findByNif(String nif)
;

public Organitzacio findByNom(String nom)
;

public Organitzacio findByUsuariId(Integer id)
;

}
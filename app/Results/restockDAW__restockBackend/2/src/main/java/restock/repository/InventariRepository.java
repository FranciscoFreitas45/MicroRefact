package restock.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import restock.entities.Inventari;
public interface InventariRepository extends JpaRepository<Inventari, Integer>{


public Inventari findByBotigaIdAndProducteId(Integer botigaId,Integer producteId)
;

public List<Inventari> findByBotigaId(Integer botigaId)
;

}
package restock.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import restock.entities.DetallComanda;
public interface DetallComandaRepository extends JpaRepository<DetallComanda, Integer>{


public List<DetallComanda> findByComandaId(Integer comandaId)
;

}
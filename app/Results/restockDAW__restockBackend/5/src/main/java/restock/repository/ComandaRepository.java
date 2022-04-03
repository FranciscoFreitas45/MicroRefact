package restock.repository;
 import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import restock.entities.Comanda;
public interface ComandaRepository extends JpaRepository<Comanda, Integer>{


@Query(value = " " + "SELECT com " + "FROM Comanda com WHERE " + "com.botiga.id =:botigaId " + "AND com.datarecepcio >:data")
public List<Comanda> findPendentsByBotigaId(Integer botigaId,Date data)
;

public List<Comanda> findByBotigaId(Integer botigaId)
;

}
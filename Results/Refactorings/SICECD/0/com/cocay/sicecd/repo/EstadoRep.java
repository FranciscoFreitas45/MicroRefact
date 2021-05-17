import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cocay.sicecd.model.Estado;
@Repository
public interface EstadoRep extends PagingAndSortingRepository<Estado, Integer> {


public List<Estado> findByNombre(String nombre)


@Query("SELECT c FROM Estado c where c.nombre = :nombre")
public Estado findByNombreEstado(String nombre)


}
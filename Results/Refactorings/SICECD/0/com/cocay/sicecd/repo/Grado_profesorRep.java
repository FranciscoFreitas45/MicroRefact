import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cocay.sicecd.model.Grado_profesor;
@Repository
public interface Grado_profesorRep extends PagingAndSortingRepository<Grado_profesor, Integer> {


public List<Grado_profesor> findByNombre(String name)


@Query("SELECT c FROM Grado_profesor c where c.nombre = :nombre")
public Grado_profesor findByNombreGrado(String nombre)


}
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.cocay.sicecd.model.Url_ws_inscripcion;
@Repository
public interface Url_ws_inscripcionRep extends PagingAndSortingRepository<Url_ws_inscripcion, Integer> {


@Query("SELECT c FROM Url_ws_inscripcion c where c.activa = true")
public List<Url_ws_inscripcion> findVarios()


}
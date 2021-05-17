import org.springframework.data.jpa.repository.JpaRepository;
import com.cocay.sicecd.model.Inscripcion;
@Repository
public interface InscripcionBatchRep extends JpaRepository<Inscripcion, Integer> {


}
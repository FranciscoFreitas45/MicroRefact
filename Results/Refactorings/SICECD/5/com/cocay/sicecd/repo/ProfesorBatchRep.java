import org.springframework.data.jpa.repository.JpaRepository;
import com.cocay.sicecd.model.Profesor;
@Repository
public interface ProfesorBatchRep extends JpaRepository<Profesor, Integer> {


}
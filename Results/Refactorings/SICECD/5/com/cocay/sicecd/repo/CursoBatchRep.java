import org.springframework.data.jpa.repository.JpaRepository;
import com.cocay.sicecd.model.Curso;
@Repository
public interface CursoBatchRep extends JpaRepository<Curso, Integer> {


}
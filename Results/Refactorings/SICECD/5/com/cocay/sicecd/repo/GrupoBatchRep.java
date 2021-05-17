import org.springframework.data.jpa.repository.JpaRepository;
import com.cocay.sicecd.model.Grupo;
@Repository
public interface GrupoBatchRep extends JpaRepository<Grupo, Integer> {


}
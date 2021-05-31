import com.ats.hrmgt.model.SalaryTypesMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SalaryTypesMasterRepo extends JpaRepository<SalaryTypesMaster, Integer> {


public List<SalaryTypesMaster> findAllByDelStatus(int i)


}
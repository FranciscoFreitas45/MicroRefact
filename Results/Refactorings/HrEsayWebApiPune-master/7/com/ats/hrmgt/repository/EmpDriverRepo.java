import com.ats.hrmgt.model.EmpDriver;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmpDriverRepo extends JpaRepository<EmpDriver, Integer> {


public EmpDriver findByEmpIdAndDelStatus(int empId,int i)


}
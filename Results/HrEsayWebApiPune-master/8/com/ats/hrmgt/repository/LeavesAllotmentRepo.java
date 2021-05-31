import com.ats.hrmgt.model.LeavesAllotment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LeavesAllotmentRepo extends JpaRepository<LeavesAllotment, Integer> {


public LeavesAllotment findByDelStatusAndEmpIdAndCalYrId(int i,int empId,int calYrId)


public List<LeavesAllotment> findByLvsIdAndDelStatus(int lvsId,int i)


}
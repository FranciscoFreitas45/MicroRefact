import com.ats.hrmgt.model.LeaveStructureHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface LeaveStructureHeaderRepo extends JpaRepository<LeaveStructureHeader, Integer> {


@Transactional
@Modifying
@Query("update LeaveStructureHeader set del_status=0  WHERE lvs_id=:lvsId")
public int deleteLeaveStructure(int lvsId)


public LeaveStructureHeader findByLvsIdAndDelStatus(int lvsId,int i)


public List<LeaveStructureHeader> findByDelStatusAndCompanyIdOrderByLvsIdDesc(int i,int companyId)


public List<LeaveStructureHeader> findByDelStatusAndCompanyId(int i,int companyId)


}
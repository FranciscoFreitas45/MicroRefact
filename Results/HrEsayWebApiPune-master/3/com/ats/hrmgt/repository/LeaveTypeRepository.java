import com.ats.hrmgt.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {


@Transactional
@Modifying
@Query("update LeaveType set del_status=0  WHERE lv_type_id=:lvTypeId")
public int deleteLeaveType(int lvTypeId)


public LeaveType findByCompanyIdAndLvTitleShort(int compId,String valueType)


public List<LeaveType> findByDelStatus(int i)


public LeaveType findByLvTypeIdAndDelStatus(int lvTypeId,int i)


public List<LeaveType> findByDelStatusAndIsStructuredAndCompanyIdOrderByLvTypeIdDesc(int i,int j,int companyId)


public List<LeaveType> findByDelStatusAndIsStructuredAndCompanyId(int i,int j,int companyId)


}
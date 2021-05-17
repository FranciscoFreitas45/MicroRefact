import com.ats.hrmgt.model.LeaveTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
public interface LeaveTrailRepository extends JpaRepository<LeaveTrail, Integer> {


@Transactional
@Modifying
@Query("delete from LeaveTrail where leave_id=:leaveId")
public int deleteByLeaveId(int leaveId)


}
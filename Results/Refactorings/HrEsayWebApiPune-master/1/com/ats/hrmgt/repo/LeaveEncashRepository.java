import com.ats.hrmgt.model.LeaveEncash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface LeaveEncashRepository extends JpaRepository<LeaveEncash, Integer> {


@Transactional
@Modifying
@Query(value = "UPDATE t_encash SET del_status = 0 WHERE id=:id", nativeQuery = true)
public int deleteEncashLeave(int id)


@Transactional
@Modifying
@Query(value = "UPDATE t_encash SET is_freeze = 1 WHERE month=:month and year=:year and emp_id in (:empIds) and del_status=1", nativeQuery = true)
public int updateEncashAmt(int month,int year,List<Integer> empIds)


}
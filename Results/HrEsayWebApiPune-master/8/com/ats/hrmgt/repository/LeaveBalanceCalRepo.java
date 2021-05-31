import com.ats.hrmgt.model.LeaveBalanceCal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface LeaveBalanceCalRepo extends JpaRepository<LeaveBalanceCal, Integer> {


@Transactional
@Modifying
@Query("update LeaveBalanceCal set op_bal=:noDays  WHERE lv_type_id=:lvTypeId AND emp_id=:empId AND cal_yr_id=:calYrID  ")
public int updateLeaveBalCal(int lvTypeId,int empId,int calYrID,int noDays)


public List<LeaveBalanceCal> findByCalYrIdAndEmpIdAndDelStatusAndIsActive(int empId,int calYrId,int j,int k)


@Transactional
@Modifying
@Query("update LeaveBalanceCal set ex_int1=0,ex_var1=:date  WHERE cal_yr_id=:yearId AND emp_id in (:empId)")
public int updateIsPaidIncash(int yearId,List<Integer> empId,String date)


public List<LeaveBalanceCal> findByCalYrIdAndEmpId(int yearId,int empId)


@Transactional
@Modifying
@Query("update LeaveBalanceCal set op_bal=:opBal  WHERE lvbal_id=:balId ")
public int updateOp(int balId,float opBal)


}
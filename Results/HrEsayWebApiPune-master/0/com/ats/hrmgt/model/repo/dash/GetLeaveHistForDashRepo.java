import com.ats.hrmgt.model.dashboard.GetLeaveHistForDash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetLeaveHistForDashRepo extends JpaRepository<GetLeaveHistForDash, Integer> {


@Query(value = "SELECT\n" + "    leave_apply.leave_id,\n" + "    leave_apply.leave_fromdt,\n" + "    leave_apply.leave_todt,\n" + "    leave_apply.leave_num_days,\n" + "    leave_apply.ex_int1,\n" + "    leave_type.lv_title_short,\n" + "    leave_apply.leave_emp_reason\n" + "FROM\n" + "    leave_apply,\n" + "    leave_type\n" + "WHERE\n" + "    leave_apply.emp_id =:empId AND leave_apply.lv_type_id = leave_type.lv_type_id", nativeQuery = true)
public List<GetLeaveHistForDash> getLeaveCnt(int empId)


}
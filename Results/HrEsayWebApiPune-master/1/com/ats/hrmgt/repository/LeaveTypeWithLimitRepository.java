import com.ats.hrmgt.model.LeaveTypeWithLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface LeaveTypeWithLimitRepository extends JpaRepository<LeaveTypeWithLimit, Integer> {


@Query(value = "SELECT\n" + "        leave_type.lv_type_id,\n" + "        leave_type.lv_title_short, \n" + "        leave_structure_header.lvs_id,\n" + "        leave_structure_details.max_no_days,\n" + "        leave_structure_details.min_no_days\n" + "    FROM\n" + "        leave_type, \n" + "        leave_structure_header,\n" + "        leave_structure_details,\n" + "        leave_structure_allotment      \n" + "    WHERE\n" + "         leave_structure_allotment.emp_id=:empId          \n" + "        AND leave_structure_allotment.lvs_id = leave_structure_header.lvs_id          \n" + "        AND leave_structure_header.lvs_id = leave_structure_details.lvs_id          \n" + "        AND leave_structure_details.lv_type_id = leave_type.lv_type_id  \n" + "        and leave_structure_allotment.cal_yr_id=:calYrId          \n" + "        and leave_type.del_status=1\n" + "        and leave_type.lv_type_id=:leaveTypeId", nativeQuery = true)
public LeaveTypeWithLimit LeaveTypeWithLimit(int empId,int leaveTypeId,int calYrId)


}
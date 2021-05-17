import com.ats.hrmgt.model.RemainingEmpForAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface RemainingEmpForAllocationRepository extends JpaRepository<RemainingEmpForAllocation, Integer> {


@Query(value = "SELECT\n" + "        emp.emp_id,\n" + "        emp.emp_code,\n" + "        IFNULL(next_shiftid,\n" + "        0) as group_id,\n" + "        IFNULL(sg.ex_int1,\n" + "        0) as group_type,\n" + "        IFNULL((select\n" + "            shift_id \n" + "        from\n" + "            t_shift_assign_daily \n" + "        where\n" + "            shift_date=(SELECT\n" + "         \n" + "        max(shift_date)\n" + "    FROM\n" + "        t_shift_assign_daily\n" + "        where emp_id=t_shift_assign_daily.emp_id) \n" + "            and emp_id=emp.emp_id),\n" + "        (select\n" + "            current_shiftid \n" + "        from\n" + "            m_employees \n" + "        where\n" + "            emp_id=emp.emp_id)) as shift_id,\n" + "            \n" + "            IFNULL((SELECT\n" + "         \n" + "        max(shift_date)\n" + "    FROM\n" + "        t_shift_assign_daily \n" + "        where t_shift_assign_daily.emp_id=emp.emp_id  ),\n" + "        (0)) as max_date,\n" + "        emp.location_id   \n" + "    FROM\n" + "        m_employees emp      \n" + "    INNER JOIN\n" + "        tbl_emp_salary_info salinfo              \n" + "            ON     emp.emp_id = salinfo.emp_id     \n" + "        \n" + "    LEFT JOIN\n" + "        m_self_grup sg              \n" + "            ON     sg.selft_group_id = emp.next_shiftid                   \n" + "    WHERE\n" + "        emp.del_status = 1 and emp.location_id=:locId ", nativeQuery = true)
public List<RemainingEmpForAllocation> checkRemainingEmployeeForProjection(int locId)


}
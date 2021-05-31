import com.ats.hrmgt.model.EmployeeLeaveDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface EmployeeLeaveDetailRepo extends JpaRepository<EmployeeLeaveDetail, Integer> {


@Query(value = "SELECT\n" + "        l.*,\n" + "        e.first_name as emp_fname,\n" + "        e.middle_name as emp_mname,\n" + "        e.emp_code,\n" + "        e.surname as emp_sname,\n" + "        \"\" as emp_photo,\n" + "        COALESCE((         select\n" + "            count(*)                   \n" + "        from\n" + "            leave_apply                \n" + "        where\n" + "            leave_apply.lvt_application_id_parent=l.leave_id),\n" + "        null) as emp_dept_name,\n" + "        lt.lv_title ,\n" + "        COALESCE((         select\n" + "            concat(e.first_name,\n" + "            \" \",\n" + "            e.middle_name,\n" + "            \" \",\n" + "            e.surname) as user_name                   \n" + "        from\n" + "            m_employees as e,\n" + "            m_user u                   \n" + "        where\n" + "            u.user_id=l.maker_user_id                           \n" + "            and e.emp_id=u.emp_id),\n" + "        null) as user_name      \n" + "    FROM\n" + "        leave_apply AS l,\n" + "        m_employees AS e,\n" + "        leave_type AS lt        \n" + "    WHERE\n" + "        l.emp_id =:empId         \n" + "        AND  l.emp_id =e.emp_id          \n" + "        AND l.del_status=1           \n" + "        AND lt.lv_type_id = l.lv_type_id      \n" + "    ORDER BY\n" + "        l.leave_id DESC", nativeQuery = true)
public List<EmployeeLeaveDetail> getLeaveListByEmp(int empId)


@Query(value = " SELECT\n" + "l.*,\n" + "e.emp_fname,\n" + "e.emp_mname," + "e.emp_code," + "e.emp_sname,\n" + "e.emp_photo,\n" + "d.emp_dept_name,\n" + "lt.lv_title ,COALESCE((\n" + "        select\n" + "            concat(e.emp_fname,\n" + "            \" \",\n" + "            e.emp_mname,\n" + "            \" \",\n" + "            e.emp_sname) as user_name \n" + "        from\n" + "            emp_info as e,\n" + "            m_user u \n" + "        where\n" + "            u.user_id=l.maker_user_id \n" + "            and e.emp_id=u.emp_id),null) as user_name\n" + "FROM\n" + "leave_apply AS l,\n" + "emp_info AS e,\n" + "m_emp_department d,\n" + "leave_type AS lt  \n" + "WHERE \n" + "l.emp_id =:empId AND  l.emp_id = e.emp_id AND l.del_status=:i and l.cal_yr_id=:calYrId  AND d.emp_dept_id = e.emp_dept_id AND lt.lv_type_id = l.lv_type_id ", nativeQuery = true)
public List<EmployeeLeaveDetail> getLeaveListByLocIdAndEmp(int empId,int calYrId,int i)


}
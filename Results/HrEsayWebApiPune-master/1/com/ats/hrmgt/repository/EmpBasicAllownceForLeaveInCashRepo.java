import com.ats.hrmgt.model.EmpBasicAllownceForLeaveInCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface EmpBasicAllownceForLeaveInCashRepo extends JpaRepository<EmpBasicAllownceForLeaveInCash, Integer> {


@Query(value = "select\n" + "        e.emp_id,\n" + "        coalesce((select sf.basic from tbl_emp_salary_info sf where   sf.emp_id=e.emp_id ),\n" + "        0) as basic,\n" + "        coalesce(sum(es.allowance_value),\n" + "        0) as allowance_value \n" + "    from\n" + "        m_employees e, \n" + "        emp_sal_allowance es \n" + "    where\n" + "        e.emp_id=:empId  \n" + "        and es.emp_id=e.emp_id \n" + "        and find_in_set ( es.allowance_id , (select\n" + "            ex_var1 \n" + "        from\n" + "            leave_structure_header \n" + "        where\n" + "            lvs_id=:lvsId))", nativeQuery = true)
public EmpBasicAllownceForLeaveInCash getEmployeeBasicAndAllownceValueByEmpIdAndStructId(int empId,int lvsId)


}
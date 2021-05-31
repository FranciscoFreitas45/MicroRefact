import com.ats.hrmgt.model.EmpInfoForDashBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface EmpInfoForDashBoardRepository extends JpaRepository<EmpInfoForDashBoard, Integer> {


@Query(value = "select e.emp_id,e.emp_code,concat(e.first_name,' ',e.surname) as emp_name,d.name as designation_name,dep.name as department_name," + "e.mobile_no_1 as contact_no,emp_info.ex_var1 as profile_pic from m_employees e,m_designation d,m_department dep, tbl_emp_info emp_info " + "where e.emp_id=:empId and d.desig_id=e.designation_id and e.depart_id=dep.depart_id and emp_info.emp_id=e.emp_id", nativeQuery = true)
public EmpInfoForDashBoard getEmpInfoForModelGraph(int empId)


}
import com.ats.hrmgt.model.GetEmpDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface GetEmpDetailRepo extends JpaRepository<GetEmpDetail, Integer> {


@Query(value = "SELECT\n" + "        emp.emp_id,\n" + "        dep.name AS dept_name,\n" + "        dg.name AS emp_desgn,\n" + "        loc.loc_name as  loc_name,\n" + "        emptyp.name AS emp_type_name\n" + "    FROM\n" + "        m_employees emp \n" + "    INNER JOIN\n" + "        tbl_emp_salary_info salinfo \n" + "            ON     emp.emp_id = salinfo.emp_id \n" + "    LEFT JOIN\n" + "        m_designation dg \n" + "            ON     emp.designation_id = dg.desig_id \n" + "    LEFT JOIN\n" + "        m_department dep \n" + "            ON     emp.depart_id = dep.depart_id \n" + "    LEFT JOIN\n" + "        m_location loc \n" + "            ON     emp.location_id = loc.loc_id \n" + "    LEFT JOIN\n" + "        tbl_mst_emp_types emptyp \n" + "            ON     emp.emp_type = emptyp.emp_type_id   \n" + "    WHERE\n" + "        emp.del_status = 1 \n" + "        and emp.emp_id in (:empIds)", nativeQuery = true)
public List<GetEmpDetail> getPayrollGenratedList(List<Integer> empIds)


}
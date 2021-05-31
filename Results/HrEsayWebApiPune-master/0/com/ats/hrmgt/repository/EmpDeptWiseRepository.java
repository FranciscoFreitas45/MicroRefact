import com.ats.hrmgt.model.EmpDeptWise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface EmpDeptWiseRepository extends JpaRepository<EmpDeptWise, Integer> {


@Query(value = "SELECT\n" + "        UUID() as id,\n" + "        count('') as dept_count,\n" + "        dep.name_sd AS dept_name,\n" + "        dep.depart_id\n" + "    FROM\n" + "        m_employees emp \n" + "    INNER JOIN\n" + "        tbl_emp_salary_info salinfo \n" + "            ON     emp.emp_id = salinfo.emp_id \n" + "    LEFT JOIN\n" + "        m_department dep \n" + "            ON     emp.depart_id = dep.depart_id \n" + "    WHERE\n" + "        emp.del_status = 1 \n" + "        AND(\n" + "            salinfo.cmp_leaving_date IS NULL \n" + "            OR salinfo.cmp_leaving_date = '' \n" + "            OR salinfo.cmp_leaving_date = 1970 -00 -00 \n" + "            OR DATE_FORMAT(             salinfo.cmp_leaving_date,             '%Y-%m'         ) >= DATE_FORMAT(CURDATE(), '%Y-%m')\n" + "        )  \n" + "        AND emp.location_id IN(:locId)\n" + "    group by dep.depart_id", nativeQuery = true)
public List<EmpDeptWise> getDeparmentWiseEmpCount(int locId)


}
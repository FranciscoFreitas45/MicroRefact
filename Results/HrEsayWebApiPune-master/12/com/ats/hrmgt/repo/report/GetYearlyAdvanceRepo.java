import com.ats.hrmgt.model.report.GetYearlyAdvance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetYearlyAdvanceRepo extends JpaRepository<GetYearlyAdvance, Integer> {


@Query(value = " SELECT UUID() as unique_id ,\n" + "    MONTH(tbl_advance.adv_date) AS month,\n" + "    tbl_advance.emp_id, 0 as year,\n" + "    CONCAT(\n" + "        m_employees.first_name,\n" + "        \" \",\n" + "        m_employees.middle_name,\n" + "        \" \",\n" + "        m_employees.surname\n" + "    ) AS emp_name,\n" + "    m_employees.emp_code,\n" + "    SUM(tbl_advance.adv_amount) AS adv_amount\n" + "FROM\n" + "    m_employees,\n" + "    tbl_advance,\n" + "    m_designation\n" + "WHERE\n" + "    tbl_advance.del_status = 1 AND tbl_advance.emp_id = m_employees.emp_id AND m_designation.desig_id = m_employees.designation_id AND tbl_advance.cmp_id =:companyId AND YEAR(tbl_advance.adv_date) =:year and m_employees.location_id=:locId\n" + "GROUP BY\n" + "    tbl_advance.emp_id,\n" + "    MONTH(tbl_advance.adv_date)", nativeQuery = true)
public List<GetYearlyAdvance> getSpecEmpAdvForReport(int companyId,int year,int locId)


}
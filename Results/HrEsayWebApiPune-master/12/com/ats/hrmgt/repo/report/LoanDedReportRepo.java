import com.ats.hrmgt.model.report.LoanDedReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface LoanDedReportRepo extends JpaRepository<LoanDedReport, String> {


@Query(value = "SELECT\n" + "        UUID() AS unique_id,\n" + "        te.emp_id,\n" + "        te.emp_code,\n" + "        CONCAT(         te.first_name,\n" + "        ' ',\n" + "        te.middle_name,\n" + "        ' ',\n" + "        te.surname     ) AS emp_name,\n" + "        dp.name as department_name,\n" + "        sum(ld.amount_emi) as amount_emi\n" + "    FROM\n" + "        m_employees AS te,  \n" + "        tbl_loan_main AS tlm,\n" + "        tbl_loan_details AS ld,\n" + "        m_department AS dp\n" + "    WHERE\n" + "        te.emp_id = tlm.emp_id \n" + "        and te.location_id=:locId   \n" + "        and tlm.id=ld.loan_main_id\n" + "        and ld.months=:month\n" + "        and ld.years=:year\n" + "        and dp.depart_id=te.depart_id\n" + "    group by tlm.emp_id\n" + "    ORDER BY\n" + "        department_name asc,emp_name ASC", nativeQuery = true)
public List<LoanDedReport> getSpecEmpDedLoanReport(String month,String year,int locId)


}
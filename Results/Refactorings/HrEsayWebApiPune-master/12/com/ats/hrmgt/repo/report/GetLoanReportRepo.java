import com.ats.hrmgt.model.report.GetLoanReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetLoanReportRepo extends JpaRepository<GetLoanReport, Integer> {


@Query(value = "SELECT\n" + "    CONCAT(\n" + "        m_employees.first_name,\n" + "        ' ' ,m_employees.middle_name,\n" + "        ' ',\n" + "        m_employees.surname\n" + "    ) AS emp_name,\n" + "    m_employees.emp_id,\n" + "    m_employees.emp_code,\n" + "    tbl_loan_main.loan_appl_no,\n" + "    tbl_loan_main.id,\n" + "    tbl_loan_main.loan_amt,\n" + "    tbl_loan_main.loan_roi,\n" + "    tbl_loan_main.loan_tenure,\n" + "    tbl_loan_main.loan_repay_start,\n" + "    tbl_loan_main.loan_repay_end,\n" + "    tbl_loan_main.loan_repay_amt,\n" + "    tbl_loan_main.loan_emi,\n" + "    tbl_loan_main.loan_emi_intrest,\n" + "    tbl_loan_main.current_totpaid,\n" + "    tbl_loan_main.current_outstanding,\n" + "    tbl_loan_main.loan_status,\n" + "    tbl_loan_main.skip_id,\n" + "    tbl_loan_main.skip_login_name,\n" + "    tbl_loan_main.skip_login_time,\n" + "    tbl_loan_main.skip_remarks,'-' as remark,'-' as skip_login_name,\n" + "  CONCAT(\n" + "        e.first_name,\n" + "        ' ' ,e.middle_name,\n" + "        ' ',\n" + "        e.surname)  AS skip_user_name\n" + "   \n" + "     \n" + "FROM\n" + "    m_employees,\n" + "    tbl_loan_main, m_employees e,tbl_loan_main loan \n" + "WHERE\n" + "    m_employees.emp_id = tbl_loan_main.emp_id AND tbl_loan_main.del_status=1 AND MONTH(tbl_loan_main.loan_repay_start)=:month AND YEAR(tbl_loan_main.loan_repay_start)=:year AND  e.emp_id=loan.skip_login_name AND loan.id=tbl_loan_main.id", nativeQuery = true)
public List<GetLoanReport> getSpecEmpAdvForReport(int year,int month)


}
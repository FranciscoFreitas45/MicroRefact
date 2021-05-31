import com.ats.hrmgt.model.report.LoanStatementDetailsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface LoanStatementRepo extends JpaRepository<LoanStatementDetailsReport, Integer> {


@Query(value = "SELECT DISTINCT\n" + "    te.emp_id,\n" + "    te.emp_code,\n" + "    CONCAT(\n" + "        te.first_name,\n" + "        ' ',\n" + "        te.middle_name,\n" + "        ' ',\n" + "        te.surname\n" + "    ) AS emp_name,\n" + "    tlm.loan_appl_no,\n" + "    tlm.loan_amt,\n" + "    tlm.loan_add_date,\n" + "    tlm.current_outstanding,\n" + "    tlm.current_totpaid,\n" + "    tlm.loan_emi_intrest,\n" + "    tlm.loan_emi,\n" + "    tlm.id,\n" + "    DATE_FORMAT(tlm.loan_repay_start, '%d-%m-%Y') AS loan_repay_start,\n" + "    DATE_FORMAT(tlm.loan_repay_end, '%d-%m-%Y') AS loan_repay_end\n" + "FROM\n" + "    m_employees AS te\n" + "INNER JOIN tbl_loan_main AS tlm\n" + "ON\n" + "    te.emp_id = tlm.emp_id\n" + "WHERE\n" + "    te.emp_id = :empId AND tlm.loan_repay_start BETWEEN :newfromDate AND LAST_DAY(:newToDate)", nativeQuery = true)
public List<LoanStatementDetailsReport> getEmpLoanStateDetailsByEmpId(String newfromDate,String newToDate,int empId)


@Query(value = "SELECT DISTINCT\n" + "    te.emp_id,\n" + "    te.emp_code,\n" + "    CONCAT(\n" + "        te.first_name,\n" + "        ' ',\n" + "        te.middle_name,\n" + "        ' ',\n" + "        te.surname\n" + "    ) AS emp_name,\n" + "    tlm.loan_appl_no,\n" + "    tlm.loan_amt,\n" + "    tlm.loan_add_date,\n" + "    tlm.current_outstanding,\n" + "    tlm.current_totpaid,\n" + "    tlm.loan_emi_intrest,\n" + "    tlm.loan_emi,\n" + "    tlm.id,\n" + "    DATE_FORMAT(tlm.loan_repay_start, '%d-%m-%Y') AS loan_repay_start,\n" + "    DATE_FORMAT(tlm.loan_repay_end, '%d-%m-%Y') AS loan_repay_end\n" + "FROM\n" + "    m_employees AS te\n" + "INNER JOIN tbl_loan_main AS tlm\n" + "ON\n" + "    te.emp_id = tlm.emp_id\n" + "WHERE\n" + "     tlm.loan_repay_start BETWEEN :fromDate AND :toDate and te.location_id=:locId", nativeQuery = true)
public List<LoanStatementDetailsReport> getEmpLoanStateDetails(int locId,String fromDate,String toDate)


}
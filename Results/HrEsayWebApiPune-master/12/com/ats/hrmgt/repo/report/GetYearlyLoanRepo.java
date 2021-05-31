import com.ats.hrmgt.model.report.GetYearlyLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetYearlyLoanRepo extends JpaRepository<GetYearlyLoan, Integer> {


@Query(value = " SELECT\n" + "    UUID() AS unique_id, SUM(tbl_loan_main.loan_amt) AS loan_amt,\n" + "    SUM(tbl_loan_main.loan_repay_amt) AS loan_repay_amt,\n" + "    SUM(tbl_loan_main.current_totpaid) AS current_totpaid,\n" + "    MONTH(tbl_loan_main.loan_repay_start) AS MONTH,\n" + "    SUM(\n" + "        tbl_loan_main.current_outstanding\n" + "    ) AS current_outstanding,\n" + "    tbl_loan_main.emp_id,\n" + "    CONCAT(\n" + "        m_employees.first_name,\n" + "        ' ',\n" + "        m_employees.middle_name,\n" + "        ' ',\n" + "        m_employees.surname\n" + "    ) AS emp_name,\n" + "    m_employees.emp_code,\n" + "    YEAR(tbl_loan_main.loan_repay_start) AS YEAR\n" + "FROM\n" + "    tbl_loan_main,\n" + "    m_employees\n" + "WHERE\n" + "    YEAR(tbl_loan_main.loan_repay_start) = :year AND m_employees.emp_id = tbl_loan_main.emp_id\n" + "GROUP BY\n" + "    MONTH(tbl_loan_main.loan_repay_start),\n" + "    tbl_loan_main.emp_id", nativeQuery = true)
public List<GetYearlyLoan> getSpecEmpAdvForReport(int year)


}
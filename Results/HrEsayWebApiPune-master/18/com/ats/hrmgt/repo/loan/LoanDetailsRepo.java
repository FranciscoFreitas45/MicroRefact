import com.ats.hrmgt.model.loan.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface LoanDetailsRepo extends JpaRepository<LoanDetails, Integer> {


public List<LoanDetails> findByLoanMainIdAndDelStatus(int loanId,int j)


@Query(value = " SELECT\n" + "    *\n" + "FROM\n" + "    tbl_loan_details\n" + "WHERE\n" + "    tbl_loan_details.loan_main_id =:loanId AND tbl_loan_details.pay_type = 'SP' AND  tbl_loan_details.del_status=1 \n" + "ORDER BY\n" + "    tbl_loan_details.id\n" + "DESC\n" + "LIMIT 1 ", nativeQuery = true)
public LoanDetails getRecord(int loanId)


@Query(value = " select count('') as count from tbl_salary_calc where emp_id=:empId and calc_month=:month and calc_year=:year", nativeQuery = true)
public int getCount(String year,String month,int empId)


}
import com.ats.hrmgt.model.loan.LoanMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface LoanMainRepo extends JpaRepository<LoanMain, Integer> {


@Query(value = "select * from tbl_loan_main  where   del_status=1 and current_outstanding>0 and emp_id=:empIds  ", nativeQuery = true)
public List<LoanMain> getLoanList(int empIds)


@Transactional
@Modifying
@Query("update LoanMain set  skip_id =:count,skip_login_name =:userId, skip_login_time=:dateTimeUpdate,skip_remarks =:skipStr,loan_repay_end =:repayEnd  WHERE id=:advId")
public int skipLoan(int advId,int userId,int count,String skipStr,String dateTimeUpdate,String repayEnd)


@Transactional
@Modifying
@Query("update LoanMain set login_name =:loginName,login_time=:loginTime ,ex_int2=:newGuarantor WHERE id=:loanId and ex_int2=:oldGuarantor")
public int updateGuarantor2(int loanId,int loginName,String loginTime,int oldGuarantor,int newGuarantor)


@Query(value = " SELECT\n" + "    tbl_loan_main.*\n" + " \n" + "FROM\n" + "     tbl_loan_main\n" + " WHERE\n" + "    tbl_loan_main.del_status = 1  AND  tbl_loan_main.cmp_id =:companyId AND tbl_loan_main.emp_id=:empId AND tbl_loan_main.loan_status='Active' ", nativeQuery = true)
public List<LoanMain> getLoanHistoryDetail(int companyId,int empId)


public LoanMain findById(int i)


@Query(value = " SELECT\n" + "    *\n" + "FROM\n" + "    tbl_loan_main\n" + "WHERE\n" + "    tbl_loan_main.del_status = 1 AND tbl_loan_main.emp_id =:empId \n" + "ORDER BY\n" + "    tbl_loan_main.id\n" + "DESC\n" + "LIMIT 1 ", nativeQuery = true)
public LoanMain getEmpLoanDetail(int empId)


@Transactional
@Modifying
@Query("update LoanMain set login_name =:userId,login_time=:dateTimeUpdate ,current_totpaid=:currentTotpaid ,current_outstanding=:currentOut,loan_repay_end=:closeDate,loan_status=:status WHERE id=:loanId")
public int forecloseLoan(int loanId,int userId,String closeDate,String currentTotpaid,String currentOut,String dateTimeUpdate,String status)


@Transactional
@Modifying
@Query("update LoanMain set login_name =:loginName,login_time=:loginTime ,ex_int1=:newGuarantor WHERE id=:loanId and ex_int1=:oldGuarantor")
public int updateGuarantor1(int loanId,int loginName,String loginTime,int oldGuarantor,int newGuarantor)


@Query(value = " SELECT\n" + "  *\n" + "FROM\n" + "    tbl_loan_main\n" + "WHERE\n" + "    tbl_loan_main.del_status = 1  \n" + "ORDER BY\n" + "    tbl_loan_main.id\n" + "DESC\n" + "LIMIT 1", nativeQuery = true)
public LoanMain getLastApplicationNo()


public List<LoanMain> findByEmpIdAndDelStatus(int empId,int i)


@Transactional
@Modifying
@Query("update LoanMain set login_name =:userId,login_time=:dateTimeUpdate ,current_totpaid=:currentTotpaid ,current_outstanding=:currentOut WHERE id=:loanId")
public int partialLoan(int loanId,int userId,String currentTotpaid,String currentOut,String dateTimeUpdate)


}
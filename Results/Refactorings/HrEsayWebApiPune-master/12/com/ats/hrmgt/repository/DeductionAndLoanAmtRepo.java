import com.ats.hrmgt.model.DeductionAndLoanAMT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface DeductionAndLoanAmtRepo extends JpaRepository<DeductionAndLoanAMT, Integer> {


@Query(value = "select UUID() as id,lm.emp_id,\n" + "                sum(loan_amt)  as amt,\n" + "                DATE_FORMAT(loan_repay_start, '%m-%Y') as month_year\n" + "            from\n" + "                tbl_loan_main lm,\n" + "                m_employees e\n" + "            where\n" + "                loan_repay_start between :fromDate and :toDate \n" + "                and lm.del_status=1\n" + "                and e.emp_id=lm.emp_id\n" + "                and e.location_id=:locId\n" + "            group by\n" + "                lm.emp_id,month_year", nativeQuery = true)
public List<DeductionAndLoanAMT> getLoanAmtList(String fromDate,String toDate,int locId)


@Query(value = "select UUID() as id,lm.emp_id,sum(ld.amount_emi)   as amt  ,DATE_FORMAT(concat(ld.years,'-',ld.months,'-01'), '%m-%Y') as " + "month_year from tbl_loan_main lm,tbl_loan_details ld,m_employees e where lm.id=ld.loan_main_id and DATE_FORMAT(concat(ld.years,'-',ld.months,'-01'), '%Y-%m-%d') " + "between :fromDate and :toDate and e.emp_id=lm.emp_id and e.location_id=:locId group by lm.emp_id,month_year order by emp_id", nativeQuery = true)
public List<DeductionAndLoanAMT> getDeductoinAmtList(String fromDate,String toDate,int locId)


}
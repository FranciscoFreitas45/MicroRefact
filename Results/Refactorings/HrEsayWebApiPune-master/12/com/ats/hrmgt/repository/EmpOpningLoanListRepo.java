import com.ats.hrmgt.model.EmpOpningLoanList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface EmpOpningLoanListRepo extends JpaRepository<EmpOpningLoanList, Integer> {


@Query(value = "select UUID() as id,a.*,(ifnull(b.loan_amt,0) - ifnull(c.paid_amt,0) ) as op_amt,ifnull(d.loan_count,0) as loan_count from ( select e.emp_id,concat(e.first_name,\" \",e.surname ,\" (\",e.emp_code,\")\") as emp_name , DATE_FORMAT(:fromDate, '%m-%Y') as month_year  from m_employees e where e.del_status=1 and e.location_id=:locId) a\n" + "left join (select lm.emp_id, sum(lm.loan_amt)  as loan_amt from tbl_loan_main lm where lm.del_status=1 and DATE_FORMAT(loan_repay_start, '%Y-%m')<DATE_FORMAT(:fromDate, '%Y-%m')  group by lm.emp_id) b on b.emp_id=a.emp_id\n" + "left join (select lm.emp_id, sum(ld.amount_emi)  as paid_amt from tbl_loan_main lm,tbl_loan_details ld where lm.id=ld.loan_main_id and DATE_FORMAT(concat(ld.years,'-',ld.months,'-01'), '%Y-%m')<DATE_FORMAT(:fromDate, '%Y-%m')  group by lm.emp_id) c on c.emp_id=a.emp_id \n" + "left join (select lm.emp_id, count('')  as loan_count from tbl_loan_main lm where loan_repay_start between :fromDate and :toDate and lm.del_status=1   group by lm.emp_id) d on d.emp_id=a.emp_id \n" + "where (ifnull(b.loan_amt,0) - ifnull(c.paid_amt,0) )>0 or loan_count>0\n" + " ", nativeQuery = true)
public List<EmpOpningLoanList> payLoanLedgerReport(String date,String toDate,int locId)


@Query(value = "select\n" + "            UUID() as id,adv.emp_id,\n" + "            concat(e.first_name,\n" + "            \" \",\n" + "            e.surname ,\n" + "            \" (\",\n" + "            e.emp_code,\n" + "            \")\") as emp_name ,\n" + "            '' as month_year,\n" + "            sum(adv.adv_amount)  as op_amt,\n" + "            0 as loan_count \n" + "        from\n" + "        tbl_advance adv ,\n" + "            m_employees e \n" + "        where\n" + "            e.location_id=:locId\n" + "            and adv.emp_id=e.emp_id\n" + "            and ded_month=:month\n" + "            and ded_year=:year\n" + "            and adv.del_status=1\n" + "            and is_ded=1\n" + "        group by e.emp_id\n" + "        order by emp_name", nativeQuery = true)
public List<EmpOpningLoanList> getAdvanceDeductionReport(String month,String year,int locId)


}
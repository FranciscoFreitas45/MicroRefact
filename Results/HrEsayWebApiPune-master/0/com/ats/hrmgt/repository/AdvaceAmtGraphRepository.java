import com.ats.hrmgt.model.AdvaceAmtGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface AdvaceAmtGraphRepository extends JpaRepository<AdvaceAmtGraph, Integer> {


@Query(value = " SELECT\n" + "        UUID() AS id, \n" + "        ROUND(ifnull(SUM(loan.loan_amt),\n" + "        0),\n" + "        2) AS adv_tot,\n" + "        date_format(loan.loan_repay_start,'%m-%Y') as month\n" + "    FROM\n" + "        tbl_loan_main loan,m_employees e      \n" + "    WHERE\n" + "        date_format(loan.loan_repay_start,'%Y-%m')>=date_format(date_sub(:defaltdt,Interval 5 month),'%Y-%m')       \n" + "        AND loan.del_status = 1\n" + "        and TIMESTAMPDIFF(MONTH,loan.loan_repay_start,:defaltdt)>=0\n" + "        and e.emp_id=loan.emp_id\n" + "        and e.location_id=:locId         \n" + "        and e.del_status=1 \n" + "    group by month ", nativeQuery = true)
public List<AdvaceAmtGraph> getLoanMonthAmt(int locId,String defaltdt)


@Query(value = "SELECT\n" + "        UUID() AS id,\n" + "        ROUND(ifnull(SUM(adv.claim_amount),\n" + "        0),\n" + "        2) AS adv_tot,\n" + "        date_format(adv.ca_from_dt,\n" + "        '%m-%Y') as month     \n" + "    FROM\n" + "        claim_apply_header adv,\n" + "        m_employees e           \n" + "    WHERE\n" + "        date_format(adv.ca_from_dt,'%Y-%m')>=date_format(date_sub(:defaltdt,Interval 5 month),'%Y-%m')                \n" + "        AND adv.del_status = 1         \n" + "        and TIMESTAMPDIFF(MONTH,adv.ca_from_dt,:defaltdt)>=0         \n" + "        and e.emp_id=adv.emp_id         \n" + "        and e.location_id=:locId                 \n" + "        and e.del_status=1 \n" + "        and claim_status=3\n" + "    group by\n" + "        month", nativeQuery = true)
public List<AdvaceAmtGraph> getClaimMonthAmt(int locId,String defaltdt)


@Query(value = " SELECT\n" + "        UUID() AS id, \n" + "        ROUND(ifnull(SUM(adv.adv_amount),\n" + "        0),\n" + "        2) AS adv_tot,\n" + "        date_format(adv.adv_date,'%m-%Y') as month\n" + "    FROM\n" + "        tbl_advance adv,m_employees e      \n" + "    WHERE\n" + "        date_format(adv.adv_date,'%Y-%m')>=date_format(date_sub(:defaltdt,Interval 5 month),'%Y-%m')       \n" + "        AND adv.del_status = 1\n" + "        and TIMESTAMPDIFF(MONTH,adv.adv_date,:defaltdt)>=0\n" + "        and e.emp_id=adv.emp_id\n" + "        and e.location_id=:locId        \n" + "        and e.del_status=1\n" + "        and adv.ex_int1=0\n" + "    group by month ", nativeQuery = true)
public List<AdvaceAmtGraph> getAdvanceMonthAmt(int locId,String defaltdt)


@Query(value = "SELECT\n" + "        UUID() AS id,\n" + "        ROUND(ifnull(SUM(adv.pay_rate),\n" + "        0),\n" + "        2) AS adv_tot,\n" + "        date_format(concat(adv.year,'-',adv.month,'-01'),\n" + "        '%m-%Y') as month     \n" + "    FROM\n" + "        tblm_pay_bonus_details adv,\n" + "        m_employees e           \n" + "    WHERE\n" + "        date_format(concat(adv.year,'-',adv.month,'-01'),'%Y-%m')>=date_format(date_sub(:defaltdt,Interval 5 month),'%Y-%m')                \n" + "        AND adv.del_status = 1         \n" + "        and TIMESTAMPDIFF(MONTH,concat(adv.year,'-',adv.month,'-01'),:defaltdt)>=0         \n" + "        and e.emp_id=adv.emp_id         \n" + "        and e.location_id=:locId                 \n" + "        and e.del_status=1     \n" + "    group by\n" + "        month", nativeQuery = true)
public List<AdvaceAmtGraph> getrewardMonthAmt(int locId,String defaltdt)


}
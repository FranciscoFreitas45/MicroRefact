import com.ats.hrmgt.model.GetPayDedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetPayDedListRepo extends JpaRepository<GetPayDedList, Integer> {


@Query(value = "select uuid() as id, emp_id, sum(CASE WHEN current_outstanding<loan_emi_intrest THEN current_outstanding ELSE loan_emi_intrest end ) as amt from tbl_loan_main " + "where :date between loan_repay_start and loan_repay_end and del_status=1 and skip_id=0 and current_outstanding>0 and emp_id in (:empIds) group by emp_id", nativeQuery = true)
public List<GetPayDedList> getLoanList(String date,List<Integer> empIds)


@Query(value = "select uuid() as id,emp_id, round(ded_rate)  as amt from tblm_pay_deduction_details where month=:month and year=:year " + "and del_status=1 and is_deducted=1 and emp_id in (:empIds) ", nativeQuery = true)
public List<GetPayDedList> getPayDedListSaparate(int month,int year,List<Integer> empIds)


@Query(value = "select uuid() as id, emp_id, sum(paid_bonus_amt) as amt from t_bonus_calc where is_bonussheet_finalized=0 and " + "date_format(paid_bonus_date,'%Y-%m')=date_format(:date,'%Y-%m') and emp_id in (:empIds) group by emp_id", nativeQuery = true)
public List<GetPayDedList> getBonusList(String date,List<Integer> empIds)


@Query(value = "select\n" + "        uuid() as id,\n" + "        emp_id,\n" + "        round(pay_rate) as amt \n" + "    from\n" + "        tblm_pay_bonus_details \n" + "    where\n" + "        month=:month \n" + "        and year=:year \n" + "        and del_status=1 \n" + "        and is_paid=1 \n" + "        and emp_id in (:empIds) ", nativeQuery = true)
public List<GetPayDedList> getBonusListSaparate(int month,int year,List<Integer> empIds)


@Query(value = "select uuid() as id,c.emp_id , sum(total_amt) as amt from t_encash c where " + "c.del_status=1 and c.month=:month and year=:year and c.emp_id in (:empIds) group by c.emp_id", nativeQuery = true)
public List<GetPayDedList> getEncashLeaveAmtList(int month,int year,List<Integer> empIds)


@Query(value = "select uuid() as id,emp_id,sum(ded_rate) as amt from tblm_pay_deduction_details where month=:month and year=:year " + "and del_status=1 and is_deducted=0 and emp_id in (:empIds) group by emp_id", nativeQuery = true)
public List<GetPayDedList> getPayDedList(int month,int year,List<Integer> empIds)


@Query(value = "select uuid() as id, emp_id, sum(paid_exgretia_amt) as amt from t_bonus_calc where is_exgretia_finalized=0 and " + "date_format(paid_exgretia_date,'%Y-%m')=date_format(:date,'%Y-%m') and emp_id in (:empIds) and ex_int1=1 group by emp_id", nativeQuery = true)
public List<GetPayDedList> getExgretiaList(String date,List<Integer> empIds)


@Query(value = "select\n" + "        uuid() as id,\n" + "        lm.emp_id,\n" + "        round(ld.amount_emi) as amt \n" + "    from\n" + "        tbl_loan_main lm,\n" + "        tbl_loan_details ld\n" + "    where\n" + "        ld.months=:month\n" + "        and ld.years=:year\n" + "        and lm.del_status=1 \n" + "        and lm.emp_id in (:empIds) and ld.loan_main_id=lm.id\n" + "        and ld.pay_type='SP'", nativeQuery = true)
public List<GetPayDedList> getLoanListSaparate(int month,int year,List<Integer> empIds)


@Query(value = "select uuid() as id, emp_id,  current_outstanding as amt from tbl_loan_main where  del_status=1 " + "and current_outstanding>0 and emp_id in (:empIds) group by emp_id", nativeQuery = true)
public GetPayDedList getLoanListForFullFinal(List<Integer> empIds)


@Query(value = "select\n" + "        uuid() as id,\n" + "        lm.emp_id,\n" + "        sum(ld.amount_emi) as amt \n" + "    from\n" + "        tbl_loan_main lm,\n" + "        tbl_loan_details ld\n" + "    where\n" + "        lm.emp_id in (:empIds)\n" + "        and ld.loan_main_id=lm.id\n" + "        and months=:month\n" + "        and years=:year\n" + "    group by\n" + "        lm.emp_id", nativeQuery = true)
public List<GetPayDedList> getPartialLoanList(int month,int year,List<Integer> empIds)


}
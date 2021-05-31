import com.ats.hrmgt.model.PayableDayAndPresentDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface PayableDayAndPresentDaysRepo extends JpaRepository<PayableDayAndPresentDays, Integer> {


@Query(value = "select uuid() as uuid ,sum(d.payable_days) as payable_days,sum(present_days) as present_days ,e.pl_calc_base from tbl_attt_summary_daily d,m_employees e" + " where STR_TO_DATE(CONCAT(d.year,'-',d.month,'-','01'), '%Y-%m-%d') between :fromDate and :toDate and d.emp_id=:empId and e.emp_id=d.emp_id", nativeQuery = true)
public PayableDayAndPresentDays getPayableDayandPresentDayByEmpId(int empId,String fromDate,String toDate)


}
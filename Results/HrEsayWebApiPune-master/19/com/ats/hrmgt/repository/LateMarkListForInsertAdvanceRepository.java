import com.ats.hrmgt.model.LateMarkListForInsertAdvance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface LateMarkListForInsertAdvanceRepository extends JpaRepository<LateMarkListForInsertAdvance, Integer> {


@Query(value = "select si.emp_id, si.gross_salary as gr_sal, sd.totlate_days, sd.total_days_inmonth from tbl_emp_salary_info si, " + "tbl_attt_summary_daily sd where sd.emp_id=si.emp_id and sd.month=:month and sd.year=:year and totlate_days>0 and sd.emp_id in (:empIds) " + "order by si.emp_id", nativeQuery = true)
public List<LateMarkListForInsertAdvance> getlatemarkList(int month,int year,List<Integer> empIds)


}
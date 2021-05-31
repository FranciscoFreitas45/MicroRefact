import com.ats.hrmgt.model.EmpSalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface EmpSalTypeRepository extends JpaRepository<EmpSalType, Integer> {


@Query(value = "select e.emp_id,es.sal_basis,es.daily_hr,es.monthly_hr_target,es.monthly_minimum_target,es.monthly_ot_hr from m_employees e," + "tbl_emp_salary_info es where e.emp_id in (select  emp_id from tbl_attt_summary_daily where month=:month and year=:year) and es.emp_id=e.emp_id and e.emp_id=:empId", nativeQuery = true)
public List<EmpSalType> empSalTypeList(int month,int year,int empId)


}
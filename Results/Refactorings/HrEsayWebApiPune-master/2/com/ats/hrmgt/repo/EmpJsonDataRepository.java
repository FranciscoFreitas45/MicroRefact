import com.ats.hrmgt.model.EmpJsonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface EmpJsonDataRepository extends JpaRepository<EmpJsonData, Integer> {


@Query(value = "  select \n" + "  e.emp_id,\n" + "  e.emp_code,\n" + "  e.cmp_code,\n" + "  e.emp_type,\n" + "  e.depart_id,\n" + "  e.designation_id,\n" + "  e.location_id,\n" + "  e.first_name,\n" + "  e.middle_name,\n" + "  e.surname,\n" + "  e.is_emp,\n" + "  e.current_shiftid,\n" + "  e.emp_category,\n" + "  si.salary_type_id,\n" + "  si.sal_basis,\n" + "  si.cmp_joining_date,\n" + "  e.holiday_category as holiday_cat_id,\n" + "  e.weekend_category as week_end_cat_id,si.monthly_hr_target,si.monthly_minimum_target,si.monthly_ot_hr,si.daily_hr from m_employees e,tbl_emp_salary_info si where e.emp_id=si.emp_id and e.del_status=1", nativeQuery = true)
public List<EmpJsonData> jsonDataList()


}
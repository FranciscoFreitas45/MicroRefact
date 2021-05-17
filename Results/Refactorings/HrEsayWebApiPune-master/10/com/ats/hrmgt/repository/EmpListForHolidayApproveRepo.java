import com.ats.hrmgt.model.EmpListForHolidayApprove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface EmpListForHolidayApproveRepo extends JpaRepository<EmpListForHolidayApprove, Integer> {


@Query(value = " select\n" + "        oh.id,\n" + "        oh.emp_id,\n" + "        oh.holidate,\n" + "        oh.del_status,\n" + "        oh.holiday_id,\n" + "        oh.status,\n" + "        oh.remark,\n" + "        e.emp_code,\n" + "        concat(e.first_name,\n" + "        ' ',\n" + "        e.surname) as emp_name,\n" + "        h.ex_var2 as holiday_name \n" + "    from\n" + "        t_optional_holiday oh,\n" + "        m_employees e,\n" + "        m_holiday h \n" + "    where\n" + "        oh.status in (:sts) \n" + "        and e.emp_id=oh.emp_id \n" + "        and e.emp_id=:empId \n" + "        and h.holiday_id=oh.holiday_id\n" + "        and oh.year_id=:yearId", nativeQuery = true)
public List<EmpListForHolidayApprove> getHistoryOptionalHoliday(int empId,int yearId,List<Integer> sts)


@Query(value = " select\n" + "        oh.id,\n" + "        oh.emp_id,\n" + "        oh.holidate,\n" + "        oh.del_status,\n" + "        oh.holiday_id,\n" + "        oh.status,\n" + "        oh.remark,\n" + "        e.emp_code,\n" + "        concat(e.first_name,\n" + "        ' ',\n" + "        e.surname) as emp_name,\n" + "        h.ex_var2 as holiday_name \n" + "    from\n" + "        t_optional_holiday oh,\n" + "        m_employees e,\n" + "        m_holiday h \n" + "    where\n" + "        oh.status in (:sts) \n" + "        and e.emp_id=oh.emp_id and h.holiday_id=oh.holiday_id", nativeQuery = true)
public List<EmpListForHolidayApprove> getOptionalHolidayApprovalList(int sts)


}
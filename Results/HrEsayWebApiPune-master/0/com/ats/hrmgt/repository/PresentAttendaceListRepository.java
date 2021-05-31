import com.ats.hrmgt.model.PresentAttendaceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface PresentAttendaceListRepository extends JpaRepository<PresentAttendaceList, Integer> {


@Query(value = " select UUID() as id,e.emp_id,concat(e.first_name,' ',e.surname) as emp_name,d.att_status,late_min,d.lv_sumup_id,dept.name as dept_name from tbl_attt_daily_daily d, " + "m_employees e,m_department dept where d.att_date = :date and d.lv_sumup_id in (5,13,14,15,20,21,18,22,7,11,6,12) and e.emp_id=d.emp_id and e.location_id in (:locId) and dept.depart_id=e.depart_id", nativeQuery = true)
public List<PresentAttendaceList> presentList(int locId,String date)


@Query(value = " select UUID() as id,e.emp_id,concat(e.first_name,' ',e.surname) as emp_name,d.att_status,late_min,d.lv_sumup_id,dept.name as dept_name from tbl_attt_daily_daily d, " + "m_employees e,m_department dept where d.att_date = :date and late_mark=1 and e.emp_id=d.emp_id and e.location_id in (:locId) and dept.depart_id=e.depart_id", nativeQuery = true)
public List<PresentAttendaceList> lateListList(int locId,String date)


}
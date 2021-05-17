import com.ats.hrmgt.model.DailyAttendaceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface DailyAttendaceReportRepository extends JpaRepository<DailyAttendaceReport, Integer> {


@Query(value = "select UUID() as id,ad.emp_code,ad.emp_name,d.name,ad.att_date,ad.att_status,CONCAT(FLOOR( ad.working_hrs/60), ':', LPAD(MOD( ad.working_hrs, 60), 2, '0')) " + "as working_hrs ,ad.in_time,ad.emp_id,ad.current_shiftid,ad.late_min,ad.current_shiftname,ad.out_time,ad.atts_sd_show, " + "CONCAT(FLOOR( ad.ot_hr/60), ':', LPAD(MOD( ad.ot_hr, 60), 2, '0')) as ot_hr from tbl_attt_daily_daily ad ,m_employees e,m_department d where " + "ad.att_date between :fromDate and :toDate  and ad.emp_id=e.emp_id and e.location_id in (:locId) and d.depart_id=e.depart_id " + "order by att_date asc,d.name asc,ad.emp_name asc", nativeQuery = true)
public List<DailyAttendaceReport> dailyAttendanceListAlllocId(String fromDate,String toDate,int locId)


}
import com.ats.hrmgt.model.SelfAttendanceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface SelfAttendanceDetailRepository extends JpaRepository<SelfAttendanceDetail, Integer> {


@Query(value = "SELECT\n" + "        UUID() as id , \n" + "        emp_code ,\n" + "        emp_name ,\n" + "        att_date ,\n" + "        atts_sd_show as att_status ,\n" + "        lv_sumup_id    \n" + "    FROM\n" + "        tbl_attt_daily_daily  \n" + "    WHERE\n" + "        att_date between :fromDate and :toDate \n" + "        and emp_id=:empId", nativeQuery = true)
public List<SelfAttendanceDetail> getSelfAttendaceForDashboard(String fromDate,int empId,String toDate)


}
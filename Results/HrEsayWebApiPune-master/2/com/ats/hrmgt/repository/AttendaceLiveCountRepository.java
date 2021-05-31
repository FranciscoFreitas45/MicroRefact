import com.ats.hrmgt.model.AttendaceLiveCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AttendaceLiveCountRepository extends JpaRepository<AttendaceLiveCount, Integer> {


@Query(value = "SELECT\n" + "               dl.lv_sumup_id,\n" + "               dl.atts_sd_show,\n" + "                COUNT('') as cnt \n" + "            FROM\n" + "                tbl_attt_daily_daily dl,\n" + "                m_employees e\n" + "            WHERE\n" + "                dl.att_date=:fromDate\n" + "                and e.emp_id=dl.emp_id\n" + "                and e.location_id in (:locId)\n" + "            group by\n" + "                dl.lv_sumup_id", nativeQuery = true)
public List<AttendaceLiveCount> presentAttendaceLiveCount(String fromDate,List<Integer> locId)


}
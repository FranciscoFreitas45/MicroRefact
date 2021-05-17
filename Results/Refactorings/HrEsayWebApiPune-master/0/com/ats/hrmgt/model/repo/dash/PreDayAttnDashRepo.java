import com.ats.hrmgt.model.dashboard.PreDayAttnDash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface PreDayAttnDashRepo extends JpaRepository<PreDayAttnDash, String> {


@Query(value = "SELECT\n" + "    UUID() AS uni_key,(\n" + "    SELECT\n" + "        COUNT(td1.id)\n" + "    FROM\n" + "        tbl_attt_daily_daily td1\n" + "    WHERE\n" + "        td1.att_date = :currDate AND td1.lv_sumup_id = 11\n" + ") AS pre_emp,\n" + "(\n" + "    SELECT\n" + "        COUNT(td2.id)\n" + "    FROM\n" + "        tbl_attt_daily_daily td2\n" + "    WHERE\n" + "        td2.att_date = :currDate AND td2.lv_sumup_id = 22\n" + ") AS absent_emp,\n" + "(\n" + "    SELECT\n" + "        COUNT(td3.id)\n" + "    FROM\n" + "        tbl_attt_daily_daily td3\n" + "    WHERE\n" + "        td3.att_date = :currDate AND td3.lv_sumup_id IN(12, 14, 16, 17, 18, 19)\n" + ") AS wo_emp,\n" + "(\n" + "    SELECT\n" + "        COUNT(td4.id)\n" + "    FROM\n" + "        tbl_attt_daily_daily td4\n" + "    WHERE\n" + "        td4.att_date = :currDate AND td4.lv_sumup_id = 7\n" + ") AS lv_emp,\n" + "'NA' AS attn_date", nativeQuery = true)
public PreDayAttnDash getAttendance(String currDate)


}
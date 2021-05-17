import com.ats.hrmgt.model.DailyRecordForCompOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface DailyRecordForCompOffRepository extends JpaRepository<DailyRecordForCompOff, Integer> {


@Query(value = "SELECT\n" + "        id,\n" + "        att_date,\n" + "        emp_id,\n" + "        att_status,\n" + "        lv_sumup_id     \n" + "    from\n" + "        tbl_attt_daily_daily dl       \n" + "    where\n" + "        emp_id =:empId \n" + "        and lv_sumup_id in (\n" + "            13,14,18\n" + "        ) \n" + "        and att_date between :fromDate and :toDate \n" + "        and multiple_entries=0\n" + "        and atsumm_uid=1 \n" + "    order by\n" + "        id asc", nativeQuery = true)
public List<DailyRecordForCompOff> dailyrecordlistforcompoff(String fromDate,String toDate,int empId)


}
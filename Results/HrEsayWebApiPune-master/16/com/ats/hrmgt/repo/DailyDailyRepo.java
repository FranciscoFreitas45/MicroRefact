import com.ats.hrmgt.model.DailyDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DailyDailyRepo extends JpaRepository<DailyDaily, Integer> {


@Query(value = " SELECT id,emp_id,DATE_FORMAT(att_date,'%d-%m-%Y') as att_date, COALESCE( (CONCAT(FLOOR(SUM(ot_hr) / 60), ':',  " + "	 		 	 LPAD(MOD(SUM(ot_hr),60), 2, '0'))),0) AS ot_hr ,DAYNAME(att_date) as day_name " + " FROM tbl_attt_daily_daily WHERE att_date BETWEEN :fromDate and :toDate and emp_id=:empId and is_fixed=0 \n" + "                and rec_status='O' group by att_date ", nativeQuery = true)
public List<DailyDaily> getData(String fromDate,String toDate,int empId)


}
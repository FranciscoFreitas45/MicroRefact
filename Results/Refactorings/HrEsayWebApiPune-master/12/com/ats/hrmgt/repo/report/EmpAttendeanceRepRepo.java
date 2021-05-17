import com.ats.hrmgt.model.report.EmpAttendeanceRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface EmpAttendeanceRepRepo extends JpaRepository<EmpAttendeanceRep, String> {


@Query(value = "SELECT\n" + "        UUID() as unid,\n" + "        (     SELECT\n" + "            COUNT('')              \n" + "        FROM\n" + "            tbl_attt_daily_daily, m_employees e\n" + "        WHERE\n" + "            tbl_attt_daily_daily.lv_sumup_id in (5,13,14,15,20,21,18)     \n" + "            AND tbl_attt_daily_daily.att_date BETWEEN :fromDate and :toDate and e.emp_id=tbl_attt_daily_daily.emp_id and e.location_id=:locId) AS emp_present,\n" + "        (     SELECT\n" + "            COUNT('')             \n" + "        FROM\n" + "            tbl_attt_daily_daily,m_employees e              \n" + "        WHERE\n" + "            tbl_attt_daily_daily.lv_sumup_id = 12     \n" + "            AND tbl_attt_daily_daily.att_date BETWEEN :fromDate and :toDate and e.emp_id=tbl_attt_daily_daily.emp_id and e.location_id=:locId) AS week_off,\n" + "        (     SELECT\n" + "            COUNT('')         \n" + "        FROM\n" + "            tbl_attt_daily_daily,m_employees e               \n" + "        WHERE\n" + "            tbl_attt_daily_daily.lv_sumup_id in ( 7,11)    \n" + "            AND  tbl_attt_daily_daily.att_date BETWEEN :fromDate and :toDate and e.emp_id=tbl_attt_daily_daily.emp_id and e.location_id=:locId) AS paid_leave,\n" + "        (     SELECT\n" + "           COUNT('')        \n" + "        FROM\n" + "            tbl_attt_daily_daily,m_employees e               \n" + "        WHERE\n" + "            tbl_attt_daily_daily.lv_sumup_id IN(\n" + "                22\n" + "            )     \n" + "            AND tbl_attt_daily_daily.att_date BETWEEN :fromDate and :toDate and e.emp_id=tbl_attt_daily_daily.emp_id and e.location_id=:locId) AS unpaid_leave", nativeQuery = true)
public List<EmpAttendeanceRep> getSpecEmpAdvForReport(String fromDate,String toDate,int locId)


}
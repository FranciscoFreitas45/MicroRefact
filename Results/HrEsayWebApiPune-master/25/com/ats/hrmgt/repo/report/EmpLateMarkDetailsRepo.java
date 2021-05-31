import com.ats.hrmgt.model.report.EmpLateMarkDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface EmpLateMarkDetailsRepo extends JpaRepository<EmpLateMarkDetails, Integer> {


@Query(value = "SELECT\n" + "        UUID() AS id,\n" + "        atd.emp_id,\n" + "        atd.emp_code,\n" + "        atd.emp_name AS emp_name,\n" + "        desg.name AS designation,\n" + "        atd.totlate_mins AS late_hr,\n" + "        MONTHNAME(STR_TO_DATE(atd.month,\n" + "        '%m')) AS MONTH \n" + "    FROM\n" + "        tbl_attt_summary_daily atd,\n" + "        m_employees emp,\n" + "        m_designation desg \n" + "    WHERE\n" + "        emp.emp_id = atd.emp_id  \n" + "        AND emp.designation_id = desg.desig_id \n" + "        AND atd.year BETWEEN :year AND :toyear\n" + "        AND atd.month BETWEEN :month AND :tomonth\n" + "        AND emp.del_status = 1\n" + "        and emp.location_id=:locId\n" + "        and atd.totlate_mins>0\n" + "    ORDER BY\n" + "        atd.month", nativeQuery = true)
public List<EmpLateMarkDetails> getEmpLateMarkSummaryReport(int locId,String month,String year,String tomonth,String toyear)


@Query(value = "SELECT\n" + "        UUID() AS id,\n" + "        atd.emp_id,\n" + "        atd.emp_code,\n" + "        atd.emp_name AS emp_name,\n" + "        desg.name AS designation,\n" + "        atd.late_min AS late_hr,\n" + "        DATE_FORMAT(atd.att_date,'%d-%m-%Y') AS MONTH\n" + "    FROM\n" + "        tbl_attt_daily_daily atd,\n" + "        m_employees emp,\n" + "        m_designation desg \n" + "    WHERE\n" + "        emp.emp_id = atd.emp_id \n" + "        AND emp.designation_id = desg.desig_id \n" + "        AND atd.att_date BETWEEN :fromDate AND :toDate AND emp.del_status = 1  and atd.late_mark=1 and emp.location_id=:locId\n" + "    ORDER BY\n" + "        atd.emp_id,atd.att_date", nativeQuery = true)
public List<EmpLateMarkDetails> getEmpLateMarkDetailReport(int locId,String fromDate,String toDate)


@Query(value = "SELECT\n" + "UUID() as id,\n" + "    atd.emp_id,\n" + "    atd.emp_code,\n" + "    atd.emp_name AS emp_name,\n" + "    desg.name AS designation,\n" + "    atd.totlate_mins AS late_hr,\n" + "    MONTHNAME(STR_TO_DATE(atd.month, '%m')) AS MONTH\n" + "FROM\n" + "    tbl_attt_summary_daily atd,\n" + "    m_employees emp,\n" + "    m_designation desg\n" + "WHERE\n" + "    emp.emp_id = atd.emp_id AND atd.emp_id=:empId AND emp.designation_id = desg.desig_id AND\n" + "    DATE_FORMAT(\n" + "        CONCAT(atd.year, '-', atd.month, '-01'),\n" + "        '%Y-%m-%d'\n" + "    ) BETWEEN DATE_FORMAT(\n" + "        CONCAT(:year, '-', :month, '-01'),\n" + "        '%Y-%m-%d'\n" + "    ) AND DATE_FORMAT(\n" + "        CONCAT(:toyear, '-', :tomonth, '-', :daysInMonth),\n" + "        '%Y-%m-%d'\n" + "    ) AND atd.totlate_days > 0 AND emp.del_status = 1\n" + "ORDER BY\n" + "    atd.month", nativeQuery = true)
public List<EmpLateMarkDetails> getEmpLateMarkDetailReportByEmpId(String month,String year,String tomonth,String toyear,int empId,int daysInMonth)


}
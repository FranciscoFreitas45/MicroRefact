import com.ats.hrmgt.model.report.EmpOtReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface EmpOtRegRepo extends JpaRepository<EmpOtReg, Integer> {


@Query(value = "SELECT\n" + "        UUID() AS id,\n" + "        atd.emp_id,\n" + "        atd.emp_code,\n" + "        atd.emp_name AS emp_name,\n" + "        desg.name AS designation,\n" + "        CONCAT(FLOOR(atd.ot_hr/60),\n" + "        ':',\n" + "        LPAD(MOD(atd.ot_hr,\n" + "        60),\n" + "        2,\n" + "        '0'))   AS ot_hr ,\n" + "        atd.att_date AS date,\n" + "        'NA' AS MONTH,\n" + "        atd.ot_hr as ot_min\n" + "    FROM\n" + "        tbl_attt_daily_daily  atd           \n" + "    inner join\n" + "        m_employees emp              \n" + "            on atd.emp_id  = emp.emp_id and emp.location_id=:locId          \n" + "    inner join\n" + "        m_designation desg              \n" + "            on emp.designation_id  = desg.desig_id            \n" + "    inner join\n" + "        tbl_mst_emp_types  emptype              \n" + "            on emp.emp_type  = emptype.emp_type_id               \n" + "            and ot_applicable='Yes'      \n" + "    WHERE\n" + "        atd.att_date BETWEEN :fromDate  AND :toDate         \n" + "        AND emp.del_status = 1 and atd.ot_hr>0          \n" + "    GROUP BY\n" + "        atd.emp_id ,\n" + "        atd.att_date      \n" + "    ORDER BY\n" + "        atd.emp_id,\n" + "        atd.att_date", nativeQuery = true)
public List<EmpOtReg> getEmpOtDetails(int locId,String fromDate,String toDate)


@Query(value = " SELECT\n" + "        UUID() AS id,\n" + "        atd.emp_id,\n" + "        atd.emp_code,\n" + "        atd.emp_name AS emp_name,\n" + "        desg.name AS designation,\n" + "        CONCAT(FLOOR(SUM(atd.tot_othr)/60),\n" + "        ':',\n" + "        LPAD(MOD(SUM(atd.tot_othr),\n" + "        60),\n" + "        2,\n" + "        '0'))\n" + "         AS ot_hr,\n" + "        MONTHNAME(STR_TO_DATE(atd.month,\n" + "        '%m')) AS MONTH,\n" + "        0001-01-01 AS date," + "SUM(atd.tot_othr) as ot_min \n" + "    FROM\n" + "        tbl_attt_summary_daily atd      \n" + "    inner join\n" + "        m_employees emp \n" + "            on atd.emp_id  = emp.emp_id and emp.location_id=:locId      \n" + "    inner join\n" + "        m_designation desg \n" + "            on emp.designation_id  = desg.desig_id       \n" + "    inner join\n" + "        tbl_mst_emp_types  emptype \n" + "            on emp.emp_type  = emptype.emp_type_id  \n" + "            and ot_applicable='Yes' \n" + "    WHERE\n" + "        atd.year BETWEEN :year AND :toyear \n" + "        AND atd.month BETWEEN :month AND :tomonth \n" + "        AND emp.del_status = 1  \n" + "    GROUP BY\n" + "        atd.emp_id ,\n" + "        atd.month \n" + "    ORDER BY\n" + "        atd.emp_id,\n" + "        atd.month", nativeQuery = true)
public List<EmpOtReg> getEmpOtSummary(int locId,String month,String year,String tomonth,String toyear)


}
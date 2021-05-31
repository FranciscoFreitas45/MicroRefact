import com.ats.hrmgt.model.SummaryDailyAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface SummaryDailyAttendanceRepository extends JpaRepository<SummaryDailyAttendance, Integer> {


@Query(value = "select * from tbl_attt_summary_daily where month>=:monthFrom  and year=:yearFrom AND month<=:monthTo  and year=:yearTo  and emp_id=:empId", nativeQuery = true)
public List<SummaryDailyAttendance> summaryDailyAttendanceList(int empId,int monthFrom,int monthTo,String yearFrom,String yearTo)


@Query(value = "select\n" + "        sd.* \n" + "    from\n" + "         tbl_attt_summary_daily sd \n" + "    where\n" + "        sd.month=:month\n" + "        and sd.year=:year\n" + "        and   sd.total_days_inmonth=(\n" + "            SELECT\n" + "                count(*) \n" + "            FROM\n" + "                tbl_attt_daily_daily \n" + "            where\n" + "                YEAR(att_date) =sd.year \n" + "                and MONTH(att_date) =sd.month \n" + "                and sd.emp_id=tbl_attt_daily_daily.emp_id \n" + "                and is_fixed=0 \n" + "                and rec_status='o'\n" + "        )", nativeQuery = true)
public List<SummaryDailyAttendance> summaryDailyAttendanceListAll(int month,int year)


public SummaryDailyAttendance findByCompanyIdAndEmpId(int companyId,int empId)


@Query(value = "SELECT\n" + "        d.* \n" + "    FROM\n" + "        `tbl_attt_summary_daily` d,\n" + "        m_employees e\n" + "    WHERE\n" + "          d.month BETWEEN :fmonth AND :lmonth \n" + "        AND d.year BETWEEN :fyear AND :lyear\n" + "        and e.emp_id=d.emp_id\n" + "        and e.location_id=:locId", nativeQuery = true)
public List<SummaryDailyAttendance> findAllByCompanyId(int locId,String fmonth,String fyear,String lmonth,String lyear)


@Query(value = "select * from tbl_attt_summary_daily where month=:month and year=:year and emp_id=:empId", nativeQuery = true)
public SummaryDailyAttendance summaryDailyAttendanceList1(String month,String year,int empId)


@Query(value = "SELECT * FROM `tbl_attt_summary_daily` WHERE emp_id=:empId AND company_id=:companyId AND month BETWEEN :fmonth AND :lmonth AND year BETWEEN :fyear AND :lyear", nativeQuery = true)
public List<SummaryDailyAttendance> findAllByCompanyIdAndEmpId(int companyId,int empId,String fmonth,String fyear,String lmonth,String lyear)


}
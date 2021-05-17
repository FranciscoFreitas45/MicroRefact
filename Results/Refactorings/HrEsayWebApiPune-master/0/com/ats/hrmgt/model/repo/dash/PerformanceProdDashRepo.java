import com.ats.hrmgt.model.dashboard.PerformanceProdDash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface PerformanceProdDashRepo extends JpaRepository<PerformanceProdDash, String> {


@Query(value = "SELECT\n" + "    UUID() AS uni_key,(\n" + "        (\n" + "            (\n" + "                (\n" + "                    tbl_emp_salary_info.gross_salary\n" + "                ) / tbl_attt_summary_daily.total_days_inmonth\n" + "            ) / 8\n" + "        ) *(\n" + "            (\n" + "                tbl_attt_summary_daily.weekly_off_present + tbl_attt_summary_daily.weekly_off_holiday_off_present + tbl_attt_summary_daily.holiday_present +(\n" + "                    tbl_attt_summary_daily.weekly_off_holiday_off_present_halfday / 2\n" + "                )\n" + "            )\n" + "        )\n" + "    ) AS prod_amt,\n" + "    (\n" + "        (\n" + "            tbl_attt_summary_daily.weekly_off_present + tbl_attt_summary_daily.weekly_off_holiday_off_present + tbl_attt_summary_daily.holiday_present +(\n" + "                tbl_attt_summary_daily.weekly_off_holiday_off_present_halfday / 2\n" + "            )\n" + "        )\n" + "    ) AS prod_days\n" + "FROM\n" + "    tbl_emp_salary_info,\n" + "    tbl_attt_summary_daily,\n" + "    m_employees,\n" + "    tbl_mst_emp_types\n" + "WHERE\n" + "    tbl_attt_summary_daily.emp_id = m_employees.emp_id AND tbl_attt_summary_daily.month = :month AND tbl_attt_summary_daily.year = :year AND tbl_emp_salary_info.emp_id = m_employees.emp_id AND m_employees.emp_type = tbl_mst_emp_types.emp_type_id AND tbl_mst_emp_types.wh_work = 'OT' AND m_employees.emp_id = :empId", nativeQuery = true)
public PerformanceProdDash getPerformanceDetails(String year,String month,int empId)


@Query(value = "\n" + "SELECT\n" + "    UUID() AS uni_key,(\n" + "        (\n" + "            (\n" + "                (\n" + "                    tbl_emp_salary_info.gross_salary\n" + "                ) / tbl_attt_summary_daily.total_days_inmonth\n" + "            ) / 8\n" + "        ) *(\n" + "            tbl_attt_summary_daily.tot_othr / 60\n" + "        )\n" + "    ) AS prod_amt,\n" + "    (\n" + "        tbl_attt_summary_daily.tot_othr / 60\n" + "    ) AS prod_days\n" + "FROM\n" + "    tbl_emp_salary_info,\n" + "    tbl_attt_summary_daily,\n" + "    m_employees,\n" + "    tbl_mst_emp_types\n" + "WHERE\n" + "    tbl_attt_summary_daily.emp_id = m_employees.emp_id AND tbl_attt_summary_daily.month =:month AND tbl_attt_summary_daily.year =:year AND tbl_emp_salary_info.emp_id = m_employees.emp_id AND m_employees.emp_type = tbl_mst_emp_types.emp_type_id AND tbl_mst_emp_types.ot_applicable = 'Yes' AND m_employees.emp_id = :empId\n" + "", nativeQuery = true)
public PerformanceProdDash getProdDetails(String year,String month,int empId)


}
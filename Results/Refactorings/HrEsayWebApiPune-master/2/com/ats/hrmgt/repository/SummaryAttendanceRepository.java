import com.ats.hrmgt.model.SummaryAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface SummaryAttendanceRepository extends JpaRepository<SummaryAttendance, Integer> {


@Query(value = "select ds.id,ds.emp_id,ds.emp_code,ds.emp_name,working_days,present_days,weekly_off,paid_holiday,paid_leave,legal_strike,lay_off,unpaid_holiday," + "unpaid_leave,absent_days,payable_days,ncp_days,CONCAT(FLOOR(totlate_mins/60),'.',LPAD(MOD(totlate_mins,60), 2, '0')) as totlate_mins,totlate_days," + "CONCAT(FLOOR(totout_mins/60),'.',LPAD(MOD(totout_mins,60), 2, '0')) as totout_mins,CONCAT(FLOOR(totworking_hrs/60),'.',LPAD(MOD(totworking_hrs,60), 2, '0')) " + "as totworking_hrs,CONCAT(FLOOR(totot_hrs/60),'.',LPAD(MOD(totot_hrs,60), 2, '0')) as totot_hrs,CONCAT(FLOOR(tot_othr/60),'.',LPAD(MOD(tot_othr,60), 2, '0')) " + "as tot_othr,tot_late,hdpresent_hdleave,es.sal_basis,ds.total_days_inmonth from tbl_attt_summary_daily ds,tbl_emp_salary_info es where month=:month " + "and year=:year and es.emp_id=ds.emp_id and ds.emp_id=:empId", nativeQuery = true)
public SummaryAttendance summaryDailyAttendanceListAll(int month,int year,int empId)


@Query(value = "select ds.id,ds.emp_id,ds.emp_code,concat(e.first_name,' ',e.surname) as emp_name,working_days,present_days,weekly_off,paid_holiday,paid_leave,legal_strike,lay_off,unpaid_holiday," + "unpaid_leave,absent_days,payable_days,ncp_days, totlate_mins as totlate_mins,totlate_days," + "CONCAT(FLOOR(totout_mins/60),'.',LPAD(MOD(totout_mins,60), 2, '0')) as totout_mins,CONCAT(FLOOR(totworking_hrs/60),'.',LPAD(MOD(totworking_hrs,60), 2, '0')) " + "as totworking_hrs,CONCAT(FLOOR(totot_hrs/60),'.',LPAD(MOD(totot_hrs,60), 2, '0')) as totot_hrs,CONCAT(FLOOR(tot_othr/60),'.',LPAD(MOD(tot_othr,60), 2, '0')) " + "as tot_othr,tot_late,hdpresent_hdleave,es.sal_basis,ds.total_days_inmonth from tbl_attt_summary_daily ds,tbl_emp_salary_info es,m_employees e where month=:month " + "and year=:year and es.emp_id=ds.emp_id and e.emp_id=ds.emp_id and e.location_id in (:locId)", nativeQuery = true)
public List<SummaryAttendance> summaryDailyAttendanceListAlllocId(int month,int year,List<Integer> locId)


@Query(value = "select ds.id, ds.emp_id, ds.emp_code, ds.emp_name, working_days, present_days, weekly_off, paid_holiday, paid_leave, legal_strike, lay_off, " + "unpaid_holiday, unpaid_leave, absent_days, payable_days, ncp_days, totlate_mins as totlate_mins, totlate_days, CONCAT(FLOOR(totout_mins/60), '.', " + "LPAD(MOD(totout_mins, 60), 2, '0')) as totout_mins, CONCAT(FLOOR(totworking_hrs/60), '.', LPAD(MOD(totworking_hrs, 60), 2, '0')) as totworking_hrs, " + "CONCAT(FLOOR(totot_hrs/60), '.', LPAD(MOD(totot_hrs, 60), 2, '0')) as totot_hrs, CONCAT(FLOOR(tot_othr/60), '.', LPAD(MOD(tot_othr, 60), 2, '0')) as tot_othr, " + "tot_late, hdpresent_hdleave, es.sal_basis, ds.total_days_inmonth from tbl_attt_summary_daily ds, tbl_emp_salary_info es, m_employees emp " + "where month=:month and year=:year and es.emp_id=ds.emp_id and emp.emp_id=ds.emp_id and depart_id in (:deptId) order by weekly_off asc,emp_name asc", nativeQuery = true)
public List<SummaryAttendance> getCountofWeeklyOff(int month,int year,List<Integer> deptId)


@Query(value = "select ds.id,ds.emp_id,ds.emp_code,ds.emp_name,working_days,present_days,weekly_off,paid_holiday,paid_leave,legal_strike,lay_off,unpaid_holiday," + "unpaid_leave,absent_days,payable_days,ncp_days, totlate_mins as totlate_mins,totlate_days," + "CONCAT(FLOOR(totout_mins/60),'.',LPAD(MOD(totout_mins,60), 2, '0')) as totout_mins,CONCAT(FLOOR(totworking_hrs/60),'.',LPAD(MOD(totworking_hrs,60), 2, '0')) " + "as totworking_hrs,CONCAT(FLOOR(totot_hrs/60),'.',LPAD(MOD(totot_hrs,60), 2, '0')) as totot_hrs,CONCAT(FLOOR(tot_othr/60),'.',LPAD(MOD(tot_othr,60), 2, '0')) " + "as tot_othr,tot_late,hdpresent_hdleave,es.sal_basis,ds.total_days_inmonth from tbl_attt_summary_daily ds,tbl_emp_salary_info es,leave_authority la where month=:month " + "and year=:year and es.emp_id=ds.emp_id and la.emp_id=ds.emp_id and la.ini_auth_emp_id=:userId", nativeQuery = true)
public List<SummaryAttendance> summaryDailyAttendanceListForHod(int month,int year,int userId)


}
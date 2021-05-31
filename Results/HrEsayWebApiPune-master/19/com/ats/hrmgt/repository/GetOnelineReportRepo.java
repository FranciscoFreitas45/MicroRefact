import com.ats.hrmgt.model.GetOnelineReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetOnelineReportRepo extends JpaRepository<GetOnelineReport, Integer> {


@Query(value = "select\n" + "        concat(e.first_name,\n" + "        ' ',\n" + "        e.surname) as name,\n" + "        et.name as emp_type_name,\n" + "        e.sub_cmp_id,\n" + "        d.name as depart_name,\n" + "        dg.name as design_name,\n" + "        sc.*,\n" + "        dd.payable_days,\n" + "        dd.present_days,\n" + "        dd.weekly_off,\n" + "        dd.paid_holiday,\n" + "        dd.paid_leave,\n" + "        dd.unpaid_leave,\n" + "        dd.absent_days,\n" + "        ei.email,\n" + "        ei.dob,\n" + "        UPPER(ei.gender) as gender,\n" + "        si.cmp_joining_date,\n" + "        bi.acc_no,\n" + "        e.esic_no,\n" + "        e.uan\n" + "    from\n" + "        tbl_salary_calc sc,\n" + "        m_employees e,\n" + "        tbl_mst_emp_types et,\n" + "        m_department d,\n" + "        m_designation dg,\n" + "        tbl_attt_summary_daily dd,\n" + "        tbl_emp_info ei,\n" + "        tbl_emp_salary_info si,\n" + "        tbl_emp_bank_info bi\n" + "    where\n" + "        sc.calc_month=:month          \n" + "        and calc_year=:year          \n" + "        and e.emp_id=sc.emp_id          \n" + "        and et.emp_type_id=sc.emp_type          \n" + "        and d.depart_id=sc.depart_id          \n" + "        and dg.desig_id=e.designation_id \n" + "        and ei.emp_id=e.emp_id         \n" + "        and dd.id=sc.att_sum_id \n" + "        and e.location_id in (:locId) and si.emp_id=e.emp_id\n" + "        and bi.emp_id=e.emp_id\n" + "    order by\n" + "        e.emp_id asc", nativeQuery = true)
public List<GetOnelineReport> getPayOneLineReport(int month,int year,List<Integer> locId)


}
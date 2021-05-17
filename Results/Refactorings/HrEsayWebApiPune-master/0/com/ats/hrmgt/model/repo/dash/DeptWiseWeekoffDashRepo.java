import com.ats.hrmgt.model.dashboard.DeptWiseWeekoffDash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface DeptWiseWeekoffDashRepo extends JpaRepository<DeptWiseWeekoffDash, Integer> {


@Query(value = "SELECT\n" + "     UUID() as id,payDed.type_name AS name_sd,\n" + "    payDed.ded_type_id AS depart_id,\n" + "    SUM(dedDet.ded_rate) AS emp_count\n" + "FROM\n" + "    tblm_pay_deduction_details dedDet,\n" + "    tbl_pay_deduction payDed\n" + "WHERE\n" + "    dedDet.emp_id =:empId AND dedDet.ded_type_id = payDed.ded_type_id AND dedDet.del_status = 1 and is_deducted=0\n" + "GROUP BY\n" + "    payDed.ded_type_id", nativeQuery = true)
public List<DeptWiseWeekoffDash> getDedTypewiseAmt(int empId)


@Query(value = "SELECT\n" + "    UUID() as id,dep.depart_id,\n" + "    dep.name_sd,\n" + "    COUNT('') AS emp_count\n" + "FROM\n" + "    tbl_attt_daily_daily tad\n" + "INNER JOIN m_employees emp ON\n" + "    tad.emp_id = emp.emp_id\n" + "INNER JOIN m_department dep ON\n" + "    emp.depart_id = dep.depart_id\n" + "WHERE\n" + "    tad.att_date = :currDate AND tad.lv_sumup_id IN(12, 14, 16, 17, 18, 19)\n" + "GROUP BY\n" + "    dep.depart_id", nativeQuery = true)
public List<DeptWiseWeekoffDash> getAttendance(String currDate)


@Query(value = "SELECT\n" + "     UUID() as id,dep.name_sd,\n" + "    COUNT('') AS emp_count\n" + "FROM\n" + "    tbl_attt_daily_daily tad\n" + "INNER JOIN m_employees emp ON\n" + "    tad.emp_id = emp.emp_id\n" + "INNER JOIN m_department dep ON\n" + "    emp.depart_id = dep.depart_id\n" + "WHERE\n" + "    tad.att_date = :currDate AND tad.lv_sumup_id IN(7, 11, 22)\n" + "GROUP BY\n" + "    dep.depart_id", nativeQuery = true)
public List<DeptWiseWeekoffDash> getLeavesAndAbsent(String currDate)


@Query(value = "SELECT\n" + "     UUID() as id,payreward.type_name AS name_sd,\n" + "    payreward.pay_type_id AS depart_id,\n" + "    SUM(payDet.pay_rate) AS emp_count\n" + "FROM\n" + "    tblm_pay_bonus_details payDet,\n" + "    tbl_pay_bonus payreward\n" + "WHERE\n" + "    payDet.emp_id = :empId AND payDet.pay_type_id = payreward.pay_type_id AND payDet.del_status = 1 and is_paid=0\n" + "GROUP BY\n" + "    payreward.pay_type_id", nativeQuery = true)
public List<DeptWiseWeekoffDash> getRewardwiseAmt(int empId)


@Query(value = "SELECT\n" + "     UUID() as id,dep.name_sd,\n" + "    dep.depart_id,\n" + "    (\n" + "    SELECT\n" + "        COUNT(DISTINCT m_employees.emp_id)\n" + "    FROM\n" + "        m_employees\n" + "    WHERE\n" + "        dep.depart_id = m_employees.depart_id AND m_employees.del_status = 1 and m_employees.location_id=:locId\n" + ") AS emp_count\n" + "FROM\n" + "    m_department dep\n" + "WHERE\n" + "    dep.del_status = 1\n" + "GROUP BY\n" + "    dep.depart_id", nativeQuery = true)
public List<DeptWiseWeekoffDash> getDeptWiseEmpDiversity(int locId)


@Query(value = "SELECT\n" + "\n" + "  UUID() as id,dep.depart_id,\n" + "    dep.name_sd,\n" + "    COUNT(DISTINCT tad.emp_id) as   emp_count\n" + "FROM\n" + "    tbl_attt_summary_daily tad\n" + "INNER JOIN m_employees emp ON\n" + "    tad.emp_id = emp.emp_id\n" + "INNER JOIN m_department dep ON\n" + "    emp.depart_id = dep.depart_id\n" + "INNER JOIN tbl_mst_emp_types typemst ON\n" + "    emp.emp_type = typemst.emp_type_id AND typemst.ot_applicable = 'Yes'\n" + "WHERE\n" + "    tad.month = :month AND tad.year = :year AND tad.tot_othr > 0\n" + "GROUP BY\n" + "    dep.depart_id", nativeQuery = true)
public List<DeptWiseWeekoffDash> getDeptWisePerformanceBonus(String month,String year)


}
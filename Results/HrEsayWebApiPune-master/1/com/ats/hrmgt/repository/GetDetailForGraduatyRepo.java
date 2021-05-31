import com.ats.hrmgt.model.GetDetailForGraduaty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface GetDetailForGraduatyRepo extends JpaRepository<GetDetailForGraduaty, Integer> {


@Query(value = " SELECT\r\n" + "        count('') as freeze_count \r\n" + "    FROM\r\n" + "        tbl_salary_calc \r\n" + "    where\r\n" + "        calc_month=:month\r\n" + "        and calc_year=:year\r\n" + "        and emp_id=:empId", nativeQuery = true)
public int getValidationOfFreezeMonthSalary(String month,String year,int empId)


@Query(value = "select\r\n" + "        uuid() as uuid,\r\n" + "        coalesce((select\r\n" + "            sf.basic \r\n" + "        from\r\n" + "            tbl_emp_salary_info sf \r\n" + "        where\r\n" + "            sf.emp_id=e.emp_id ),\r\n" + "        0) as basic,\r\n" + "        coalesce(sum(es.allowance_value),\r\n" + "        0) as allowance_value,\r\n" + "        ei.cmp_joining_date,\r\n" + "        TIMESTAMPDIFF(YEAR,cmp_joining_date,CURDATE()) AS service\r\n" + "    from\r\n" + "        m_employees e,\r\n" + "        emp_sal_allowance es,\r\n" + "        tbl_emp_salary_info ei\r\n" + "    where\r\n" + "        e.emp_id=:empId           \r\n" + "        and es.emp_id=e.emp_id\r\n" + "        and es.allowance_id=1 \r\n" + "        and ei.emp_id=e.emp_id ", nativeQuery = true)
public GetDetailForGraduaty getdetailforgraduaty(int empId)


@Query(value = "SELECT count('') as freeze_count FROM tbl_attt_daily_daily where att_date between :fromDate and :toDate and tbl_attt_daily_daily.emp_id=:empId " + " and is_fixed=1 and rec_status='F'", nativeQuery = true)
public int getValidationOfFreezeMonth(String fromDate,String toDate,int empId)


}
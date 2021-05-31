import com.ats.hrmgt.model.SalAllownceCal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface SalAllownceCalRepo extends JpaRepository<SalAllownceCal, Integer> {


@Query(value = "select\n" + "        sa.emp_sal_allowance_id,\n" + "        sa.salary_calc_id,\n" + "        sa.emp_id,\n" + "        sa.allowance_id,\n" + "        sa.allowance_value,\n" + "        sa.allowance_value_cal,\n" + "        sa.maker_enter_datetime,\n" + "        sa.del_status,\n" + "        sa.ex_int1,\n" + "        sa.ex_int2,\n" + "        a.name as ex_var1,\n" + "        sa.ex_var2,\n" + "        sa.short_name\n" + "    from\n" + "        tbl_salary_calc_allowance_cal sa,\n" + "        tbl_salary_calc sc,\n" + "        m_allowances a\n" + "    where\n" + "        sa.salary_calc_id=sc.id \n" + "        and a.allowance_id=sa.allowance_id\n" + "        and sc.calc_month=:month\n" + "        and calc_year=:year \n" + "        and sc.emp_id in(:empIds)", nativeQuery = true)
public List<SalAllownceCal> getPayrollAllownceList(int month,int year,List<Integer> empIds)


public List<SalAllownceCal> findByEmpId(int i)


@Query(value = "select\n" + "        sa.* \n" + "    from\n" + "        tbl_salary_calc_allowance_cal sa,\n" + "        tbl_salary_calc sc,\n" + "        m_employees e \n" + "    where\n" + "        sa.salary_calc_id=sc.id \n" + "        and date_format(concat(sc.calc_year,'-',sc.calc_month,'-01'),'%Y-%m-%d') between :fromDate and :toDate      \n" + "        and sc.emp_id=e.emp_id \n" + "        and e.emp_id in (:empIds) ", nativeQuery = true)
public List<SalAllownceCal> getPayrollAllownceListlocId(List<Integer> empIds,String fromDate,String toDate)


@Query(value = "select sa.* from t_arear_detail sa,t_arear_header sc,m_employees e where sa.salary_calc_id=sc.id and " + "sc.calc_month=:month and calc_year=:year and sc.emp_id=e.emp_id and e.location_id in (:locId) ", nativeQuery = true)
public List<SalAllownceCal> getArearsAllownceListlocId(int month,int year,List<Integer> locId)


@Query(value = "select\n" + "        sa.emp_sal_allowance_id,\n" + "        sa.salary_calc_id,\n" + "        sc.depart_id as emp_id,\n" + "        sa.allowance_id,\n" + "        sum(sa.allowance_value) as allowance_value,\n" + "        sum(sa.allowance_value_cal) as allowance_value_cal,\n" + "        sa.maker_enter_datetime,\n" + "        sa.del_status,\n" + "        sa.ex_int1,\n" + "        sa.ex_int2,\n" + "        sa.ex_var1,\n" + "        sa.ex_var2,\n" + "        sa.short_name  \n" + "    from\n" + "        tbl_salary_calc_allowance_cal sa,\n" + "        tbl_salary_calc sc,\n" + "        m_employees e \n" + "    where\n" + "        sa.salary_calc_id=sc.id \n" + "        and  sc.calc_month=:month \n" + "        and calc_year=:year\n" + "        and sc.emp_id=e.emp_id \n" + "        and e.location_id in (:locId) \n" + "    group by\n" + "        sc.depart_id,\n" + "        sa.allowance_id \n" + "    order by\n" + "        sc.depart_id,\n" + "        sa.allowance_id", nativeQuery = true)
public List<SalAllownceCal> getPayrollAllownceListlocIdDept(int month,int year,List<Integer> locId)


}
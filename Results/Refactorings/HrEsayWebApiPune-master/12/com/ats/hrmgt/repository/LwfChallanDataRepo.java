import com.ats.hrmgt.model.LwfChallanData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface LwfChallanDataRepo extends JpaRepository<LwfChallanData, Integer> {


@Query(value = "select UUID() as id,a.*,ifnull(b.employer_value,0) as employer_value,ifnull(b.employee_value,0) as employee_value from " + "(select count('') as emp_count,(select count('') as count_lwf from tbl_salary_calc where mlwf_applicable='yes' and " + "tbl_salary_calc.calc_month=sal.calc_month and tbl_salary_calc.calc_year=sal.calc_year) as count_lwf,(select count('') " + "as lwf_no_count from tbl_salary_calc where mlwf_applicable='no' and tbl_salary_calc.calc_month=sal.calc_month and tbl_salary_calc.calc_year=sal.calc_year) " + "as lwf_no_count,sum(mlwf) as mlwf,sum(employer_mlwf) as employer_mlwf ,e.location_id,sal.calc_month as month from tbl_salary_calc " + "sal,m_employees e where  e.emp_id=sal.emp_id and sal.calc_month=:month and sal.calc_year=:year and e.location_id=:locId ) a " + "left join ( select month,employer_value,employee_value,location_id from tbl_mlwf  ) b on   a.location_id=b.location_id and a.month=b.month", nativeQuery = true)
public LwfChallanData getLwfDataForChallan(int locId,String month,String year)


}
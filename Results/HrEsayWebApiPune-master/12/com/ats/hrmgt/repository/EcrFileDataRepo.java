import com.ats.hrmgt.model.EcrFileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface EcrFileDataRepo extends JpaRepository<EcrFileData, Integer> {


@Query(value = "select UUID() as id,e.uan,e.first_name,e.middle_name,e.surname,sal.gross_salary,sal.epf_wages,sal.epf_wages_employer,sal.eps_wages,sal.employee_pf," + "sal.employer_eps,sal.employer_pf,ds.ncp_days as ncp_days,0 as adv  from tbl_salary_calc sal,m_employees e,tbl_attt_summary_daily ds where  " + "e.emp_id=sal.emp_id and sal.pf_status=1 and ds.emp_id=e.emp_id and ds.month=sal.calc_month and sal.calc_year=ds.year and " + "sal.calc_month=:month and sal.calc_year=:year and e.location_id=:locId and sal.cmp_id=:cmpId", nativeQuery = true)
public List<EcrFileData> getEcrFileData(int cmpId,String month,String year,int locId)


@Query(value = "select UUID() as id,e.uan,e.first_name,e.middle_name,e.surname,sal.gross_salary,sal.epf_wages,sal.epf_wages_employer,sal.eps_wages,sal.employee_pf," + "sal.employer_eps,sal.employer_pf,ds.ncp_days as ncp_days,0 as adv  from t_arear_header sal,m_employees e,tbl_attt_summary_daily ds where  " + "e.emp_id=sal.emp_id and sal.pf_status=1 and ds.emp_id=e.emp_id and ds.month=sal.calc_month and sal.calc_year=ds.year and " + "sal.calc_month=:month and sal.calc_year=:year and e.location_id=:locId and sal.cmp_id=:cmpId", nativeQuery = true)
public List<EcrFileData> getArrearsEcrFileData(int cmpId,String month,String year,int locId)


}
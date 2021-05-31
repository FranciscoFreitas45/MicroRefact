import com.ats.hrmgt.model.GetPfStatementSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface GetPfStatementSummaryRepo extends JpaRepository<GetPfStatementSummary, Integer> {


@Query(value = "SELECT\n" + "        salc.id,\n" + "        salc.emp_id,salc.emp_code,\n" + "        sum(salc.epf_wages) as epf_wages,\n" + "        sum(salc.epf_wages_employer) as epf_wages_employer,\n" + "        sum(salc.eps_wages) as eps_wages, \n" + "        sum(salc.employer_eps) as employer_eps,\n" + "        sum(salc.employee_pf) as employee_pf,\n" + "        sum(salc.employer_pf) as employer_pf,\n" + "        CONCAT(         emp.first_name,\n" + "        ' ',\n" + "        emp.middle_name,\n" + "        ' ',\n" + "        emp.surname     ) AS emp_name,\n" + "        emp.pf_no  ,\n" + "        emp.uan  \n" + "    FROM\n" + "        tbl_salary_calc salc \n" + "    INNER JOIN\n" + "        m_employees emp \n" + "            ON     salc.emp_id = emp.emp_id \n" + "            and emp.location_id=:locId \n" + "    WHERE\n" + "        salc.pf_status = 1 \n" + "        and date_format(concat(salc.calc_year,'-',salc.calc_month,'-01'),'%Y-%m-%d') between concat(:fromYear,'-',:fromMonth,'-01') and concat(:toYear,'-',:toMonth,'-01')\n" + "        AND salc.cmp_id =:companyId\n" + "    group by   salc.emp_id", nativeQuery = true)
public List<GetPfStatementSummary> getPfStatementSummary(int companyId,String fromYear,String fromMonth,String toYear,String toMonth,int locId)


}
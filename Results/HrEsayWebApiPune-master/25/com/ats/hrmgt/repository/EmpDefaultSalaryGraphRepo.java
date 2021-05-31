import com.ats.hrmgt.model.graph.EmpDefaultSalaryGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface EmpDefaultSalaryGraphRepo extends JpaRepository<EmpDefaultSalaryGraph, Integer> {


@Query(value = "SELECT UUID() AS id,\n" + "    calc_month AS month,\n" + "    calc_year AS year,\n" + "    COALESCE((gross_salary),0) AS default_saL_amt\n" + "FROM\n" + "    `tbl_salary_calc`\n" + "WHERE\n" + "    emp_id = :empId AND cmp_id = 1 AND DATE_FORMAT(\n" + "        CONCAT(calc_year, '-', calc_month, '-01'),\n" + "        '%Y-%m-%d'\n" + "    ) BETWEEN :newfromDate AND LAST_DAY(:newToDate)", nativeQuery = true)
public List<EmpDefaultSalaryGraph> getGrossSalByEmpId(String newfromDate,String newToDate,int empId)


@Query(value = "SELECT UUID() AS id,\n" + "    calc_month AS month,\n" + "    calc_year AS year,\n" + "    COALESCE((gross_sal_default),0) AS default_saL_amt\n" + "FROM\n" + "    `tbl_salary_calc`\n" + "WHERE\n" + "    emp_id = :empId AND cmp_id = 1 AND DATE_FORMAT(\n" + "        CONCAT(calc_year, '-', calc_month, '-01'),\n" + "        '%Y-%m-%d'\n" + "    ) BETWEEN :newfromDate AND LAST_DAY(:newToDate)", nativeQuery = true)
public List<EmpDefaultSalaryGraph> getDefGrossSalByEmpId(String newfromDate,String newToDate,int empId)


}
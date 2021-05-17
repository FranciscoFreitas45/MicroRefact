import com.ats.hrmgt.model.SalaryCalc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface SalaryCalcRepo extends JpaRepository<SalaryCalc, Integer> {


public SalaryCalc findByEmpId(int empId)


@Query(value = " SELECT\n" + "    *\n" + "FROM\n" + "    tbl_salary_calc\n" + "\n" + "WHERE\n" + "   tbl_salary_calc.emp_id =:empId AND(\n" + "        (\n" + "            tbl_salary_calc.calc_month >= :monthFrom AND tbl_salary_calc.calc_year = :yearFrom\n" + "        ) OR(\n" + "            tbl_salary_calc.calc_month <= :monthTo AND tbl_salary_calc.calc_year = :yearTo)\n" + "        )\n" + "        order by calc_year , calc_month ASC\n" + "    \n" + "    limit 0,1", nativeQuery = true)
public SalaryCalc getBonusParametersGS(int empId,int monthFrom,int monthTo,String yearFrom,String yearTo)


public List<SalaryCalc> findAllByEmpId(int empId)


}
import com.ats.hrmgt.model.SalaryCalcTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface SalaryCalcTempRepo extends JpaRepository<SalaryCalcTemp, Integer> {


@Query(value = "select * from tbl_salary_dynamic_temp  where calc_month=:month and calc_year=:year and emp_id in (:empIds)", nativeQuery = true)
public List<SalaryCalcTemp> listForUpdatedValue(int month,int year,List<Integer> empIds)


@Transactional
@Modifying
@Query("delete from SalaryCalcTemp where calc_month=:month and calc_year=:year and emp_id in (:empIds) ")
public int deleteFromTemp(int month,int year,List<Integer> empIds)


@Transactional
@Modifying
@Query(value = "UPDATE tbl_salary_dynamic_temp SET itded=:itAmt,performance_bonus=:perBonus,other1=:other1,night_allow=:nightAllowAmt WHERE id=:tempSalDaynamicId", nativeQuery = true)
public int updateBonusAmt(int tempSalDaynamicId,float itAmt,float perBonus,float other1,float nightAllowAmt)


}
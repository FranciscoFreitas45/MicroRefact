import com.ats.hrmgt.model.SalAllownceTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface SalAllownceTempRepo extends JpaRepository<SalAllownceTemp, Integer> {


@Query(value = "select at.* from tbl_salary_dynamic_temp_allowance_cal at,tbl_salary_dynamic_temp st  where at.tbl_salary_dynamic_temp_id=st.id and st.calc_month=:month " + "and st.calc_year=:year and st.emp_id in (:empIds)", nativeQuery = true)
public List<SalAllownceTemp> getAllowanceTempList(int month,int year,List<Integer> empIds)


@Transactional
@Modifying
@Query("delete from SalAllownceTemp where emp_sal_allowance_id in (:detailIds)")
public int deleteFromTempAll(List<Integer> detailIds)


@Query(value = "select\n" + "    sta.*\n" + "from\n" + "    tbl_salary_dynamic_temp_allowance_cal sta,tbl_salary_dynamic_temp sdt \n" + "where\n" + "    sta.del_status=1\n" + "    and sdt.id=sta.tbl_salary_dynamic_temp_id\n" + "    and sdt.calc_month=:month\n" + "    and sdt.calc_year=:year\n" + "    and (\n" + "        sta.emp_id in (:empIds)\n" + "    )", nativeQuery = true)
public List<SalAllownceTemp> findByDelStatusAndEmpIdIn(List<Integer> empIds,int month,int year)


}
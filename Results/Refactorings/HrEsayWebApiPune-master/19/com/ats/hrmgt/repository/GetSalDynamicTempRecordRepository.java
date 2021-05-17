import com.ats.hrmgt.model.GetSalDynamicTempRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetSalDynamicTempRecordRepository extends JpaRepository<GetSalDynamicTempRecord, Integer> {


@Query(value = "select \n" + "        sdt.*,\n" + "        concat(e.first_name, ' ',e.surname) as emp_name,\n" + "        de.name as designation,\n" + "        st.sal_type_name\n" + "    from\n" + "        tbl_salary_dynamic_temp sdt,\n" + "        m_employees e,\n" + "        m_designation de,\n" + "        mst_salary_types st\n" + "    where\n" + "         \n" + "          sdt.id=:tempSalDaynamicId\n" + "          and e.emp_id=sdt.emp_id\n" + "          and de.desig_id=e.designation_id\n" + "          and st.sal_type_id=sdt.sal_type_id\n" + "    order by\n" + "        sdt.emp_id", nativeQuery = true)
public GetSalDynamicTempRecord getSalDynamicTempRecordById(int tempSalDaynamicId)


@Query(value = "select \n" + "        sdt.*,\n" + "        concat(e.first_name, ' ',e.surname) as emp_name,\n" + "        de.name as designation,\n" + "        st.sal_type_name\n" + "    from\n" + "        tbl_salary_dynamic_temp sdt,\n" + "        m_employees e,\n" + "        m_designation de,\n" + "        mst_salary_types st\n" + "    where\n" + "         \n" + "          sdt.calc_month=:month and sdt.calc_year=:year\n" + "          and e.emp_id=sdt.emp_id\n" + "          and de.desig_id=e.designation_id\n" + "          and st.sal_type_id=sdt.sal_type_id and sdt.emp_id in (:empIds)\n" + "    order by\n" + "        sdt.emp_id", nativeQuery = true)
public List<GetSalDynamicTempRecord> getSalDynamicTempRecord(int month,int year,List<Integer> empIds)


}
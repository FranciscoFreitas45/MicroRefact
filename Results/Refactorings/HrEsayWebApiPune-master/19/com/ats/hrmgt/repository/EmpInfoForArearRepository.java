import com.ats.hrmgt.model.EmpInfoForArear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface EmpInfoForArearRepository extends JpaRepository<EmpInfoForArear, Integer> {


@Query(value = " select\n" + "        data.*,\n" + "        0 can_generate_sal,\n" + "        0 count_leave     \n" + "    from\n" + "        (select\n" + "            e.emp_code,\n" + "            e.sub_cmp_id,\n" + "            et.name as emp_type_name,\n" + "            et.emp_type_id,\n" + "            l.loc_name,\n" + "            st.sal_type_name,\n" + "            de.name as designation,\n" + "            dp.name as dept_name,\n" + "            de.desig_id,\n" + "            l.loc_id,\n" + "            dp.depart_id,\n" + "            e.contractor_id,\n" + "            concat(e.first_name,\n" + "            ' ',\n" + "            e.surname) as emp_name,\n" + "            si.*,\n" + "            0 as sum_id                    \n" + "        from\n" + "            tbl_emp_salary_info si,\n" + "            m_employees e,\n" + "            tbl_mst_emp_types et,\n" + "            m_location l,\n" + "            mst_salary_types st,\n" + "            m_designation de,\n" + "            m_department dp                 \n" + "        where\n" + "            e.emp_id=si.emp_id                                \n" + "            and e.del_status=1                                \n" + "            and et.emp_type_id=e.emp_type                                \n" + "            and l.loc_id = e.location_id                                \n" + "            and st.sal_type_id=si.salary_type_id                                \n" + "            and de.desig_id=e.designation_id                                \n" + "            and dp.depart_id=e.depart_id                   \n" + "            and e.emp_id in (:empIds)                   \n" + "        order by\n" + "            e.emp_id) data", nativeQuery = true)
public List<EmpInfoForArear> getEmployeeListWithEmpSalEnfoForArrearEmpId(List<Integer> empIds)


}
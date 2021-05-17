import com.ats.hrmgt.model.GetStructureAllotment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetStructureAllotmentRepo extends JpaRepository<GetStructureAllotment, Integer> {


@Query(value = "  SELECT\n" + "        e.emp_id,\n" + "        e.emp_code,\n" + "        e.emp_fname,\n" + "        e.emp_mname,\n" + "        e.emp_sname    ,\n" + "        d.emp_dept_name,\n" + "        c.emp_cat_name ,\n" + "        l.lvs_name      \n" + "    FROM\n" + "        emp_info e          \n" + "    LEFT JOIN\n" + "        leave_structure_allotment lsa              \n" + "            ON e.emp_id=lsa.emp_id              \n" + "            AND lsa.cal_yr_id=:calYrId      \n" + "    LEFT JOIN\n" + "        m_emp_department d              \n" + "            ON e.emp_dept_id=d.emp_dept_id         \n" + "    LEFT JOIN\n" + "        m_emp_category c                 \n" + "            ON e.emp_cat_id =c.emp_cat_id      \n" + "    LEFT JOIN\n" + "        leave_structure_header l              \n" + "            ON    lsa.lvs_id=l.lvs_id              \n" + "            AND l.del_status=1       \n" + "    WHERE\n" + "        e.del_status=1          \n" + "        AND e.is_active=1           \n" + "        AND e.company_id=:companyId        \n" + "        AND e.loc_id IN(\n" + "            :locIdList       \n" + "        ) AND lsa.lvs_id=:lvsId", nativeQuery = true)
public List<GetStructureAllotment> getStructureAllotmentForProb(int companyId,List<Integer> locIdList,int calYrId,int lvsId)


@Query(value = "SELECT\n" + "    e.emp_id,\n" + "    e.emp_code,\n" + "    e.first_name AS emp_fname,\n" + "    e.middle_name AS emp_mname,\n" + "    e.surname AS emp_sname,\n" + "    dep.name AS emp_dept_name,\n" + "    dg.name AS emp_cat_name,\n" + "    l.lvs_name\n" + "FROM\n" + "    m_employees e\n" + "LEFT JOIN leave_structure_allotment lsa ON\n" + "    e.emp_id = lsa.emp_id AND lsa.cal_yr_id = :calYrId\n" + "LEFT JOIN leave_structure_header l ON\n" + "    lsa.lvs_id = l.lvs_id AND l.del_status = 1\n" + "LEFT JOIN m_designation dg ON\n" + "    e.designation_id = dg.desig_id\n" + "LEFT JOIN m_department dep ON\n" + "    e.depart_id = dep.depart_id where e.del_status=1 and e.location_id in (:locId)", nativeQuery = true)
public List<GetStructureAllotment> getStructureAllotmentListLocId(int calYrId,List<Integer> locId)


@Query(value = "SELECT\n" + "    e.emp_id,\n" + "    e.emp_code,\n" + "    e.first_name AS emp_fname,\n" + "    e.middle_name AS emp_mname,\n" + "    e.surname AS emp_sname,\n" + "    dep.name AS emp_dept_name,\n" + "    dg.name AS emp_cat_name,\n" + "    l.lvs_name\n" + "FROM\n" + "    m_employees e\n" + "LEFT JOIN leave_structure_allotment lsa ON\n" + "    e.emp_id = lsa.emp_id AND lsa.cal_yr_id = :companyId\n" + "LEFT JOIN leave_structure_header l ON\n" + "    lsa.lvs_id = l.lvs_id AND l.del_status = 1\n" + "LEFT JOIN m_designation dg ON\n" + "    e.designation_id = dg.desig_id\n" + "LEFT JOIN m_department dep ON\n" + "    e.depart_id = dep.depart_id where e.del_status=1", nativeQuery = true)
public List<GetStructureAllotment> getStructureAllotment(int companyId)


}
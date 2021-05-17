import com.ats.hrmgt.model.claim.GetClaimStructureAllotment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetClaimStructureAllotmentRepo extends JpaRepository<GetClaimStructureAllotment, Integer> {


@Query(value = "  SELECT\n" + "    e.emp_id,\n" + "    e.emp_code,\n" + "    e.first_name AS emp_fname,\n" + "    e.middle_name AS emp_mname,\n" + "    e.surname AS emp_sname,\n" + "    d.name emp_dept_name,\n" + "    '-' AS emp_cat_name,\n" + "    l.claim_struct_name AS clms_name\n" + "FROM\n" + "    m_employees e\n" + "LEFT JOIN claim_structure_allotment lsa ON\n" + "    e.emp_id = lsa.emp_id\n" + "LEFT JOIN m_department d ON\n" + "    e.depart_id = d.depart_id\n" + "LEFT JOIN claim_structure_header l ON\n" + "    lsa.clms_id = l.clm_struct_head_id AND l.del_status = 1\n" + "WHERE\n" + "    e.del_status = 1   AND e.cmp_code =:companyId  and e.location_id in (:locIdList) order by lsa.clms_id", nativeQuery = true)
public List<GetClaimStructureAllotment> getStructureAllotment(int companyId,List<Integer> locIdList)


}
import com.ats.hrmgt.model.claim.GetClaimApplyAuthWise;
import com.ats.hrmgt.model.claim.GetEmployeeAuthorityWise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetClaimApplyAuthRepo extends JpaRepository<GetClaimApplyAuthWise, Integer> {


@Query(value = "SELECT\n" + "        ca.claim_id,\n" + "        ca.emp_id,\n" + "        ca.project_id,\n" + "        ca.claim_date,\n" + "        ca.claim_amount,\n" + "        ca.claim_remarks,\n" + "        ca.claim_final_status,\n" + "        ca.circulated_to,   \n" + "        e.emp_code,\n" + "        e.emp_fname,\n" + "        e.emp_mname,\n" + "        e.emp_sname,\n" + "        e.emp_photo,\n" + "        ct.claim_type_title,\n" + "        p.project_type_title\n" + "     \n" + "    FROM\n" + "        claim_type as ct,\n" + "        claim_apply as ca,\n" + "        emp_info as e ,\n" + "        project_type as p\n" + "    WHERE\n" + "        ca.emp_id IN(empIdList) \n" + "        AND e.emp_id = ca.emp_id \n" + "        AND ca.claim_type_id = ct.claim_type_id \n" + "        AND ca.project_id=p.project_type_id\n" + "        AND ca.del_status=1 \n" + "        AND ca.is_active=1 \n" + "        AND ca.ex_int1 IN (statusList) ", nativeQuery = true)
public List<GetClaimApplyAuthWise> getClaimApplyList(List<GetEmployeeAuthorityWise> empIdList,List<Integer> statusList)


}
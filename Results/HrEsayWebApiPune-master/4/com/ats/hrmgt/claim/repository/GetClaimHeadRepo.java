import com.ats.hrmgt.model.claim.GetClaimHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetClaimHeadRepo extends JpaRepository<GetClaimHead, Integer> {


@Query(value = "SELECT\n" + "        claim_apply_header.ca_head_id,\n" + "        claim_apply_header.ex_var1,\n" + "        claim_apply_header.ca_from_dt as claim_from_date,\n" + "        claim_apply_header.ca_to_dt as claim_to_date,\n" + "        claim_apply_header.claim_title,\n" + "        claim_apply_header.proj_id as project_id,\n" + "        claim_apply_header.emp_id,\n" + "        claim_apply_header.claim_status as claim_final_status,\n" + "        claim_apply_header.claim_amount,\n" + "        emp_info.first_name as emp_fname,\n" + "        emp_info.surname as emp_sname,\n" + "        '-' as project_title,\n" + "        emp_info.emp_code,\n" + "        m_department.name as emp_dept_name \n" + "    FROM\n" + "        claim_apply_header,\n" + "      m_employees  emp_info,\n" + "         m_department \n" + "    WHERE\n" + "           claim_apply_header.emp_id=emp_info.emp_id \n" + "        AND claim_apply_header.del_status=1 \n" + "        AND claim_apply_header.is_active=1 \n" + "        AND claim_apply_header.emp_id=:empId \n" + "        AND m_department.depart_id=emp_info.depart_id order by claim_apply_header.ca_head_id desc", nativeQuery = true)
public List<GetClaimHead> getClaimHeadByEmpId(int empId)


}
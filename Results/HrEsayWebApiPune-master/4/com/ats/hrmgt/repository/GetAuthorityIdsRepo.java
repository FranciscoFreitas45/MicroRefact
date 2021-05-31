import com.ats.hrmgt.model.GetAuthorityIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface GetAuthorityIdsRepo extends JpaRepository<GetAuthorityIds, Integer> {


@Query(value = "  SELECT leave_authority.emp_id,leave_authority.ini_auth_emp_id,leave_authority.fin_auth_emp_id,leave_authority.rep_to_emp_ids from " + " leave_authority where leave_authority.emp_id=:empId   AND leave_authority.del_status=1 ", nativeQuery = true)
public GetAuthorityIds getAuthIds(int empId)


@Query(value = "  SELECT \n" + "        leave_authority.emp_id,\n" + "        CONCAT(leave_authority.ini_auth_emp_id,\",\",\n" + "       \n" + "        leave_authority.fin_auth_emp_id,\",\"\n" + "        ,\n" + "        leave_authority.rep_to_emp_ids\n" + " \n" + "        ) as rep_to_emp_ids,\n" + "        0 as ini_auth_emp_id,\n" + "        0 as fin_auth_emp_id         \n" + "    from\n" + "        leave_authority      \n" + "    where\n" + "        leave_authority.emp_id=:empId ", nativeQuery = true)
public GetAuthorityIds getAuthIdsDict(int empId)


@Query(value = " SELECT \n" + "        claim_authority.emp_id,\n" + "        CONCAT(claim_authority.ca_ini_auth_emp_id,\",\",\n" + "       \n" + "        claim_authority.ca_fin_auth_emp_id,\",\"\n" + "        ,\n" + "        claim_authority.ca_rep_to_emp_ids\n" + " \n" + "        ) as rep_to_emp_ids,\n" + "        0 as ini_auth_emp_id,\n" + "        0 as fin_auth_emp_id         \n" + "    from\n" + "        claim_authority      \n" + "    where\n" + "        claim_authority.emp_id=:empId ", nativeQuery = true)
public GetAuthorityIds getClaimAuthIdsDict(int empId)


@Query(value = "  SELECT claim_authority.emp_id,claim_authority.ca_ini_auth_emp_id as ini_auth_emp_id,claim_authority.ca_rep_to_emp_ids as rep_to_emp_ids ," + "claim_authority.ca_fin_auth_emp_id  as fin_auth_emp_id from " + " claim_authority where claim_authority.emp_id=:empId   AND claim_authority.del_status=1 AND claim_authority.company_id=:companyId ", nativeQuery = true)
public GetAuthorityIds getClaimAuthIds(int empId,int companyId)


}
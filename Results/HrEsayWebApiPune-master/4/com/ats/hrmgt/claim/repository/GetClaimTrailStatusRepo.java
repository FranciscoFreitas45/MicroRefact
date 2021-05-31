import com.ats.hrmgt.model.claim.GetClaimTrailStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface GetClaimTrailStatusRepo extends JpaRepository<GetClaimTrailStatus, Integer> {


@Query(value = "SELECT\n" + "        claim_trail.claim_id,\n" + "        claim_trail.emp_remarks,\n" + "        claim_trail.claim_status,\n" + "        claim_trail.claim_trail_pkey,\n" + "        claim_apply_header.emp_id,\n" + "        claim_apply_header.claim_title,\n" + "        claim_apply_header.claim_amount,\n" + "        e.emp_code,\n" + "        claim_apply_header.ca_from_dt,\n" + "        claim_apply_header.ca_to_dt,\n" + "        e.first_name as emp_fname,\n" + "        e.middle_name as emp_mname,\n" + "        e.surname as emp_sname,\n" + "        '-' as project_title,\n" + "        claim_trail.maker_enter_datetime,\n" + "        COALESCE(         (         SELECT\n" + "            DISTINCT             CONCAT(                 e.first_name,\n" + "            \" \",\n" + "            e.middle_name,\n" + "            \" \",\n" + "            e.surname             ) AS user_name         \n" + "        FROM\n" + "            m_employees AS e,\n" + "            m_user u         \n" + "        WHERE\n" + "            u.user_id = claim_trail.maker_user_id \n" + "            AND e.emp_id = u.emp_id     ),\n" + "        NULL     ) AS user_name \n" + "    FROM\n" + "        claim_apply_header,\n" + "        m_employees e,\n" + "         claim_trail \n" + "    WHERE\n" + "           claim_apply_header.ca_head_id = claim_trail.claim_id \n" + "        AND claim_apply_header.emp_id = e.emp_id \n" + "        AND claim_trail.claim_id =:claimId \n" + "    ORDER BY\n" + "        claim_trail.claim_id DESC\n" + "        " + "    ", nativeQuery = true)
public List<GetClaimTrailStatus> getClaimTrailByClaimId(int claimId)


}
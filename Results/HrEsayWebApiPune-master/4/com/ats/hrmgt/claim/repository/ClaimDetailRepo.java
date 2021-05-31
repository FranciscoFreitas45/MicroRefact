import com.ats.hrmgt.model.claim.ClaimDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ClaimDetailRepo extends JpaRepository<ClaimDetail, Integer> {


@Query(value = " SELECT\n" + "   claim_apply.*,claim_type.claim_type_title,claim_type.claim_type_title_short as claim_type_title_sshort\n" + "FROM\n" + "    claim_apply,\n" + "    claim_type\n" + " WHERE\n" + "   claim_type.claim_type_id= claim_apply.claim_type_id AND  claim_apply.ex_int2=:caHeadId AND claim_apply.del_status=1 AND claim_apply.is_active=1", nativeQuery = true)
public List<ClaimDetail> getClaimDetailList(int caHeadId)


}
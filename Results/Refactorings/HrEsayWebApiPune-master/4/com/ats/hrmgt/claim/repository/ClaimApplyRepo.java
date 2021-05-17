import com.ats.hrmgt.model.claim.ClaimApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
public interface ClaimApplyRepo extends JpaRepository<ClaimApply, Integer> {


@Transactional
@Modifying
@Query("update ClaimApply set ex_int1=:status  WHERE claim_id=:claimId")
public int updateClaimStatus(int claimId,int status)


public ClaimApply findByClaimIdAndDelStatus(int claimId,int delStatus)


}
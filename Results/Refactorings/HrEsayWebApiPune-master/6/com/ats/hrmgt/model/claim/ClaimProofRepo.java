import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface ClaimProofRepo extends JpaRepository<ClaimProof, Integer> {


public List<ClaimProof> findByClaimIdAndDelStatus(int claimId,int i)


@Transactional
@Modifying
@Query("update ClaimProof set del_status=0  WHERE cp_id=:cpId")
public int deleteClaimProof(int cpId)


}
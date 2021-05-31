import com.ats.hrmgt.model.claim.ClaimType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface ClaimRepository extends JpaRepository<ClaimType, Integer> {


public List<ClaimType> findByDelStatusOrderByCompanyIdDesc(int i)


public List<ClaimType> findByDelStatusOrderByClaimTypeIdDesc(int i)


public ClaimType findByClaimTypeIdAndDelStatus(int claimTypeId,int i)


public List<ClaimType> findByDelStatusAndCompanyIdOrderByCompanyIdDesc(int i,int companyId)


@Transactional
@Modifying
@Query("update ClaimType set del_status=0  WHERE claim_type_id=:claimTypeId")
public int deleteClaimType(int claimTypeId)


}
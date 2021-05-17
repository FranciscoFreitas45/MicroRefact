import com.ats.hrmgt.model.claim.ClaimStructureHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface ClaimStructureHeaderRepo extends JpaRepository<ClaimStructureHeader, Integer> {


public ClaimStructureHeader findByClmStructHeadIdAndDelStatus(int headId,int i)


public List<ClaimStructureHeader> findByDelStatusAndCompanyId(int i,int companyId)


@Transactional
@Modifying
@Query("update ClaimStructureHeader set del_status=0  WHERE clm_struct_head_id=:clmsId")
public int deleteClaimStructure(int clmsId)


}
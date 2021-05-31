import com.ats.hrmgt.model.claim.ClaimStructureAllotment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
public interface ClaimStructureAllotmentRepo extends JpaRepository<ClaimStructureAllotment, Integer> {


@Transactional
@Modifying
@Query("update ClaimStructureAllotment set clmsId=:lvsId,makerUserId =:userId,makerEnterDatetime=:dateTime  WHERE emp_id=:claimId")
public int updateClaimStructure(int claimId,int userId,String dateTime,int lvsId)


public ClaimStructureAllotment findByEmpIdAndDelStatus(int empId,int i)


}
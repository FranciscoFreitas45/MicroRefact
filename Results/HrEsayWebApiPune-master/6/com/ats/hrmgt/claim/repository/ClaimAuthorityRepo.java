import com.ats.hrmgt.model.claim.ClaimAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClaimAuthorityRepo extends JpaRepository<ClaimAuthority, Integer> {


public ClaimAuthority findByDelStatusAndEmpId(int i,int empId)


}
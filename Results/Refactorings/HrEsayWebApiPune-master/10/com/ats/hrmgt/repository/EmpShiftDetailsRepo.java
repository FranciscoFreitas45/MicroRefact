import com.ats.hrmgt.model.EmpShiftDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
public interface EmpShiftDetailsRepo extends JpaRepository<EmpShiftDetails, Integer> {


@Transactional
@Modifying
@Query("delete from EmpShiftDetails")
public int deleteEmpShiftDetailsAll()


}
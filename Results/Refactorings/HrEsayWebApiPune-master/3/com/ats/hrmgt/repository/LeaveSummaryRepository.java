import com.ats.hrmgt.model.LeaveSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface LeaveSummaryRepository extends JpaRepository<LeaveSummary, Integer> {


@Transactional
@Modifying
@Query("update LeaveSummary set del_status=0  WHERE lv_sumup_id=:lvSumupId")
public int deleteLeaveSummary(int lvSumupId)


public List<LeaveSummary> findByDelStatus(int i)


public LeaveSummary findByLvSumupIdAndDelStatus(int lvSumupId,int i)


public List<LeaveSummary> findByDelStatusAndCompanyId(int i,int compId)


}
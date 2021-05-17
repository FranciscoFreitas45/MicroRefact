import com.ats.hrmgt.model.OptionalHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface OptionalHolidayRepo extends JpaRepository<OptionalHoliday, Integer> {


@Transactional
@Modifying
@Query("update OptionalHoliday set status=:sts,approved_by=:userId,approved_daetime=:date  WHERE id in (:ids)")
public int updateStsOfOptionalHoliday(String date,int sts,int userId,List<Integer> ids)


}
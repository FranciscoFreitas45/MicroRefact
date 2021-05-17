import com.ats.hrmgt.model.ShiftCurrentMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
public interface ShiftCurrentMonthRepository extends JpaRepository<ShiftCurrentMonth, Integer> {


@Transactional
@Modifying
@Query("update ShiftCurrentMonth set is_current=0  WHERE id=:id")
public int updateIsCurrent(int id)


public ShiftCurrentMonth findByIsCurrentAndLocId(int i,int locId)


}
import com.ats.hrmgt.model.TempFistTimeAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.Date;
public interface TempFistTimeAssignRepository extends JpaRepository<TempFistTimeAssign, Integer> {


@Transactional
@Modifying
@Query("delete from TempFistTimeAssign where date=:date and extra1=:locId")
public void deleterecord(int locId,Date date)


@Transactional
@Modifying
@Query("update TempFistTimeAssign set shift_id=:shiftId  WHERE id=:id")
public int updateshift(int id,int shiftId)


}
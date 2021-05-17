import com.ats.hrmgt.model.CalenderYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface CalculateYearRepository extends JpaRepository<CalenderYear, Integer> {


public List<CalenderYear> findByCalYrToDateAndCalYrIdNot(String inputValue,int primaryKey)


@Query(value = "SELECT COUNT(*) FROM `dm_cal_year`", nativeQuery = true)
public int countCalyear()


public List<CalenderYear> findByCalYrFromDateAndCalYrIdNot(String inputValue,int primaryKey)


@Query(value = "SELECT * FROM `dm_cal_year` ORDER BY cal_yr_id DESC", nativeQuery = true)
public List<CalenderYear> getAllCalYearOrderByDesc()


public List<CalenderYear> findByCalYrToDate(String inputValue)


public CalenderYear findByCalYrId(int calYrId)


public CalenderYear findByIsCurrent(int i)


@Modifying
@Transactional
@Query(value = "UPDATE `dm_cal_year` SET is_current=0 WHERE cal_yr_id !=:calYrId", nativeQuery = true)
public int updateOtherIds(int calYrId)


public List<CalenderYear> findByCalYrFromDate(String inputValue)


}
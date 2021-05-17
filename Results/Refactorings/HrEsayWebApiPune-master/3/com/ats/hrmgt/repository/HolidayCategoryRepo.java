import com.ats.hrmgt.model.HolidayCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface HolidayCategoryRepo extends JpaRepository<HolidayCategory, Integer> {


public HolidayCategory findByHoCatIdAndDelStatus(int hoCatId,int i)


@Transactional
@Modifying
@Query("update HolidayCategory set del_status=0  WHERE ho_cat_id=:hoCatId")
public int deleteHoliCat(int hoCatId)


public List<HolidayCategory> findByHoCatNameAndCompanyIdAndHoCatIdNot(String trim,int compId,int primaryKey)


public List<HolidayCategory> findByDelStatusOrderByHoCatIdDesc(int i)


public List<HolidayCategory> findByHoCatNameAndCompanyId(String inputValue,int compId)


}
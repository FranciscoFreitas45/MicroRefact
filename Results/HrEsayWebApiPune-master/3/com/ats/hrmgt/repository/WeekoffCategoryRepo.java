import com.ats.hrmgt.model.WeekoffCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface WeekoffCategoryRepo extends JpaRepository<WeekoffCategory, Integer> {


public List<WeekoffCategory> findByWoCatNameAndCompanyId(String inputValue,int compId)


public List<WeekoffCategory> findByWoCatNameAndCompanyIdAndWoCatIdNot(String trim,int compId,int primaryKey)


public List<WeekoffCategory> findByDelStatus(int i)


public List<WeekoffCategory> findByDelStatusOrderByWoCatIdDesc(int i)


@Transactional
@Modifying
@Query("update WeekoffCategory set del_status=0  WHERE wo_cat_id=:hoCatId")
public int deleteWoCat(int hoCatId)


public WeekoffCategory findByWoCatIdAndDelStatus(int hoCatId,int i)


}
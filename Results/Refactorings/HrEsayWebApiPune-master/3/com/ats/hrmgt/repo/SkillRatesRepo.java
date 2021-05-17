import com.ats.hrmgt.model.SkillRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface SkillRatesRepo extends JpaRepository<SkillRates, Integer> {


public List<SkillRates> findByDelStatus(int i)


public List<SkillRates> findByDelStatusOrderBySkillIdDesc(int i)


@Transactional
@Modifying
@Query("update SkillRates set del_status=0  WHERE skill_id=:skillId")
public int deleteSkillRate(int skillId)


public SkillRates findBySkillIdAndDelStatus(int skillId,int i)


}
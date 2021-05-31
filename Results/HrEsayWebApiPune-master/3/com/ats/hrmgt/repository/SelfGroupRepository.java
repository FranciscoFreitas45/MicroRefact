import com.ats.hrmgt.model.SelfGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface SelfGroupRepository extends JpaRepository<SelfGroup, Integer> {


@Query(value = "select * from m_self_grup where del_status=1 order by selft_group_id desc", nativeQuery = true)
public List<SelfGroup> selfGrouptList()


public SelfGroup findBySelftGroupIdAndDelStatus(int selftGroupId,int i)


@Transactional
@Modifying
@Query("update SelfGroup set del_status=0  WHERE selft_group_id=:bonusId")
public int deleteSelfGroup(int bonusId)


@Query(value = "select * from m_self_grup where del_status=1 and ex_int1=0", nativeQuery = true)
public List<SelfGroup> getSelftGroupListForAddShift()


}
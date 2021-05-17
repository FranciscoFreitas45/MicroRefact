import com.ats.hrmgt.model.bonus.BonusMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface BonusMasterRepo extends JpaRepository<BonusMaster, Integer> {


@Transactional
@Modifying
@Query("update BonusMaster set del_status=0  WHERE bonus_id=:bonusId")
public int deleteBonus(int bonusId)


@Query(value = " SELECT\n" + "    *\n" + "FROM\n" + "    m_bonus_fy\n" + "WHERE\n" + "    m_bonus_fy.del_status = 1 AND m_bonus_fy.bonus_id NOT IN(\n" + "    SELECT\n" + "        t_bonus_applicable.bonus_id\n" + "    FROM\n" + "        t_bonus_applicable\n" + "    WHERE\n" + "        t_bonus_applicable.is_payroll_finalized = 'Yes'\n" + ")", nativeQuery = true)
public List<BonusMaster> findByDelStatus()


public List<BonusMaster> findByFyTitleAndDelStatus(String bonusTitle,int i)


public BonusMaster findByBonusIdAndDelStatus(int bonusId,int i)


}
import com.ats.hrmgt.model.bonus.PayBonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;
public interface PayBonusRepo extends JpaRepository<PayBonus, Integer> {


public PayBonus findByPayTypeIdAndDelStatus(int payBonusId,int i)


public List<PayBonus> findByDelStatus(int i)


@Transactional
@Modifying
@Query("update PayBonus set del_status=0  WHERE pay_type_id=:bonusId")
public int deleteBonusPayType(int bonusId)


}
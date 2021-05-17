import com.ats.hrmgt.model.bonus.BonusApplicable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
public interface BonusApplicableRepo extends JpaRepository<BonusApplicable, Integer> {


@Transactional
@Modifying
@Query("update BonusApplicable set  is_bonussheet_finalized='1', payroll_month=:month ,payroll_year =:year  WHERE bapp_no=:bonusAppId")
public int updateBonus(int bonusAppId,String month,String year)


public BonusApplicable findByBonusId(int bonusId)


@Transactional
@Modifying
@Query("update BonusApplicable set  exgretia_formula =:bonus_formula, exgretia_percentage =:exgretia_percentage ,login_id_exgretia=:userId, " + "login_time_exgretia =:dateTime,ded_exgretia_amt_percentage=:ded_exgretia_amt_percentage,is_exgretia_finalized='1' ,exgretia_remark =:remark, exgratia_paid_date=:exgratiaDate WHERE bapp_no=:bonusAppId")
public int updateBonusExgratia(int bonusAppId,String bonus_formula,double exgretia_percentage,double ded_exgretia_amt_percentage,int userId,String dateTime,String remark,String exgratiaDate)


}
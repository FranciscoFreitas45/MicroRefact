import com.ats.hrmgt.model.bonus.BonusCalc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface BonusCalcRepo extends JpaRepository<BonusCalc, Integer> {


public BonusCalc findByEmpIdAndBonusIdAndDelStatus(int empId,int bonusId,int i)


@Transactional
@Modifying
@Query("update BonusCalc set  exgretia_details=:json  WHERE bonus_calc_id=:bonusCalcId")
public int updateExgratiaDetails(String json,int bonusCalcId)


@Query(value = "SELECT * from t_bonus_calc WHERE t_bonus_calc.del_status=1 AND t_bonus_calc.bonus_id=:bonusId ", nativeQuery = true)
public List<BonusCalc> getEmpDetailListForBonus(int bonusId)


@Transactional
@Modifying
@Query("update BonusCalc set  total_exgretia_days =:payableDays,total_exgretia_wages=:formTot ,exgretia_applicable =:isApp,gross_exgretia_amt=:grossExgratiaAmt,ded_exgretia_amt=:dedExgratiaAmt,net_exgretia_amt=:exgratiaAmt,login_id_exgretia=:userId,login_time_exgretia=:dateTime ,ex_int1=1, 	exgratia_prcnt =:exgretia_percentage  WHERE bonus_calc_id=:bonusCalcId")
public int updateExgratiaAmts(double formTot,double grossExgratiaAmt,double exgratiaAmt,double dedExgratiaAmt,double payableDays,String dateTime,int userId,String isApp,int bonusCalcId,double exgretia_percentage)


@Transactional
@Modifying
@Query("Delete From  BonusCalc  WHERE bonus_calc_id=:bonusCalcId")
public int deleteBonus(int bonusCalcId)


public List<BonusCalc> findByDelStatus(int i)


@Transactional
@Modifying
@Query("update BonusCalc set  is_bonussheet_finalized='Yes',paid_bonus_date =:paidDate  WHERE bonus_id=:bonusId")
public int updateCalcFinalize(int bonusId,String paidDate)


public BonusCalc findByBonusCalcIdAndDelStatus(int bonusCalcId,int i)


@Transactional
@Modifying
@Query("update BonusCalc set  is_exgretia_finalized ='Yes',paid_exgretia_date =:paidDate  WHERE bonus_id=:bonusId")
public int updateCalcFinalizeExgratia(int bonusId,String paidDate)


@Query(value = "SELECT * from t_bonus_calc WHERE t_bonus_calc.del_status=1 AND t_bonus_calc.bonus_id=:bonusId AND t_bonus_calc.ex_int1=1", nativeQuery = true)
public List<BonusCalc> getEmpDetailListForBonusEx(int bonusId)


@Transactional
@Modifying
@Query("update BonusCalc set  paid_exgretia_amt=:paidExgratiaAmt, gross_exgretia_amt=:grossExgratiaAmt,net_exgretia_amt=:netExgretiaAmt," + "login_id_exgretia=:userId,login_time_exgretia=:dateTime ,ex_int1=1, exgratia_prcnt =:exgretia_percentage  WHERE bonus_calc_id=:bonusCalcId")
public int updateExgratiaAmtsGS(double paidExgratiaAmt,double grossExgratiaAmt,double netExgretiaAmt,double exgretia_percentage,String dateTime,int userId,int bonusCalcId)


@Transactional
@Modifying
@Query("update BonusCalc set  total_exgretia_days =0,total_exgretia_wages=0 ,exgretia_applicable =0,gross_exgretia_amt=0,ded_exgretia_amt=0,net_exgretia_amt=0,ex_int1=0  WHERE bonus_calc_id=:bonusCalcId")
public int updateExgratiaAmtsDelete(int bonusCalcId)


public List<BonusCalc> findByDelStatusAndBonusId(int i,int bonusId)


}
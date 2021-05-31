import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "m_bonus_fy")
public class BonusMaster {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int bonusId;

 private  String fyTitle;

 private  Date fyFromdt;

 private  Date fyTodt;

 private  int isCurrent;

 private  String remark;

 private  double bonusPercentage;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;

 private  int minDays;

 private  double exgratiaPercentage;

 private  int bonusAppBelowAmount;

 private  int bonusAppBelowApplicable;

 private  int bonusSealingLimitApplicable;

 private  int bonuSealingLimitAmountPerMonth;

 private  int dedBonusPujaAmtPercentage;

 private  int dedBonusAdvAmtPercentage;

 private  int dedBonusLossAmtPercentage;

@Transient
 private  boolean error;


public String getExVar2(){
    return exVar2;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getFyTodt(){
    return fyTodt;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public int getBonusAppBelowAmount(){
    return bonusAppBelowAmount;
}


public void setFyTitle(String fyTitle){
    this.fyTitle = fyTitle;
}


public double getBonusPercentage(){
    return bonusPercentage;
}


public String getExVar1(){
    return exVar1;
}


public void setBonusId(int bonusId){
    this.bonusId = bonusId;
}


public void setBonusAppBelowApplicable(int bonusAppBelowApplicable){
    this.bonusAppBelowApplicable = bonusAppBelowApplicable;
}


public int getDedBonusLossAmtPercentage(){
    return dedBonusLossAmtPercentage;
}


public void setExgratiaPercentage(double exgratiaPercentage){
    this.exgratiaPercentage = exgratiaPercentage;
}


public int getBonusAppBelowApplicable(){
    return bonusAppBelowApplicable;
}


public int getBonusSealingLimitApplicable(){
    return bonusSealingLimitApplicable;
}


public void setDedBonusLossAmtPercentage(int dedBonusLossAmtPercentage){
    this.dedBonusLossAmtPercentage = dedBonusLossAmtPercentage;
}


public boolean isError(){
    return error;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getRemark(){
    return remark;
}


public String getFyTitle(){
    return fyTitle;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setBonusAppBelowAmount(int bonusAppBelowAmount){
    this.bonusAppBelowAmount = bonusAppBelowAmount;
}


public int getDedBonusPujaAmtPercentage(){
    return dedBonusPujaAmtPercentage;
}


public void setDedBonusPujaAmtPercentage(int dedBonusPujaAmtPercentage){
    this.dedBonusPujaAmtPercentage = dedBonusPujaAmtPercentage;
}


public int getDedBonusAdvAmtPercentage(){
    return dedBonusAdvAmtPercentage;
}


public void setFyFromdt(Date fyFromdt){
    this.fyFromdt = fyFromdt;
}


public double getExgratiaPercentage(){
    return exgratiaPercentage;
}


public void setMinDays(int minDays){
    this.minDays = minDays;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setBonuSealingLimitAmountPerMonth(int bonuSealingLimitAmountPerMonth){
    this.bonuSealingLimitAmountPerMonth = bonuSealingLimitAmountPerMonth;
}


public int getBonuSealingLimitAmountPerMonth(){
    return bonuSealingLimitAmountPerMonth;
}


public void setFyTodt(Date fyTodt){
    this.fyTodt = fyTodt;
}


public void setBonusPercentage(double bonusPercentage){
    this.bonusPercentage = bonusPercentage;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getFyFromdt(){
    return fyFromdt;
}


public int getIsCurrent(){
    return isCurrent;
}


public void setIsCurrent(int isCurrent){
    this.isCurrent = isCurrent;
}


public int getDelStatus(){
    return delStatus;
}


public void setError(boolean error){
    this.error = error;
}


public void setBonusSealingLimitApplicable(int bonusSealingLimitApplicable){
    this.bonusSealingLimitApplicable = bonusSealingLimitApplicable;
}


public int getBonusId(){
    return bonusId;
}


@Override
public String toString(){
    return "BonusMaster [bonusId=" + bonusId + ", fyTitle=" + fyTitle + ", fyFromdt=" + fyFromdt + ", fyTodt=" + fyTodt + ", isCurrent=" + isCurrent + ", remark=" + remark + ", bonusPercentage=" + bonusPercentage + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", minDays=" + minDays + ", exgratiaPercentage=" + exgratiaPercentage + ", bonusAppBelowAmount=" + bonusAppBelowAmount + ", bonusAppBelowApplicable=" + bonusAppBelowApplicable + ", bonusAealingLimitApplicable=" + bonusSealingLimitApplicable + ", bonuSealingLimitAmountPerMonth=" + bonuSealingLimitAmountPerMonth + ", dedBonusPujaAmtPercentage=" + dedBonusPujaAmtPercentage + ", dedBonusAdvAmtPercentage=" + dedBonusAdvAmtPercentage + ", dedBonusLossAmtPercentage=" + dedBonusLossAmtPercentage + ", error=" + error + "]";
}


public int getMinDays(){
    return minDays;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setDedBonusAdvAmtPercentage(int dedBonusAdvAmtPercentage){
    this.dedBonusAdvAmtPercentage = dedBonusAdvAmtPercentage;
}


}
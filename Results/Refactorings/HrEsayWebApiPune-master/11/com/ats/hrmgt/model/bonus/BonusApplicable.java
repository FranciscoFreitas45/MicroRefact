import javax.persistence;
@Entity
@Table(name = "t_bonus_applicable")
public class BonusApplicable {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int bappNo;

 private  int companyId;

 private  int bonusId;

 private  String bonusFormula;

 private  double bonusAppBelowAmount;

 private  String bonusSealingLimitApplicable;

 private  double bonusSealingLimitAmount;

 private  double bonusPercentage;

 private  String exgretiaFormula;

 private  double exgretiaPercentage;

 private  double dedExgretiaAmtPercentage;

 private  int payrollMonth;

 private  int payrollYear;

 private  String isPayrollFinalized;

 private  String isBonussheetFinalized;

 private  String isExgretiaFinalized;

 private  int bonusItSub;

 private  int exgretiaItSub;

 private  int loginIdBonus;

 private  int loginIdExgretia;

 private  String loginTimeBonus;

 private  String loginTimeExgretia;

 private  String bonusRemark;

 private  String exgretiaRemark;

 private  String bonusPaidDate;

 private  String exgratiaPaidDate;

 private  int exInt1;

 private  String exVar1;


public double getBonusAppBelowAmount(){
    return bonusAppBelowAmount;
}


public String getExVar1(){
    return exVar1;
}


public String getLoginTimeBonus(){
    return loginTimeBonus;
}


public String getBonusSealingLimitApplicable(){
    return bonusSealingLimitApplicable;
}


public void setExgratiaPaidDate(String exgratiaPaidDate){
    this.exgratiaPaidDate = exgratiaPaidDate;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setIsPayrollFinalized(String isPayrollFinalized){
    this.isPayrollFinalized = isPayrollFinalized;
}


public void setBonusSealingLimitAmount(double bonusSealingLimitAmount){
    this.bonusSealingLimitAmount = bonusSealingLimitAmount;
}


public void setPayrollMonth(int payrollMonth){
    this.payrollMonth = payrollMonth;
}


public void setBonusItSub(int bonusItSub){
    this.bonusItSub = bonusItSub;
}


public String getIsBonussheetFinalized(){
    return isBonussheetFinalized;
}


public int getLoginIdBonus(){
    return loginIdBonus;
}


public void setLoginTimeBonus(String loginTimeBonus){
    this.loginTimeBonus = loginTimeBonus;
}


public String getExgretiaRemark(){
    return exgretiaRemark;
}


public String getExgratiaPaidDate(){
    return exgratiaPaidDate;
}


public String getIsExgretiaFinalized(){
    return isExgretiaFinalized;
}


public String getBonusPaidDate(){
    return bonusPaidDate;
}


public String getIsPayrollFinalized(){
    return isPayrollFinalized;
}


public void setBonusPercentage(double bonusPercentage){
    this.bonusPercentage = bonusPercentage;
}


public void setExgretiaFormula(String exgretiaFormula){
    this.exgretiaFormula = exgretiaFormula;
}


public void setBappNo(int bappNo){
    this.bappNo = bappNo;
}


public void setBonusRemark(String bonusRemark){
    this.bonusRemark = bonusRemark;
}


public void setDedExgretiaAmtPercentage(double dedExgretiaAmtPercentage){
    this.dedExgretiaAmtPercentage = dedExgretiaAmtPercentage;
}


public void setPayrollYear(int payrollYear){
    this.payrollYear = payrollYear;
}


public double getBonusSealingLimitAmount(){
    return bonusSealingLimitAmount;
}


public int getExgretiaItSub(){
    return exgretiaItSub;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setExgretiaPercentage(double exgretiaPercentage){
    this.exgretiaPercentage = exgretiaPercentage;
}


public int getExInt1(){
    return exInt1;
}


public double getBonusPercentage(){
    return bonusPercentage;
}


public void setBonusId(int bonusId){
    this.bonusId = bonusId;
}


public void setIsBonussheetFinalized(String isBonussheetFinalized){
    this.isBonussheetFinalized = isBonussheetFinalized;
}


public void setExgretiaRemark(String exgretiaRemark){
    this.exgretiaRemark = exgretiaRemark;
}


public void setLoginIdBonus(int loginIdBonus){
    this.loginIdBonus = loginIdBonus;
}


public int getLoginIdExgretia(){
    return loginIdExgretia;
}


public int getBappNo(){
    return bappNo;
}


public String getBonusFormula(){
    return bonusFormula;
}


public double getDedExgretiaAmtPercentage(){
    return dedExgretiaAmtPercentage;
}


public int getBonusItSub(){
    return bonusItSub;
}


public int getPayrollMonth(){
    return payrollMonth;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setIsExgretiaFinalized(String isExgretiaFinalized){
    this.isExgretiaFinalized = isExgretiaFinalized;
}


public void setBonusAppBelowAmount(double bonusAppBelowAmount){
    this.bonusAppBelowAmount = bonusAppBelowAmount;
}


public int getPayrollYear(){
    return payrollYear;
}


public double getExgretiaPercentage(){
    return exgretiaPercentage;
}


public String getBonusRemark(){
    return bonusRemark;
}


public void setLoginIdExgretia(int loginIdExgretia){
    this.loginIdExgretia = loginIdExgretia;
}


public void setLoginTimeExgretia(String loginTimeExgretia){
    this.loginTimeExgretia = loginTimeExgretia;
}


public String getExgretiaFormula(){
    return exgretiaFormula;
}


public void setExgretiaItSub(int exgretiaItSub){
    this.exgretiaItSub = exgretiaItSub;
}


public void setBonusFormula(String bonusFormula){
    this.bonusFormula = bonusFormula;
}


public int getCompanyId(){
    return companyId;
}


public void setBonusSealingLimitApplicable(String bonusSealingLimitApplicable){
    this.bonusSealingLimitApplicable = bonusSealingLimitApplicable;
}


public void setBonusPaidDate(String bonusPaidDate){
    this.bonusPaidDate = bonusPaidDate;
}


public String getLoginTimeExgretia(){
    return loginTimeExgretia;
}


public int getBonusId(){
    return bonusId;
}


@Override
public String toString(){
    return "BonusApplicable [bappNo=" + bappNo + ", companyId=" + companyId + ", bonusId=" + bonusId + ", bonusFormula=" + bonusFormula + ", bonusAppBelowAmount=" + bonusAppBelowAmount + ", bonusSealingLimitApplicable=" + bonusSealingLimitApplicable + ", bonusSealingLimitAmount=" + bonusSealingLimitAmount + ", bonusPercentage=" + bonusPercentage + ", exgretiaFormula=" + exgretiaFormula + ", exgretiaPercentage=" + exgretiaPercentage + ", dedExgretiaAmtPercentage=" + dedExgretiaAmtPercentage + ", payrollMonth=" + payrollMonth + ", payrollYear=" + payrollYear + ", isPayrollFinalized=" + isPayrollFinalized + ", isBonussheetFinalized=" + isBonussheetFinalized + ", isExgretiaFinalized=" + isExgretiaFinalized + ", bonusItSub=" + bonusItSub + ", exgretiaItSub=" + exgretiaItSub + ", loginIdBonus=" + loginIdBonus + ", loginIdExgretia=" + loginIdExgretia + ", loginTimeBonus=" + loginTimeBonus + ", loginTimeExgretia=" + loginTimeExgretia + ", bonusRemark=" + bonusRemark + ", exgretiaRemark=" + exgretiaRemark + ", bonusPaidDate=" + bonusPaidDate + ", exgratiaPaidDate=" + exgratiaPaidDate + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + "]";
}


}
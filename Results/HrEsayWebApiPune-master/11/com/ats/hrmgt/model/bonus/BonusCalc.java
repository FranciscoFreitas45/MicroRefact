import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "t_bonus_calc")
public class BonusCalc {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int bonusCalcId;

 private  int companyId;

 private  int bonusId;

 private  int empId;

 private  String companyEmpCode;

 private  String location;

 private  String empName;

 private  int currAge;

 private  String currDesignation;

 private  String bonusDetails;

 private  double totalBonusDays;

 private  double totalBonusWages;

 private  String bonusApplicable;

 private  double grossBonusAmt;

 private  double dedBonusPujaAmt;

 private  double dedBonusAdvAmt;

 private  double dedBonusLossAmt;

 private  double netBonusAmt;

 private  double paidBonusAmt;

 private  Date paidBonusDate;

 private  String isBonussheetFinalized;

 private  String exgretiaDetails;

 private  double totalExgretiaDays;

 private  double totalExgretiaWages;

 private  String exgretiaApplicable;

 private  double grossExgretiaAmt;

 private  double dedExgretiaAmt;

 private  String dedReason;

 private  double netExgretiaAmt;

 private  double exgratiaPrcnt;

 private  double paidExgretiaAmt;

 private  Date paidExgretiaDate;

 private  String isExgretiaFinalized;

 private  int recStatus;

 private  int loginIdBonus;

 private  int loginIdExgretia;

 private  String loginTimeBonus;

 private  String loginTimeExgretia;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public String getLocation(){
    return location;
}


public double getGrossBonusAmt(){
    return grossBonusAmt;
}


public String getExgretiaApplicable(){
    return exgretiaApplicable;
}


public String getExVar1(){
    return exVar1;
}


public void setDedReason(String dedReason){
    this.dedReason = dedReason;
}


public void setPaidExgretiaAmt(double paidExgretiaAmt){
    this.paidExgretiaAmt = paidExgretiaAmt;
}


public String getLoginTimeBonus(){
    return loginTimeBonus;
}


public double getNetExgretiaAmt(){
    return netExgretiaAmt;
}


public void setExgratiaPrcnt(double exgratiaPrcnt){
    this.exgratiaPrcnt = exgratiaPrcnt;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public int getRecStatus(){
    return recStatus;
}


public double getGrossExgretiaAmt(){
    return grossExgretiaAmt;
}


public void setPaidBonusDate(Date paidBonusDate){
    this.paidBonusDate = paidBonusDate;
}


public String getIsBonussheetFinalized(){
    return isBonussheetFinalized;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getPaidBonusDate(){
    return paidBonusDate;
}


public double getTotalExgretiaDays(){
    return totalExgretiaDays;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setNetBonusAmt(double netBonusAmt){
    this.netBonusAmt = netBonusAmt;
}


public double getExgratiaPrcnt(){
    return exgratiaPrcnt;
}


public int getLoginIdBonus(){
    return loginIdBonus;
}


public void setLoginTimeBonus(String loginTimeBonus){
    this.loginTimeBonus = loginTimeBonus;
}


public String getBonusApplicable(){
    return bonusApplicable;
}


public void setPaidBonusAmt(double paidBonusAmt){
    this.paidBonusAmt = paidBonusAmt;
}


public String getIsExgretiaFinalized(){
    return isExgretiaFinalized;
}


public double getPaidExgretiaAmt(){
    return paidExgretiaAmt;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setRecStatus(int recStatus){
    this.recStatus = recStatus;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public double getDedBonusPujaAmt(){
    return dedBonusPujaAmt;
}


public void setBonusCalcId(int bonusCalcId){
    this.bonusCalcId = bonusCalcId;
}


public String getBonusDetails(){
    return bonusDetails;
}


public void setCurrDesignation(String currDesignation){
    this.currDesignation = currDesignation;
}


public void setDedBonusLossAmt(double dedBonusLossAmt){
    this.dedBonusLossAmt = dedBonusLossAmt;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setDedExgretiaAmt(double dedExgretiaAmt){
    this.dedExgretiaAmt = dedExgretiaAmt;
}


public void setCurrAge(int currAge){
    this.currAge = currAge;
}


public int getExInt2(){
    return exInt2;
}


public void setDedBonusPujaAmt(double dedBonusPujaAmt){
    this.dedBonusPujaAmt = dedBonusPujaAmt;
}


public int getExInt1(){
    return exInt1;
}


public void setBonusId(int bonusId){
    this.bonusId = bonusId;
}


public void setIsBonussheetFinalized(String isBonussheetFinalized){
    this.isBonussheetFinalized = isBonussheetFinalized;
}


public void setLoginIdBonus(int loginIdBonus){
    this.loginIdBonus = loginIdBonus;
}


public int getLoginIdExgretia(){
    return loginIdExgretia;
}


public void setCompanyEmpCode(String companyEmpCode){
    this.companyEmpCode = companyEmpCode;
}


public void setTotalBonusWages(double totalBonusWages){
    this.totalBonusWages = totalBonusWages;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getPaidExgretiaDate(){
    return paidExgretiaDate;
}


public int getBonusCalcId(){
    return bonusCalcId;
}


public String getEmpName(){
    return empName;
}


public int getCurrAge(){
    return currAge;
}


public void setBonusDetails(String bonusDetails){
    this.bonusDetails = bonusDetails;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public void setExgretiaDetails(String exgretiaDetails){
    this.exgretiaDetails = exgretiaDetails;
}


public String getExgretiaDetails(){
    return exgretiaDetails;
}


public void setLocation(String location){
    this.location = location;
}


public double getTotalExgretiaWages(){
    return totalExgretiaWages;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setTotalExgretiaDays(double totalExgretiaDays){
    this.totalExgretiaDays = totalExgretiaDays;
}


public void setIsExgretiaFinalized(String isExgretiaFinalized){
    this.isExgretiaFinalized = isExgretiaFinalized;
}


public void setTotalExgretiaWages(double totalExgretiaWages){
    this.totalExgretiaWages = totalExgretiaWages;
}


public void setGrossBonusAmt(double grossBonusAmt){
    this.grossBonusAmt = grossBonusAmt;
}


public double getPaidBonusAmt(){
    return paidBonusAmt;
}


public double getNetBonusAmt(){
    return netBonusAmt;
}


public void setTotalBonusDays(double totalBonusDays){
    this.totalBonusDays = totalBonusDays;
}


public void setDedBonusAdvAmt(double dedBonusAdvAmt){
    this.dedBonusAdvAmt = dedBonusAdvAmt;
}


public String getDedReason(){
    return dedReason;
}


public void setLoginIdExgretia(int loginIdExgretia){
    this.loginIdExgretia = loginIdExgretia;
}


public String getCurrDesignation(){
    return currDesignation;
}


public void setGrossExgretiaAmt(double grossExgretiaAmt){
    this.grossExgretiaAmt = grossExgretiaAmt;
}


public void setLoginTimeExgretia(String loginTimeExgretia){
    this.loginTimeExgretia = loginTimeExgretia;
}


public String getCompanyEmpCode(){
    return companyEmpCode;
}


public void setExgretiaApplicable(String exgretiaApplicable){
    this.exgretiaApplicable = exgretiaApplicable;
}


public void setNetExgretiaAmt(double netExgretiaAmt){
    this.netExgretiaAmt = netExgretiaAmt;
}


public double getTotalBonusDays(){
    return totalBonusDays;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


public double getDedBonusLossAmt(){
    return dedBonusLossAmt;
}


public double getTotalBonusWages(){
    return totalBonusWages;
}


public double getDedBonusAdvAmt(){
    return dedBonusAdvAmt;
}


public void setPaidExgretiaDate(Date paidExgretiaDate){
    this.paidExgretiaDate = paidExgretiaDate;
}


public String getLoginTimeExgretia(){
    return loginTimeExgretia;
}


public int getBonusId(){
    return bonusId;
}


public double getDedExgretiaAmt(){
    return dedExgretiaAmt;
}


@Override
public String toString(){
    return "BonusCalc [bonusCalcId=" + bonusCalcId + ", companyId=" + companyId + ", bonusId=" + bonusId + ", empId=" + empId + ", companyEmpCode=" + companyEmpCode + ", location=" + location + ", empName=" + empName + ", currAge=" + currAge + ", currDesignation=" + currDesignation + ", bonusDetails=" + bonusDetails + ", totalBonusDays=" + totalBonusDays + ", totalBonusWages=" + totalBonusWages + ", bonusApplicable=" + bonusApplicable + ", grossBonusAmt=" + grossBonusAmt + ", dedBonusPujaAmt=" + dedBonusPujaAmt + ", dedBonusAdvAmt=" + dedBonusAdvAmt + ", dedBonusLossAmt=" + dedBonusLossAmt + ", netBonusAmt=" + netBonusAmt + ", paidBonusAmt=" + paidBonusAmt + ", paidBonusDate=" + paidBonusDate + ", isBonussheetFinalized=" + isBonussheetFinalized + ", exgretiaDetails=" + exgretiaDetails + ", totalExgretiaDays=" + totalExgretiaDays + ", totalExgretiaWages=" + totalExgretiaWages + ", exgretiaApplicable=" + exgretiaApplicable + ", grossExgretiaAmt=" + grossExgretiaAmt + ", dedExgretiaAmt=" + dedExgretiaAmt + ", dedReason=" + dedReason + ", netExgretiaAmt=" + netExgretiaAmt + ", exgratiaPrcnt=" + exgratiaPrcnt + ", paidExgretiaAmt=" + paidExgretiaAmt + ", paidExgretiaDate=" + paidExgretiaDate + ", isExgretiaFinalized=" + isExgretiaFinalized + ", recStatus=" + recStatus + ", loginIdBonus=" + loginIdBonus + ", loginIdExgretia=" + loginIdExgretia + ", loginTimeBonus=" + loginTimeBonus + ", loginTimeExgretia=" + loginTimeExgretia + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setBonusApplicable(String bonusApplicable){
    this.bonusApplicable = bonusApplicable;
}


}
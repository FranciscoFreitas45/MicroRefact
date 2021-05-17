import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
public class BonusCalc {

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


public String getLoginTimeBonus(){
    return loginTimeBonus;
}


public double getNetExgretiaAmt(){
    return netExgretiaAmt;
}


public int getRecStatus(){
    return recStatus;
}


public double getGrossExgretiaAmt(){
    return grossExgretiaAmt;
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


public double getExgratiaPrcnt(){
    return exgratiaPrcnt;
}


public int getLoginIdBonus(){
    return loginIdBonus;
}


public String getBonusApplicable(){
    return bonusApplicable;
}


public String getIsExgretiaFinalized(){
    return isExgretiaFinalized;
}


public double getPaidExgretiaAmt(){
    return paidExgretiaAmt;
}


public int getEmpId(){
    return empId;
}


public double getDedBonusPujaAmt(){
    return dedBonusPujaAmt;
}


public String getBonusDetails(){
    return bonusDetails;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public int getLoginIdExgretia(){
    return loginIdExgretia;
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


public String getExgretiaDetails(){
    return exgretiaDetails;
}


public double getTotalExgretiaWages(){
    return totalExgretiaWages;
}


public double getPaidBonusAmt(){
    return paidBonusAmt;
}


public double getNetBonusAmt(){
    return netBonusAmt;
}


public String getDedReason(){
    return dedReason;
}


public String getCurrDesignation(){
    return currDesignation;
}


public String getCompanyEmpCode(){
    return companyEmpCode;
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


public String getLoginTimeExgretia(){
    return loginTimeExgretia;
}


public int getBonusId(){
    return bonusId;
}


public double getDedExgretiaAmt(){
    return dedExgretiaAmt;
}


}
import javax.persistence;
public class SalaryCalc {

 private  int id;

 private  int cmpId;

 private  int empId;

 private  String empCode;

 private  int empType;

 private  int contractorId;

 private  int departId;

 private  int designationId;

 private  int locationId;

 private  int calcMonth;

 private  int calcYear;

 private  int salTypeId;

 private  int attSumId;

 private  double basicCal;

 private  double performanceBonus;

 private  double otWages;

 private  double miscExpAdd;

 private  double bonusCal;

 private  double exgretiaCal;

 private  double daArreasCal;

 private  double incrementArreasCal;

 private  double epfWages;

 private  double epfWagesEmployer;

 private  double esicWagesCal;

 private  double grossSalary;

 private  double epsWages;

 private  double esicWagesDec;

 private  double employeePf;

 private  double employerEps;

 private  double employerPf;

 private  double esic;

 private  double employerEsic;

 private  int esicStatus;

 private  int pfStatus;

 private  double mlwf;

 private  double tds;

 private  double itded;

 private  double fund;

 private  double totPfAdminCh;

 private  double totEdliCh;

 private  double totEdliAdminCh;

 private  int ncpDays;

 private  int status;

 private  double ptDed;

 private  double advanceDed;

 private  double loanDed;

 private  double miscExpDed;

 private  int miscExpDedDeduct;

 private  double netSalary;

 private  String isLocked;

 private  String loginName;

 private  String loginTime;

 private  String mlwfApplicable;

 private  String ptApplicable;

 private  double payDed;

 private  String commentsForItBonus;

 private  double societyContribution;

 private  String empCategory;

 private  double basicDefault;

 private  double abDeduction;

 private  double epfPercentage;

 private  double epsEmployeePercentage;

 private  double productionInsentive;

 private  double epfEmployerPercentage;

 private  double epsEmployerPercentage;

 private  double presentInsentive;

 private  double nightAllow;

 private  double epsDefault;

 private  double epmloyerEpfDefault;

 private  double epmloyerEpfExtra;

 private  double pfAdminChPercentage;

 private  double edliPercentage;

 private  double edliAdminPercentage;

 private  double employerEsicPercentage;

 private  double employeeEsicPercentage;

 private  double employerMlwf;

 private  double grossSalDefault;

 private  double adjustMinus;

 private  double adjustPlus;

 private  double reward;

 private  double nightRate;

 private  double otRate;

 private  double bhatta;

 private  double other1;

 private  double leaveEncashAmt;


public double getGrossSalary(){
    return grossSalary;
}


public double getOther1(){
    return other1;
}


public int getCmpId(){
    return cmpId;
}


public int getStatus(){
    return status;
}


public double getEpsEmployerPercentage(){
    return epsEmployerPercentage;
}


public double getEpfWagesEmployer(){
    return epfWagesEmployer;
}


public double getEdliAdminPercentage(){
    return edliAdminPercentage;
}


public double getMiscExpAdd(){
    return miscExpAdd;
}


public double getEpfWages(){
    return epfWages;
}


public int getEmpType(){
    return empType;
}


public double getTotPfAdminCh(){
    return totPfAdminCh;
}


public double getLeaveEncashAmt(){
    return leaveEncashAmt;
}


public int getAttSumId(){
    return attSumId;
}


public double getBhatta(){
    return bhatta;
}


public int getEmpId(){
    return empId;
}


public String getLoginName(){
    return loginName;
}


public double getProductionInsentive(){
    return productionInsentive;
}


public double getEsicWagesDec(){
    return esicWagesDec;
}


public double getTotEdliCh(){
    return totEdliCh;
}


public double getAdvanceDed(){
    return advanceDed;
}


public double getNetSalary(){
    return netSalary;
}


public double getEmployerPf(){
    return employerPf;
}


public double getReward(){
    return reward;
}


public double getFund(){
    return fund;
}


public double getTds(){
    return tds;
}


public int getDesignationId(){
    return designationId;
}


public double getBasicCal(){
    return basicCal;
}


public double getOtRate(){
    return otRate;
}


public double getPtDed(){
    return ptDed;
}


public double getLoanDed(){
    return loanDed;
}


public double getEmployerEps(){
    return employerEps;
}


public String getPtApplicable(){
    return ptApplicable;
}


public double getEmployerEsicPercentage(){
    return employerEsicPercentage;
}


public int getSalTypeId(){
    return salTypeId;
}


public double getEpsDefault(){
    return epsDefault;
}


public String getEmpCode(){
    return empCode;
}


public double getDaArreasCal(){
    return daArreasCal;
}


public double getPerformanceBonus(){
    return performanceBonus;
}


public double getExgretiaCal(){
    return exgretiaCal;
}


public double getEmployeePf(){
    return employeePf;
}


public double getGrossSalDefault(){
    return grossSalDefault;
}


public int getLocationId(){
    return locationId;
}


public double getEmployerEsic(){
    return employerEsic;
}


public int getContractorId(){
    return contractorId;
}


public double getIncrementArreasCal(){
    return incrementArreasCal;
}


public String getEmpCategory(){
    return empCategory;
}


public double getEpfPercentage(){
    return epfPercentage;
}


public double getBonusCal(){
    return bonusCal;
}


public double getEmployeeEsicPercentage(){
    return employeeEsicPercentage;
}


public double getBasicDefault(){
    return basicDefault;
}


public double getMlwf(){
    return mlwf;
}


public double getPfAdminChPercentage(){
    return pfAdminChPercentage;
}


public double getNightRate(){
    return nightRate;
}


public int getPfStatus(){
    return pfStatus;
}


public double getEpmloyerEpfDefault(){
    return epmloyerEpfDefault;
}


public double getAdjustPlus(){
    return adjustPlus;
}


public double getOtWages(){
    return otWages;
}


public int getMiscExpDedDeduct(){
    return miscExpDedDeduct;
}


public int getCalcYear(){
    return calcYear;
}


public double getNightAllow(){
    return nightAllow;
}


public double getEsic(){
    return esic;
}


public String getCommentsForItBonus(){
    return commentsForItBonus;
}


public double getEsicWagesCal(){
    return esicWagesCal;
}


public double getEpmloyerEpfExtra(){
    return epmloyerEpfExtra;
}


public int getId(){
    return id;
}


public double getTotEdliAdminCh(){
    return totEdliAdminCh;
}


public int getCalcMonth(){
    return calcMonth;
}


public double getAbDeduction(){
    return abDeduction;
}


public double getAdjustMinus(){
    return adjustMinus;
}


public String getLoginTime(){
    return loginTime;
}


public double getEpsEmployeePercentage(){
    return epsEmployeePercentage;
}


public String getIsLocked(){
    return isLocked;
}


public double getEpfEmployerPercentage(){
    return epfEmployerPercentage;
}


public double getItded(){
    return itded;
}


public double getPresentInsentive(){
    return presentInsentive;
}


public double getEmployerMlwf(){
    return employerMlwf;
}


public int getEsicStatus(){
    return esicStatus;
}


public double getEpsWages(){
    return epsWages;
}


public int getNcpDays(){
    return ncpDays;
}


public double getEdliPercentage(){
    return edliPercentage;
}


public String getMlwfApplicable(){
    return mlwfApplicable;
}


public double getSocietyContribution(){
    return societyContribution;
}


public int getDepartId(){
    return departId;
}


public double getPayDed(){
    return payDed;
}


public double getMiscExpDed(){
    return miscExpDed;
}


}
import javax.persistence;
import java.util.List;
@Entity
public class EmpSalInfoDaiyInfoTempInfo {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  String uuid;

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

 private  double grossSalaryDytemp;

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

 private  int ncpDaysDytemp;

 private  int statusDytemp;

 private  double ptDed;

 private  double advanceDed;

 private  double loanDed;

 private  double miscExpDed;

 private  int miscExpDedDeduct;

 private  double netSalary;

 private  String isLocked;

 private  String mlwfApplicableDytemp;

 private  String ptApplicableDytemp;

 private  double payDed;

 private  String commentsForItBonus;

 private  double societyContributionDytemp;

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

 private  double adjustMinus;

 private  double adjustPlus;

 private  double reward;

 private  double nightRate;

 private  double otRate;

 private  double bhatta;

 private  double other1;

 private  double leaveEncashAmt;

@Transient
 private  double epsDefault;

@Transient
 private  double epmloyerEpfDefault;

@Transient
 private  double epmloyerEpfExtra;

@Transient
 private  double pfAdminChPercentage;

@Transient
 private  double edliPercentage;

@Transient
 private  double edliAdminPercentage;

@Transient
 private  double employerEsicPercentageSal;

@Transient
 private  double employeeEsicPercentageSal;

@Transient
 private  double employerMlwf;

 private  int salaryInfoId;

 private  int salaryTypeId;

 private  double basic;

 private  double da;

 private  double hra;

 private  double spa;

 private  String pfApplicable;

 private  String pfType;

 private  double pfEmpPer;

 private  double pfEmplrPer;

 private  String esicApplicable;

 private  String cmpJoiningDate;

 private  String cmpLeavingDate;

 private  String epfJoiningDate;

 private  String leavingReason;

 private  String salBasis;

 private  String ceilingLimitEmpApplicable;

 private  String ceilingLimitEmployerApplicable;

 private  String leavingReasonEsic;

 private  String leavingReasonPf;

 private  String mlwfApplicable;

 private  String ptApplicable;

 private  double grossSalary;

 private  double societyContribution;

 private  double basicCompany;

 private  double hraCompany;

 private  double daCompany;

 private  double employeeEsicPercentage;

 private  double employerEsicPercentage;

 private  int delStatus;

 private  int sumDailyId;

 private  int companyId;

 private  String empName;

 private  int month;

 private  int year;

 private  float workingDays;

 private  float presentDays;

 private  float weeklyOff;

 private  float paidHoliday;

 private  float paidLeave;

 private  float legalStrike;

 private  float layOff;

 private  float unpaidHoliday;

 private  float unpaidLeave;

 private  int absentDays;

 private  float payableDays;

 private  float ncpDays;

 private  float totlateMins;

 private  float totlateDays;

 private  float totoutMins;

 private  float totworkingHrs;

 private  float tototHrs;

 private  float totOthr;

 private  int totLate;

 private  String recStatus;

 private  String loginName;

 private  String loginTime;

 private  int status;

 private  String importDate;

 private  int recStatusPaid;

 private  int totalDaysInmonth;

 private  int lateDedLeavePaid;

 private  float holidayPresent;

 private  float weeklyOffPresent;

 private  float fullNight;

 private  float halfNight;

 private  float holidayPresentHalf;

 private  float weeklyOffPresentHalf;

 private  float weeklyOffHolidayOff;

 private  float weeklyOffHolidayOffPresent;

 private  float weeklyOffHolidayOffPresentHalfday;

 private  float hdpresentHdleave;

 private  int totEarlyGoing;

 private  String atsummUid;

 private  int calculationDone;

 private  String dob;

 private  int skillId;

 private  double rate;

 private  String dailyHr;

 private  String monthlyHrTarget;

 private  String monthlyMinimumTarget;

 private  String monthlyOtHr;

 private  int currentLoc;

 private  String gender;

 private  int otPerHr;

@Transient
 private  List<SalAllownceTemp> getAllowanceTempList;


public int getRecStatusPaid(){
    return recStatusPaid;
}


public float getUnpaidLeave(){
    return unpaidLeave;
}


public void setIsLocked(String isLocked){
    this.isLocked = isLocked;
}


public void setCmpLeavingDate(String cmpLeavingDate){
    this.cmpLeavingDate = cmpLeavingDate;
}


public List<SalAllownceTemp> getGetAllowanceTempList(){
    return getAllowanceTempList;
}


public int getCalculationDone(){
    return calculationDone;
}


public void setLayOff(float layOff){
    this.layOff = layOff;
}


public void setFund(double fund){
    this.fund = fund;
}


public String getImportDate(){
    return importDate;
}


public double getEpfWagesEmployer(){
    return epfWagesEmployer;
}


public void setWeeklyOff(float weeklyOff){
    this.weeklyOff = weeklyOff;
}


public void setGender(String gender){
    this.gender = gender;
}


public double getMiscExpAdd(){
    return miscExpAdd;
}


public float getLegalStrike(){
    return legalStrike;
}


public double getEpfWages(){
    return epfWages;
}


public void setEmployerEps(double employerEps){
    this.employerEps = employerEps;
}


public void setLoginName(String loginName){
    this.loginName = loginName;
}


public String getLeavingReasonPf(){
    return leavingReasonPf;
}


public int getMonth(){
    return month;
}


public double getTotPfAdminCh(){
    return totPfAdminCh;
}


public void setLeaveEncashAmt(double leaveEncashAmt){
    this.leaveEncashAmt = leaveEncashAmt;
}


public double getLeaveEncashAmt(){
    return leaveEncashAmt;
}


public void setTotlateDays(float totlateDays){
    this.totlateDays = totlateDays;
}


public double getBhatta(){
    return bhatta;
}


public void setTotlateMins(float totlateMins){
    this.totlateMins = totlateMins;
}


public void setMiscExpAdd(double miscExpAdd){
    this.miscExpAdd = miscExpAdd;
}


public void setPresentInsentive(double presentInsentive){
    this.presentInsentive = presentInsentive;
}


public int getEmpId(){
    return empId;
}


public float getWeeklyOffPresentHalf(){
    return weeklyOffPresentHalf;
}


public void setRate(double rate){
    this.rate = rate;
}


public String getLoginName(){
    return loginName;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public float getUnpaidHoliday(){
    return unpaidHoliday;
}


public int getSumDailyId(){
    return sumDailyId;
}


public void setGrossSalary(double grossSalary){
    this.grossSalary = grossSalary;
}


public void setTotEdliAdminCh(double totEdliAdminCh){
    this.totEdliAdminCh = totEdliAdminCh;
}


public void setCeilingLimitEmpApplicable(String ceilingLimitEmpApplicable){
    this.ceilingLimitEmpApplicable = ceilingLimitEmpApplicable;
}


public void setPtApplicableDytemp(String ptApplicableDytemp){
    this.ptApplicableDytemp = ptApplicableDytemp;
}


public String getCmpLeavingDate(){
    return cmpLeavingDate;
}


public void setWeeklyOffHolidayOffPresent(float weeklyOffHolidayOffPresent){
    this.weeklyOffHolidayOffPresent = weeklyOffHolidayOffPresent;
}


public void setTotoutMins(float totoutMins){
    this.totoutMins = totoutMins;
}


public float getTotworkingHrs(){
    return totworkingHrs;
}


public void setMonthlyOtHr(String monthlyOtHr){
    this.monthlyOtHr = monthlyOtHr;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public String getLeavingReason(){
    return leavingReason;
}


public void setContractorId(int contractorId){
    this.contractorId = contractorId;
}


public double getEmployerPf(){
    return employerPf;
}


public void setTds(double tds){
    this.tds = tds;
}


public double getFund(){
    return fund;
}


public void setWeeklyOffPresent(float weeklyOffPresent){
    this.weeklyOffPresent = weeklyOffPresent;
}


public int getDesignationId(){
    return designationId;
}


public int getSalaryTypeId(){
    return salaryTypeId;
}


public double getBasic(){
    return basic;
}


public void setEsicApplicable(String esicApplicable){
    this.esicApplicable = esicApplicable;
}


public void setEmployerEsicPercentageSal(double employerEsicPercentageSal){
    this.employerEsicPercentageSal = employerEsicPercentageSal;
}


public double getGrossSalaryDytemp(){
    return grossSalaryDytemp;
}


public void setEpfPercentage(double epfPercentage){
    this.epfPercentage = epfPercentage;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public double getPfEmpPer(){
    return pfEmpPer;
}


public void setEsicWagesDec(double esicWagesDec){
    this.esicWagesDec = esicWagesDec;
}


public void setEmployeeEsicPercentage(double employeeEsicPercentage){
    this.employeeEsicPercentage = employeeEsicPercentage;
}


public void setDaCompany(double daCompany){
    this.daCompany = daCompany;
}


public void setFullNight(float fullNight){
    this.fullNight = fullNight;
}


public double getOtRate(){
    return otRate;
}


public void setBasic(double basic){
    this.basic = basic;
}


public void setPaidHoliday(float paidHoliday){
    this.paidHoliday = paidHoliday;
}


public void setEpfWages(double epfWages){
    this.epfWages = epfWages;
}


public void setEpfEmployerPercentage(double epfEmployerPercentage){
    this.epfEmployerPercentage = epfEmployerPercentage;
}


public int getAbsentDays(){
    return absentDays;
}


public float getTotOthr(){
    return totOthr;
}


public void setCommentsForItBonus(String commentsForItBonus){
    this.commentsForItBonus = commentsForItBonus;
}


public void setMonth(int month){
    this.month = month;
}


public void setPfType(String pfType){
    this.pfType = pfType;
}


public void setMonthlyMinimumTarget(String monthlyMinimumTarget){
    this.monthlyMinimumTarget = monthlyMinimumTarget;
}


public void setTotOthr(float totOthr){
    this.totOthr = totOthr;
}


public void setOther1(double other1){
    this.other1 = other1;
}


public void setLeavingReasonEsic(String leavingReasonEsic){
    this.leavingReasonEsic = leavingReasonEsic;
}


public String getPtApplicable(){
    return ptApplicable;
}


public double getEmployerEsicPercentage(){
    return employerEsicPercentage;
}


public int getSalaryInfoId(){
    return salaryInfoId;
}


public float getWorkingDays(){
    return workingDays;
}


public String getEmpCode(){
    return empCode;
}


public float getTotoutMins(){
    return totoutMins;
}


public int getDelStatus(){
    return delStatus;
}


public void setCurrentLoc(int currentLoc){
    this.currentLoc = currentLoc;
}


public double getDaArreasCal(){
    return daArreasCal;
}


public void setMlwfApplicableDytemp(String mlwfApplicableDytemp){
    this.mlwfApplicableDytemp = mlwfApplicableDytemp;
}


public void setTotworkingHrs(float totworkingHrs){
    this.totworkingHrs = totworkingHrs;
}


public String getPfApplicable(){
    return pfApplicable;
}


public void setSumDailyId(int sumDailyId){
    this.sumDailyId = sumDailyId;
}


public void setEmployerPf(double employerPf){
    this.employerPf = employerPf;
}


public double getBasicCompany(){
    return basicCompany;
}


@Override
public String toString(){
    return "EmpSalInfoDaiyInfoTempInfo [uuid=" + uuid + ", id=" + id + ", cmpId=" + cmpId + ", empId=" + empId + ", empCode=" + empCode + ", empType=" + empType + ", contractorId=" + contractorId + ", departId=" + departId + ", designationId=" + designationId + ", locationId=" + locationId + ", calcMonth=" + calcMonth + ", calcYear=" + calcYear + ", salTypeId=" + salTypeId + ", attSumId=" + attSumId + ", basicCal=" + basicCal + ", performanceBonus=" + performanceBonus + ", otWages=" + otWages + ", miscExpAdd=" + miscExpAdd + ", bonusCal=" + bonusCal + ", exgretiaCal=" + exgretiaCal + ", daArreasCal=" + daArreasCal + ", incrementArreasCal=" + incrementArreasCal + ", epfWages=" + epfWages + ", epfWagesEmployer=" + epfWagesEmployer + ", esicWagesCal=" + esicWagesCal + ", grossSalaryDytemp=" + grossSalaryDytemp + ", epsWages=" + epsWages + ", esicWagesDec=" + esicWagesDec + ", employeePf=" + employeePf + ", employerEps=" + employerEps + ", employerPf=" + employerPf + ", esic=" + esic + ", employerEsic=" + employerEsic + ", esicStatus=" + esicStatus + ", pfStatus=" + pfStatus + ", mlwf=" + mlwf + ", tds=" + tds + ", itded=" + itded + ", fund=" + fund + ", totPfAdminCh=" + totPfAdminCh + ", totEdliCh=" + totEdliCh + ", totEdliAdminCh=" + totEdliAdminCh + ", ncpDaysDytemp=" + ncpDaysDytemp + ", statusDytemp=" + statusDytemp + ", ptDed=" + ptDed + ", advanceDed=" + advanceDed + ", loanDed=" + loanDed + ", miscExpDed=" + miscExpDed + ", miscExpDedDeduct=" + miscExpDedDeduct + ", netSalary=" + netSalary + ", isLocked=" + isLocked + ", mlwfApplicableDytemp=" + mlwfApplicableDytemp + ", ptApplicableDytemp=" + ptApplicableDytemp + ", payDed=" + payDed + ", commentsForItBonus=" + commentsForItBonus + ", societyContributionDytemp=" + societyContributionDytemp + ", empCategory=" + empCategory + ", basicDefault=" + basicDefault + ", abDeduction=" + abDeduction + ", epfPercentage=" + epfPercentage + ", epsEmployeePercentage=" + epsEmployeePercentage + ", productionInsentive=" + productionInsentive + ", epfEmployerPercentage=" + epfEmployerPercentage + ", epsEmployerPercentage=" + epsEmployerPercentage + ", presentInsentive=" + presentInsentive + ", nightAllow=" + nightAllow + ", adjustMinus=" + adjustMinus + ", adjustPlus=" + adjustPlus + ", reward=" + reward + ", nightRate=" + nightRate + ", otRate=" + otRate + ", bhatta=" + bhatta + ", other1=" + other1 + ", leaveEncashAmt=" + leaveEncashAmt + ", epsDefault=" + epsDefault + ", epmloyerEpfDefault=" + epmloyerEpfDefault + ", epmloyerEpfExtra=" + epmloyerEpfExtra + ", pfAdminChPercentage=" + pfAdminChPercentage + ", edliPercentage=" + edliPercentage + ", edliAdminPercentage=" + edliAdminPercentage + ", employerEsicPercentageSal=" + employerEsicPercentageSal + ", employeeEsicPercentageSal=" + employeeEsicPercentageSal + ", employerMlwf=" + employerMlwf + ", salaryInfoId=" + salaryInfoId + ", salaryTypeId=" + salaryTypeId + ", basic=" + basic + ", da=" + da + ", hra=" + hra + ", spa=" + spa + ", pfApplicable=" + pfApplicable + ", pfType=" + pfType + ", pfEmpPer=" + pfEmpPer + ", pfEmplrPer=" + pfEmplrPer + ", esicApplicable=" + esicApplicable + ", cmpJoiningDate=" + cmpJoiningDate + ", cmpLeavingDate=" + cmpLeavingDate + ", epfJoiningDate=" + epfJoiningDate + ", leavingReason=" + leavingReason + ", salBasis=" + salBasis + ", ceilingLimitEmpApplicable=" + ceilingLimitEmpApplicable + ", ceilingLimitEmployerApplicable=" + ceilingLimitEmployerApplicable + ", leavingReasonEsic=" + leavingReasonEsic + ", leavingReasonPf=" + leavingReasonPf + ", mlwfApplicable=" + mlwfApplicable + ", ptApplicable=" + ptApplicable + ", grossSalary=" + grossSalary + ", societyContribution=" + societyContribution + ", basicCompany=" + basicCompany + ", hraCompany=" + hraCompany + ", daCompany=" + daCompany + ", employeeEsicPercentage=" + employeeEsicPercentage + ", employerEsicPercentage=" + employerEsicPercentage + ", delStatus=" + delStatus + ", sumDailyId=" + sumDailyId + ", companyId=" + companyId + ", empName=" + empName + ", month=" + month + ", year=" + year + ", workingDays=" + workingDays + ", presentDays=" + presentDays + ", weeklyOff=" + weeklyOff + ", paidHoliday=" + paidHoliday + ", paidLeave=" + paidLeave + ", legalStrike=" + legalStrike + ", layOff=" + layOff + ", unpaidHoliday=" + unpaidHoliday + ", unpaidLeave=" + unpaidLeave + ", absentDays=" + absentDays + ", payableDays=" + payableDays + ", ncpDays=" + ncpDays + ", totlateMins=" + totlateMins + ", totlateDays=" + totlateDays + ", totoutMins=" + totoutMins + ", totworkingHrs=" + totworkingHrs + ", tototHrs=" + tototHrs + ", totOthr=" + totOthr + ", totLate=" + totLate + ", recStatus=" + recStatus + ", loginName=" + loginName + ", loginTime=" + loginTime + ", status=" + status + ", importDate=" + importDate + ", recStatusPaid=" + recStatusPaid + ", totalDaysInmonth=" + totalDaysInmonth + ", lateDedLeavePaid=" + lateDedLeavePaid + ", holidayPresent=" + holidayPresent + ", weeklyOffPresent=" + weeklyOffPresent + ", fullNight=" + fullNight + ", halfNight=" + halfNight + ", holidayPresentHalf=" + holidayPresentHalf + ", weeklyOffPresentHalf=" + weeklyOffPresentHalf + ", weeklyOffHolidayOff=" + weeklyOffHolidayOff + ", weeklyOffHolidayOffPresent=" + weeklyOffHolidayOffPresent + ", weeklyOffHolidayOffPresentHalfday=" + weeklyOffHolidayOffPresentHalfday + ", hdpresentHdleave=" + hdpresentHdleave + ", totEarlyGoing=" + totEarlyGoing + ", atsummUid=" + atsummUid + ", calculationDone=" + calculationDone + ", dob=" + dob + ", skillId=" + skillId + ", rate=" + rate + ", dailyHr=" + dailyHr + ", monthlyHrTarget=" + monthlyHrTarget + ", monthlyMinimumTarget=" + monthlyMinimumTarget + ", monthlyOtHr=" + monthlyOtHr + ", currentLoc=" + currentLoc + ", gender=" + gender + ", otPerHr=" + otPerHr + ", getAllowanceTempList=" + getAllowanceTempList + "]";
}


public double getPerformanceBonus(){
    return performanceBonus;
}


public int getTotalDaysInmonth(){
    return totalDaysInmonth;
}


public void setPaidLeave(float paidLeave){
    this.paidLeave = paidLeave;
}


public float getWeeklyOffHolidayOffPresent(){
    return weeklyOffHolidayOffPresent;
}


public void setMlwf(double mlwf){
    this.mlwf = mlwf;
}


public float getHolidayPresentHalf(){
    return holidayPresentHalf;
}


public float getHdpresentHdleave(){
    return hdpresentHdleave;
}


public void setEsicWagesCal(double esicWagesCal){
    this.esicWagesCal = esicWagesCal;
}


public void setPfAdminChPercentage(double pfAdminChPercentage){
    this.pfAdminChPercentage = pfAdminChPercentage;
}


public double getEmployerEsic(){
    return employerEsic;
}


public int getStatusDytemp(){
    return statusDytemp;
}


public void setSocietyContribution(double societyContribution){
    this.societyContribution = societyContribution;
}


public void setExgretiaCal(double exgretiaCal){
    this.exgretiaCal = exgretiaCal;
}


public void setWorkingDays(float workingDays){
    this.workingDays = workingDays;
}


public void setAbsentDays(int absentDays){
    this.absentDays = absentDays;
}


public String getEmpCategory(){
    return empCategory;
}


public String getCeilingLimitEmployerApplicable(){
    return ceilingLimitEmployerApplicable;
}


public float getFullNight(){
    return fullNight;
}


public double getBonusCal(){
    return bonusCal;
}


public void setIncrementArreasCal(double incrementArreasCal){
    this.incrementArreasCal = incrementArreasCal;
}


public void setItded(double itded){
    this.itded = itded;
}


public void setEmployeeEsicPercentageSal(double employeeEsicPercentageSal){
    this.employeeEsicPercentageSal = employeeEsicPercentageSal;
}


public double getBasicDefault(){
    return basicDefault;
}


public double getMlwf(){
    return mlwf;
}


public void setReward(double reward){
    this.reward = reward;
}


public void setPfApplicable(String pfApplicable){
    this.pfApplicable = pfApplicable;
}


public void setCeilingLimitEmployerApplicable(String ceilingLimitEmployerApplicable){
    this.ceilingLimitEmployerApplicable = ceilingLimitEmployerApplicable;
}


public double getPfAdminChPercentage(){
    return pfAdminChPercentage;
}


public void setEmpCategory(String empCategory){
    this.empCategory = empCategory;
}


public float getPresentDays(){
    return presentDays;
}


public double getNightRate(){
    return nightRate;
}


public int getPfStatus(){
    return pfStatus;
}


public float getHolidayPresent(){
    return holidayPresent;
}


public void setDepartId(int departId){
    this.departId = departId;
}


public int getMiscExpDedDeduct(){
    return miscExpDedDeduct;
}


public int getCalcYear(){
    return calcYear;
}


public float getPaidHoliday(){
    return paidHoliday;
}


public String getPfType(){
    return pfType;
}


public double getNightAllow(){
    return nightAllow;
}


public void setEdliAdminPercentage(double edliAdminPercentage){
    this.edliAdminPercentage = edliAdminPercentage;
}


public void setPresentDays(float presentDays){
    this.presentDays = presentDays;
}


public float getPayableDays(){
    return payableDays;
}


public void setHolidayPresent(float holidayPresent){
    this.holidayPresent = holidayPresent;
}


public int getTotEarlyGoing(){
    return totEarlyGoing;
}


public String getEpfJoiningDate(){
    return epfJoiningDate;
}


public int getId(){
    return id;
}


public void setNightAllow(double nightAllow){
    this.nightAllow = nightAllow;
}


public int getCalcMonth(){
    return calcMonth;
}


public void setPfEmplrPer(double pfEmplrPer){
    this.pfEmplrPer = pfEmplrPer;
}


public double getDaCompany(){
    return daCompany;
}


public float getLayOff(){
    return layOff;
}


public float getWeeklyOff(){
    return weeklyOff;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public double getRate(){
    return rate;
}


public void setLoginTime(String loginTime){
    this.loginTime = loginTime;
}


public void setAttSumId(int attSumId){
    this.attSumId = attSumId;
}


public void setUnpaidHoliday(float unpaidHoliday){
    this.unpaidHoliday = unpaidHoliday;
}


public String getIsLocked(){
    return isLocked;
}


public void setHra(double hra){
    this.hra = hra;
}


public double getEpfEmployerPercentage(){
    return epfEmployerPercentage;
}


public int getLateDedLeavePaid(){
    return lateDedLeavePaid;
}


public int getSkillId(){
    return skillId;
}


public void setEpsWages(double epsWages){
    this.epsWages = epsWages;
}


public void setEpmloyerEpfExtra(double epmloyerEpfExtra){
    this.epmloyerEpfExtra = epmloyerEpfExtra;
}


public double getEmployerMlwf(){
    return employerMlwf;
}


public int getEsicStatus(){
    return esicStatus;
}


public void setRecStatusPaid(int recStatusPaid){
    this.recStatusPaid = recStatusPaid;
}


public double getEpsWages(){
    return epsWages;
}


public double getHra(){
    return hra;
}


public String getMlwfApplicable(){
    return mlwfApplicable;
}


public void setNcpDaysDytemp(int ncpDaysDytemp){
    this.ncpDaysDytemp = ncpDaysDytemp;
}


public double getSocietyContribution(){
    return societyContribution;
}


public void setEsicStatus(int esicStatus){
    this.esicStatus = esicStatus;
}


public void setLegalStrike(float legalStrike){
    this.legalStrike = legalStrike;
}


public void setHalfNight(float halfNight){
    this.halfNight = halfNight;
}


public void setDailyHr(String dailyHr){
    this.dailyHr = dailyHr;
}


public void setEpfJoiningDate(String epfJoiningDate){
    this.epfJoiningDate = epfJoiningDate;
}


public void setEpmloyerEpfDefault(double epmloyerEpfDefault){
    this.epmloyerEpfDefault = epmloyerEpfDefault;
}


public int getDepartId(){
    return departId;
}


public double getPayDed(){
    return payDed;
}


public int getCompanyId(){
    return companyId;
}


public void setNcpDays(float ncpDays){
    this.ncpDays = ncpDays;
}


public void setDesignationId(int designationId){
    this.designationId = designationId;
}


public void setDa(double da){
    this.da = da;
}


public void setOtPerHr(int otPerHr){
    this.otPerHr = otPerHr;
}


public void setEpfWagesEmployer(double epfWagesEmployer){
    this.epfWagesEmployer = epfWagesEmployer;
}


public double getGrossSalary(){
    return grossSalary;
}


public double getOther1(){
    return other1;
}


public void setEmpType(int empType){
    this.empType = empType;
}


public void setSalaryTypeId(int salaryTypeId){
    this.salaryTypeId = salaryTypeId;
}


public int getCmpId(){
    return cmpId;
}


public void setTotalDaysInmonth(int totalDaysInmonth){
    this.totalDaysInmonth = totalDaysInmonth;
}


public int getStatus(){
    return status;
}


public void setPfStatus(int pfStatus){
    this.pfStatus = pfStatus;
}


public void setHraCompany(double hraCompany){
    this.hraCompany = hraCompany;
}


public double getEpsEmployerPercentage(){
    return epsEmployerPercentage;
}


public void setMlwfApplicable(String mlwfApplicable){
    this.mlwfApplicable = mlwfApplicable;
}


public double getEdliAdminPercentage(){
    return edliAdminPercentage;
}


public String getPtApplicableDytemp(){
    return ptApplicableDytemp;
}


public float getWeeklyOffHolidayOff(){
    return weeklyOffHolidayOff;
}


public void setUnpaidLeave(float unpaidLeave){
    this.unpaidLeave = unpaidLeave;
}


public String getRecStatus(){
    return recStatus;
}


public void setSalTypeId(int salTypeId){
    this.salTypeId = salTypeId;
}


public int getEmpType(){
    return empType;
}


public void setBasicCompany(double basicCompany){
    this.basicCompany = basicCompany;
}


public double getHraCompany(){
    return hraCompany;
}


public int getAttSumId(){
    return attSumId;
}


public void setSocietyContributionDytemp(double societyContributionDytemp){
    this.societyContributionDytemp = societyContributionDytemp;
}


public String getGender(){
    return gender;
}


public void setRecStatus(String recStatus){
    this.recStatus = recStatus;
}


public void setWeeklyOffHolidayOffPresentHalfday(float weeklyOffHolidayOffPresentHalfday){
    this.weeklyOffHolidayOffPresentHalfday = weeklyOffHolidayOffPresentHalfday;
}


public String getMonthlyMinimumTarget(){
    return monthlyMinimumTarget;
}


public void setWeeklyOffPresentHalf(float weeklyOffPresentHalf){
    this.weeklyOffPresentHalf = weeklyOffPresentHalf;
}


public void setEmployeePf(double employeePf){
    this.employeePf = employeePf;
}


public String getSalBasis(){
    return salBasis;
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


public void setMonthlyHrTarget(String monthlyHrTarget){
    this.monthlyHrTarget = monthlyHrTarget;
}


public int getYear(){
    return year;
}


public double getAdvanceDed(){
    return advanceDed;
}


public void setEpsDefault(double epsDefault){
    this.epsDefault = epsDefault;
}


public double getNetSalary(){
    return netSalary;
}


public void setStatusDytemp(int statusDytemp){
    this.statusDytemp = statusDytemp;
}


public double getReward(){
    return reward;
}


public void setBonusCal(double bonusCal){
    this.bonusCal = bonusCal;
}


public double getTds(){
    return tds;
}


public void setBhatta(double bhatta){
    this.bhatta = bhatta;
}


public void setGetAllowanceTempList(List<SalAllownceTemp> getAllowanceTempList){
    this.getAllowanceTempList = getAllowanceTempList;
}


public double getBasicCal(){
    return basicCal;
}


public float getWeeklyOffPresent(){
    return weeklyOffPresent;
}


public void setEpsEmployeePercentage(double epsEmployeePercentage){
    this.epsEmployeePercentage = epsEmployeePercentage;
}


public void setPayableDays(float payableDays){
    this.payableDays = payableDays;
}


public double getPtDed(){
    return ptDed;
}


public void setOtWages(double otWages){
    this.otWages = otWages;
}


public void setOtRate(double otRate){
    this.otRate = otRate;
}


public void setTotLate(int totLate){
    this.totLate = totLate;
}


public double getLoanDed(){
    return loanDed;
}


public double getPfEmplrPer(){
    return pfEmplrPer;
}


public double getEmployerEps(){
    return employerEps;
}


public void setEmployerMlwf(double employerMlwf){
    this.employerMlwf = employerMlwf;
}


public void setLoanDed(double loanDed){
    this.loanDed = loanDed;
}


public double getEmployerEsicPercentageSal(){
    return employerEsicPercentageSal;
}


public String getMonthlyOtHr(){
    return monthlyOtHr;
}


public void setAtsummUid(String atsummUid){
    this.atsummUid = atsummUid;
}


public void setPerformanceBonus(double performanceBonus){
    this.performanceBonus = performanceBonus;
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


public void setCmpId(int cmpId){
    this.cmpId = cmpId;
}


public float getTotlateDays(){
    return totlateDays;
}


public int getSalTypeId(){
    return salTypeId;
}


public void setAdvanceDed(double advanceDed){
    this.advanceDed = advanceDed;
}


public double getEpsDefault(){
    return epsDefault;
}


public void setTotEdliCh(double totEdliCh){
    this.totEdliCh = totEdliCh;
}


public String getCeilingLimitEmpApplicable(){
    return ceilingLimitEmpApplicable;
}


public void setBasicCal(double basicCal){
    this.basicCal = basicCal;
}


public String getLeavingReasonEsic(){
    return leavingReasonEsic;
}


public void setDob(String dob){
    this.dob = dob;
}


public float getTotlateMins(){
    return totlateMins;
}


public int getTotLate(){
    return totLate;
}


public double getEmployeeEsicPercentageSal(){
    return employeeEsicPercentageSal;
}


public double getExgretiaCal(){
    return exgretiaCal;
}


public double getEmployeePf(){
    return employeePf;
}


public void setCalcYear(int calcYear){
    this.calcYear = calcYear;
}


public int getLocationId(){
    return locationId;
}


public String getCmpJoiningDate(){
    return cmpJoiningDate;
}


public void setEsic(double esic){
    this.esic = esic;
}


public int getNcpDaysDytemp(){
    return ncpDaysDytemp;
}


public void setHolidayPresentHalf(float holidayPresentHalf){
    this.holidayPresentHalf = holidayPresentHalf;
}


public void setPfEmpPer(double pfEmpPer){
    this.pfEmpPer = pfEmpPer;
}


public String getDob(){
    return dob;
}


public String getMonthlyHrTarget(){
    return monthlyHrTarget;
}


public int getContractorId(){
    return contractorId;
}


public void setHdpresentHdleave(float hdpresentHdleave){
    this.hdpresentHdleave = hdpresentHdleave;
}


public String getAtsummUid(){
    return atsummUid;
}


public double getIncrementArreasCal(){
    return incrementArreasCal;
}


public void setSalBasis(String salBasis){
    this.salBasis = salBasis;
}


public void setGrossSalaryDytemp(double grossSalaryDytemp){
    this.grossSalaryDytemp = grossSalaryDytemp;
}


public void setPtDed(double ptDed){
    this.ptDed = ptDed;
}


public double getEpfPercentage(){
    return epfPercentage;
}


public void setId(int id){
    this.id = id;
}


public float getWeeklyOffHolidayOffPresentHalfday(){
    return weeklyOffHolidayOffPresentHalfday;
}


public double getEmployeeEsicPercentage(){
    return employeeEsicPercentage;
}


public int getCurrentLoc(){
    return currentLoc;
}


public double getDa(){
    return da;
}


public void setCmpJoiningDate(String cmpJoiningDate){
    this.cmpJoiningDate = cmpJoiningDate;
}


public void setDaArreasCal(double daArreasCal){
    this.daArreasCal = daArreasCal;
}


public double getEpmloyerEpfDefault(){
    return epmloyerEpfDefault;
}


public float getPaidLeave(){
    return paidLeave;
}


public double getAdjustPlus(){
    return adjustPlus;
}


public void setSkillId(int skillId){
    this.skillId = skillId;
}


public String getEsicApplicable(){
    return esicApplicable;
}


public String getUuid(){
    return uuid;
}


public void setMiscExpDedDeduct(int miscExpDedDeduct){
    this.miscExpDedDeduct = miscExpDedDeduct;
}


public double getOtWages(){
    return otWages;
}


public void setTotEarlyGoing(int totEarlyGoing){
    this.totEarlyGoing = totEarlyGoing;
}


public float getTototHrs(){
    return tototHrs;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setCalculationDone(int calculationDone){
    this.calculationDone = calculationDone;
}


public double getEsic(){
    return esic;
}


public String getCommentsForItBonus(){
    return commentsForItBonus;
}


public void setEmployerEsic(double employerEsic){
    this.employerEsic = employerEsic;
}


public double getEsicWagesCal(){
    return esicWagesCal;
}


public void setEdliPercentage(double edliPercentage){
    this.edliPercentage = edliPercentage;
}


public double getEpmloyerEpfExtra(){
    return epmloyerEpfExtra;
}


public void setAbDeduction(double abDeduction){
    this.abDeduction = abDeduction;
}


public int getOtPerHr(){
    return otPerHr;
}


public void setPayDed(double payDed){
    this.payDed = payDed;
}


public double getSocietyContributionDytemp(){
    return societyContributionDytemp;
}


public double getTotEdliAdminCh(){
    return totEdliAdminCh;
}


public void setMiscExpDed(double miscExpDed){
    this.miscExpDed = miscExpDed;
}


public String getEmpName(){
    return empName;
}


public String getMlwfApplicableDytemp(){
    return mlwfApplicableDytemp;
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


public void setLeavingReasonPf(String leavingReasonPf){
    this.leavingReasonPf = leavingReasonPf;
}


public double getEpsEmployeePercentage(){
    return epsEmployeePercentage;
}


public void setSalaryInfoId(int salaryInfoId){
    this.salaryInfoId = salaryInfoId;
}


public float getHalfNight(){
    return halfNight;
}


public void setPtApplicable(String ptApplicable){
    this.ptApplicable = ptApplicable;
}


public double getItded(){
    return itded;
}


public double getPresentInsentive(){
    return presentInsentive;
}


public void setEmployerEsicPercentage(double employerEsicPercentage){
    this.employerEsicPercentage = employerEsicPercentage;
}


public void setSpa(double spa){
    this.spa = spa;
}


public void setBasicDefault(double basicDefault){
    this.basicDefault = basicDefault;
}


public void setWeeklyOffHolidayOff(float weeklyOffHolidayOff){
    this.weeklyOffHolidayOff = weeklyOffHolidayOff;
}


public void setLeavingReason(String leavingReason){
    this.leavingReason = leavingReason;
}


public void setAdjustPlus(double adjustPlus){
    this.adjustPlus = adjustPlus;
}


public void setCalcMonth(int calcMonth){
    this.calcMonth = calcMonth;
}


public double getSpa(){
    return spa;
}


public void setProductionInsentive(double productionInsentive){
    this.productionInsentive = productionInsentive;
}


public void setEpsEmployerPercentage(double epsEmployerPercentage){
    this.epsEmployerPercentage = epsEmployerPercentage;
}


public float getNcpDays(){
    return ncpDays;
}


public double getEdliPercentage(){
    return edliPercentage;
}


public void setLateDedLeavePaid(int lateDedLeavePaid){
    this.lateDedLeavePaid = lateDedLeavePaid;
}


public void setYear(int year){
    this.year = year;
}


public void setStatus(int status){
    this.status = status;
}


public String getDailyHr(){
    return dailyHr;
}


public void setNightRate(double nightRate){
    this.nightRate = nightRate;
}


public void setTotPfAdminCh(double totPfAdminCh){
    this.totPfAdminCh = totPfAdminCh;
}


public void setLocationId(int locationId){
    this.locationId = locationId;
}


public void setNetSalary(double netSalary){
    this.netSalary = netSalary;
}


public double getMiscExpDed(){
    return miscExpDed;
}


public void setTototHrs(float tototHrs){
    this.tototHrs = tototHrs;
}


public void setImportDate(String importDate){
    this.importDate = importDate;
}


public void setAdjustMinus(double adjustMinus){
    this.adjustMinus = adjustMinus;
}


}
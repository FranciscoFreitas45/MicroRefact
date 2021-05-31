import javax.persistence;
@Entity
@Table(name = "tbl_salary_calc")
public class SalaryCalc {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
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


public void setEpfWagesEmployer(double epfWagesEmployer){
    this.epfWagesEmployer = epfWagesEmployer;
}


public double getGrossSalary(){
    return grossSalary;
}


public void setIsLocked(String isLocked){
    this.isLocked = isLocked;
}


public double getOther1(){
    return other1;
}


public void setEmpType(int empType){
    this.empType = empType;
}


public int getCmpId(){
    return cmpId;
}


public int getStatus(){
    return status;
}


public void setFund(double fund){
    this.fund = fund;
}


public void setPfStatus(int pfStatus){
    this.pfStatus = pfStatus;
}


public double getEpsEmployerPercentage(){
    return epsEmployerPercentage;
}


public double getEpfWagesEmployer(){
    return epfWagesEmployer;
}


public void setMlwfApplicable(String mlwfApplicable){
    this.mlwfApplicable = mlwfApplicable;
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


public void setEmployerEps(double employerEps){
    this.employerEps = employerEps;
}


public void setLoginName(String loginName){
    this.loginName = loginName;
}


public void setSalTypeId(int salTypeId){
    this.salTypeId = salTypeId;
}


public int getEmpType(){
    return empType;
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


public int getAttSumId(){
    return attSumId;
}


public double getBhatta(){
    return bhatta;
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


public String getLoginName(){
    return loginName;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setEmployeePf(double employeePf){
    this.employeePf = employeePf;
}


public double getProductionInsentive(){
    return productionInsentive;
}


public double getEsicWagesDec(){
    return esicWagesDec;
}


public void setGrossSalary(double grossSalary){
    this.grossSalary = grossSalary;
}


public double getTotEdliCh(){
    return totEdliCh;
}


public void setTotEdliAdminCh(double totEdliAdminCh){
    this.totEdliAdminCh = totEdliAdminCh;
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


public void setContractorId(int contractorId){
    this.contractorId = contractorId;
}


public double getEmployerPf(){
    return employerPf;
}


public void setTds(double tds){
    this.tds = tds;
}


public double getReward(){
    return reward;
}


public double getFund(){
    return fund;
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


public int getDesignationId(){
    return designationId;
}


public double getBasicCal(){
    return basicCal;
}


public void setEpfPercentage(double epfPercentage){
    this.epfPercentage = epfPercentage;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setEsicWagesDec(double esicWagesDec){
    this.esicWagesDec = esicWagesDec;
}


public void setEmployeeEsicPercentage(double employeeEsicPercentage){
    this.employeeEsicPercentage = employeeEsicPercentage;
}


public void setEpsEmployeePercentage(double epsEmployeePercentage){
    this.epsEmployeePercentage = epsEmployeePercentage;
}


public double getOtRate(){
    return otRate;
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


public void setEpfWages(double epfWages){
    this.epfWages = epfWages;
}


public void setEpfEmployerPercentage(double epfEmployerPercentage){
    this.epfEmployerPercentage = epfEmployerPercentage;
}


public double getLoanDed(){
    return loanDed;
}


public double getEmployerEps(){
    return employerEps;
}


public void setEmployerMlwf(double employerMlwf){
    this.employerMlwf = employerMlwf;
}


public void setCommentsForItBonus(String commentsForItBonus){
    this.commentsForItBonus = commentsForItBonus;
}


public void setLoanDed(double loanDed){
    this.loanDed = loanDed;
}


public void setOther1(double other1){
    this.other1 = other1;
}


public void setPerformanceBonus(double performanceBonus){
    this.performanceBonus = performanceBonus;
}


public void setCmpId(int cmpId){
    this.cmpId = cmpId;
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


public void setAdvanceDed(double advanceDed){
    this.advanceDed = advanceDed;
}


public double getEpsDefault(){
    return epsDefault;
}


public String getEmpCode(){
    return empCode;
}


public void setTotEdliCh(double totEdliCh){
    this.totEdliCh = totEdliCh;
}


public void setBasicCal(double basicCal){
    this.basicCal = basicCal;
}


public double getDaArreasCal(){
    return daArreasCal;
}


public void setEmployerPf(double employerPf){
    this.employerPf = employerPf;
}


@Override
public String toString(){
    return "SalaryCalc [id=" + id + ", cmpId=" + cmpId + ", empId=" + empId + ", empCode=" + empCode + ", empType=" + empType + ", contractorId=" + contractorId + ", departId=" + departId + ", designationId=" + designationId + ", locationId=" + locationId + ", calcMonth=" + calcMonth + ", calcYear=" + calcYear + ", salTypeId=" + salTypeId + ", attSumId=" + attSumId + ", basicCal=" + basicCal + ", performanceBonus=" + performanceBonus + ", otWages=" + otWages + ", miscExpAdd=" + miscExpAdd + ", bonusCal=" + bonusCal + ", exgretiaCal=" + exgretiaCal + ", daArreasCal=" + daArreasCal + ", incrementArreasCal=" + incrementArreasCal + ", epfWages=" + epfWages + ", epfWagesEmployer=" + epfWagesEmployer + ", esicWagesCal=" + esicWagesCal + ", grossSalary=" + grossSalary + ", epsWages=" + epsWages + ", esicWagesDec=" + esicWagesDec + ", employeePf=" + employeePf + ", employerEps=" + employerEps + ", employerPf=" + employerPf + ", esic=" + esic + ", employerEsic=" + employerEsic + ", esicStatus=" + esicStatus + ", pfStatus=" + pfStatus + ", mlwf=" + mlwf + ", tds=" + tds + ", itded=" + itded + ", fund=" + fund + ", totPfAdminCh=" + totPfAdminCh + ", totEdliCh=" + totEdliCh + ", totEdliAdminCh=" + totEdliAdminCh + ", ncpDays=" + ncpDays + ", status=" + status + ", ptDed=" + ptDed + ", advanceDed=" + advanceDed + ", loanDed=" + loanDed + ", miscExpDed=" + miscExpDed + ", miscExpDedDeduct=" + miscExpDedDeduct + ", netSalary=" + netSalary + ", isLocked=" + isLocked + ", loginName=" + loginName + ", loginTime=" + loginTime + ", mlwfApplicable=" + mlwfApplicable + ", ptApplicable=" + ptApplicable + ", payDed=" + payDed + ", commentsForItBonus=" + commentsForItBonus + ", societyContribution=" + societyContribution + ", empCategory=" + empCategory + ", basicDefault=" + basicDefault + ", abDeduction=" + abDeduction + ", epfPercentage=" + epfPercentage + ", epsEmployeePercentage=" + epsEmployeePercentage + ", productionInsentive=" + productionInsentive + ", epfEmployerPercentage=" + epfEmployerPercentage + ", epsEmployerPercentage=" + epsEmployerPercentage + ", presentInsentive=" + presentInsentive + ", nightAllow=" + nightAllow + ", epsDefault=" + epsDefault + ", epmloyerEpfDefault=" + epmloyerEpfDefault + ", epmloyerEpfExtra=" + epmloyerEpfExtra + ", pfAdminChPercentage=" + pfAdminChPercentage + ", edliPercentage=" + edliPercentage + ", edliAdminPercentage=" + edliAdminPercentage + ", employerEsicPercentage=" + employerEsicPercentage + ", employeeEsicPercentage=" + employeeEsicPercentage + ", employerMlwf=" + employerMlwf + ", grossSalDefault=" + grossSalDefault + ", adjustMinus=" + adjustMinus + ", adjustPlus=" + adjustPlus + ", reward=" + reward + ", nightRate=" + nightRate + ", otRate=" + otRate + ", bhatta=" + bhatta + ", other1=" + other1 + ", leaveEncashAmt=" + leaveEncashAmt + "]";
}


public double getPerformanceBonus(){
    return performanceBonus;
}


public double getExgretiaCal(){
    return exgretiaCal;
}


public void setMlwf(double mlwf){
    this.mlwf = mlwf;
}


public double getEmployeePf(){
    return employeePf;
}


public double getGrossSalDefault(){
    return grossSalDefault;
}


public void setCalcYear(int calcYear){
    this.calcYear = calcYear;
}


public int getLocationId(){
    return locationId;
}


public void setEsicWagesCal(double esicWagesCal){
    this.esicWagesCal = esicWagesCal;
}


public void setEsic(double esic){
    this.esic = esic;
}


public void setGrossSalDefault(double grossSalDefault){
    this.grossSalDefault = grossSalDefault;
}


public void setPfAdminChPercentage(double pfAdminChPercentage){
    this.pfAdminChPercentage = pfAdminChPercentage;
}


public double getEmployerEsic(){
    return employerEsic;
}


public void setSocietyContribution(double societyContribution){
    this.societyContribution = societyContribution;
}


public void setExgretiaCal(double exgretiaCal){
    this.exgretiaCal = exgretiaCal;
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


public void setPtDed(double ptDed){
    this.ptDed = ptDed;
}


public double getEpfPercentage(){
    return epfPercentage;
}


public void setId(int id){
    this.id = id;
}


public double getBonusCal(){
    return bonusCal;
}


public void setIncrementArreasCal(double incrementArreasCal){
    this.incrementArreasCal = incrementArreasCal;
}


public double getEmployeeEsicPercentage(){
    return employeeEsicPercentage;
}


public void setItded(double itded){
    this.itded = itded;
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


public double getPfAdminChPercentage(){
    return pfAdminChPercentage;
}


public void setEmpCategory(String empCategory){
    this.empCategory = empCategory;
}


public void setDaArreasCal(double daArreasCal){
    this.daArreasCal = daArreasCal;
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


public void setDepartId(int departId){
    this.departId = departId;
}


public void setMiscExpDedDeduct(int miscExpDedDeduct){
    this.miscExpDedDeduct = miscExpDedDeduct;
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


public void setEdliAdminPercentage(double edliAdminPercentage){
    this.edliAdminPercentage = edliAdminPercentage;
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


public void setPayDed(double payDed){
    this.payDed = payDed;
}


public int getId(){
    return id;
}


public double getTotEdliAdminCh(){
    return totEdliAdminCh;
}


public void setNightAllow(double nightAllow){
    this.nightAllow = nightAllow;
}


public int getCalcMonth(){
    return calcMonth;
}


public void setMiscExpDed(double miscExpDed){
    this.miscExpDed = miscExpDed;
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


public void setLoginTime(String loginTime){
    this.loginTime = loginTime;
}


public void setAttSumId(int attSumId){
    this.attSumId = attSumId;
}


public double getEpsEmployeePercentage(){
    return epsEmployeePercentage;
}


public String getIsLocked(){
    return isLocked;
}


public void setPtApplicable(String ptApplicable){
    this.ptApplicable = ptApplicable;
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


public void setEpsWages(double epsWages){
    this.epsWages = epsWages;
}


public void setEpmloyerEpfExtra(double epmloyerEpfExtra){
    this.epmloyerEpfExtra = epmloyerEpfExtra;
}


public void setEmployerEsicPercentage(double employerEsicPercentage){
    this.employerEsicPercentage = employerEsicPercentage;
}


public double getEmployerMlwf(){
    return employerMlwf;
}


public int getEsicStatus(){
    return esicStatus;
}


public void setBasicDefault(double basicDefault){
    this.basicDefault = basicDefault;
}


public void setAdjustPlus(double adjustPlus){
    this.adjustPlus = adjustPlus;
}


public void setCalcMonth(int calcMonth){
    this.calcMonth = calcMonth;
}


public double getEpsWages(){
    return epsWages;
}


public void setProductionInsentive(double productionInsentive){
    this.productionInsentive = productionInsentive;
}


public void setEpsEmployerPercentage(double epsEmployerPercentage){
    this.epsEmployerPercentage = epsEmployerPercentage;
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


public void setStatus(int status){
    this.status = status;
}


public double getSocietyContribution(){
    return societyContribution;
}


public void setEsicStatus(int esicStatus){
    this.esicStatus = esicStatus;
}


public void setNightRate(double nightRate){
    this.nightRate = nightRate;
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


public void setNcpDays(int ncpDays){
    this.ncpDays = ncpDays;
}


public void setTotPfAdminCh(double totPfAdminCh){
    this.totPfAdminCh = totPfAdminCh;
}


public void setLocationId(int locationId){
    this.locationId = locationId;
}


public void setDesignationId(int designationId){
    this.designationId = designationId;
}


public void setNetSalary(double netSalary){
    this.netSalary = netSalary;
}


public double getMiscExpDed(){
    return miscExpDed;
}


public void setAdjustMinus(double adjustMinus){
    this.adjustMinus = adjustMinus;
}


}
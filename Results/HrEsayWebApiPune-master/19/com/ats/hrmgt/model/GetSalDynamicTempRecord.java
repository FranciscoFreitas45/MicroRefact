import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class GetSalDynamicTempRecord {

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

 private  float employeePf;

 private  double employerEps;

 private  double employerPf;

 private  float esic;

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

 private  double nightAllow;

 private  int miscExpDedDeduct;

 private  double netSalary;

 private  String isLocked;

 private  String loginName;

 private  String loginTime;

 private  String mlwfApplicable;

 private  String ptApplicable;

 private  double payDed;

 private  double reward;

 private  double bhatta;

 private  double other1;

 private  String commentsForItBonus;

 private  double societyContribution;

 private  String empCategory;

 private  double basicDefault;

 private  String empName;

 private  String designation;

 private  String salTypeName;


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


public double getEpfWagesEmployer(){
    return epfWagesEmployer;
}


public void setMlwfApplicable(String mlwfApplicable){
    this.mlwfApplicable = mlwfApplicable;
}


public double getMiscExpAdd(){
    return miscExpAdd;
}


public void setSalTypeName(String salTypeName){
    this.salTypeName = salTypeName;
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


public int getAttSumId(){
    return attSumId;
}


public double getBhatta(){
    return bhatta;
}


public void setMiscExpAdd(double miscExpAdd){
    this.miscExpAdd = miscExpAdd;
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


public void setEmployeePf(float employeePf){
    this.employeePf = employeePf;
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


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setEsicWagesDec(double esicWagesDec){
    this.esicWagesDec = esicWagesDec;
}


public double getPtDed(){
    return ptDed;
}


public void setOtWages(double otWages){
    this.otWages = otWages;
}


public void setEpfWages(double epfWages){
    this.epfWages = epfWages;
}


public double getLoanDed(){
    return loanDed;
}


public double getEmployerEps(){
    return employerEps;
}


public void setCommentsForItBonus(String commentsForItBonus){
    this.commentsForItBonus = commentsForItBonus;
}


public String getSalTypeName(){
    return salTypeName;
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


public int getSalTypeId(){
    return salTypeId;
}


public void setAdvanceDed(double advanceDed){
    this.advanceDed = advanceDed;
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
    return "GetSalDynamicTempRecord [id=" + id + ", cmpId=" + cmpId + ", empId=" + empId + ", empCode=" + empCode + ", empType=" + empType + ", contractorId=" + contractorId + ", departId=" + departId + ", designationId=" + designationId + ", locationId=" + locationId + ", calcMonth=" + calcMonth + ", calcYear=" + calcYear + ", salTypeId=" + salTypeId + ", attSumId=" + attSumId + ", basicCal=" + basicCal + ", performanceBonus=" + performanceBonus + ", otWages=" + otWages + ", miscExpAdd=" + miscExpAdd + ", bonusCal=" + bonusCal + ", exgretiaCal=" + exgretiaCal + ", daArreasCal=" + daArreasCal + ", incrementArreasCal=" + incrementArreasCal + ", epfWages=" + epfWages + ", epfWagesEmployer=" + epfWagesEmployer + ", esicWagesCal=" + esicWagesCal + ", grossSalary=" + grossSalary + ", epsWages=" + epsWages + ", esicWagesDec=" + esicWagesDec + ", employeePf=" + employeePf + ", employerEps=" + employerEps + ", employerPf=" + employerPf + ", esic=" + esic + ", employerEsic=" + employerEsic + ", esicStatus=" + esicStatus + ", pfStatus=" + pfStatus + ", mlwf=" + mlwf + ", tds=" + tds + ", itded=" + itded + ", fund=" + fund + ", totPfAdminCh=" + totPfAdminCh + ", totEdliCh=" + totEdliCh + ", totEdliAdminCh=" + totEdliAdminCh + ", ncpDays=" + ncpDays + ", status=" + status + ", ptDed=" + ptDed + ", advanceDed=" + advanceDed + ", loanDed=" + loanDed + ", miscExpDed=" + miscExpDed + ", nightAllow=" + nightAllow + ", miscExpDedDeduct=" + miscExpDedDeduct + ", netSalary=" + netSalary + ", isLocked=" + isLocked + ", loginName=" + loginName + ", loginTime=" + loginTime + ", mlwfApplicable=" + mlwfApplicable + ", ptApplicable=" + ptApplicable + ", payDed=" + payDed + ", reward=" + reward + ", bhatta=" + bhatta + ", other1=" + other1 + ", commentsForItBonus=" + commentsForItBonus + ", societyContribution=" + societyContribution + ", empCategory=" + empCategory + ", basicDefault=" + basicDefault + ", empName=" + empName + ", designation=" + designation + ", salTypeName=" + salTypeName + "]";
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


public float getEmployeePf(){
    return employeePf;
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


public void setEsic(float esic){
    this.esic = esic;
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


public void setId(int id){
    this.id = id;
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


public double getBasicDefault(){
    return basicDefault;
}


public double getMlwf(){
    return mlwf;
}


public void setReward(double reward){
    this.reward = reward;
}


public void setEmpCategory(String empCategory){
    this.empCategory = empCategory;
}


public void setDaArreasCal(double daArreasCal){
    this.daArreasCal = daArreasCal;
}


public int getPfStatus(){
    return pfStatus;
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


public void setDesignation(String designation){
    this.designation = designation;
}


public double getNightAllow(){
    return nightAllow;
}


public float getEsic(){
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


public String getEmpName(){
    return empName;
}


public void setEmpName(String empName){
    this.empName = empName;
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


public String getIsLocked(){
    return isLocked;
}


public void setPtApplicable(String ptApplicable){
    this.ptApplicable = ptApplicable;
}


public double getItded(){
    return itded;
}


public void setEpsWages(double epsWages){
    this.epsWages = epsWages;
}


public int getEsicStatus(){
    return esicStatus;
}


public void setBasicDefault(double basicDefault){
    this.basicDefault = basicDefault;
}


public String getDesignation(){
    return designation;
}


public void setCalcMonth(int calcMonth){
    this.calcMonth = calcMonth;
}


public double getEpsWages(){
    return epsWages;
}


public int getNcpDays(){
    return ncpDays;
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


}
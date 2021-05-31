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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://11";


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


public void setEmpId(int empId){
    this.empId = empId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmpId"));

.queryParam("empId",empId);
restTemplate.put(builder.toUriString(),null);
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmpCode"));

.queryParam("empCode",empCode);
restTemplate.put(builder.toUriString(),null);
}


public void setEmpType(int empType){
    this.empType = empType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmpType"));

.queryParam("empType",empType);
restTemplate.put(builder.toUriString(),null);
}


public void setContractorId(int contractorId){
    this.contractorId = contractorId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setContractorId"));

.queryParam("contractorId",contractorId);
restTemplate.put(builder.toUriString(),null);
}


public void setDepartId(int departId){
    this.departId = departId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDepartId"));

.queryParam("departId",departId);
restTemplate.put(builder.toUriString(),null);
}


public void setDesignationId(int designationId){
    this.designationId = designationId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDesignationId"));

.queryParam("designationId",designationId);
restTemplate.put(builder.toUriString(),null);
}


public void setLocationId(int locationId){
    this.locationId = locationId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLocationId"));

.queryParam("locationId",locationId);
restTemplate.put(builder.toUriString(),null);
}


public void setCalcMonth(int calcMonth){
    this.calcMonth = calcMonth;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCalcMonth"));

.queryParam("calcMonth",calcMonth);
restTemplate.put(builder.toUriString(),null);
}


public void setCalcYear(int calcYear){
    this.calcYear = calcYear;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCalcYear"));

.queryParam("calcYear",calcYear);
restTemplate.put(builder.toUriString(),null);
}


public void setAttSumId(int attSumId){
    this.attSumId = attSumId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAttSumId"));

.queryParam("attSumId",attSumId);
restTemplate.put(builder.toUriString(),null);
}


public void setSalTypeId(int salTypeId){
    this.salTypeId = salTypeId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setSalTypeId"));

.queryParam("salTypeId",salTypeId);
restTemplate.put(builder.toUriString(),null);
}


public void setBasicCal(double basicCal){
    this.basicCal = basicCal;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setBasicCal"));

.queryParam("basicCal",basicCal);
restTemplate.put(builder.toUriString(),null);
}


public void setPerformanceBonus(double performanceBonus){
    this.performanceBonus = performanceBonus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPerformanceBonus"));

.queryParam("performanceBonus",performanceBonus);
restTemplate.put(builder.toUriString(),null);
}


public void setOtWages(double otWages){
    this.otWages = otWages;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setOtWages"));

.queryParam("otWages",otWages);
restTemplate.put(builder.toUriString(),null);
}


public void setMiscExpAdd(double miscExpAdd){
    this.miscExpAdd = miscExpAdd;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMiscExpAdd"));

.queryParam("miscExpAdd",miscExpAdd);
restTemplate.put(builder.toUriString(),null);
}


public void setBonusCal(double bonusCal){
    this.bonusCal = bonusCal;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setBonusCal"));

.queryParam("bonusCal",bonusCal);
restTemplate.put(builder.toUriString(),null);
}


public void setExgretiaCal(double exgretiaCal){
    this.exgretiaCal = exgretiaCal;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setExgretiaCal"));

.queryParam("exgretiaCal",exgretiaCal);
restTemplate.put(builder.toUriString(),null);
}


public void setDaArreasCal(double daArreasCal){
    this.daArreasCal = daArreasCal;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDaArreasCal"));

.queryParam("daArreasCal",daArreasCal);
restTemplate.put(builder.toUriString(),null);
}


public void setIncrementArreasCal(double incrementArreasCal){
    this.incrementArreasCal = incrementArreasCal;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setIncrementArreasCal"));

.queryParam("incrementArreasCal",incrementArreasCal);
restTemplate.put(builder.toUriString(),null);
}


public void setEpfWages(double epfWages){
    this.epfWages = epfWages;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEpfWages"));

.queryParam("epfWages",epfWages);
restTemplate.put(builder.toUriString(),null);
}


public void setEpfWagesEmployer(double epfWagesEmployer){
    this.epfWagesEmployer = epfWagesEmployer;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEpfWagesEmployer"));

.queryParam("epfWagesEmployer",epfWagesEmployer);
restTemplate.put(builder.toUriString(),null);
}


public void setEsicWagesCal(double esicWagesCal){
    this.esicWagesCal = esicWagesCal;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEsicWagesCal"));

.queryParam("esicWagesCal",esicWagesCal);
restTemplate.put(builder.toUriString(),null);
}


public void setGrossSalary(double grossSalary){
    this.grossSalary = grossSalary;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setGrossSalary"));

.queryParam("grossSalary",grossSalary);
restTemplate.put(builder.toUriString(),null);
}


public void setEpsWages(double epsWages){
    this.epsWages = epsWages;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEpsWages"));

.queryParam("epsWages",epsWages);
restTemplate.put(builder.toUriString(),null);
}


public void setEsicWagesDec(double esicWagesDec){
    this.esicWagesDec = esicWagesDec;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEsicWagesDec"));

.queryParam("esicWagesDec",esicWagesDec);
restTemplate.put(builder.toUriString(),null);
}


public void setEmployeePf(double employeePf){
    this.employeePf = employeePf;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmployeePf"));

.queryParam("employeePf",employeePf);
restTemplate.put(builder.toUriString(),null);
}


public void setEmployerPf(double employerPf){
    this.employerPf = employerPf;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmployerPf"));

.queryParam("employerPf",employerPf);
restTemplate.put(builder.toUriString(),null);
}


public void setEmployerEps(double employerEps){
    this.employerEps = employerEps;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmployerEps"));

.queryParam("employerEps",employerEps);
restTemplate.put(builder.toUriString(),null);
}


public void setEsic(double esic){
    this.esic = esic;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEsic"));

.queryParam("esic",esic);
restTemplate.put(builder.toUriString(),null);
}


public void setEmployerEsic(double employerEsic){
    this.employerEsic = employerEsic;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmployerEsic"));

.queryParam("employerEsic",employerEsic);
restTemplate.put(builder.toUriString(),null);
}


public void setEsicStatus(int esicStatus){
    this.esicStatus = esicStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEsicStatus"));

.queryParam("esicStatus",esicStatus);
restTemplate.put(builder.toUriString(),null);
}


public void setPfStatus(int pfStatus){
    this.pfStatus = pfStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPfStatus"));

.queryParam("pfStatus",pfStatus);
restTemplate.put(builder.toUriString(),null);
}


public void setMlwf(double mlwf){
    this.mlwf = mlwf;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMlwf"));

.queryParam("mlwf",mlwf);
restTemplate.put(builder.toUriString(),null);
}


public void setTds(double tds){
    this.tds = tds;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTds"));

.queryParam("tds",tds);
restTemplate.put(builder.toUriString(),null);
}


public void setItded(double itded){
    this.itded = itded;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setItded"));

.queryParam("itded",itded);
restTemplate.put(builder.toUriString(),null);
}


public void setFund(double fund){
    this.fund = fund;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setFund"));

.queryParam("fund",fund);
restTemplate.put(builder.toUriString(),null);
}


public void setTotPfAdminCh(double totPfAdminCh){
    this.totPfAdminCh = totPfAdminCh;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTotPfAdminCh"));

.queryParam("totPfAdminCh",totPfAdminCh);
restTemplate.put(builder.toUriString(),null);
}


public void setTotEdliCh(double totEdliCh){
    this.totEdliCh = totEdliCh;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTotEdliCh"));

.queryParam("totEdliCh",totEdliCh);
restTemplate.put(builder.toUriString(),null);
}


public void setTotEdliAdminCh(double totEdliAdminCh){
    this.totEdliAdminCh = totEdliAdminCh;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTotEdliAdminCh"));

.queryParam("totEdliAdminCh",totEdliAdminCh);
restTemplate.put(builder.toUriString(),null);
}


public void setNcpDays(int ncpDays){
    this.ncpDays = ncpDays;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNcpDays"));

.queryParam("ncpDays",ncpDays);
restTemplate.put(builder.toUriString(),null);
}


public void setStatus(int status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setStatus"));

.queryParam("status",status);
restTemplate.put(builder.toUriString(),null);
}


public void setPtDed(double ptDed){
    this.ptDed = ptDed;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPtDed"));

.queryParam("ptDed",ptDed);
restTemplate.put(builder.toUriString(),null);
}


public void setAdvanceDed(double advanceDed){
    this.advanceDed = advanceDed;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAdvanceDed"));

.queryParam("advanceDed",advanceDed);
restTemplate.put(builder.toUriString(),null);
}


public void setLoanDed(double loanDed){
    this.loanDed = loanDed;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLoanDed"));

.queryParam("loanDed",loanDed);
restTemplate.put(builder.toUriString(),null);
}


public void setMiscExpDed(double miscExpDed){
    this.miscExpDed = miscExpDed;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMiscExpDed"));

.queryParam("miscExpDed",miscExpDed);
restTemplate.put(builder.toUriString(),null);
}


public void setMiscExpDedDeduct(int miscExpDedDeduct){
    this.miscExpDedDeduct = miscExpDedDeduct;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMiscExpDedDeduct"));

.queryParam("miscExpDedDeduct",miscExpDedDeduct);
restTemplate.put(builder.toUriString(),null);
}


public void setNetSalary(double netSalary){
    this.netSalary = netSalary;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNetSalary"));

.queryParam("netSalary",netSalary);
restTemplate.put(builder.toUriString(),null);
}


public void setIsLocked(String isLocked){
    this.isLocked = isLocked;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setIsLocked"));

.queryParam("isLocked",isLocked);
restTemplate.put(builder.toUriString(),null);
}


public void setLoginName(String loginName){
    this.loginName = loginName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLoginName"));

.queryParam("loginName",loginName);
restTemplate.put(builder.toUriString(),null);
}


public void setLoginTime(String loginTime){
    this.loginTime = loginTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLoginTime"));

.queryParam("loginTime",loginTime);
restTemplate.put(builder.toUriString(),null);
}


public void setMlwfApplicable(String mlwfApplicable){
    this.mlwfApplicable = mlwfApplicable;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMlwfApplicable"));

.queryParam("mlwfApplicable",mlwfApplicable);
restTemplate.put(builder.toUriString(),null);
}


public void setPtApplicable(String ptApplicable){
    this.ptApplicable = ptApplicable;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPtApplicable"));

.queryParam("ptApplicable",ptApplicable);
restTemplate.put(builder.toUriString(),null);
}


public void setPayDed(double payDed){
    this.payDed = payDed;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPayDed"));

.queryParam("payDed",payDed);
restTemplate.put(builder.toUriString(),null);
}


public void setCommentsForItBonus(String commentsForItBonus){
    this.commentsForItBonus = commentsForItBonus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCommentsForItBonus"));

.queryParam("commentsForItBonus",commentsForItBonus);
restTemplate.put(builder.toUriString(),null);
}


public void setSocietyContribution(double societyContribution){
    this.societyContribution = societyContribution;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setSocietyContribution"));

.queryParam("societyContribution",societyContribution);
restTemplate.put(builder.toUriString(),null);
}


public void setEmpCategory(String empCategory){
    this.empCategory = empCategory;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmpCategory"));

.queryParam("empCategory",empCategory);
restTemplate.put(builder.toUriString(),null);
}


public void setBasicDefault(double basicDefault){
    this.basicDefault = basicDefault;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setBasicDefault"));

.queryParam("basicDefault",basicDefault);
restTemplate.put(builder.toUriString(),null);
}


public void setCmpId(int cmpId){
    this.cmpId = cmpId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCmpId"));

.queryParam("cmpId",cmpId);
restTemplate.put(builder.toUriString(),null);
}


public void setAbDeduction(double abDeduction){
    this.abDeduction = abDeduction;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAbDeduction"));

.queryParam("abDeduction",abDeduction);
restTemplate.put(builder.toUriString(),null);
}


public void setProductionInsentive(double productionInsentive){
    this.productionInsentive = productionInsentive;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setProductionInsentive"));

.queryParam("productionInsentive",productionInsentive);
restTemplate.put(builder.toUriString(),null);
}


public void setPresentInsentive(double presentInsentive){
    this.presentInsentive = presentInsentive;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPresentInsentive"));

.queryParam("presentInsentive",presentInsentive);
restTemplate.put(builder.toUriString(),null);
}


public void setNightAllow(double nightAllow){
    this.nightAllow = nightAllow;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNightAllow"));

.queryParam("nightAllow",nightAllow);
restTemplate.put(builder.toUriString(),null);
}


public void setEpfPercentage(double epfPercentage){
    this.epfPercentage = epfPercentage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEpfPercentage"));

.queryParam("epfPercentage",epfPercentage);
restTemplate.put(builder.toUriString(),null);
}


public void setEpsEmployeePercentage(double epsEmployeePercentage){
    this.epsEmployeePercentage = epsEmployeePercentage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEpsEmployeePercentage"));

.queryParam("epsEmployeePercentage",epsEmployeePercentage);
restTemplate.put(builder.toUriString(),null);
}


public void setEpfEmployerPercentage(double epfEmployerPercentage){
    this.epfEmployerPercentage = epfEmployerPercentage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEpfEmployerPercentage"));

.queryParam("epfEmployerPercentage",epfEmployerPercentage);
restTemplate.put(builder.toUriString(),null);
}


public void setEpsEmployerPercentage(double epsEmployerPercentage){
    this.epsEmployerPercentage = epsEmployerPercentage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEpsEmployerPercentage"));

.queryParam("epsEmployerPercentage",epsEmployerPercentage);
restTemplate.put(builder.toUriString(),null);
}


public void setEpsDefault(double epsDefault){
    this.epsDefault = epsDefault;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEpsDefault"));

.queryParam("epsDefault",epsDefault);
restTemplate.put(builder.toUriString(),null);
}


public void setEpmloyerEpfDefault(double epmloyerEpfDefault){
    this.epmloyerEpfDefault = epmloyerEpfDefault;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEpmloyerEpfDefault"));

.queryParam("epmloyerEpfDefault",epmloyerEpfDefault);
restTemplate.put(builder.toUriString(),null);
}


public void setEpmloyerEpfExtra(double epmloyerEpfExtra){
    this.epmloyerEpfExtra = epmloyerEpfExtra;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEpmloyerEpfExtra"));

.queryParam("epmloyerEpfExtra",epmloyerEpfExtra);
restTemplate.put(builder.toUriString(),null);
}


public void setPfAdminChPercentage(double pfAdminChPercentage){
    this.pfAdminChPercentage = pfAdminChPercentage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPfAdminChPercentage"));

.queryParam("pfAdminChPercentage",pfAdminChPercentage);
restTemplate.put(builder.toUriString(),null);
}


public void setEdliPercentage(double edliPercentage){
    this.edliPercentage = edliPercentage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEdliPercentage"));

.queryParam("edliPercentage",edliPercentage);
restTemplate.put(builder.toUriString(),null);
}


public void setEdliAdminPercentage(double edliAdminPercentage){
    this.edliAdminPercentage = edliAdminPercentage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEdliAdminPercentage"));

.queryParam("edliAdminPercentage",edliAdminPercentage);
restTemplate.put(builder.toUriString(),null);
}


public void setEmployeeEsicPercentage(double employeeEsicPercentage){
    this.employeeEsicPercentage = employeeEsicPercentage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmployeeEsicPercentage"));

.queryParam("employeeEsicPercentage",employeeEsicPercentage);
restTemplate.put(builder.toUriString(),null);
}


public void setEmployerEsicPercentage(double employerEsicPercentage){
    this.employerEsicPercentage = employerEsicPercentage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmployerEsicPercentage"));

.queryParam("employerEsicPercentage",employerEsicPercentage);
restTemplate.put(builder.toUriString(),null);
}


public void setEmployerMlwf(double employerMlwf){
    this.employerMlwf = employerMlwf;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEmployerMlwf"));

.queryParam("employerMlwf",employerMlwf);
restTemplate.put(builder.toUriString(),null);
}


public void setGrossSalDefault(double grossSalDefault){
    this.grossSalDefault = grossSalDefault;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setGrossSalDefault"));

.queryParam("grossSalDefault",grossSalDefault);
restTemplate.put(builder.toUriString(),null);
}


public void setAdjustMinus(double adjustMinus){
    this.adjustMinus = adjustMinus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAdjustMinus"));

.queryParam("adjustMinus",adjustMinus);
restTemplate.put(builder.toUriString(),null);
}


public void setAdjustPlus(double adjustPlus){
    this.adjustPlus = adjustPlus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAdjustPlus"));

.queryParam("adjustPlus",adjustPlus);
restTemplate.put(builder.toUriString(),null);
}


public void setReward(double reward){
    this.reward = reward;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setReward"));

.queryParam("reward",reward);
restTemplate.put(builder.toUriString(),null);
}


public void setNightRate(double nightRate){
    this.nightRate = nightRate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNightRate"));

.queryParam("nightRate",nightRate);
restTemplate.put(builder.toUriString(),null);
}


public void setOtRate(double otRate){
    this.otRate = otRate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setOtRate"));

.queryParam("otRate",otRate);
restTemplate.put(builder.toUriString(),null);
}


public void setBhatta(double bhatta){
    this.bhatta = bhatta;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setBhatta"));

.queryParam("bhatta",bhatta);
restTemplate.put(builder.toUriString(),null);
}


public void setOther1(double other1){
    this.other1 = other1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setOther1"));

.queryParam("other1",other1);
restTemplate.put(builder.toUriString(),null);
}


public void setLeaveEncashAmt(double leaveEncashAmt){
    this.leaveEncashAmt = leaveEncashAmt;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setLeaveEncashAmt"));

.queryParam("leaveEncashAmt",leaveEncashAmt);
restTemplate.put(builder.toUriString(),null);
}


}
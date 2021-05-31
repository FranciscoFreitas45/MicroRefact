import javax.persistence;
import java.util.List;
@Entity
public class GetDeptPayReport {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int id;

 private  String departName;

 private  int departId;

 private  int calcMonth;

 private  int calcYear;

 private  int salTypeId;

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

 private  double mlwf;

 private  double tds;

 private  double itded;

 private  double fund;

 private  double totPfAdminCh;

 private  double totEdliCh;

 private  double totEdliAdminCh;

 private  int ncpDays;

 private  double ptDed;

 private  double advanceDed;

 private  double loanDed;

 private  double miscExpDed;

 private  int miscExpDedDeduct;

 private  double netSalary;

 private  double payDed;

 private  double societyContribution;

 private  double basicDefault;

 private  double abDeduction;

 private  double productionInsentive;

 private  double presentInsentive;

 private  double nightAllow;

 private  double grossSalDefault;

 private  double adjustMinus;

 private  double adjustPlus;

 private  double reward;

 private  double bhatta;

 private  double other1;

 private  double leaveEncashAmt;

@Transient
 private  List<SalAllownceCal> payrollAllownceList;


public void setEpfWagesEmployer(double epfWagesEmployer){
    this.epfWagesEmployer = epfWagesEmployer;
}


public double getGrossSalary(){
    return grossSalary;
}


public double getOther1(){
    return other1;
}


public void setFund(double fund){
    this.fund = fund;
}


public double getEpfWagesEmployer(){
    return epfWagesEmployer;
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


public void setSalTypeId(int salTypeId){
    this.salTypeId = salTypeId;
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


public double getBhatta(){
    return bhatta;
}


public void setMiscExpAdd(double miscExpAdd){
    this.miscExpAdd = miscExpAdd;
}


public void setPresentInsentive(double presentInsentive){
    this.presentInsentive = presentInsentive;
}


public void setEmployeePf(float employeePf){
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


public double getNetSalary(){
    return netSalary;
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


public void setDepartName(String departName){
    this.departName = departName;
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


public double getBasicCal(){
    return basicCal;
}


public void setEsicWagesDec(double esicWagesDec){
    this.esicWagesDec = esicWagesDec;
}


public void setPayrollAllownceList(List<SalAllownceCal> payrollAllownceList){
    this.payrollAllownceList = payrollAllownceList;
}


public double getPtDed(){
    return ptDed;
}


public String getDepartName(){
    return departName;
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


public void setLoanDed(double loanDed){
    this.loanDed = loanDed;
}


public void setOther1(double other1){
    this.other1 = other1;
}


public void setPerformanceBonus(double performanceBonus){
    this.performanceBonus = performanceBonus;
}


public int getSalTypeId(){
    return salTypeId;
}


public void setAdvanceDed(double advanceDed){
    this.advanceDed = advanceDed;
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
    return "GetDeptPayReport [id=" + id + ", departName=" + departName + ", departId=" + departId + ", calcMonth=" + calcMonth + ", calcYear=" + calcYear + ", salTypeId=" + salTypeId + ", basicCal=" + basicCal + ", performanceBonus=" + performanceBonus + ", otWages=" + otWages + ", miscExpAdd=" + miscExpAdd + ", bonusCal=" + bonusCal + ", exgretiaCal=" + exgretiaCal + ", daArreasCal=" + daArreasCal + ", incrementArreasCal=" + incrementArreasCal + ", epfWages=" + epfWages + ", epfWagesEmployer=" + epfWagesEmployer + ", esicWagesCal=" + esicWagesCal + ", grossSalary=" + grossSalary + ", epsWages=" + epsWages + ", esicWagesDec=" + esicWagesDec + ", employeePf=" + employeePf + ", employerEps=" + employerEps + ", employerPf=" + employerPf + ", esic=" + esic + ", employerEsic=" + employerEsic + ", mlwf=" + mlwf + ", tds=" + tds + ", itded=" + itded + ", fund=" + fund + ", totPfAdminCh=" + totPfAdminCh + ", totEdliCh=" + totEdliCh + ", totEdliAdminCh=" + totEdliAdminCh + ", ncpDays=" + ncpDays + ", ptDed=" + ptDed + ", advanceDed=" + advanceDed + ", loanDed=" + loanDed + ", miscExpDed=" + miscExpDed + ", miscExpDedDeduct=" + miscExpDedDeduct + ", netSalary=" + netSalary + ", payDed=" + payDed + ", societyContribution=" + societyContribution + ", basicDefault=" + basicDefault + ", abDeduction=" + abDeduction + ", productionInsentive=" + productionInsentive + ", presentInsentive=" + presentInsentive + ", nightAllow=" + nightAllow + ", grossSalDefault=" + grossSalDefault + ", adjustMinus=" + adjustMinus + ", adjustPlus=" + adjustPlus + ", reward=" + reward + ", bhatta=" + bhatta + ", other1=" + other1 + ", leaveEncashAmt=" + leaveEncashAmt + ", payrollAllownceList=" + payrollAllownceList + "]";
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


public double getGrossSalDefault(){
    return grossSalDefault;
}


public void setCalcYear(int calcYear){
    this.calcYear = calcYear;
}


public List<SalAllownceCal> getPayrollAllownceList(){
    return payrollAllownceList;
}


public void setEsicWagesCal(double esicWagesCal){
    this.esicWagesCal = esicWagesCal;
}


public void setEsic(float esic){
    this.esic = esic;
}


public void setGrossSalDefault(double grossSalDefault){
    this.grossSalDefault = grossSalDefault;
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


public double getIncrementArreasCal(){
    return incrementArreasCal;
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


public void setDaArreasCal(double daArreasCal){
    this.daArreasCal = daArreasCal;
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


public float getEsic(){
    return esic;
}


public void setEmployerEsic(double employerEsic){
    this.employerEsic = employerEsic;
}


public double getEsicWagesCal(){
    return esicWagesCal;
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


public double getItded(){
    return itded;
}


public double getPresentInsentive(){
    return presentInsentive;
}


public void setEpsWages(double epsWages){
    this.epsWages = epsWages;
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


public int getNcpDays(){
    return ncpDays;
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


public void setNcpDays(int ncpDays){
    this.ncpDays = ncpDays;
}


public void setTotPfAdminCh(double totPfAdminCh){
    this.totPfAdminCh = totPfAdminCh;
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
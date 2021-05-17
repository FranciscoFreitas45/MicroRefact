import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.sql.Date;
@Entity
@Table(name = "tbl_emp_salary_info")
public class EmpSalaryInfo {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "salary_info_id")
 private  int salaryInfoId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "salary_type_id")
 private  int salaryTypeId;

@Column(name = "basic")
 private  double basic;

@Column(name = "da")
 private  double da;

@Column(name = "hra")
 private  double hra;

@Column(name = "spa")
 private  double spa;

@Column(name = "pf_applicable")
 private  String pfApplicable;

@Column(name = "pf_type")
 private  String pfType;

@Column(name = "pf_emp_per")
 private  double pfEmpPer;

@Column(name = "pf_emplr_per")
 private  double pfEmplrPer;

@Column(name = "esic_applicable")
 private  String esicApplicable;

@Column(name = "cmp_joining_date")
 private  Date cmpJoiningDate;

@Column(name = "cmp_leaving_date")
 private  Date cmpLeavingDate;

@Column(name = "epf_joining_date")
 private  Date epfJoiningDate;

@Column(name = "leaving_reason")
 private  String leavingReason;

@Column(name = "salBasis")
 private  String salBasis;

@Column(name = "ceiling_limit_emp_applicable")
 private  String ceilingLimitEmpApplicable;

@Column(name = "ceiling_limit_employer_applicable")
 private  String ceilingLimitEmployerApplicable;

@Column(name = "leaving_reason_esic")
 private  String leavingReasonEsic;

@Column(name = "leaving_reason_pf")
 private  String leavingReasonPf;

@Column(name = "mlwf_applicable")
 private  String mlwfApplicable;

@Column(name = "pt_applicable")
 private  String ptApplicable;

@Column(name = "gross_salary")
 private  double grossSalary;

@Column(name = "society_contribution")
 private  double societyContribution;

@Column(name = "basic_company")
 private  double basicCompany;

@Column(name = "hra_company")
 private  double hraCompany;

@Column(name = "da_company")
 private  double daCompany;

@Column(name = "employee_esic_percentage")
 private  double employeeEsicPercentage;

@Column(name = "employer_esic_percentage")
 private  double employerEsicPercentage;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;

@Column(name = "daily_hr")
 private  String dailyHr;

@Column(name = "monthly_hr_target")
 private  String monthlyHrTarget;

@Column(name = "monthly_minimum_target")
 private  String monthlyMinimumTarget;

@Column(name = "monthly_ot_hr")
 private  String monthlyOtHr;


public String getExVar2(){
    return exVar2;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCmpJoiningDate(){
    return cmpJoiningDate;
}


public double getGrossSalary(){
    return grossSalary;
}


public void setCmpLeavingDate(Date cmpLeavingDate){
    this.cmpLeavingDate = cmpLeavingDate;
}


public void setSalaryTypeId(int salaryTypeId){
    this.salaryTypeId = salaryTypeId;
}


public void setSocietyContribution(double societyContribution){
    this.societyContribution = societyContribution;
}


public String getExVar1(){
    return exVar1;
}


public void setPfEmpPer(double pfEmpPer){
    this.pfEmpPer = pfEmpPer;
}


public String getMonthlyHrTarget(){
    return monthlyHrTarget;
}


public void setHraCompany(double hraCompany){
    this.hraCompany = hraCompany;
}


public void setMlwfApplicable(String mlwfApplicable){
    this.mlwfApplicable = mlwfApplicable;
}


public void setSalBasis(String salBasis){
    this.salBasis = salBasis;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public String getCeilingLimitEmployerApplicable(){
    return ceilingLimitEmployerApplicable;
}


public void setBasicCompany(double basicCompany){
    this.basicCompany = basicCompany;
}


public String getLeavingReasonPf(){
    return leavingReasonPf;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public double getEmployeeEsicPercentage(){
    return employeeEsicPercentage;
}


public double getHraCompany(){
    return hraCompany;
}


public double getDa(){
    return da;
}


public void setCmpJoiningDate(Date cmpJoiningDate){
    this.cmpJoiningDate = cmpJoiningDate;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setPfApplicable(String pfApplicable){
    this.pfApplicable = pfApplicable;
}


public void setCeilingLimitEmployerApplicable(String ceilingLimitEmployerApplicable){
    this.ceilingLimitEmployerApplicable = ceilingLimitEmployerApplicable;
}


public String getMonthlyMinimumTarget(){
    return monthlyMinimumTarget;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getSalBasis(){
    return salBasis;
}


public void setGrossSalary(double grossSalary){
    this.grossSalary = grossSalary;
}


public void setMonthlyHrTarget(String monthlyHrTarget){
    this.monthlyHrTarget = monthlyHrTarget;
}


public void setCeilingLimitEmpApplicable(String ceilingLimitEmpApplicable){
    this.ceilingLimitEmpApplicable = ceilingLimitEmpApplicable;
}


public String getEsicApplicable(){
    return esicApplicable;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCmpLeavingDate(){
    return cmpLeavingDate;
}


public void setMonthlyOtHr(String monthlyOtHr){
    this.monthlyOtHr = monthlyOtHr;
}


public String getPfType(){
    return pfType;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public String getLeavingReason(){
    return leavingReason;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
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


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getEpfJoiningDate(){
    return epfJoiningDate;
}


public double getPfEmpPer(){
    return pfEmpPer;
}


public void setPfEmplrPer(double pfEmplrPer){
    this.pfEmplrPer = pfEmplrPer;
}


public void setEmployeeEsicPercentage(double employeeEsicPercentage){
    this.employeeEsicPercentage = employeeEsicPercentage;
}


public double getDaCompany(){
    return daCompany;
}


public void setDaCompany(double daCompany){
    this.daCompany = daCompany;
}


public void setBasic(double basic){
    this.basic = basic;
}


public void setLeavingReasonPf(String leavingReasonPf){
    this.leavingReasonPf = leavingReasonPf;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setSalaryInfoId(int salaryInfoId){
    this.salaryInfoId = salaryInfoId;
}


public void setHra(double hra){
    this.hra = hra;
}


public void setPtApplicable(String ptApplicable){
    this.ptApplicable = ptApplicable;
}


public void setEmployerEsicPercentage(double employerEsicPercentage){
    this.employerEsicPercentage = employerEsicPercentage;
}


public double getPfEmplrPer(){
    return pfEmplrPer;
}


public void setSpa(double spa){
    this.spa = spa;
}


public void setPfType(String pfType){
    this.pfType = pfType;
}


public void setLeavingReason(String leavingReason){
    this.leavingReason = leavingReason;
}


public void setMonthlyMinimumTarget(String monthlyMinimumTarget){
    this.monthlyMinimumTarget = monthlyMinimumTarget;
}


public double getSpa(){
    return spa;
}


public String getMonthlyOtHr(){
    return monthlyOtHr;
}


public double getHra(){
    return hra;
}


public String getMlwfApplicable(){
    return mlwfApplicable;
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


public double getSocietyContribution(){
    return societyContribution;
}


public void setDailyHr(String dailyHr){
    this.dailyHr = dailyHr;
}


public int getSalaryInfoId(){
    return salaryInfoId;
}


public String getDailyHr(){
    return dailyHr;
}


public void setEpfJoiningDate(Date epfJoiningDate){
    this.epfJoiningDate = epfJoiningDate;
}


public int getDelStatus(){
    return delStatus;
}


public String getCeilingLimitEmpApplicable(){
    return ceilingLimitEmpApplicable;
}


public String getLeavingReasonEsic(){
    return leavingReasonEsic;
}


public String getPfApplicable(){
    return pfApplicable;
}


public double getBasicCompany(){
    return basicCompany;
}


@Override
public String toString(){
    return "EmpSalaryInfo [salaryInfoId=" + salaryInfoId + ", empId=" + empId + ", salaryTypeId=" + salaryTypeId + ", basic=" + basic + ", da=" + da + ", hra=" + hra + ", spa=" + spa + ", pfApplicable=" + pfApplicable + ", pfType=" + pfType + ", pfEmpPer=" + pfEmpPer + ", pfEmplrPer=" + pfEmplrPer + ", esicApplicable=" + esicApplicable + ", cmpJoiningDate=" + cmpJoiningDate + ", cmpLeavingDate=" + cmpLeavingDate + ", epfJoiningDate=" + epfJoiningDate + ", leavingReason=" + leavingReason + ", salBasis=" + salBasis + ", ceilingLimitEmpApplicable=" + ceilingLimitEmpApplicable + ", ceilingLimitEmployerApplicable=" + ceilingLimitEmployerApplicable + ", leavingReasonEsic=" + leavingReasonEsic + ", leavingReasonPf=" + leavingReasonPf + ", mlwfApplicable=" + mlwfApplicable + ", ptApplicable=" + ptApplicable + ", grossSalary=" + grossSalary + ", societyContribution=" + societyContribution + ", basicCompany=" + basicCompany + ", hraCompany=" + hraCompany + ", daCompany=" + daCompany + ", employeeEsicPercentage=" + employeeEsicPercentage + ", employerEsicPercentage=" + employerEsicPercentage + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", dailyHr=" + dailyHr + ", monthlyHrTarget=" + monthlyHrTarget + ", monthlyMinimumTarget=" + monthlyMinimumTarget + ", monthlyOtHr=" + monthlyOtHr + "]";
}


public void setDa(double da){
    this.da = da;
}


}
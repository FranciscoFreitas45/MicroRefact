import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
import java.util.List;
@Entity
public class EmpSalaryInfoForPayroll {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "salary_info_id")
 private  int salaryInfoId;

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

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "emp_type_name")
 private  String empTypeName;

@Column(name = "sal_type_name")
 private  String salTypeName;

@Column(name = "designation")
 private  String designation;

@Column(name = "dept_name")
 private  String deptName;

@Column(name = "emp_name")
 private  String empName;

@Column(name = "loc_name")
 private  String locName;

@Column(name = "emp_type_id")
 private  int empTypeId;

@Column(name = "loc_id")
 private  int locId;

@Column(name = "depart_id")
 private  int departId;

@Column(name = "contractor_id")
 private  int contractorId;

@Column(name = "desig_id")
 private  int desigId;

@Column(name = "sum_id")
 private  int sumId;

@Column(name = "sub_cmp_id")
 private  int subCmpId;

@Column(name = "count_leave")
 private  int countLeave;

@Column(name = "can_generate_sal")
 private  int canGenerateSal;

@Transient
 private List<EmpAllowanceList> empAllowanceList;


public String getExVar2(){
    return exVar2;
}


public double getGrossSalary(){
    return grossSalary;
}


public void setCmpLeavingDate(Date cmpLeavingDate){
    this.cmpLeavingDate = cmpLeavingDate;
}


public int getLocId(){
    return locId;
}


public void setSalaryTypeId(int salaryTypeId){
    this.salaryTypeId = salaryTypeId;
}


public String getExVar1(){
    return exVar1;
}


public void setHraCompany(double hraCompany){
    this.hraCompany = hraCompany;
}


public void setMlwfApplicable(String mlwfApplicable){
    this.mlwfApplicable = mlwfApplicable;
}


public void setSalTypeName(String salTypeName){
    this.salTypeName = salTypeName;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setEmpTypeId(int empTypeId){
    this.empTypeId = empTypeId;
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


public int getEmpTypeId(){
    return empTypeId;
}


public double getHraCompany(){
    return hraCompany;
}


public void setDesigId(int desigId){
    this.desigId = desigId;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getEmpId(){
    return empId;
}


public int getDesigId(){
    return desigId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getSalBasis(){
    return salBasis;
}


public void setSubCmpId(int subCmpId){
    this.subCmpId = subCmpId;
}


public void setGrossSalary(double grossSalary){
    this.grossSalary = grossSalary;
}


public void setSumId(int sumId){
    this.sumId = sumId;
}


public void setCeilingLimitEmpApplicable(String ceilingLimitEmpApplicable){
    this.ceilingLimitEmpApplicable = ceilingLimitEmpApplicable;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCmpLeavingDate(){
    return cmpLeavingDate;
}


public void setEmpTypeName(String empTypeName){
    this.empTypeName = empTypeName;
}


public int getSumId(){
    return sumId;
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


public void setContractorId(int contractorId){
    this.contractorId = contractorId;
}


public int getExInt1(){
    return exInt1;
}


public String getLocName(){
    return locName;
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


public void setLocName(String locName){
    this.locName = locName;
}


public double getPfEmpPer(){
    return pfEmpPer;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setEmployeeEsicPercentage(double employeeEsicPercentage){
    this.employeeEsicPercentage = employeeEsicPercentage;
}


public int getSubCmpId(){
    return subCmpId;
}


public void setDaCompany(double daCompany){
    this.daCompany = daCompany;
}


public void setBasic(double basic){
    this.basic = basic;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getEmpTypeName(){
    return empTypeName;
}


public double getPfEmplrPer(){
    return pfEmplrPer;
}


public String getSalTypeName(){
    return salTypeName;
}


public void setPfType(String pfType){
    this.pfType = pfType;
}


public void setEmpAllowanceList(List<EmpAllowanceList> empAllowanceList){
    this.empAllowanceList = empAllowanceList;
}


public void setLocId(int locId){
    this.locId = locId;
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


public List<EmpAllowanceList> getEmpAllowanceList(){
    return empAllowanceList;
}


public String getEmpCode(){
    return empCode;
}


public int getDelStatus(){
    return delStatus;
}


public String getCeilingLimitEmpApplicable(){
    return ceilingLimitEmpApplicable;
}


public void setCountLeave(int countLeave){
    this.countLeave = countLeave;
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
    return "EmpSalaryInfoForPayroll [empId=" + empId + ", salaryInfoId=" + salaryInfoId + ", salaryTypeId=" + salaryTypeId + ", basic=" + basic + ", da=" + da + ", hra=" + hra + ", spa=" + spa + ", pfApplicable=" + pfApplicable + ", pfType=" + pfType + ", pfEmpPer=" + pfEmpPer + ", pfEmplrPer=" + pfEmplrPer + ", esicApplicable=" + esicApplicable + ", cmpJoiningDate=" + cmpJoiningDate + ", cmpLeavingDate=" + cmpLeavingDate + ", epfJoiningDate=" + epfJoiningDate + ", leavingReason=" + leavingReason + ", salBasis=" + salBasis + ", ceilingLimitEmpApplicable=" + ceilingLimitEmpApplicable + ", ceilingLimitEmployerApplicable=" + ceilingLimitEmployerApplicable + ", leavingReasonEsic=" + leavingReasonEsic + ", leavingReasonPf=" + leavingReasonPf + ", mlwfApplicable=" + mlwfApplicable + ", ptApplicable=" + ptApplicable + ", grossSalary=" + grossSalary + ", societyContribution=" + societyContribution + ", basicCompany=" + basicCompany + ", hraCompany=" + hraCompany + ", daCompany=" + daCompany + ", employeeEsicPercentage=" + employeeEsicPercentage + ", employerEsicPercentage=" + employerEsicPercentage + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", empCode=" + empCode + ", empTypeName=" + empTypeName + ", salTypeName=" + salTypeName + ", designation=" + designation + ", deptName=" + deptName + ", empName=" + empName + ", locName=" + locName + ", empTypeId=" + empTypeId + ", locId=" + locId + ", departId=" + departId + ", contractorId=" + contractorId + ", desigId=" + desigId + ", sumId=" + sumId + ", subCmpId=" + subCmpId + ", countLeave=" + countLeave + ", canGenerateSal=" + canGenerateSal + ", empAllowanceList=" + empAllowanceList + "]";
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCmpJoiningDate(){
    return cmpJoiningDate;
}


public void setSocietyContribution(double societyContribution){
    this.societyContribution = societyContribution;
}


public void setPfEmpPer(double pfEmpPer){
    this.pfEmpPer = pfEmpPer;
}


public int getContractorId(){
    return contractorId;
}


public void setSalBasis(String salBasis){
    this.salBasis = salBasis;
}


public String getCeilingLimitEmployerApplicable(){
    return ceilingLimitEmployerApplicable;
}


public double getEmployeeEsicPercentage(){
    return employeeEsicPercentage;
}


public int getCountLeave(){
    return countLeave;
}


public double getDa(){
    return da;
}


public void setCmpJoiningDate(Date cmpJoiningDate){
    this.cmpJoiningDate = cmpJoiningDate;
}


public void setCanGenerateSal(int canGenerateSal){
    this.canGenerateSal = canGenerateSal;
}


public void setPfApplicable(String pfApplicable){
    this.pfApplicable = pfApplicable;
}


public void setCeilingLimitEmployerApplicable(String ceilingLimitEmployerApplicable){
    this.ceilingLimitEmployerApplicable = ceilingLimitEmployerApplicable;
}


public String getDeptName(){
    return deptName;
}


public int getCanGenerateSal(){
    return canGenerateSal;
}


public String getEsicApplicable(){
    return esicApplicable;
}


public void setDepartId(int departId){
    this.departId = departId;
}


public String getPfType(){
    return pfType;
}


public void setDesignation(String designation){
    this.designation = designation;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getEpfJoiningDate(){
    return epfJoiningDate;
}


public void setPfEmplrPer(double pfEmplrPer){
    this.pfEmplrPer = pfEmplrPer;
}


public String getEmpName(){
    return empName;
}


public double getDaCompany(){
    return daCompany;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public void setLeavingReasonPf(String leavingReasonPf){
    this.leavingReasonPf = leavingReasonPf;
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


public void setSpa(double spa){
    this.spa = spa;
}


public void setLeavingReason(String leavingReason){
    this.leavingReason = leavingReason;
}


public String getDesignation(){
    return designation;
}


public double getSpa(){
    return spa;
}


public double getHra(){
    return hra;
}


public String getMlwfApplicable(){
    return mlwfApplicable;
}


public double getSocietyContribution(){
    return societyContribution;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setEpfJoiningDate(Date epfJoiningDate){
    this.epfJoiningDate = epfJoiningDate;
}


public int getDepartId(){
    return departId;
}


public void setDa(double da){
    this.da = da;
}


}
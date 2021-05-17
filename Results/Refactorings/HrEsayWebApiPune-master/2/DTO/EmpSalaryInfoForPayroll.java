import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
import java.util.List;
public class EmpSalaryInfoForPayroll {

 private  int empId;

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

 private  Date cmpJoiningDate;

 private  Date cmpLeavingDate;

 private  Date epfJoiningDate;

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

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;

 private  String empCode;

 private  String empTypeName;

 private  String salTypeName;

 private  String designation;

 private  String deptName;

 private  String empName;

 private  String locName;

 private  int empTypeId;

 private  int locId;

 private  int departId;

 private  int contractorId;

 private  int desigId;

 private  int sumId;

 private  int subCmpId;

 private  int countLeave;

 private  int canGenerateSal;

 private List<EmpAllowanceList> empAllowanceList;


public String getExVar2(){
    return exVar2;
}


public double getGrossSalary(){
    return grossSalary;
}


public int getLocId(){
    return locId;
}


public String getExVar1(){
    return exVar1;
}


public String getLeavingReasonPf(){
    return leavingReasonPf;
}


public int getEmpTypeId(){
    return empTypeId;
}


public double getHraCompany(){
    return hraCompany;
}


public int getEmpId(){
    return empId;
}


public int getDesigId(){
    return desigId;
}


public String getSalBasis(){
    return salBasis;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCmpLeavingDate(){
    return cmpLeavingDate;
}


public int getSumId(){
    return sumId;
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


public String getLocName(){
    return locName;
}


public int getSalaryTypeId(){
    return salaryTypeId;
}


public double getBasic(){
    return basic;
}


public double getPfEmpPer(){
    return pfEmpPer;
}


public int getSubCmpId(){
    return subCmpId;
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


public String getLeavingReasonEsic(){
    return leavingReasonEsic;
}


public String getPfApplicable(){
    return pfApplicable;
}


public double getBasicCompany(){
    return basicCompany;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCmpJoiningDate(){
    return cmpJoiningDate;
}


public int getContractorId(){
    return contractorId;
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


public String getDeptName(){
    return deptName;
}


public int getCanGenerateSal(){
    return canGenerateSal;
}


public String getEsicApplicable(){
    return esicApplicable;
}


public String getPfType(){
    return pfType;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getEpfJoiningDate(){
    return epfJoiningDate;
}


public String getEmpName(){
    return empName;
}


public double getDaCompany(){
    return daCompany;
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


public int getDepartId(){
    return departId;
}


}
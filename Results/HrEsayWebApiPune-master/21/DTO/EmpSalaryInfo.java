import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.sql.Date;
public class EmpSalaryInfo {

 private  int salaryInfoId;

 private  int empId;

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

 private  String dailyHr;

 private  String monthlyHrTarget;

 private  String monthlyMinimumTarget;

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


public String getExVar1(){
    return exVar1;
}


public String getMonthlyHrTarget(){
    return monthlyHrTarget;
}


public String getCeilingLimitEmployerApplicable(){
    return ceilingLimitEmployerApplicable;
}


public String getLeavingReasonPf(){
    return leavingReasonPf;
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


public String getMonthlyMinimumTarget(){
    return monthlyMinimumTarget;
}


public int getEmpId(){
    return empId;
}


public String getSalBasis(){
    return salBasis;
}


public String getEsicApplicable(){
    return esicApplicable;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getCmpLeavingDate(){
    return cmpLeavingDate;
}


public String getPfType(){
    return pfType;
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


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getEpfJoiningDate(){
    return epfJoiningDate;
}


public double getPfEmpPer(){
    return pfEmpPer;
}


public double getDaCompany(){
    return daCompany;
}


public double getPfEmplrPer(){
    return pfEmplrPer;
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


public String getPtApplicable(){
    return ptApplicable;
}


public double getEmployerEsicPercentage(){
    return employerEsicPercentage;
}


public double getSocietyContribution(){
    return societyContribution;
}


public int getSalaryInfoId(){
    return salaryInfoId;
}


public String getDailyHr(){
    return dailyHr;
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


}
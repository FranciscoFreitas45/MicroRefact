import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class StatutoryEsicRep {

@Id
 private  String keyNew;

 private  int empId;

 private  String empName;

 private  String empCode;

 private  String esicNo;

 private  double netSalary;

 private  double esicWagesCal;

 private  double employerEsic;

 private  double esic;

 private  int presentDays;

 private  int month;

 private  int year;

 private  String reason;

 private  Date esicLeaveDate;


public void setEsicNo(String esicNo){
    this.esicNo = esicNo;
}


public double getEsic(){
    return esic;
}


public String getKeyNew(){
    return keyNew;
}


public void setEmployerEsic(double employerEsic){
    this.employerEsic = employerEsic;
}


public void setPresentDays(int presentDays){
    this.presentDays = presentDays;
}


public void setEsicLeaveDate(Date esicLeaveDate){
    this.esicLeaveDate = esicLeaveDate;
}


public void setEsicWagesCal(double esicWagesCal){
    this.esicWagesCal = esicWagesCal;
}


public void setEsic(double esic){
    this.esic = esic;
}


public double getEsicWagesCal(){
    return esicWagesCal;
}


public double getEmployerEsic(){
    return employerEsic;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getEmpName(){
    return empName;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public void setReason(String reason){
    this.reason = reason;
}


public int getMonth(){
    return month;
}


public void setMonth(int month){
    this.month = month;
}


@JsonFormat(locale = "English", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getEsicLeaveDate(){
    return esicLeaveDate;
}


public String getReason(){
    return reason;
}


public void setKeyNew(String keyNew){
    this.keyNew = keyNew;
}


public String getEsicNo(){
    return esicNo;
}


public void setYear(int year){
    this.year = year;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public int getPresentDays(){
    return presentDays;
}


public String getEmpCode(){
    return empCode;
}


public int getYear(){
    return year;
}


@Override
public String toString(){
    return "StatutoryEsicRep [keyNew=" + keyNew + ", empId=" + empId + ", empName=" + empName + ", empCode=" + empCode + ", esicNo=" + esicNo + ", netSalary=" + netSalary + ", esicWagesCal=" + esicWagesCal + ", employerEsic=" + employerEsic + ", esic=" + esic + ", presentDays=" + presentDays + ", month=" + month + ", year=" + year + "]";
}


public double getNetSalary(){
    return netSalary;
}


public void setNetSalary(double netSalary){
    this.netSalary = netSalary;
}


}
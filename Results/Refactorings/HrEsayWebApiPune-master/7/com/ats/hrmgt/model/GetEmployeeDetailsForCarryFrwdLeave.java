import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetEmployeeDetailsForCarryFrwdLeave {

@Id
@Column(name = "emp_id")
 private  int empId;

 private  String empCode;

 private  String empName;

 private  String deptName;

 private  String empDesgn;

 private  String locName;

 private  double grossSalary;

 private  String empTypeName;

 private  String salTypeName;

 private  String subCompName;

 private  double basic;

 private  int lvsId;

 private  String lvsName;

 private  double allowSum;


public double getGrossSalary(){
    return grossSalary;
}


public String getEmpDesgn(){
    return empDesgn;
}


public String getSubCompName(){
    return subCompName;
}


public String getLocName(){
    return locName;
}


public void setLocName(String locName){
    this.locName = locName;
}


public double getBasic(){
    return basic;
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


public void setBasic(double basic){
    this.basic = basic;
}


public void setSalTypeName(String salTypeName){
    this.salTypeName = salTypeName;
}


public double getAllowSum(){
    return allowSum;
}


public void setSubCompName(String subCompName){
    this.subCompName = subCompName;
}


public String getEmpTypeName(){
    return empTypeName;
}


public String getLvsName(){
    return lvsName;
}


public void setAllowSum(double allowSum){
    this.allowSum = allowSum;
}


public String getSalTypeName(){
    return salTypeName;
}


public void setLvsName(String lvsName){
    this.lvsName = lvsName;
}


public int getLvsId(){
    return lvsId;
}


public int getEmpId(){
    return empId;
}


public String getDeptName(){
    return deptName;
}


public void setEmpDesgn(String empDesgn){
    this.empDesgn = empDesgn;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public String getEmpCode(){
    return empCode;
}


public void setGrossSalary(double grossSalary){
    this.grossSalary = grossSalary;
}


public void setLvsId(int lvsId){
    this.lvsId = lvsId;
}


public void setEmpTypeName(String empTypeName){
    this.empTypeName = empTypeName;
}


@Override
public String toString(){
    return "GetEmployeeDetailsForCarryFrwdLeave [empId=" + empId + ", empCode=" + empCode + ", empName=" + empName + ", deptName=" + deptName + ", empDesgn=" + empDesgn + ", locName=" + locName + ", grossSalary=" + grossSalary + ", empTypeName=" + empTypeName + ", salTypeName=" + salTypeName + ", subCompName=" + subCompName + ", basic=" + basic + ", lvsId=" + lvsId + ", lvsName=" + lvsName + ", allowSum=" + allowSum + "]";
}


}
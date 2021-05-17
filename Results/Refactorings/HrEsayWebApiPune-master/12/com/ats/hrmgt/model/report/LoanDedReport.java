import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class LoanDedReport {

@Id
 private  String uniqueId;

 private  int empId;

 private  String empCode;

 private  String empName;

 private  String departmentName;

 private  float amountEmi;


public String getDepartmentName(){
    return departmentName;
}


public float getAmountEmi(){
    return amountEmi;
}


public void setAmountEmi(float amountEmi){
    this.amountEmi = amountEmi;
}


public void setUniqueId(String uniqueId){
    this.uniqueId = uniqueId;
}


public int getEmpId(){
    return empId;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getUniqueId(){
    return uniqueId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpName(){
    return empName;
}


public String getEmpCode(){
    return empCode;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public void setDepartmentName(String departmentName){
    this.departmentName = departmentName;
}


@Override
public String toString(){
    return "LoanDedReport [uniqueId=" + uniqueId + ", empId=" + empId + ", empCode=" + empCode + ", empName=" + empName + ", departmentName=" + departmentName + ", amountEmi=" + amountEmi + "]";
}


}
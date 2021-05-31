import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EmpLateMarkDetails {

@Id
 private  String id;

 private  int empId;

 private  String empCode;

 private  String empName;

 private  String designation;

 private  float lateHr;

 private  String month;


public void setMonth(String month){
    this.month = month;
}


public String getDesignation(){
    return designation;
}


public float getLateHr(){
    return lateHr;
}


public String getId(){
    return id;
}


public int getEmpId(){
    return empId;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
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


public void setLateHr(float lateHr){
    this.lateHr = lateHr;
}


public void setId(String id){
    this.id = id;
}


@Override
public String toString(){
    return "EmpLateMarkDetails [id=" + id + ", empId=" + empId + ", empCode=" + empCode + ", empName=" + empName + ", designation=" + designation + ", lateHr=" + lateHr + ", month=" + month + "]";
}


public String getMonth(){
    return month;
}


public void setDesignation(String designation){
    this.designation = designation;
}


}
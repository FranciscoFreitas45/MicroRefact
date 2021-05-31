import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
@Entity
public class EmpOtReg {

@Id
 private  String id;

 private  int empId;

 private  String empCode;

 private  String empName;

 private  String designation;

 private  String otHr;

 private  String month;

 private  Date date;

 private  float otMin;


public void setMonth(String month){
    this.month = month;
}


public String getDesignation(){
    return designation;
}


public String getOtHr(){
    return otHr;
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


public void setOtMin(float otMin){
    this.otMin = otMin;
}


public float getOtMin(){
    return otMin;
}


public void setId(String id){
    this.id = id;
}


public void setDate(Date date){
    this.date = date;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDate(){
    return date;
}


@Override
public String toString(){
    return "EmpOtReg [id=" + id + ", empId=" + empId + ", empCode=" + empCode + ", empName=" + empName + ", designation=" + designation + ", otHr=" + otHr + ", month=" + month + ", date=" + date + ", otMin=" + otMin + "]";
}


public String getMonth(){
    return month;
}


public void setDesignation(String designation){
    this.designation = designation;
}


public void setOtHr(String otHr){
    this.otHr = otHr;
}


}
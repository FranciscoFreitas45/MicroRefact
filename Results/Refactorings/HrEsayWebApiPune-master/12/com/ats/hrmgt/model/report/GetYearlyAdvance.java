import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetYearlyAdvance {

@Id
 private  String uniqueId;

 private  int empId;

 private  String empName;

 private  String empCode;

 private  int month;

 private  int year;

 private  String advAmount;


public void setMonth(int month){
    this.month = month;
}


public void setAdvAmount(String advAmount){
    this.advAmount = advAmount;
}


public void setUniqueId(String uniqueId){
    this.uniqueId = uniqueId;
}


public void setYear(int year){
    this.year = year;
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


public void setEmpName(String empName){
    this.empName = empName;
}


public String getEmpCode(){
    return empCode;
}


public int getYear(){
    return year;
}


public String getAdvAmount(){
    return advAmount;
}


@Override
public String toString(){
    return "GetYearlyAdvance [uniqueId=" + uniqueId + ", empId=" + empId + ", empName=" + empName + ", empCode=" + empCode + ", month=" + month + ", year=" + year + ", advAmount=" + advAmount + "]";
}


public int getMonth(){
    return month;
}


}
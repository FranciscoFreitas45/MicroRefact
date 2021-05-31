import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class GetBirthDaysForDash {

@Id
 private  String uuid;

 private  int empId;

 private  String name;

 private  Date dob;

 private  String empCode;

 private  int age;

 private  String dateMonth;


public void setName(String name){
    this.name = name;
}


public int getAge(){
    return age;
}


public String getName(){
    return name;
}


public String getDateMonth(){
    return dateMonth;
}


@JsonFormat(locale = "English", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDob(){
    return dob;
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpCode(){
    return empCode;
}


public String getUuid(){
    return uuid;
}


public void setDob(Date dob){
    this.dob = dob;
}


@Override
public String toString(){
    return "GetBirthDaysForDash [uuid=" + uuid + ", empId=" + empId + ", name=" + name + ", dob=" + dob + ", empCode=" + empCode + ", age=" + age + ", dateMonth=" + dateMonth + "]";
}


public void setDateMonth(String dateMonth){
    this.dateMonth = dateMonth;
}


public void setAge(int age){
    this.age = age;
}


}
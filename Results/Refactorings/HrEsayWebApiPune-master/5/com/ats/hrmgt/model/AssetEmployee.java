import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class AssetEmployee {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int empId;

 private  String empCode;

 private  String firstName;

 private  String middleName;

 private  String surname;

 private  String empType;

 private  String designation;

 private  String location;

 private  String department;

 private  int exInt1;

 private  String exVar1;

 private  int locId;


public String getLocation(){
    return location;
}


public int getExInt1(){
    return exInt1;
}


public void setEmpType(String empType){
    this.empType = empType;
}


public String getDepartment(){
    return department;
}


public int getLocId(){
    return locId;
}


public String getExVar1(){
    return exVar1;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getMiddleName(){
    return middleName;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setSurname(String surname){
    this.surname = surname;
}


public void setLocation(String location){
    this.location = location;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getEmpType(){
    return empType;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public String getDesignation(){
    return designation;
}


public void setDepartment(String department){
    this.department = department;
}


public void setLocId(int locId){
    this.locId = locId;
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


public void setFirstName(String firstName){
    this.firstName = firstName;
}


@Override
public String toString(){
    return "AssetEmployee [empId=" + empId + ", empCode=" + empCode + ", firstName=" + firstName + ", middleName=" + middleName + ", surname=" + surname + ", empType=" + empType + ", designation=" + designation + ", location=" + location + ", department=" + department + ", exInt1=" + exInt1 + ", exVar1=" + exVar1 + ", locId=" + locId + "]";
}


public String getFirstName(){
    return firstName;
}


public void setDesignation(String designation){
    this.designation = designation;
}


public String getSurname(){
    return surname;
}


}
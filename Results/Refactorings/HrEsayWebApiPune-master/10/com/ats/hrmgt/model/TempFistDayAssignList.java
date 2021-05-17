import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
@Entity
public class TempFistDayAssignList {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "date")
 private  Date date;

@Column(name = "extra1")
 private  String extra1;

@Column(name = "extra2")
 private  int extra2;

@Column(name = "shift_id")
 private  int shiftId;

@Column(name = "first_name")
 private  String firstName;

@Column(name = "middle_name")
 private  String middleName;

@Column(name = "surname")
 private  String surname;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "shift_group")
 private  int shiftGroup;

@Column(name = "shift_type")
 private  int shiftType;

@Column(name = "shift_name")
 private  String shiftName;

@Column(name = "shift_group_name")
 private  String shiftGroupName;


public void setShiftGroup(int shiftGroup){
    this.shiftGroup = shiftGroup;
}


public int getId(){
    return id;
}


public int getShiftType(){
    return shiftType;
}


public String getMiddleName(){
    return middleName;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setExtra2(int extra2){
    this.extra2 = extra2;
}


public void setExtra1(String extra1){
    this.extra1 = extra1;
}


public String getExtra1(){
    return extra1;
}


public void setSurname(String surname){
    this.surname = surname;
}


public void setShiftName(String shiftName){
    this.shiftName = shiftName;
}


public void setId(int id){
    this.id = id;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getDate(){
    return date;
}


public int getExtra2(){
    return extra2;
}


public void setShiftType(int shiftType){
    this.shiftType = shiftType;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public String getShiftName(){
    return shiftName;
}


public void setShiftId(int shiftId){
    this.shiftId = shiftId;
}


public int getShiftGroup(){
    return shiftGroup;
}


public int getEmpId(){
    return empId;
}


public int getShiftId(){
    return shiftId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpCode(){
    return empCode;
}


public void setShiftGroupName(String shiftGroupName){
    this.shiftGroupName = shiftGroupName;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public void setDate(Date date){
    this.date = date;
}


@Override
public String toString(){
    return "TempFistDayAssignList [id=" + id + ", empId=" + empId + ", date=" + date + ", extra1=" + extra1 + ", extra2=" + extra2 + ", shiftId=" + shiftId + ", firstName=" + firstName + ", middleName=" + middleName + ", surname=" + surname + ", empCode=" + empCode + ", shiftGroup=" + shiftGroup + ", shiftType=" + shiftType + ", shiftName=" + shiftName + ", shiftGroupName=" + shiftGroupName + "]";
}


public String getShiftGroupName(){
    return shiftGroupName;
}


public String getFirstName(){
    return firstName;
}


public String getSurname(){
    return surname;
}


}
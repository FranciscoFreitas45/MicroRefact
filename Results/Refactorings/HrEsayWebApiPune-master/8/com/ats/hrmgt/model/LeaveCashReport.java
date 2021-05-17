import javax.persistence;
@Entity
public class LeaveCashReport {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "leave_count")
 private  float leaveCount;

@Column(name = "cash")
 private  float cash;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "first_name")
 private  String firstName;

@Column(name = "surname")
 private  String surname;

@Column(name = "paid_date")
 private  String paidDate;


public float getLeaveCount(){
    return leaveCount;
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


public String getEmpCode(){
    return empCode;
}


public float getCash(){
    return cash;
}


public void setLeaveCount(float leaveCount){
    this.leaveCount = leaveCount;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public void setSurname(String surname){
    this.surname = surname;
}


public String getPaidDate(){
    return paidDate;
}


@Override
public String toString(){
    return "LeaveCashReport [empId=" + empId + ", leaveCount=" + leaveCount + ", cash=" + cash + ", empCode=" + empCode + ", firstName=" + firstName + ", surname=" + surname + ", paidDate=" + paidDate + "]";
}


public void setCash(float cash){
    this.cash = cash;
}


public String getFirstName(){
    return firstName;
}


public void setPaidDate(String paidDate){
    this.paidDate = paidDate;
}


public String getSurname(){
    return surname;
}


}
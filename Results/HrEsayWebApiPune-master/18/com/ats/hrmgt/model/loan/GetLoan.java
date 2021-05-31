import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetLoan {

@Id
 private  int empId;

 private  int loanAmt;

 private  int loanRepayAmt;

 private  int loanEmi;

 private  int loanEmiIntrest;

 private  String empCode;

 private  String firstName;

 private  String middleName;

 private  String surname;

 private  String designation;

 private  String exVar1;

 private  String exVar2;

 private  String exVar3;

 private  int currentOutstanding;


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public int getLoanEmiIntrest(){
    return loanEmiIntrest;
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


public void setCurrentOutstanding(int currentOutstanding){
    this.currentOutstanding = currentOutstanding;
}


public int getCurrentOutstanding(){
    return currentOutstanding;
}


public void setLoanEmiIntrest(int loanEmiIntrest){
    this.loanEmiIntrest = loanEmiIntrest;
}


public int getLoanRepayAmt(){
    return loanRepayAmt;
}


public void setSurname(String surname){
    this.surname = surname;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public int getLoanEmi(){
    return loanEmi;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public int getLoanAmt(){
    return loanAmt;
}


public String getDesignation(){
    return designation;
}


public void setLoanAmt(int loanAmt){
    this.loanAmt = loanAmt;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setLoanRepayAmt(int loanRepayAmt){
    this.loanRepayAmt = loanRepayAmt;
}


public String getEmpCode(){
    return empCode;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


@Override
public String toString(){
    return "GetLoan [empId=" + empId + ", loanAmt=" + loanAmt + ", loanRepayAmt=" + loanRepayAmt + ", loanEmi=" + loanEmi + ", loanEmiIntrest=" + loanEmiIntrest + ", empCode=" + empCode + ", firstName=" + firstName + ", middleName=" + middleName + ", surname=" + surname + ", designation=" + designation + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", currentOutstanding=" + currentOutstanding + "]";
}


public void setLoanEmi(int loanEmi){
    this.loanEmi = loanEmi;
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
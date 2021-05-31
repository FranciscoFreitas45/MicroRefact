import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
@Entity
public class PendingLoanReport {

@Id
 private  String id;

 private  String empCode;

 private  String firstName;

 private  String middleName;

 private  String surname;

 private  String designation;

 private  String depatarment;

 private  float currentTotpaid;

 private  float currentOutstanding;

 private  float loanAmt;

 private  float loanEmi;

 private  Date loanRepayStart;

 private  Date loanRepayEnd;


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getLoanRepayStart(){
    return loanRepayStart;
}


public String getId(){
    return id;
}


public float getCurrentTotpaid(){
    return currentTotpaid;
}


public String getMiddleName(){
    return middleName;
}


public void setCurrentOutstanding(float currentOutstanding){
    this.currentOutstanding = currentOutstanding;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public float getCurrentOutstanding(){
    return currentOutstanding;
}


public void setLoanRepayStart(Date loanRepayStart){
    this.loanRepayStart = loanRepayStart;
}


public void setSurname(String surname){
    this.surname = surname;
}


public void setId(String id){
    this.id = id;
}


public float getLoanEmi(){
    return loanEmi;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public float getLoanAmt(){
    return loanAmt;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getLoanRepayEnd(){
    return loanRepayEnd;
}


public String getDepatarment(){
    return depatarment;
}


public String getDesignation(){
    return designation;
}


public void setLoanAmt(float loanAmt){
    this.loanAmt = loanAmt;
}


public void setLoanRepayEnd(Date loanRepayEnd){
    this.loanRepayEnd = loanRepayEnd;
}


public void setDepatarment(String depatarment){
    this.depatarment = depatarment;
}


public void setCurrentTotpaid(float currentTotpaid){
    this.currentTotpaid = currentTotpaid;
}


public String getEmpCode(){
    return empCode;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


@Override
public String toString(){
    return "PendingLoanReport [id=" + id + ", empCode=" + empCode + ", firstName=" + firstName + ", middleName=" + middleName + ", surname=" + surname + ", designation=" + designation + ", depatarment=" + depatarment + ", currentTotpaid=" + currentTotpaid + ", currentOutstanding=" + currentOutstanding + ", loanAmt=" + loanAmt + ", loanEmi=" + loanEmi + ", loanRepayStart=" + loanRepayStart + ", loanRepayEnd=" + loanRepayEnd + "]";
}


public void setLoanEmi(float loanEmi){
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
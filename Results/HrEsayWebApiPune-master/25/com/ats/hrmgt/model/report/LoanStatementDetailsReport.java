import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class LoanStatementDetailsReport {

@Id
 private  int id;

 private  int empId;

 private  String empCode;

 private  String empName;

 private  String loanApplNo;

 private  float loanAmt;

 private  String loanAddDate;

 private  float currentOutstanding;

 private  float currentTotpaid;

 private  float loanEmiIntrest;

 private  float loanEmi;

 private  String loanRepayStart;

 private  String loanRepayEnd;


public float getLoanEmiIntrest(){
    return loanEmiIntrest;
}


public String getLoanRepayStart(){
    return loanRepayStart;
}


public void setLoanApplNo(String loanApplNo){
    this.loanApplNo = loanApplNo;
}


public int getId(){
    return id;
}


public float getCurrentTotpaid(){
    return currentTotpaid;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setCurrentOutstanding(float currentOutstanding){
    this.currentOutstanding = currentOutstanding;
}


public String getEmpName(){
    return empName;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public float getCurrentOutstanding(){
    return currentOutstanding;
}


public void setLoanRepayStart(String loanRepayStart){
    this.loanRepayStart = loanRepayStart;
}


public void setLoanEmiIntrest(float loanEmiIntrest){
    this.loanEmiIntrest = loanEmiIntrest;
}


public void setId(int id){
    this.id = id;
}


public float getLoanEmi(){
    return loanEmi;
}


public float getLoanAmt(){
    return loanAmt;
}


public String getLoanRepayEnd(){
    return loanRepayEnd;
}


public String getLoanApplNo(){
    return loanApplNo;
}


public void setLoanAddDate(String loanAddDate){
    this.loanAddDate = loanAddDate;
}


public void setLoanAmt(float loanAmt){
    this.loanAmt = loanAmt;
}


public int getEmpId(){
    return empId;
}


public void setLoanRepayEnd(String loanRepayEnd){
    this.loanRepayEnd = loanRepayEnd;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setCurrentTotpaid(float currentTotpaid){
    this.currentTotpaid = currentTotpaid;
}


public String getEmpCode(){
    return empCode;
}


@Override
public String toString(){
    return "LoanStatementDetailsReport [id=" + id + ", empId=" + empId + ", empCode=" + empCode + ", empName=" + empName + ", loanApplNo=" + loanApplNo + ", loanAmt=" + loanAmt + ", loanAddDate=" + loanAddDate + ", currentOutstanding=" + currentOutstanding + ", currentTotpaid=" + currentTotpaid + ", loanEmiIntrest=" + loanEmiIntrest + ", loanEmi=" + loanEmi + ", loanRepayStart=" + loanRepayStart + ", loanRepayEnd=" + loanRepayEnd + "]";
}


public void setLoanEmi(float loanEmi){
    this.loanEmi = loanEmi;
}


public String getLoanAddDate(){
    return loanAddDate;
}


}
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "tbl_loan_main")
public class LoanMain {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int id;

 private  int cmpId;

 private  int empId;

 private  String loanApplNo;

 private  int loanAmt;

 private  double loanRoi;

 private  int loanTenure;

 private  Date loanRepayStart;

 private  Date loanRepayEnd;

 private  int loanRepayAmt;

 private  int loanEmi;

 private  int loanEmiIntrest;

 private  int currentTotpaid;

 private  int currentOutstanding;

 private  String loanStatus;

 private  String loginName;

 private  String loginTime;

 private  String allData;

 private  String remark;

 private  int skipId;

 private  Date loanAddDate;

 private  String skipLoginName;

 private  String skipLoginTime;

 private  String skipRemarks;

 private  int delStatus;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public int getLoanEmiIntrest(){
    return loanEmiIntrest;
}


public String getSkipLoginTime(){
    return skipLoginTime;
}


public String getExVar1(){
    return exVar1;
}


public int getCmpId(){
    return cmpId;
}


public void setLoanApplNo(String loanApplNo){
    this.loanApplNo = loanApplNo;
}


public void setCurrentOutstanding(int currentOutstanding){
    this.currentOutstanding = currentOutstanding;
}


public int getCurrentOutstanding(){
    return currentOutstanding;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public int getLoanRepayAmt(){
    return loanRepayAmt;
}


public void setLoanEmiIntrest(int loanEmiIntrest){
    this.loanEmiIntrest = loanEmiIntrest;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setLoginName(String loginName){
    this.loginName = loginName;
}


public String getRemark(){
    return remark;
}


public void setId(int id){
    this.id = id;
}


public int getLoanEmi(){
    return loanEmi;
}


public int getSkipId(){
    return skipId;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public String getSkipRemarks(){
    return skipRemarks;
}


public String getLoanStatus(){
    return loanStatus;
}


public void setLoanAmt(int loanAmt){
    this.loanAmt = loanAmt;
}


public void setSkipLoginName(String skipLoginName){
    this.skipLoginName = skipLoginName;
}


public void setLoanRoi(double loanRoi){
    this.loanRoi = loanRoi;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getEmpId(){
    return empId;
}


public void setLoanRepayEnd(Date loanRepayEnd){
    this.loanRepayEnd = loanRepayEnd;
}


public String getLoginName(){
    return loginName;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setLoanRepayAmt(int loanRepayAmt){
    this.loanRepayAmt = loanRepayAmt;
}


public void setSkipRemarks(String skipRemarks){
    this.skipRemarks = skipRemarks;
}


public void setLoanEmi(int loanEmi){
    this.loanEmi = loanEmi;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getLoanAddDate(){
    return loanAddDate;
}


public String getSkipLoginName(){
    return skipLoginName;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getLoanRepayStart(){
    return loanRepayStart;
}


public int getId(){
    return id;
}


public int getCurrentTotpaid(){
    return currentTotpaid;
}


public String getAllData(){
    return allData;
}


public String getLoginTime(){
    return loginTime;
}


public void setLoginTime(String loginTime){
    this.loginTime = loginTime;
}


public void setLoanRepayStart(Date loanRepayStart){
    this.loanRepayStart = loanRepayStart;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setLoanTenure(int loanTenure){
    this.loanTenure = loanTenure;
}


public int getLoanAmt(){
    return loanAmt;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getLoanRepayEnd(){
    return loanRepayEnd;
}


public void setSkipId(int skipId){
    this.skipId = skipId;
}


public String getLoanApplNo(){
    return loanApplNo;
}


public double getLoanRoi(){
    return loanRoi;
}


public void setLoanAddDate(Date loanAddDate){
    this.loanAddDate = loanAddDate;
}


public void setCmpId(int cmpId){
    this.cmpId = cmpId;
}


public void setSkipLoginTime(String skipLoginTime){
    this.skipLoginTime = skipLoginTime;
}


public void setCurrentTotpaid(int currentTotpaid){
    this.currentTotpaid = currentTotpaid;
}


public void setAllData(String allData){
    this.allData = allData;
}


public int getLoanTenure(){
    return loanTenure;
}


public int getDelStatus(){
    return delStatus;
}


@Override
public String toString(){
    return "LoanMain [id=" + id + ", cmpId=" + cmpId + ", empId=" + empId + ", loanApplNo=" + loanApplNo + ", loanAmt=" + loanAmt + ", loanRoi=" + loanRoi + ", loanTenure=" + loanTenure + ", loanRepayStart=" + loanRepayStart + ", loanRepayEnd=" + loanRepayEnd + ", loanRepayAmt=" + loanRepayAmt + ", loanEmi=" + loanEmi + ", loanEmiIntrest=" + loanEmiIntrest + ", currentTotpaid=" + currentTotpaid + ", currentOutstanding=" + currentOutstanding + ", loanStatus=" + loanStatus + ", loginName=" + loginName + ", loginTime=" + loginTime + ", allData=" + allData + ", remark=" + remark + ", skipId=" + skipId + ", loanAddDate=" + loanAddDate + ", skipLoginName=" + skipLoginName + ", skipLoginTime=" + skipLoginTime + ", skipRemarks=" + skipRemarks + ", delStatus=" + delStatus + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public void setLoanStatus(String loanStatus){
    this.loanStatus = loanStatus;
}


}
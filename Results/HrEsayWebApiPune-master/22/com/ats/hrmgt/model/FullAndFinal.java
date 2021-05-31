import javax.persistence;
@Entity
@Table(name = "t_fulnfinal")
public class FullAndFinal {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "emp_data_json")
 private  String empDataJson;

@Column(name = "no_of_month")
 private  String noOfMonth;

@Column(name = "no_of_present_count")
 private  float noOfPresentCount;

@Column(name = "advance_bal_amt")
 private  float advanceBalAmt;

@Column(name = "loan_bal_amt")
 private  float loanBalAmt;

@Column(name = "pay_bal_amt")
 private  float payBalAmt;

@Column(name = "leave_in_cash_amt")
 private  float leaveInCashAmt;

@Column(name = "claim_amt")
 private  float claimAmt;

@Column(name = "reward_amt")
 private  float rewardAmt;

@Column(name = "gratuity_amt")
 private  float gratuityAmt;

@Column(name = "payroll_amt")
 private  float payrollAmt;

@Column(name = "bonus_exgratia_amt")
 private  float bonusExgratiaAmt;

@Column(name = "adjst_plus")
 private  float adjstPlus;

@Column(name = "adjst_minus")
 private  float adjstMinus;

@Column(name = "net_amt")
 private  float netAmt;

@Column(name = "comment")
 private  String comment;

@Column(name = "login_id")
 private  int loginId;

@Column(name = "login_date")
 private  String loginDate;

@Column(name = "leaving_date")
 private  String leavingDate;

@Column(name = "extra_int1")
 private  int extraInt1;

@Column(name = "extra_int2")
 private  int extraInt2;

@Column(name = "extra_int3")
 private  int extraInt3;

@Column(name = "extra_varchar1")
 private  String extraVarchar1;

@Column(name = "extra_varchar2")
 private  String extraVarchar2;

@Column(name = "bonus_from_month")
 private  String bonusFromMonth;

@Column(name = "bonus_to_month")
 private  String bonusToMonth;

@Column(name = "leave_count_cash")
 private  int leaveCountCash;


public void setLoginId(int loginId){
    this.loginId = loginId;
}


public float getPayrollAmt(){
    return payrollAmt;
}


public void setPayrollAmt(float payrollAmt){
    this.payrollAmt = payrollAmt;
}


public void setLeavingDate(String leavingDate){
    this.leavingDate = leavingDate;
}


public void setId(int id){
    this.id = id;
}


public int getLoginId(){
    return loginId;
}


public String getBonusToMonth(){
    return bonusToMonth;
}


public float getGratuityAmt(){
    return gratuityAmt;
}


public void setAdvanceBalAmt(float advanceBalAmt){
    this.advanceBalAmt = advanceBalAmt;
}


public void setClaimAmt(float claimAmt){
    this.claimAmt = claimAmt;
}


public float getPayBalAmt(){
    return payBalAmt;
}


public float getBonusExgratiaAmt(){
    return bonusExgratiaAmt;
}


public float getAdjstPlus(){
    return adjstPlus;
}


public float getAdjstMinus(){
    return adjstMinus;
}


public void setNetAmt(float netAmt){
    this.netAmt = netAmt;
}


public int getEmpId(){
    return empId;
}


public void setLeaveInCashAmt(float leaveInCashAmt){
    this.leaveInCashAmt = leaveInCashAmt;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public float getLeaveInCashAmt(){
    return leaveInCashAmt;
}


public String getNoOfMonth(){
    return noOfMonth;
}


public void setExtraInt3(int extraInt3){
    this.extraInt3 = extraInt3;
}


public void setExtraInt2(int extraInt2){
    this.extraInt2 = extraInt2;
}


public void setExtraInt1(int extraInt1){
    this.extraInt1 = extraInt1;
}


public void setExtraVarchar1(String extraVarchar1){
    this.extraVarchar1 = extraVarchar1;
}


public void setExtraVarchar2(String extraVarchar2){
    this.extraVarchar2 = extraVarchar2;
}


public String getLeavingDate(){
    return leavingDate;
}


public float getAdvanceBalAmt(){
    return advanceBalAmt;
}


public void setRewardAmt(float rewardAmt){
    this.rewardAmt = rewardAmt;
}


public void setBonusFromMonth(String bonusFromMonth){
    this.bonusFromMonth = bonusFromMonth;
}


public int getId(){
    return id;
}


public void setLoginDate(String loginDate){
    this.loginDate = loginDate;
}


public void setBonusExgratiaAmt(float bonusExgratiaAmt){
    this.bonusExgratiaAmt = bonusExgratiaAmt;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public float getLoanBalAmt(){
    return loanBalAmt;
}


public int getExtraInt3(){
    return extraInt3;
}


public int getExtraInt2(){
    return extraInt2;
}


public int getExtraInt1(){
    return extraInt1;
}


public void setNoOfPresentCount(float noOfPresentCount){
    this.noOfPresentCount = noOfPresentCount;
}


public String getComment(){
    return comment;
}


public String getEmpDataJson(){
    return empDataJson;
}


public float getRewardAmt(){
    return rewardAmt;
}


public float getNoOfPresentCount(){
    return noOfPresentCount;
}


public void setBonusToMonth(String bonusToMonth){
    this.bonusToMonth = bonusToMonth;
}


public void setGratuityAmt(float gratuityAmt){
    this.gratuityAmt = gratuityAmt;
}


public String getBonusFromMonth(){
    return bonusFromMonth;
}


public String getExtraVarchar1(){
    return extraVarchar1;
}


public String getExtraVarchar2(){
    return extraVarchar2;
}


public String getLoginDate(){
    return loginDate;
}


public int getLeaveCountCash(){
    return leaveCountCash;
}


public float getClaimAmt(){
    return claimAmt;
}


public void setLeaveCountCash(int leaveCountCash){
    this.leaveCountCash = leaveCountCash;
}


public float getNetAmt(){
    return netAmt;
}


public void setNoOfMonth(String noOfMonth){
    this.noOfMonth = noOfMonth;
}


public String getEmpCode(){
    return empCode;
}


public void setAdjstMinus(float adjstMinus){
    this.adjstMinus = adjstMinus;
}


public void setLoanBalAmt(float loanBalAmt){
    this.loanBalAmt = loanBalAmt;
}


public void setComment(String comment){
    this.comment = comment;
}


@Override
public String toString(){
    return "FullAndFinal [id=" + id + ", empId=" + empId + ", empCode=" + empCode + ", empDataJson=" + empDataJson + ", noOfMonth=" + noOfMonth + ", noOfPresentCount=" + noOfPresentCount + ", advanceBalAmt=" + advanceBalAmt + ", loanBalAmt=" + loanBalAmt + ", payBalAmt=" + payBalAmt + ", leaveInCashAmt=" + leaveInCashAmt + ", claimAmt=" + claimAmt + ", rewardAmt=" + rewardAmt + ", gratuityAmt=" + gratuityAmt + ", payrollAmt=" + payrollAmt + ", bonusExgratiaAmt=" + bonusExgratiaAmt + ", adjstPlus=" + adjstPlus + ", adjstMinus=" + adjstMinus + ", netAmt=" + netAmt + ", comment=" + comment + ", loginId=" + loginId + ", loginDate=" + loginDate + ", leavingDate=" + leavingDate + ", extraInt1=" + extraInt1 + ", extraInt2=" + extraInt2 + ", extraInt3=" + extraInt3 + ", extraVarchar1=" + extraVarchar1 + ", extraVarchar2=" + extraVarchar2 + ", bonusFromMonth=" + bonusFromMonth + ", bonusToMonth=" + bonusToMonth + ", leaveCountCash=" + leaveCountCash + "]";
}


public void setEmpDataJson(String empDataJson){
    this.empDataJson = empDataJson;
}


public void setPayBalAmt(float payBalAmt){
    this.payBalAmt = payBalAmt;
}


public void setAdjstPlus(float adjstPlus){
    this.adjstPlus = adjstPlus;
}


}
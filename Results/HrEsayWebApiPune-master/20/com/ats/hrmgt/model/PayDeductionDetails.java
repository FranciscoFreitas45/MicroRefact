import javax.persistence;
@Entity
@Table(name = "tblm_pay_deduction_details")
public class PayDeductionDetails {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ded_id")
 private  int dedId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "cmp_id")
 private  int cmpId;

@Column(name = "ded_type_id")
 private  int dedTypeId;

@Column(name = "ded_rate")
 private  double dedRate;

@Column(name = "ded_occurence")
 private  int dedOccurence;

@Column(name = "ded_total")
 private  int dedTotal;

@Column(name = "ded_remark")
 private  String dedRemark;

@Column(name = "ded_login_name")
 private  String dedLoginName;

@Column(name = "ded_login_date_time")
 private  String dedLoginDteTime;

@Column(name = "ded_approved_by")
 private  String dedApproveBy;

@Column(name = "ded_approval_remark")
 private  String dedApprovalRemark;

@Column(name = "ded_approval_datetime")
 private  String dedApprovalDatetime;

@Column(name = "is_deducted")
 private  int isDeducted;

@Column(name = "final_status")
 private  int finalStatus;

@Column(name = "month")
 private  int month;

@Column(name = "year")
 private  int year;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "maker_enter_datetime")
 private  String makerEnterDatetime;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public int getDedTypeId(){
    return dedTypeId;
}


public void setFinalStatus(int finalStatus){
    this.finalStatus = finalStatus;
}


public int getExInt2(){
    return exInt2;
}


public void setDedApprovalDatetime(String dedApprovalDatetime){
    this.dedApprovalDatetime = dedApprovalDatetime;
}


public void setIsDeducted(int isDeducted){
    this.isDeducted = isDeducted;
}


public int getExInt1(){
    return exInt1;
}


public int getDedId(){
    return dedId;
}


public void setDedApproveBy(String dedApproveBy){
    this.dedApproveBy = dedApproveBy;
}


public String getExVar1(){
    return exVar1;
}


public int getCmpId(){
    return cmpId;
}


public void setDedLoginDteTime(String dedLoginDteTime){
    this.dedLoginDteTime = dedLoginDteTime;
}


public String getDedApproveBy(){
    return dedApproveBy;
}


public void setDedRemark(String dedRemark){
    this.dedRemark = dedRemark;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setDedOccurence(int dedOccurence){
    this.dedOccurence = dedOccurence;
}


public void setDedTotal(int dedTotal){
    this.dedTotal = dedTotal;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getDedApprovalRemark(){
    return dedApprovalRemark;
}


public String getDedLoginDteTime(){
    return dedLoginDteTime;
}


public int getMonth(){
    return month;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public void setDedId(int dedId){
    this.dedId = dedId;
}


public void setDedTypeId(int dedTypeId){
    this.dedTypeId = dedTypeId;
}


public void setMonth(int month){
    this.month = month;
}


public void setDedApprovalRemark(String dedApprovalRemark){
    this.dedApprovalRemark = dedApprovalRemark;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getIsDeducted(){
    return isDeducted;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getDedTotal(){
    return dedTotal;
}


public void setDedLoginName(String dedLoginName){
    this.dedLoginName = dedLoginName;
}


public int getEmpId(){
    return empId;
}


public void setCmpId(int cmpId){
    this.cmpId = cmpId;
}


public void setYear(int year){
    this.year = year;
}


public void setDedRate(double dedRate){
    this.dedRate = dedRate;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public double getDedRate(){
    return dedRate;
}


public int getDedOccurence(){
    return dedOccurence;
}


public String getDedLoginName(){
    return dedLoginName;
}


public int getFinalStatus(){
    return finalStatus;
}


public String getDedRemark(){
    return dedRemark;
}


public int getYear(){
    return year;
}


public int getDelStatus(){
    return delStatus;
}


@Override
public String toString(){
    return "PayDeductionDetails [dedId=" + dedId + ", empId=" + empId + ", cmpId=" + cmpId + ", dedTypeId=" + dedTypeId + ", dedRate=" + dedRate + ", dedOccurence=" + dedOccurence + ", dedTotal=" + dedTotal + ", dedRemark=" + dedRemark + ", dedLoginName=" + dedLoginName + ", dedLoginDteTime=" + dedLoginDteTime + ", dedApproveBy=" + dedApproveBy + ", dedApprovalRemark=" + dedApprovalRemark + ", dedApprovalDatetime=" + dedApprovalDatetime + ", isDeducted=" + isDeducted + ", finalStatus=" + finalStatus + ", month=" + month + ", year=" + year + ", delStatus=" + delStatus + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public String getDedApprovalDatetime(){
    return dedApprovalDatetime;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}
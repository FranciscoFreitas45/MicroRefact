import javax.persistence;
@Entity
@Table(name = "tblm_pay_bonus_details")
public class PayBonusDetails {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int payId;

 private  int empId;

 private  int cmpId;

 private  int payTypeId;

 private  double payRate;

 private  int payOccurence;

 private  int payTotal;

 private  String payRemark;

 private  String payLoginName;

 private  String payLoginDateTime;

 private  String payApproveBy;

 private  String payApprovalRemark;

 private  String payApprovalDatetime;

 private  int isPaid;

 private  int finalStatus;

 private  int month;

 private  int year;

 private  int delStatus;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  String exVar1;

 private  String exVar2;

@Transient
 private  boolean error;


public String getExVar2(){
    return exVar2;
}


public void setPayRemark(String payRemark){
    this.payRemark = payRemark;
}


public int getPayOccurence(){
    return payOccurence;
}


public void setPayApprovalDatetime(String payApprovalDatetime){
    this.payApprovalDatetime = payApprovalDatetime;
}


public String getExVar1(){
    return exVar1;
}


public int getCmpId(){
    return cmpId;
}


public void setPayLoginName(String payLoginName){
    this.payLoginName = payLoginName;
}


public String getPayApprovalRemark(){
    return payApprovalRemark;
}


public void setPayLoginDateTime(String payLoginDateTime){
    this.payLoginDateTime = payLoginDateTime;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public int getPayTotal(){
    return payTotal;
}


public int getMonth(){
    return month;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public String getPayLoginDateTime(){
    return payLoginDateTime;
}


public void setPayId(int payId){
    this.payId = payId;
}


public String getPayRemark(){
    return payRemark;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public void setPayRate(double payRate){
    this.payRate = payRate;
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


public double getPayRate(){
    return payRate;
}


public String getPayApproveBy(){
    return payApproveBy;
}


public int getPayTypeId(){
    return payTypeId;
}


public int getIsPaid(){
    return isPaid;
}


public int getYear(){
    return year;
}


public void setError(boolean error){
    this.error = error;
}


public String getPayLoginName(){
    return payLoginName;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setFinalStatus(int finalStatus){
    this.finalStatus = finalStatus;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public boolean isError(){
    return error;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public int getPayId(){
    return payId;
}


public void setPayOccurence(int payOccurence){
    this.payOccurence = payOccurence;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public void setPayTotal(int payTotal){
    this.payTotal = payTotal;
}


public void setMonth(int month){
    this.month = month;
}


public void setPayApprovalRemark(String payApprovalRemark){
    this.payApprovalRemark = payApprovalRemark;
}


public void setIsPaid(int isPaid){
    this.isPaid = isPaid;
}


public String getPayApprovalDatetime(){
    return payApprovalDatetime;
}


public void setCmpId(int cmpId){
    this.cmpId = cmpId;
}


public void setYear(int year){
    this.year = year;
}


public int getFinalStatus(){
    return finalStatus;
}


public void setPayTypeId(int payTypeId){
    this.payTypeId = payTypeId;
}


public int getDelStatus(){
    return delStatus;
}


public void setPayApproveBy(String payApproveBy){
    this.payApproveBy = payApproveBy;
}


@Override
public String toString(){
    return "PayBonusDetails [payId=" + payId + ", empId=" + empId + ", cmpId=" + cmpId + ", payTypeId=" + payTypeId + ", payRate=" + payRate + ", payOccurence=" + payOccurence + ", payTotal=" + payTotal + ", payRemark=" + payRemark + ", payLoginName=" + payLoginName + ", payLoginDateTime=" + payLoginDateTime + ", payApproveBy=" + payApproveBy + ", payApprovalRemark=" + payApprovalRemark + ", payApprovalDatetime=" + payApprovalDatetime + ", isPaid=" + isPaid + ", finalStatus=" + finalStatus + ", month=" + month + ", year=" + year + ", delStatus=" + delStatus + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", error=" + error + "]";
}


}
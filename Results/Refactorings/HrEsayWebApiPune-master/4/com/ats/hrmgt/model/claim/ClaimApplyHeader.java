import javax.persistence;
import java.util.List;
@Entity
@Table(name = "claim_apply_header")
public class ClaimApplyHeader {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ca_head_id")
 private  int caHeadId;

@Column(name = "ca_from_dt")
 private  String cafromDt;

@Column(name = "ca_to_dt")
 private  String caToDt;

@Column(name = "claim_title ")
 private  String claimTitle;

@Column(name = "proj_id")
 private  int projId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "claim_status")
 private  int claimStatus;

@Column(name = "claim_amount")
 private  float claimAmount;

@Column(name = "circulated_to")
 private  String circulatedTo;

@Column(name = "maker_user_id ")
 private  int makerUserId;

@Column(name = "maker_enter_datetime")
 private  String makerEnterDatetime;

@Column(name = "del_status ")
 private  int delStatus;

@Column(name = "is_active ")
 private  int isActive;

@Column(name = "ex_int1 ")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_int3")
 private  int exInt3;

@Column(name = "ex_var1 ")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;

@Column(name = "ex_var3")
 private  String exVar3;

@Column(name = "month ")
 private  int month;

@Column(name = "year")
 private  int year;

@Column(name = "is_paid")
 private  int isPaid;

@Transient
 private List<ClaimApply> detailList;


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public void setClaimTitle(String claimTitle){
    this.claimTitle = claimTitle;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt3(){
    return exInt3;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public int getCaHeadId(){
    return caHeadId;
}


public float getClaimAmount(){
    return claimAmount;
}


public void setDetailList(List<ClaimApply> detailList){
    this.detailList = detailList;
}


public void setCaToDt(String caToDt){
    this.caToDt = caToDt;
}


public String getCirculatedTo(){
    return circulatedTo;
}


public void setCirculatedTo(String circulatedTo){
    this.circulatedTo = circulatedTo;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setCafromDt(String cafromDt){
    this.cafromDt = cafromDt;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getCaToDt(){
    return caToDt;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


public int getMonth(){
    return month;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public String getCafromDt(){
    return cafromDt;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public int getClaimStatus(){
    return claimStatus;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public void setClaimStatus(int claimStatus){
    this.claimStatus = claimStatus;
}


public void setMonth(int month){
    this.month = month;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setCaHeadId(int caHeadId){
    this.caHeadId = caHeadId;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public String getClaimTitle(){
    return claimTitle;
}


public void setClaimAmount(float claimAmount){
    this.claimAmount = claimAmount;
}


public void setIsPaid(int isPaid){
    this.isPaid = isPaid;
}


public void setYear(int year){
    this.year = year;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public int getIsActive(){
    return isActive;
}


public int getIsPaid(){
    return isPaid;
}


public int getYear(){
    return year;
}


public int getDelStatus(){
    return delStatus;
}


@Override
public String toString(){
    return "ClaimApplyHeader [caHeadId=" + caHeadId + ", cafromDt=" + cafromDt + ", caToDt=" + caToDt + ", claimTitle=" + claimTitle + ", projId=" + projId + ", empId=" + empId + ", claimStatus=" + claimStatus + ", claimAmount=" + claimAmount + ", circulatedTo=" + circulatedTo + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", delStatus=" + delStatus + ", isActive=" + isActive + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", month=" + month + ", year=" + year + ", isPaid=" + isPaid + ", detailList=" + detailList + "]";
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


public List<ClaimApply> getDetailList(){
    return detailList;
}


public int getProjId(){
    return projId;
}


public void setProjId(int projId){
    this.projId = projId;
}


}
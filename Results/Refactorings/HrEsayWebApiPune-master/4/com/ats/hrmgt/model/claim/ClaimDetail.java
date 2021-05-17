import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class ClaimDetail {

@Id
 private  int claimId;

 private  int claimTypeId;

 private  float claimAmount;

 private  String claimRemarks;

 private  int delStatus;

 private  int isActive;

 private  int makerUserId;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  int exInt3;

 private  String exVar1;

 private  String exVar2;

 private  String exVar3;

 private  String claimTypeTitle;

 private  String claimTypeTitleSshort;


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
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


public float getClaimAmount(){
    return claimAmount;
}


public int getClaimTypeId(){
    return claimTypeId;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public String getClaimTypeTitle(){
    return claimTypeTitle;
}


public String getClaimTypeTitleSshort(){
    return claimTypeTitleSshort;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public String getClaimRemarks(){
    return claimRemarks;
}


public void setClaimTypeId(int claimTypeId){
    this.claimTypeId = claimTypeId;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setClaimTypeTitleSshort(String claimTypeTitleSshort){
    this.claimTypeTitleSshort = claimTypeTitleSshort;
}


public void setClaimTypeTitle(String claimTypeTitle){
    this.claimTypeTitle = claimTypeTitle;
}


public int getClaimId(){
    return claimId;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setClaimAmount(float claimAmount){
    this.claimAmount = claimAmount;
}


public void setClaimRemarks(String claimRemarks){
    this.claimRemarks = claimRemarks;
}


public void setClaimId(int claimId){
    this.claimId = claimId;
}


public int getIsActive(){
    return isActive;
}


public int getDelStatus(){
    return delStatus;
}


@Override
public String toString(){
    return "ClaimDetail [claimId=" + claimId + ", claimTypeId=" + claimTypeId + ", claimAmount=" + claimAmount + ", claimRemarks=" + claimRemarks + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", claimTypeTitle=" + claimTypeTitle + ", claimTypeTitleSshort=" + claimTypeTitleSshort + ", getClaimId()=" + getClaimId() + ", getClaimTypeId()=" + getClaimTypeId() + ", getClaimAmount()=" + getClaimAmount() + ", getClaimRemarks()=" + getClaimRemarks() + ", getDelStatus()=" + getDelStatus() + ", getIsActive()=" + getIsActive() + ", getMakerUserId()=" + getMakerUserId() + ", getMakerEnterDatetime()=" + getMakerEnterDatetime() + ", getExInt1()=" + getExInt1() + ", getExInt2()=" + getExInt2() + ", getExInt3()=" + getExInt3() + ", getExVar1()=" + getExVar1() + ", getExVar2()=" + getExVar2() + ", getExVar3()=" + getExVar3() + ", getClaimTypeTitle()=" + getClaimTypeTitle() + ", getClaimTypeTitleSshort()=" + getClaimTypeTitleSshort() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


}
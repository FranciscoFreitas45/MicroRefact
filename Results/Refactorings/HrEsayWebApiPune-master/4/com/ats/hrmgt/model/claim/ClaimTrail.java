import javax.persistence;
@Entity
@Table(name = "claim_trail")
public class ClaimTrail {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "claim_trail_pkey")
 private  int claimTrailPkey;

@Column(name = "claim_id")
 private  int claimId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "emp_remarks")
 private  String empRemarks;

@Column(name = "claim_status")
 private  int claimStatus;

@Column(name = "maker_user_id ")
 private  int makerUserId;

@Column(name = "maker_enter_datetime")
 private  String makerEnterDatetime;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_int3")
 private  int exInt3;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;

@Column(name = "ex_var3")
 private  String exVar3;

@Transient
 private  boolean error;


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


public void setEmpRemarks(String empRemarks){
    this.empRemarks = empRemarks;
}


public String getExVar1(){
    return exVar1;
}


public void setClaimTrailPkey(int claimTrailPkey){
    this.claimTrailPkey = claimTrailPkey;
}


public boolean isError(){
    return error;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
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


public int getClaimStatus(){
    return claimStatus;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public void setClaimStatus(int claimStatus){
    this.claimStatus = claimStatus;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public String getEmpRemarks(){
    return empRemarks;
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


public int getEmpId(){
    return empId;
}


public int getClaimTrailPkey(){
    return claimTrailPkey;
}


public void setClaimId(int claimId){
    this.claimId = claimId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setError(boolean error){
    this.error = error;
}


@Override
public String toString(){
    return "ClaimTrail [claimTrailPkey=" + claimTrailPkey + ", claimId=" + claimId + ", empId=" + empId + ", empRemarks=" + empRemarks + ", claimStatus=" + claimStatus + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + "]";
}


}
import javax.persistence;
@Entity
@Table(name = "leave_balance_cal")
public class LeaveBalanceCal {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "lvbal_id")
 private  int lvbalId;

@Column(name = "cal_yr_id")
 private  int calYrId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "lv_type_id")
 private  int lvTypeId;

@Column(name = "op_bal")
 private  float opBal;

@Column(name = "lvAlloted")
 private  float lvAlloted;

@Column(name = "lv_encash")
 private  float lvEncash;

@Column(name = "lv_encash_remarks")
 private  String lvEncashRemarks;

@Column(name = "lv_carry_fwd")
 private  float lvCarryFwd;

@Column(name = "lv_carry_fwd_remarks")
 private  String lvCarryFwdRemarks;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "is_active")
 private  int isActive;

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


public String getLvEncashRemarks(){
    return lvEncashRemarks;
}


public float getLvCarryFwd(){
    return lvCarryFwd;
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


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public float getLvEncash(){
    return lvEncash;
}


public void setOpBal(float opBal){
    this.opBal = opBal;
}


public boolean isError(){
    return error;
}


public int getLvbalId(){
    return lvbalId;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setLvCarryFwd(float lvCarryFwd){
    this.lvCarryFwd = lvCarryFwd;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


public float getOpBal(){
    return opBal;
}


public void setLvCarryFwdRemarks(String lvCarryFwdRemarks){
    this.lvCarryFwdRemarks = lvCarryFwdRemarks;
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


public void setLvEncash(float lvEncash){
    this.lvEncash = lvEncash;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public String getLvCarryFwdRemarks(){
    return lvCarryFwdRemarks;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setLvEncashRemarks(String lvEncashRemarks){
    this.lvEncashRemarks = lvEncashRemarks;
}


public void setLvAlloted(float lvAlloted){
    this.lvAlloted = lvAlloted;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public void setCalYrId(int calYrId){
    this.calYrId = calYrId;
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


public int getIsActive(){
    return isActive;
}


public void setLvbalId(int lvbalId){
    this.lvbalId = lvbalId;
}


public int getCalYrId(){
    return calYrId;
}


public int getDelStatus(){
    return delStatus;
}


public void setError(boolean error){
    this.error = error;
}


public int getLvTypeId(){
    return lvTypeId;
}


public float getLvAlloted(){
    return lvAlloted;
}


@Override
public String toString(){
    return "LeaveBalanceCal [lvbalId=" + lvbalId + ", calYrId=" + calYrId + ", empId=" + empId + ", lvTypeId=" + lvTypeId + ", opBal=" + opBal + ", lvAlloted=" + lvAlloted + ", lvEncash=" + lvEncash + ", lvEncashRemarks=" + lvEncashRemarks + ", lvCarryFwd=" + lvCarryFwd + ", lvCarryFwdRemarks=" + lvCarryFwdRemarks + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", error=" + error + "]";
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


}
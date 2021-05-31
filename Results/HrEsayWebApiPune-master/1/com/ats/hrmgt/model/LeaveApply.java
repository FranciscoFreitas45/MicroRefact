import javax.persistence;
@Entity
@Table(name = "leave_apply")
public class LeaveApply {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "leave_id")
 private  int leaveId;

@Column(name = "cal_yr_id")
 private  int calYrId;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "lv_type_id")
 private  int lvTypeId;

@Column(name = "leave_duration")
 private  String leaveDuration;

@Column(name = "leave_fromdt")
 private  String leaveFromdt;

@Column(name = "leave_todt")
 private  String leaveTodt;

@Column(name = "leave_num_days")
 private  float leaveNumDays;

@Column(name = "leave_emp_reason")
 private  String leaveEmpReason;

@Column(name = "final_status")
 private  int finalStatus;

@Column(name = "circulated_to")
 private  String circulatedTo;

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

@Column(name = "status")
 private  int status;

@Column(name = "leave_cancle_remark")
 private  String leaveCancleRemark;

@Column(name = "lvt_application_id_parent")
 private  int lvtApplicationIdParent;

@Column(name = "rec_status")
 private  String recStatus;

@Transient
 private  boolean error;


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public String getExVar1(){
    return exVar1;
}


public int getStatus(){
    return status;
}


public void setLeaveEmpReason(String leaveEmpReason){
    this.leaveEmpReason = leaveEmpReason;
}


public void setCirculatedTo(String circulatedTo){
    this.circulatedTo = circulatedTo;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public String getRecStatus(){
    return recStatus;
}


public void setLeaveDuration(String leaveDuration){
    this.leaveDuration = leaveDuration;
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public String getLeaveTodt(){
    return leaveTodt;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public void setLeaveFromdt(String leaveFromdt){
    this.leaveFromdt = leaveFromdt;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public void setLeaveTodt(String leaveTodt){
    this.leaveTodt = leaveTodt;
}


public String getLeaveCancleRemark(){
    return leaveCancleRemark;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
}


public int getLvtApplicationIdParent(){
    return lvtApplicationIdParent;
}


public int getLeaveId(){
    return leaveId;
}


public void setCalYrId(int calYrId){
    this.calYrId = calYrId;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setRecStatus(String recStatus){
    this.recStatus = recStatus;
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public int getCalYrId(){
    return calYrId;
}


public void setError(boolean error){
    this.error = error;
}


public void setLeaveId(int leaveId){
    this.leaveId = leaveId;
}


public int getLvTypeId(){
    return lvTypeId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


public void setFinalStatus(int finalStatus){
    this.finalStatus = finalStatus;
}


public void setLeaveCancleRemark(String leaveCancleRemark){
    this.leaveCancleRemark = leaveCancleRemark;
}


public int getExInt2(){
    return exInt2;
}


public void setLeaveNumDays(float leaveNumDays){
    this.leaveNumDays = leaveNumDays;
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


public String getCirculatedTo(){
    return circulatedTo;
}


public boolean isError(){
    return error;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getLeaveFromdt(){
    return leaveFromdt;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public void setLvtApplicationIdParent(int lvtApplicationIdParent){
    this.lvtApplicationIdParent = lvtApplicationIdParent;
}


public int getMakerUserId(){
    return makerUserId;
}


public String getLeaveDuration(){
    return leaveDuration;
}


public void setStatus(int status){
    this.status = status;
}


public String getLeaveEmpReason(){
    return leaveEmpReason;
}


public int getFinalStatus(){
    return finalStatus;
}


public int getIsActive(){
    return isActive;
}


public int getDelStatus(){
    return delStatus;
}


@Override
public String toString(){
    return "LeaveApply [leaveId=" + leaveId + ", calYrId=" + calYrId + ", empId=" + empId + ", lvTypeId=" + lvTypeId + ", leaveDuration=" + leaveDuration + ", leaveFromdt=" + leaveFromdt + ", leaveTodt=" + leaveTodt + ", leaveNumDays=" + leaveNumDays + ", leaveEmpReason=" + leaveEmpReason + ", finalStatus=" + finalStatus + ", circulatedTo=" + circulatedTo + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", status=" + status + ", leaveCancleRemark=" + leaveCancleRemark + ", lvtApplicationIdParent=" + lvtApplicationIdParent + ", recStatus=" + recStatus + ", error=" + error + "]";
}


public float getLeaveNumDays(){
    return leaveNumDays;
}


}
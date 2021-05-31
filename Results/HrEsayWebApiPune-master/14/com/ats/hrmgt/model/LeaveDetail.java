import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;
@Entity
public class LeaveDetail {

@Id
 private  int leaveId;

 private  int calYrId;

 private  int empId;

 private  int lvTypeId;

 private  String leaveDuration;

 private  String leaveFromdt;

 private  String leaveTodt;

 private  float leaveNumDays;

 private  String leaveEmpReason;

 private  int finalStatus;

 private  String circulatedTo;

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

 private  String empFname;

 private  String empMname;

 private  String empSname;

 private  String empCode;

 private  String empPhoto;

 private  String empDeptName;

 private  String lvTitle;

 private  String userName;

@Transient
 private List<GetLeaveStatus> GetLeaveStatusList;


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public String getExVar1(){
    return exVar1;
}


public void setLeaveEmpReason(String leaveEmpReason){
    this.leaveEmpReason = leaveEmpReason;
}


public String getEmpSname(){
    return empSname;
}


public void setCirculatedTo(String circulatedTo){
    this.circulatedTo = circulatedTo;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setEmpFname(String empFname){
    this.empFname = empFname;
}


public String getUserName(){
    return userName;
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


public String getEmpFname(){
    return empFname;
}


public String getLeaveTodt(){
    return leaveTodt;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public void setLvTitle(String lvTitle){
    this.lvTitle = lvTitle;
}


public void setLeaveFromdt(String leaveFromdt){
    this.leaveFromdt = leaveFromdt;
}


public String getLvTitle(){
    return lvTitle;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public void setLeaveTodt(String leaveTodt){
    this.leaveTodt = leaveTodt;
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
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


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpPhoto(){
    return empPhoto;
}


public List<GetLeaveStatus> getGetLeaveStatusList(){
    return GetLeaveStatusList;
}


public String getEmpDeptName(){
    return empDeptName;
}


public int getCalYrId(){
    return calYrId;
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


public void setGetLeaveStatusList(List<GetLeaveStatus> getLeaveStatusList){
    GetLeaveStatusList = getLeaveStatusList;
}


public void setFinalStatus(int finalStatus){
    this.finalStatus = finalStatus;
}


public String getEmpMname(){
    return empMname;
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


public void setEmpSname(String empSname){
    this.empSname = empSname;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getCirculatedTo(){
    return circulatedTo;
}


public void setUserName(String userName){
    this.userName = userName;
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


public void setEmpDeptName(String empDeptName){
    this.empDeptName = empDeptName;
}


public int getMakerUserId(){
    return makerUserId;
}


public String getLeaveDuration(){
    return leaveDuration;
}


public void setEmpPhoto(String empPhoto){
    this.empPhoto = empPhoto;
}


public void setEmpMname(String empMname){
    this.empMname = empMname;
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


public String getEmpCode(){
    return empCode;
}


public int getDelStatus(){
    return delStatus;
}


@Override
public String toString(){
    return "LeaveDetail [leaveId=" + leaveId + ", calYrId=" + calYrId + ", empId=" + empId + ", lvTypeId=" + lvTypeId + ", leaveDuration=" + leaveDuration + ", leaveFromdt=" + leaveFromdt + ", leaveTodt=" + leaveTodt + ", leaveNumDays=" + leaveNumDays + ", leaveEmpReason=" + leaveEmpReason + ", finalStatus=" + finalStatus + ", circulatedTo=" + circulatedTo + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exInt3=" + exInt3 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", exVar3=" + exVar3 + ", empFname=" + empFname + ", empMname=" + empMname + ", empSname=" + empSname + ", empCode=" + empCode + ", empPhoto=" + empPhoto + ", empDeptName=" + empDeptName + ", lvTitle=" + lvTitle + ", userName=" + userName + ", GetLeaveStatusList=" + GetLeaveStatusList + ", getLeaveId()=" + getLeaveId() + ", getCalYrId()=" + getCalYrId() + ", getEmpId()=" + getEmpId() + ", getLvTypeId()=" + getLvTypeId() + ", getLeaveDuration()=" + getLeaveDuration() + ", getLeaveFromdt()=" + getLeaveFromdt() + ", getLeaveTodt()=" + getLeaveTodt() + ", getLeaveNumDays()=" + getLeaveNumDays() + ", getLeaveEmpReason()=" + getLeaveEmpReason() + ", getFinalStatus()=" + getFinalStatus() + ", getCirculatedTo()=" + getCirculatedTo() + ", getDelStatus()=" + getDelStatus() + ", getIsActive()=" + getIsActive() + ", getMakerUserId()=" + getMakerUserId() + ", getMakerEnterDatetime()=" + getMakerEnterDatetime() + ", getExInt1()=" + getExInt1() + ", getExInt2()=" + getExInt2() + ", getExInt3()=" + getExInt3() + ", getExVar1()=" + getExVar1() + ", getExVar2()=" + getExVar2() + ", getExVar3()=" + getExVar3() + ", getEmpFname()=" + getEmpFname() + ", getEmpMname()=" + getEmpMname() + ", getEmpSname()=" + getEmpSname() + ", getEmpCode()=" + getEmpCode() + ", getEmpPhoto()=" + getEmpPhoto() + ", getEmpDeptName()=" + getEmpDeptName() + ", getLvTitle()=" + getLvTitle() + ", getGetLeaveStatusList()=" + getGetLeaveStatusList() + ", getUserName()=" + getUserName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}


public float getLeaveNumDays(){
    return leaveNumDays;
}


}
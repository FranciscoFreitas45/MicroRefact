import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetLeaveApplyAuthwise {

@Id
 private  int leaveId;

 private  int calYrId;

 private  String leaveTitle;

 private  int empId;

 private  String empName;

 private  String iniAuthEmpId;

 private  String finAuthEmpId;

 private  int exInt1;

 private  String empCode;

 private  int lvTypeId;

 private  String leaveTypeName;

 private  String leaveDuration;

 private  String leaveFromdt;

 private  String leaveTodt;

 private  float leaveNumDays;

 private  String leaveEmpReason;

 private  String circulatedTo;

 private  String empPhoto;

 private  String exVar2;


public String getLeaveTypeName(){
    return leaveTypeName;
}


public String getExVar2(){
    return exVar2;
}


public void setFinAuthEmpId(String finAuthEmpId){
    this.finAuthEmpId = finAuthEmpId;
}


public void setLeaveNumDays(float leaveNumDays){
    this.leaveNumDays = leaveNumDays;
}


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
}


public int getExInt1(){
    return exInt1;
}


public String getFinAuthEmpId(){
    return finAuthEmpId;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public void setLeaveEmpReason(String leaveEmpReason){
    this.leaveEmpReason = leaveEmpReason;
}


public String getLeaveTitle(){
    return leaveTitle;
}


public String getEmpName(){
    return empName;
}


public String getCirculatedTo(){
    return circulatedTo;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setCirculatedTo(String circulatedTo){
    this.circulatedTo = circulatedTo;
}


public void setLeaveDuration(String leaveDuration){
    this.leaveDuration = leaveDuration;
}


public String getLeaveFromdt(){
    return leaveFromdt;
}


public String getLeaveTodt(){
    return leaveTodt;
}


public void setLeaveTitle(String leaveTitle){
    this.leaveTitle = leaveTitle;
}


public void setLeaveFromdt(String leaveFromdt){
    this.leaveFromdt = leaveFromdt;
}


public void setLeaveTypeName(String leaveTypeName){
    this.leaveTypeName = leaveTypeName;
}


public void setLeaveTodt(String leaveTodt){
    this.leaveTodt = leaveTodt;
}


public String getLeaveDuration(){
    return leaveDuration;
}


public int getLeaveId(){
    return leaveId;
}


public void setCalYrId(int calYrId){
    this.calYrId = calYrId;
}


public void setIniAuthEmpId(String iniAuthEmpId){
    this.iniAuthEmpId = iniAuthEmpId;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public String getIniAuthEmpId(){
    return iniAuthEmpId;
}


public void setEmpPhoto(String empPhoto){
    this.empPhoto = empPhoto;
}


public int getEmpId(){
    return empId;
}


public String getEmpPhoto(){
    return empPhoto;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getLeaveEmpReason(){
    return leaveEmpReason;
}


public String getEmpCode(){
    return empCode;
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


@Override
public String toString(){
    return "GetLeaveApplyAuthwise [leaveId=" + leaveId + ", calYrId=" + calYrId + ", leaveTitle=" + leaveTitle + ", empId=" + empId + ", empName=" + empName + ", iniAuthEmpId=" + iniAuthEmpId + ", finAuthEmpId=" + finAuthEmpId + ", exInt1=" + exInt1 + ", empCode=" + empCode + ", lvTypeId=" + lvTypeId + ", leaveTypeName=" + leaveTypeName + ", leaveDuration=" + leaveDuration + ", leaveFromdt=" + leaveFromdt + ", leaveTodt=" + leaveTodt + ", leaveNumDays=" + leaveNumDays + ", leaveEmpReason=" + leaveEmpReason + ", circulatedTo=" + circulatedTo + ", empPhoto=" + empPhoto + ", exVar2=" + exVar2 + "]";
}


public float getLeaveNumDays(){
    return leaveNumDays;
}


}
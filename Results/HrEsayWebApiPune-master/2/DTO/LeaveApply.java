import javax.persistence;
public class LeaveApply {

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

 private  int status;

 private  String leaveCancleRemark;

 private  int lvtApplicationIdParent;

 private  String recStatus;

 private  boolean error;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


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


public String getRecStatus(){
    return recStatus;
}


public String getLeaveTodt(){
    return leaveTodt;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public String getLeaveCancleRemark(){
    return leaveCancleRemark;
}


public int getLvtApplicationIdParent(){
    return lvtApplicationIdParent;
}


public int getLeaveId(){
    return leaveId;
}


public int getEmpId(){
    return empId;
}


public int getCalYrId(){
    return calYrId;
}


public int getLvTypeId(){
    return lvTypeId;
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


public String getCirculatedTo(){
    return circulatedTo;
}


public String getLeaveFromdt(){
    return leaveFromdt;
}


public int getMakerUserId(){
    return makerUserId;
}


public String getLeaveDuration(){
    return leaveDuration;
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


public float getLeaveNumDays(){
    return leaveNumDays;
}


public void setCalYrId(int calYrId){
    this.calYrId = calYrId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setCalYrId"));

.queryParam("calYrId",calYrId);
restTemplate.put(builder.toUriString(),null);
}


public void setEmpId(int empId){
    this.empId = empId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setEmpId"));

.queryParam("empId",empId);
restTemplate.put(builder.toUriString(),null);
}


public void setFinalStatus(int finalStatus){
    this.finalStatus = finalStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setFinalStatus"));

.queryParam("finalStatus",finalStatus);
restTemplate.put(builder.toUriString(),null);
}


public void setLeaveDuration(String leaveDuration){
    this.leaveDuration = leaveDuration;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setLeaveDuration"));

.queryParam("leaveDuration",leaveDuration);
restTemplate.put(builder.toUriString(),null);
}


public void setCirculatedTo(String circulatedTo){
    this.circulatedTo = circulatedTo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setCirculatedTo"));

.queryParam("circulatedTo",circulatedTo);
restTemplate.put(builder.toUriString(),null);
}


public void setLeaveEmpReason(String leaveEmpReason){
    this.leaveEmpReason = leaveEmpReason;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setLeaveEmpReason"));

.queryParam("leaveEmpReason",leaveEmpReason);
restTemplate.put(builder.toUriString(),null);
}


public void setLeaveCancleRemark(String leaveCancleRemark){
    this.leaveCancleRemark = leaveCancleRemark;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setLeaveCancleRemark"));

.queryParam("leaveCancleRemark",leaveCancleRemark);
restTemplate.put(builder.toUriString(),null);
}


public void setLvtApplicationIdParent(int lvtApplicationIdParent){
    this.lvtApplicationIdParent = lvtApplicationIdParent;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setLvtApplicationIdParent"));

.queryParam("lvtApplicationIdParent",lvtApplicationIdParent);
restTemplate.put(builder.toUriString(),null);
}


public void setLvTypeId(int lvTypeId){
    this.lvTypeId = lvTypeId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setLvTypeId"));

.queryParam("lvTypeId",lvTypeId);
restTemplate.put(builder.toUriString(),null);
}


public void setLeaveFromdt(String leaveFromdt){
    this.leaveFromdt = leaveFromdt;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setLeaveFromdt"));

.queryParam("leaveFromdt",leaveFromdt);
restTemplate.put(builder.toUriString(),null);
}


public void setLeaveTodt(String leaveTodt){
    this.leaveTodt = leaveTodt;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setLeaveTodt"));

.queryParam("leaveTodt",leaveTodt);
restTemplate.put(builder.toUriString(),null);
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setExInt1"));

.queryParam("exInt1",exInt1);
restTemplate.put(builder.toUriString(),null);
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setExInt2"));

.queryParam("exInt2",exInt2);
restTemplate.put(builder.toUriString(),null);
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setExInt3"));

.queryParam("exInt3",exInt3);
restTemplate.put(builder.toUriString(),null);
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setExVar1"));

.queryParam("exVar1",exVar1);
restTemplate.put(builder.toUriString(),null);
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setExVar2"));

.queryParam("exVar2",exVar2);
restTemplate.put(builder.toUriString(),null);
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setExVar3"));

.queryParam("exVar3",exVar3);
restTemplate.put(builder.toUriString(),null);
}


public void setIsActive(int isActive){
    this.isActive = isActive;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setIsActive"));

.queryParam("isActive",isActive);
restTemplate.put(builder.toUriString(),null);
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setDelStatus"));

.queryParam("delStatus",delStatus);
restTemplate.put(builder.toUriString(),null);
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setMakerUserId"));

.queryParam("makerUserId",makerUserId);
restTemplate.put(builder.toUriString(),null);
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setMakerEnterDatetime"));

.queryParam("makerEnterDatetime",makerEnterDatetime);
restTemplate.put(builder.toUriString(),null);
}


public void setLeaveNumDays(float leaveNumDays){
    this.leaveNumDays = leaveNumDays;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ leaveId).concat("/setLeaveNumDays"));

.queryParam("leaveNumDays",leaveNumDays);
restTemplate.put(builder.toUriString(),null);
}


}
import javax.persistence;
public class LeaveTrail {

 private  int trailPkey;

 private  int leaveId;

 private  int empId;

 private  String empRemarks;

 private  int leaveStatus;

 private  int makerUserId;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  int exInt3;

 private  String exVar1;

 private  String exVar2;

 private  String exVar3;

 private  boolean error;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


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


public int getLeaveStatus(){
    return leaveStatus;
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


public int getTrailPkey(){
    return trailPkey;
}


public int getLeaveId(){
    return leaveId;
}


public int getEmpId(){
    return empId;
}


public void setEmpRemarks(String empRemarks){
    this.empRemarks = empRemarks;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setEmpRemarks"));

.queryParam("empRemarks",empRemarks);
restTemplate.put(builder.toUriString(),null);
}


public void setLeaveId(int leaveId){
    this.leaveId = leaveId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setLeaveId"));

.queryParam("leaveId",leaveId);
restTemplate.put(builder.toUriString(),null);
}


public void setLeaveStatus(int leaveStatus){
    this.leaveStatus = leaveStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setLeaveStatus"));

.queryParam("leaveStatus",leaveStatus);
restTemplate.put(builder.toUriString(),null);
}


public void setEmpId(int empId){
    this.empId = empId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setEmpId"));

.queryParam("empId",empId);
restTemplate.put(builder.toUriString(),null);
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setExInt1"));

.queryParam("exInt1",exInt1);
restTemplate.put(builder.toUriString(),null);
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setExInt2"));

.queryParam("exInt2",exInt2);
restTemplate.put(builder.toUriString(),null);
}


public void setExInt3(int exInt3){
    this.exInt3 = exInt3;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setExInt3"));

.queryParam("exInt3",exInt3);
restTemplate.put(builder.toUriString(),null);
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setExVar1"));

.queryParam("exVar1",exVar1);
restTemplate.put(builder.toUriString(),null);
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setExVar2"));

.queryParam("exVar2",exVar2);
restTemplate.put(builder.toUriString(),null);
}


public void setExVar3(String exVar3){
    this.exVar3 = exVar3;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setExVar3"));

.queryParam("exVar3",exVar3);
restTemplate.put(builder.toUriString(),null);
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setMakerUserId"));

.queryParam("makerUserId",makerUserId);
restTemplate.put(builder.toUriString(),null);
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ trailPkey).concat("/setMakerEnterDatetime"));

.queryParam("makerEnterDatetime",makerEnterDatetime);
restTemplate.put(builder.toUriString(),null);
}


}
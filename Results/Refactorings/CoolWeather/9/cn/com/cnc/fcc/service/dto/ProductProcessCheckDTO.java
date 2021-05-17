import java.time.ZonedDateTime;
public class ProductProcessCheckDTO {

 private  Long id;

 private  String checkNumber;

 private  Integer bomTechnologyId;

 private  Integer processId;

 private  Integer materielId;

 private  String materielCd;

 private  String materielName;

 private  String processName;

 private  String serialNumber;

 private  String furnace;

 private  String workno;

 private  String remark;

 private  String isOk;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String approveResultDiff;


public String getProcessName(){
    return processName;
}


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public Integer getBomTechnologyId(){
    return bomTechnologyId;
}


public String getMakeUser(){
    return makeUser;
}


public String getMaterielCd(){
    return materielCd;
}


public String getFurnace(){
    return furnace;
}


public void setFurnace(String furnace){
    this.furnace = furnace;
}


public String getCheckNumber(){
    return checkNumber;
}


public void setProcessName(String processName){
    this.processName = processName;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public void setMaterielId(Integer materielId){
    this.materielId = materielId;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public Integer getProcessId(){
    return processId;
}


public void setId(Long id){
    this.id = id;
}


public String getIsOk(){
    return isOk;
}


public void setCheckNumber(String checkNumber){
    this.checkNumber = checkNumber;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public String getSerialNumber(){
    return serialNumber;
}


public void setIsOk(String isOk){
    this.isOk = isOk;
}


public void setSerialNumber(String serialNumber){
    this.serialNumber = serialNumber;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public void setProcessId(Integer processId){
    this.processId = processId;
}


public String getMaterielName(){
    return materielName;
}


public void setApproveResultDiff(String approveResultDiff){
    this.approveResultDiff = approveResultDiff;
}


public String getWorkno(){
    return workno;
}


public void setBomTechnologyId(Integer bomTechnologyId){
    this.bomTechnologyId = bomTechnologyId;
}


public void setWorkno(String workno){
    this.workno = workno;
}


public String getApproveResultDiff(){
    return approveResultDiff;
}


}
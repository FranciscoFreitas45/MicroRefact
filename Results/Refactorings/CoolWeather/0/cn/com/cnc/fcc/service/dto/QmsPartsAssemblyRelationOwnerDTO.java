import java.time.ZonedDateTime;
public class QmsPartsAssemblyRelationOwnerDTO {

 private  Long id;

 private  Integer bomTechnologyId;

 private  Integer assemblyNum;

 private  Integer assemblyMaterielId;

 private  String assemblyMaterielCd;

 private  String assemblyMaterielName;

 private  Integer assemblyCount;

 private  String remark;

 private  String flagStatus;

 private  String compPkid;

 private  String reserveFirst;

 private  String reserveSecond;

 private  String reserveThird;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;


public Long getId(){
    return id;
}


public Integer getBomTechnologyId(){
    return bomTechnologyId;
}


public String getMakeUser(){
    return makeUser;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public Integer getAssemblyCount(){
    return assemblyCount;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public void setAssemblyMaterielCd(String assemblyMaterielCd){
    this.assemblyMaterielCd = assemblyMaterielCd;
}


public String getReserveThird(){
    return reserveThird;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public Integer getAssemblyMaterielId(){
    return assemblyMaterielId;
}


public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public String getAssemblyMaterielName(){
    return assemblyMaterielName;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public void setAssemblyNum(Integer assemblyNum){
    this.assemblyNum = assemblyNum;
}


public void setAssemblyMaterielId(Integer assemblyMaterielId){
    this.assemblyMaterielId = assemblyMaterielId;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getCompPkid(){
    return compPkid;
}


public String getAssemblyMaterielCd(){
    return assemblyMaterielCd;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public String getModifyUser(){
    return modifyUser;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public void setAssemblyCount(Integer assemblyCount){
    this.assemblyCount = assemblyCount;
}


public void setBomTechnologyId(Integer bomTechnologyId){
    this.bomTechnologyId = bomTechnologyId;
}


public void setAssemblyMaterielName(String assemblyMaterielName){
    this.assemblyMaterielName = assemblyMaterielName;
}


public Integer getAssemblyNum(){
    return assemblyNum;
}


public String getFlagStatus(){
    return flagStatus;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


}
package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsPartsAssemblyRelation implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer bomTechnologyId;

 private  Integer assemblyNum;

 private  Integer assemblyMaterielId;

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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://18";


public Long getId(){
    return id;
}


public QmsPartsAssemblyRelation reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public Integer getBomTechnologyId(){
    return bomTechnologyId;
}


public String getMakeUser(){
    return makeUser;
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


public String getReserveFirst(){
    return reserveFirst;
}


public void setAssemblyNum(Integer assemblyNum){
    this.assemblyNum = assemblyNum;
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


public String getModifyUser(){
    return modifyUser;
}


public QmsPartsAssemblyRelation makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsPartsAssemblyRelation makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public Integer getAssemblyNum(){
    return assemblyNum;
}


public QmsPartsAssemblyRelation assemblyCount(Integer assemblyCount){
    this.assemblyCount = assemblyCount;
    return this;
}


@Override
public String toString(){
    return "QmsPartsAssemblyRelation{" + "id=" + getId() + ", bomTechnologyId=" + getBomTechnologyId() + ", assemblyNum=" + getAssemblyNum() + ", assemblyMaterielId=" + getAssemblyMaterielId() + ", assemblyCount=" + getAssemblyCount() + ", remark='" + getRemark() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getFlagStatus(){
    return flagStatus;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public void setBomTechnologyId(Integer bomTechnologyId){
    this.bomTechnologyId = bomTechnologyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setBomTechnologyId"))

.queryParam("bomTechnologyId",bomTechnologyId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAssemblyMaterielId(Integer assemblyMaterielId){
    this.assemblyMaterielId = assemblyMaterielId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAssemblyMaterielId"))

.queryParam("assemblyMaterielId",assemblyMaterielId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAssemblyCount(Integer assemblyCount){
    this.assemblyCount = assemblyCount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAssemblyCount"))

.queryParam("assemblyCount",assemblyCount)
;
restTemplate.put(builder.toUriString(),null);
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setModifyTime"))

.queryParam("modifyTime",modifyTime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setFlagStatus"))

.queryParam("flagStatus",flagStatus)
;
restTemplate.put(builder.toUriString(),null);
}


}
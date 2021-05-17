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


public Long getId(){
    return id;
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


public String getReserveThird(){
    return reserveThird;
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


public String getReserveSecond(){
    return reserveSecond;
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


public Integer getAssemblyNum(){
    return assemblyNum;
}


public String getFlagStatus(){
    return flagStatus;
}


public void setBomTechnologyId(Integer bomTechnologyId){
    this.bomTechnologyId = bomTechnologyId;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setBomTechnologyId"));

.queryParam("bomTechnologyId",bomTechnologyId);
restTemplate.put(builder.toUriString(),null);


public void setAssemblyNum(Integer assemblyNum){
    this.assemblyNum = assemblyNum;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAssemblyNum"));

.queryParam("assemblyNum",assemblyNum);
restTemplate.put(builder.toUriString(),null);


public void setAssemblyMaterielId(Integer assemblyMaterielId){
    this.assemblyMaterielId = assemblyMaterielId;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAssemblyMaterielId"));

.queryParam("assemblyMaterielId",assemblyMaterielId);
restTemplate.put(builder.toUriString(),null);


public void setAssemblyCount(Integer assemblyCount){
    this.assemblyCount = assemblyCount;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAssemblyCount"));

.queryParam("assemblyCount",assemblyCount);
restTemplate.put(builder.toUriString(),null);


public void setRemark(String remark){
    this.remark = remark;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRemark"));

.queryParam("remark",remark);
restTemplate.put(builder.toUriString(),null);


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMakeTime"));

.queryParam("makeTime",makeTime);
restTemplate.put(builder.toUriString(),null);


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMakeUser"));

.queryParam("makeUser",makeUser);
restTemplate.put(builder.toUriString(),null);


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setModifyTime"));

.queryParam("modifyTime",modifyTime);
restTemplate.put(builder.toUriString(),null);


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setModifyUser"));

.queryParam("modifyUser",modifyUser);
restTemplate.put(builder.toUriString(),null);


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setFlagStatus"));

.queryParam("flagStatus",flagStatus);
restTemplate.put(builder.toUriString(),null);


}
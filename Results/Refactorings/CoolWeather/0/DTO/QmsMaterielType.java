import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsMaterielType implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String materielTypeCd;

 private  String materielTypeName;

 private  String parentCd;

 private  String flagStatus;

 private  String compPkid;

 private  String remark;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;

 private RestTemplate restTemplate = new RestTemplate();


public String getParentCd(){
    return parentCd;
}


public Long getId(){
    return id;
}


public String getMakeUser(){
    return makeUser;
}


public String getMaterielTypeName(){
    return materielTypeName;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public String getMaterielTypeCd(){
    return materielTypeCd;
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


public String getFlagStatus(){
    return flagStatus;
}


public void setParentCd(String parentCd){
    this.parentCd = parentCd;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setParentCd"));

.queryParam("parentCd",parentCd);
restTemplate.put(builder.toUriString(),null);


public void setMaterielTypeCd(String materielTypeCd){
    this.materielTypeCd = materielTypeCd;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMaterielTypeCd"));

.queryParam("materielTypeCd",materielTypeCd);
restTemplate.put(builder.toUriString(),null);


public void setMaterielTypeName(String materielTypeName){
    this.materielTypeName = materielTypeName;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMaterielTypeName"));

.queryParam("materielTypeName",materielTypeName);
restTemplate.put(builder.toUriString(),null);


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setFlagStatus"));

.queryParam("flagStatus",flagStatus);
restTemplate.put(builder.toUriString(),null);


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMakeUser"));

.queryParam("makeUser",makeUser);
restTemplate.put(builder.toUriString(),null);


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMakeTime"));

.queryParam("makeTime",makeTime);
restTemplate.put(builder.toUriString(),null);


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setModifyUser"));

.queryParam("modifyUser",modifyUser);
restTemplate.put(builder.toUriString(),null);


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setModifyTime"));

.queryParam("modifyTime",modifyTime);
restTemplate.put(builder.toUriString(),null);


@Override
public String toString(){
    return "QmsMaterielType{" + "id=" + getId() + ", materielTypeCd='" + getMaterielTypeCd() + "'" + ", materielTypeName='" + getMaterielTypeName() + "'" + ", parentCd='" + getParentCd() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/toString"));

String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setCompPkid"));

.queryParam("compPkid",compPkid);
restTemplate.put(builder.toUriString(),null);


}
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsEnclosure implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer inspectionInfoId;

 private  String inspectionKbn;

 private  String enclosureAddress;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;

 private RestTemplate restTemplate = new RestTemplate();


public Long getId(){
    return id;
}


public String getMakeUser(){
    return makeUser;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public Integer getInspectionInfoId(){
    return inspectionInfoId;
}


public String getInspectionKbn(){
    return inspectionKbn;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public String getModifyUser(){
    return modifyUser;
}


public String getEnclosureAddress(){
    return enclosureAddress;
}


public void setEnclosureAddress(String enclosureAddress){
    this.enclosureAddress = enclosureAddress;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setEnclosureAddress"));

.queryParam("enclosureAddress",enclosureAddress);
restTemplate.put(builder.toUriString(),null);


public void setInspectionInfoId(Integer inspectionInfoId){
    this.inspectionInfoId = inspectionInfoId;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setInspectionInfoId"));

.queryParam("inspectionInfoId",inspectionInfoId);
restTemplate.put(builder.toUriString(),null);


public void setInspectionKbn(String inspectionKbn){
    this.inspectionKbn = inspectionKbn;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setInspectionKbn"));

.queryParam("inspectionKbn",inspectionKbn);
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


}
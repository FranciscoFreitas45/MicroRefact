import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsProduct implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String productBatch;

 private  String productNum;

 private  Integer materielId;

 private  String productDiff;

 private  String isUse;

 private  String isOk;

 private  String flagStatus;

 private  String compPkid;

 private  String remark;

 private  String reserveFirst;

 private  String reserveSecond;

 private  String reserveThird;

 private  String makeUser;

 private  ZonedDateTime makeTime;

 private  String modifyUser;

 private  ZonedDateTime modifyTime;

 private RestTemplate restTemplate = new RestTemplate();


public String getMakeUser(){
    return makeUser;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public String getIsOk(){
    return isOk;
}


public String getProductNum(){
    return productNum;
}


public String getProductDiff(){
    return productDiff;
}


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public String getProductBatch(){
    return productBatch;
}


public String getReserveThird(){
    return reserveThird;
}


public String getReserveFirst(){
    return reserveFirst;
}


public String getIsUse(){
    return isUse;
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


public String getFlagStatus(){
    return flagStatus;
}


public void setProductNum(String productNum){
    this.productNum = productNum;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setProductNum"));

.queryParam("productNum",productNum);
restTemplate.put(builder.toUriString(),null);


public void setMaterielId(Integer materielId){
    this.materielId = materielId;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMaterielId"));

.queryParam("materielId",materielId);
restTemplate.put(builder.toUriString(),null);


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMakeUser"));

.queryParam("makeUser",makeUser);
restTemplate.put(builder.toUriString(),null);


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setModifyUser"));

.queryParam("modifyUser",modifyUser);
restTemplate.put(builder.toUriString(),null);


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMakeTime"));

.queryParam("makeTime",makeTime);
restTemplate.put(builder.toUriString(),null);


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setModifyTime"));

.queryParam("modifyTime",modifyTime);
restTemplate.put(builder.toUriString(),null);


}
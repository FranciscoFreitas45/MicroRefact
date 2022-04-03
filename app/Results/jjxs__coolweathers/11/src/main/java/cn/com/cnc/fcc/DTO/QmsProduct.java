package cn.com.cnc.fcc.DTO;
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

  String url = "http://15";


public QmsProduct productDiff(String productDiff){
    this.productDiff = productDiff;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public void setIsUse(String isUse){
    this.isUse = isUse;
}


public QmsProduct modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public void setProductDiff(String productDiff){
    this.productDiff = productDiff;
}


public String getIsOk(){
    return isOk;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public String getProductNum(){
    return productNum;
}


public QmsProduct makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsProduct flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public String getProductDiff(){
    return productDiff;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public QmsProduct reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public String getProductBatch(){
    return productBatch;
}


public QmsProduct reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public String getReserveThird(){
    return reserveThird;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public String getReserveFirst(){
    return reserveFirst;
}


public QmsProduct productBatch(String productBatch){
    this.productBatch = productBatch;
    return this;
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


public void setProductNum(String productNum){
    this.productNum = productNum;
}


public String getFlagStatus(){
    return flagStatus;
}


public void setMaterielId(Integer materielId){
    this.materielId = materielId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMaterielId"))

.queryParam("materielId",materielId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMakeUser"))

.queryParam("makeUser",makeUser)
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


}
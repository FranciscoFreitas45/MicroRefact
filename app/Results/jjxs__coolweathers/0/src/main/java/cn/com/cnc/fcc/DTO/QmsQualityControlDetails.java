package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsQualityControlDetails implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer bomTechnologyId;

 private  String inspectionItem;

 private  String technicalRequirement;

 private  String inspectionInstrument;

 private  String placeDiff;

 private  Double standard;

 private  Double upperDeviation;

 private  Double lowerDeviation;

 private  String inspectionResultDiff;

 private  String remark;

 private  String inspectionType;

 private  String isCheckObj;

 private  String abcType;

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


public QmsQualityControlDetails standard(Double standard){
    this.standard = standard;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public QmsQualityControlDetails modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public QmsQualityControlDetails modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getRemark(){
    return remark;
}


public void setInspectionItem(String inspectionItem){
    this.inspectionItem = inspectionItem;
}


public String getIsCheckObj(){
    return isCheckObj;
}


public String getInspectionItem(){
    return inspectionItem;
}


public Double getStandard(){
    return standard;
}


public void setAbcType(String abcType){
    this.abcType = abcType;
}


public String getInspectionResultDiff(){
    return inspectionResultDiff;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public Double getLowerDeviation(){
    return lowerDeviation;
}


public QmsQualityControlDetails makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsQualityControlDetails makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public Double getUpperDeviation(){
    return upperDeviation;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public void setUpperDeviation(Double upperDeviation){
    this.upperDeviation = upperDeviation;
}


public Long getId(){
    return id;
}


public QmsQualityControlDetails reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public Integer getBomTechnologyId(){
    return bomTechnologyId;
}


public QmsQualityControlDetails isCheckObj(String isCheckObj){
    this.isCheckObj = isCheckObj;
    return this;
}


public void setLowerDeviation(Double lowerDeviation){
    this.lowerDeviation = lowerDeviation;
}


public String getPlaceDiff(){
    return placeDiff;
}


public String getReserveThird(){
    return reserveThird;
}


public String getAbcType(){
    return abcType;
}


public String getReserveFirst(){
    return reserveFirst;
}


public String getInspectionInstrument(){
    return inspectionInstrument;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setPlaceDiff(String placeDiff){
    this.placeDiff = placeDiff;
}


public QmsQualityControlDetails reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
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


public void setBomTechnologyId(Integer bomTechnologyId){
    this.bomTechnologyId = bomTechnologyId;
}


@Override
public String toString(){
    return "QmsQualityControlDetails{" + "id=" + getId() + ", bomTechnologyId=" + getBomTechnologyId() + ", inspectionItem='" + getInspectionItem() + "'" + ", technicalRequirement='" + getTechnicalRequirement() + "'" + ", inspectionInstrument='" + getInspectionInstrument() + "'" + ", placeDiff='" + getPlaceDiff() + "'" + ", standard=" + getStandard() + ", upperDeviation=" + getUpperDeviation() + ", lowerDeviation=" + getLowerDeviation() + ", inspectionResultDiff='" + getInspectionResultDiff() + "'" + ", remark='" + getRemark() + "'" + ", inspectionType='" + getInspectionType() + "'" + ", isCheckObj='" + getIsCheckObj() + "'" + ", abcType='" + getAbcType() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public String getInspectionType(){
    return inspectionType;
}


public String getTechnicalRequirement(){
    return technicalRequirement;
}


public String getFlagStatus(){
    return flagStatus;
}


public void setTechnicalRequirement(String technicalRequirement){
    this.technicalRequirement = technicalRequirement;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setTechnicalRequirement"))

.queryParam("technicalRequirement",technicalRequirement)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInspectionInstrument(String inspectionInstrument){
    this.inspectionInstrument = inspectionInstrument;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setInspectionInstrument"))

.queryParam("inspectionInstrument",inspectionInstrument)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStandard(Double standard){
    this.standard = standard;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setStandard"))

.queryParam("standard",standard)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInspectionResultDiff(String inspectionResultDiff){
    this.inspectionResultDiff = inspectionResultDiff;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setInspectionResultDiff"))

.queryParam("inspectionResultDiff",inspectionResultDiff)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsCheckObj(String isCheckObj){
    this.isCheckObj = isCheckObj;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setIsCheckObj"))

.queryParam("isCheckObj",isCheckObj)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRemark(String remark){
    this.remark = remark;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setRemark"))

.queryParam("remark",remark)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMakeTime"))

.queryParam("makeTime",makeTime)
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
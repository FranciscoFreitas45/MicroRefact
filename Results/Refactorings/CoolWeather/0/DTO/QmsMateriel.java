import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsMateriel implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String materielCd;

 private  String materielName;

 private  String figureNumber;

 private  String innerCd;

 private  String abcNumber;

 private  String productMode;

 private  Integer materielTypeId;

 private  String propertyType;

 private  Integer packgeUnitId;

 private  Integer useUnitId;

 private  Double conversion;

 private  String specificationType;

 private  Double weight;

 private  Double density;

 private  Double workHours;

 private  Double taredHours;

 private  Integer schedulerRoleId;

 private  String organizationCd;

 private  String inHouseType;

 private  Double vesselAmount;

 private  String qualityLevel;

 private  String texTure;

 private  Integer mhandlerRoleId;

 private  String eightPrevention;

 private  String ifKey;

 private  String ubiety;

 private  String sapCd;

 private  String isCheck;

 private  String checkType;

 private  Integer checkRate;

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


public Double getTaredHours(){
    return taredHours;
}


public Double getConversion(){
    return conversion;
}


public String getMakeUser(){
    return makeUser;
}


public String getInnerCd(){
    return innerCd;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public String getProductMode(){
    return productMode;
}


public String getRemark(){
    return remark;
}


public String getQualityLevel(){
    return qualityLevel;
}


public String getSapCd(){
    return sapCd;
}


public Integer getMaterielTypeId(){
    return materielTypeId;
}


public String getTexTure(){
    return texTure;
}


public Integer getMhandlerRoleId(){
    return mhandlerRoleId;
}


public Double getVesselAmount(){
    return vesselAmount;
}


public String getPropertyType(){
    return propertyType;
}


public String getSpecificationType(){
    return specificationType;
}


public String getReserveThird(){
    return reserveThird;
}


public String getReserveSecond(){
    return reserveSecond;
}


public String getCompPkid(){
    return compPkid;
}


public Integer getCheckRate(){
    return checkRate;
}


public String getCheckType(){
    return checkType;
}


public Double getWeight(){
    return weight;
}


public String getEightPrevention(){
    return eightPrevention;
}


public String getMaterielCd(){
    return materielCd;
}


public Integer getUseUnitId(){
    return useUnitId;
}


public String getIsCheck(){
    return isCheck;
}


public String getOrganizationCd(){
    return organizationCd;
}


public String getAbcNumber(){
    return abcNumber;
}


public Integer getPackgeUnitId(){
    return packgeUnitId;
}


public Long getId(){
    return id;
}


public String getIfKey(){
    return ifKey;
}


public String getReserveFirst(){
    return reserveFirst;
}


public String getInHouseType(){
    return inHouseType;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public Double getDensity(){
    return density;
}


public String getMaterielName(){
    return materielName;
}


public String getModifyUser(){
    return modifyUser;
}


public Double getWorkHours(){
    return workHours;
}


public String getUbiety(){
    return ubiety;
}


public Integer getSchedulerRoleId(){
    return schedulerRoleId;
}


public String getFigureNumber(){
    return figureNumber;
}


public String getFlagStatus(){
    return flagStatus;
}


@Override
public String toString(){
    return "QmsMateriel{" + "id=" + getId() + ", materielCd='" + getMaterielCd() + "'" + ", materielName='" + getMaterielName() + "'" + ", figureNumber='" + getFigureNumber() + "'" + ", innerCd='" + getInnerCd() + "'" + ", abcNumber='" + getAbcNumber() + "'" + ", productMode='" + getProductMode() + "'" + ", materielTypeId=" + getMaterielTypeId() + ", propertyType='" + getPropertyType() + "'" + ", packgeUnitId=" + getPackgeUnitId() + ", useUnitId=" + getUseUnitId() + ", conversion=" + getConversion() + ", specificationType='" + getSpecificationType() + "'" + ", weight=" + getWeight() + ", density=" + getDensity() + ", workHours=" + getWorkHours() + ", taredHours=" + getTaredHours() + ", schedulerRoleId=" + getSchedulerRoleId() + ", organizationCd='" + getOrganizationCd() + "'" + ", inHouseType='" + getInHouseType() + "'" + ", vesselAmount=" + getVesselAmount() + ", qualityLevel='" + getQualityLevel() + "'" + ", texTure='" + getTexTure() + "'" + ", mhandlerRoleId=" + getMhandlerRoleId() + ", eightPrevention='" + getEightPrevention() + "'" + ", ifKey='" + getIfKey() + "'" + ", ubiety='" + getUbiety() + "'" + ", sapCd='" + getSapCd() + "'" + ", isCheck='" + getIsCheck() + "'" + ", checkType='" + getCheckType() + "'" + ", checkRate=" + getCheckRate() + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/toString"));

String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux


}
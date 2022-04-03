package cn.com.cnc.fcc.DTO;
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


public QmsMateriel eightPrevention(String eightPrevention){
    this.eightPrevention = eightPrevention;
    return this;
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


public void setProductMode(String productMode){
    this.productMode = productMode;
}


public void setMhandlerRoleId(Integer mhandlerRoleId){
    this.mhandlerRoleId = mhandlerRoleId;
}


public String getQualityLevel(){
    return qualityLevel;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public String getSapCd(){
    return sapCd;
}


public QmsMateriel innerCd(String innerCd){
    this.innerCd = innerCd;
    return this;
}


public QmsMateriel flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
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


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public Double getVesselAmount(){
    return vesselAmount;
}


public QmsMateriel organizationCd(String organizationCd){
    this.organizationCd = organizationCd;
    return this;
}


public QmsMateriel taredHours(Double taredHours){
    this.taredHours = taredHours;
    return this;
}


public QmsMateriel packgeUnitId(Integer packgeUnitId){
    this.packgeUnitId = packgeUnitId;
    return this;
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


public QmsMateriel density(Double density){
    this.density = density;
    return this;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setWeight(Double weight){
    this.weight = weight;
}


public String getCompPkid(){
    return compPkid;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    QmsMateriel qmsMateriel = (QmsMateriel) o;
    if (qmsMateriel.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsMateriel.getId());
}


@Override
public String toString(){
    return "QmsMateriel{" + "id=" + getId() + ", materielCd='" + getMaterielCd() + "'" + ", materielName='" + getMaterielName() + "'" + ", figureNumber='" + getFigureNumber() + "'" + ", innerCd='" + getInnerCd() + "'" + ", abcNumber='" + getAbcNumber() + "'" + ", productMode='" + getProductMode() + "'" + ", materielTypeId=" + getMaterielTypeId() + ", propertyType='" + getPropertyType() + "'" + ", packgeUnitId=" + getPackgeUnitId() + ", useUnitId=" + getUseUnitId() + ", conversion=" + getConversion() + ", specificationType='" + getSpecificationType() + "'" + ", weight=" + getWeight() + ", density=" + getDensity() + ", workHours=" + getWorkHours() + ", taredHours=" + getTaredHours() + ", schedulerRoleId=" + getSchedulerRoleId() + ", organizationCd='" + getOrganizationCd() + "'" + ", inHouseType='" + getInHouseType() + "'" + ", vesselAmount=" + getVesselAmount() + ", qualityLevel='" + getQualityLevel() + "'" + ", texTure='" + getTexTure() + "'" + ", mhandlerRoleId=" + getMhandlerRoleId() + ", eightPrevention='" + getEightPrevention() + "'" + ", ifKey='" + getIfKey() + "'" + ", ubiety='" + getUbiety() + "'" + ", sapCd='" + getSapCd() + "'" + ", isCheck='" + getIsCheck() + "'" + ", checkType='" + getCheckType() + "'" + ", checkRate=" + getCheckRate() + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
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


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public String getIsCheck(){
    return isCheck;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public void setId(Long id){
    this.id = id;
}


public void setAbcNumber(String abcNumber){
    this.abcNumber = abcNumber;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setCheckType(String checkType){
    this.checkType = checkType;
}


public QmsMateriel useUnitId(Integer useUnitId){
    this.useUnitId = useUnitId;
    return this;
}


public QmsMateriel weight(Double weight){
    this.weight = weight;
    return this;
}


public QmsMateriel abcNumber(String abcNumber){
    this.abcNumber = abcNumber;
    return this;
}


public void setFigureNumber(String figureNumber){
    this.figureNumber = figureNumber;
}


public void setInHouseType(String inHouseType){
    this.inHouseType = inHouseType;
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


public QmsMateriel materielTypeId(Integer materielTypeId){
    this.materielTypeId = materielTypeId;
    return this;
}


public QmsMateriel checkRate(Integer checkRate){
    this.checkRate = checkRate;
    return this;
}


public Long getId(){
    return id;
}


public QmsMateriel reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
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


public void setIfKey(String ifKey){
    this.ifKey = ifKey;
}


public QmsMateriel reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
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


public QmsMateriel ifKey(String ifKey){
    this.ifKey = ifKey;
    return this;
}


public String getFigureNumber(){
    return figureNumber;
}


public String getFlagStatus(){
    return flagStatus;
}


}
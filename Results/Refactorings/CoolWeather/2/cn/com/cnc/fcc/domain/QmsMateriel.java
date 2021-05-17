import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_materiel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsMateriel implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Size(max = 20)
@Column(name = "materiel_cd", length = 20)
 private  String materielCd;

@Size(max = 100)
@Column(name = "materiel_name", length = 100)
 private  String materielName;

@Size(max = 30)
@Column(name = "figure_number", length = 30)
 private  String figureNumber;

@Size(max = 100)
@Column(name = "inner_cd", length = 100)
 private  String innerCd;

@Size(max = 1)
@Column(name = "abc_number", length = 1)
 private  String abcNumber;

@Size(max = 1)
@Column(name = "product_mode", length = 1)
 private  String productMode;

@Column(name = "materiel_type_id")
 private  Integer materielTypeId;

@Size(max = 1)
@Column(name = "property_type", length = 1)
 private  String propertyType;

@Column(name = "packge_unit_id")
 private  Integer packgeUnitId;

@Column(name = "use_unit_id")
 private  Integer useUnitId;

@Column(name = "conversion")
 private  Double conversion;

@Size(max = 100)
@Column(name = "specification_type", length = 100)
 private  String specificationType;

@Column(name = "weight")
 private  Double weight;

@Column(name = "density")
 private  Double density;

@Column(name = "work_hours")
 private  Double workHours;

@Column(name = "tared_hours")
 private  Double taredHours;

@Column(name = "scheduler_role_id")
 private  Integer schedulerRoleId;

@Size(max = 10)
@Column(name = "organization_cd", length = 10)
 private  String organizationCd;

@Size(max = 1)
@Column(name = "in_house_type", length = 1)
 private  String inHouseType;

@Column(name = "vessel_amount")
 private  Double vesselAmount;

@Size(max = 100)
@Column(name = "quality_level", length = 100)
 private  String qualityLevel;

@Size(max = 100)
@Column(name = "tex_ture", length = 100)
 private  String texTure;

@Column(name = "mhandler_role_id")
 private  Integer mhandlerRoleId;

@Size(max = 1)
@Column(name = "eight_prevention", length = 1)
 private  String eightPrevention;

@Size(max = 1)
@Column(name = "if_key", length = 1)
 private  String ifKey;

@Size(max = 20)
@Column(name = "ubiety", length = 20)
 private  String ubiety;

@Size(max = 20)
@Column(name = "sap_cd", length = 20)
 private  String sapCd;

@Size(max = 1)
@Column(name = "is_check", length = 1)
 private  String isCheck;

@Size(max = 1)
@Column(name = "check_type", length = 1)
 private  String checkType;

@Column(name = "check_rate")
 private  Integer checkRate;

@Size(max = 1)
@Column(name = "flag_status", length = 1)
 private  String flagStatus;

@Size(max = 10)
@Column(name = "comp_pkid", length = 10)
 private  String compPkid;

@Size(max = 200)
@Column(name = "remark", length = 200)
 private  String remark;

@Size(max = 20)
@Column(name = "reserve_first", length = 20)
 private  String reserveFirst;

@Size(max = 20)
@Column(name = "reserve_second", length = 20)
 private  String reserveSecond;

@Size(max = 20)
@Column(name = "reserve_third", length = 20)
 private  String reserveThird;

@Size(max = 10)
@Column(name = "make_user", length = 10)
 private  String makeUser;

@Column(name = "make_time")
 private  ZonedDateTime makeTime;

@Size(max = 10)
@Column(name = "modify_user", length = 10)
 private  String modifyUser;

@Column(name = "modify_time")
 private  ZonedDateTime modifyTime;


public Double getTaredHours(){
    return taredHours;
}


public Double getConversion(){
    return conversion;
}


public void setInnerCd(String innerCd){
    this.innerCd = innerCd;
}


public String getMakeUser(){
    return makeUser;
}


public QmsMateriel figureNumber(String figureNumber){
    this.figureNumber = figureNumber;
    return this;
}


public String getInnerCd(){
    return innerCd;
}


public void setSapCd(String sapCd){
    this.sapCd = sapCd;
}


public QmsMateriel eightPrevention(String eightPrevention){
    this.eightPrevention = eightPrevention;
    return this;
}


public void setRemark(String remark){
    this.remark = remark;
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


public void setEightPrevention(String eightPrevention){
    this.eightPrevention = eightPrevention;
}


public void setProductMode(String productMode){
    this.productMode = productMode;
}


public void setQualityLevel(String qualityLevel){
    this.qualityLevel = qualityLevel;
}


public void setMhandlerRoleId(Integer mhandlerRoleId){
    this.mhandlerRoleId = mhandlerRoleId;
}


public QmsMateriel productMode(String productMode){
    this.productMode = productMode;
    return this;
}


public String getQualityLevel(){
    return qualityLevel;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public String getSapCd(){
    return sapCd;
}


public void setMaterielTypeId(Integer materielTypeId){
    this.materielTypeId = materielTypeId;
}


public QmsMateriel innerCd(String innerCd){
    this.innerCd = innerCd;
    return this;
}


public void setConversion(Double conversion){
    this.conversion = conversion;
}


public QmsMateriel flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public void setSchedulerRoleId(Integer schedulerRoleId){
    this.schedulerRoleId = schedulerRoleId;
}


public Integer getMaterielTypeId(){
    return materielTypeId;
}


public String getTexTure(){
    return texTure;
}


public void setUbiety(String ubiety){
    this.ubiety = ubiety;
}


public Integer getMhandlerRoleId(){
    return mhandlerRoleId;
}


public QmsMateriel compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public void setCheckRate(Integer checkRate){
    this.checkRate = checkRate;
}


public Double getVesselAmount(){
    return vesselAmount;
}


public void setWorkHours(Double workHours){
    this.workHours = workHours;
}


public QmsMateriel organizationCd(String organizationCd){
    this.organizationCd = organizationCd;
    return this;
}


public QmsMateriel reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public QmsMateriel taredHours(Double taredHours){
    this.taredHours = taredHours;
    return this;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
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


public QmsMateriel texTure(String texTure){
    this.texTure = texTure;
    return this;
}


public String getReserveThird(){
    return reserveThird;
}


public QmsMateriel sapCd(String sapCd){
    this.sapCd = sapCd;
    return this;
}


public QmsMateriel density(Double density){
    this.density = density;
    return this;
}


public String getReserveSecond(){
    return reserveSecond;
}


public void setSpecificationType(String specificationType){
    this.specificationType = specificationType;
}


public void setWeight(Double weight){
    this.weight = weight;
}


public String getCompPkid(){
    return compPkid;
}


public QmsMateriel isCheck(String isCheck){
    this.isCheck = isCheck;
    return this;
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


public QmsMateriel materielName(String materielName){
    this.materielName = materielName;
    return this;
}


@Override
public String toString(){
    return "QmsMateriel{" + "id=" + getId() + ", materielCd='" + getMaterielCd() + "'" + ", materielName='" + getMaterielName() + "'" + ", figureNumber='" + getFigureNumber() + "'" + ", innerCd='" + getInnerCd() + "'" + ", abcNumber='" + getAbcNumber() + "'" + ", productMode='" + getProductMode() + "'" + ", materielTypeId=" + getMaterielTypeId() + ", propertyType='" + getPropertyType() + "'" + ", packgeUnitId=" + getPackgeUnitId() + ", useUnitId=" + getUseUnitId() + ", conversion=" + getConversion() + ", specificationType='" + getSpecificationType() + "'" + ", weight=" + getWeight() + ", density=" + getDensity() + ", workHours=" + getWorkHours() + ", taredHours=" + getTaredHours() + ", schedulerRoleId=" + getSchedulerRoleId() + ", organizationCd='" + getOrganizationCd() + "'" + ", inHouseType='" + getInHouseType() + "'" + ", vesselAmount=" + getVesselAmount() + ", qualityLevel='" + getQualityLevel() + "'" + ", texTure='" + getTexTure() + "'" + ", mhandlerRoleId=" + getMhandlerRoleId() + ", eightPrevention='" + getEightPrevention() + "'" + ", ifKey='" + getIfKey() + "'" + ", ubiety='" + getUbiety() + "'" + ", sapCd='" + getSapCd() + "'" + ", isCheck='" + getIsCheck() + "'" + ", checkType='" + getCheckType() + "'" + ", checkRate=" + getCheckRate() + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/toString"));

String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux


public Integer getCheckRate(){
    return checkRate;
}


public String getCheckType(){
    return checkType;
}


public void setPropertyType(String propertyType){
    this.propertyType = propertyType;
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


public void setPackgeUnitId(Integer packgeUnitId){
    this.packgeUnitId = packgeUnitId;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public String getIsCheck(){
    return isCheck;
}


public QmsMateriel modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setMaterielCd(String materielCd){
    this.materielCd = materielCd;
}


public QmsMateriel ubiety(String ubiety){
    this.ubiety = ubiety;
    return this;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsMateriel modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public void setId(Long id){
    this.id = id;
}


public void setVesselAmount(Double vesselAmount){
    this.vesselAmount = vesselAmount;
}


public void setAbcNumber(String abcNumber){
    this.abcNumber = abcNumber;
}


public QmsMateriel checkType(String checkType){
    this.checkType = checkType;
    return this;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public void setMaterielName(String materielName){
    this.materielName = materielName;
}


public void setCheckType(String checkType){
    this.checkType = checkType;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public QmsMateriel useUnitId(Integer useUnitId){
    this.useUnitId = useUnitId;
    return this;
}


public QmsMateriel mhandlerRoleId(Integer mhandlerRoleId){
    this.mhandlerRoleId = mhandlerRoleId;
    return this;
}


public QmsMateriel weight(Double weight){
    this.weight = weight;
    return this;
}


public void setUseUnitId(Integer useUnitId){
    this.useUnitId = useUnitId;
}


public QmsMateriel abcNumber(String abcNumber){
    this.abcNumber = abcNumber;
    return this;
}


public QmsMateriel makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public void setFigureNumber(String figureNumber){
    this.figureNumber = figureNumber;
}


public QmsMateriel makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public void setInHouseType(String inHouseType){
    this.inHouseType = inHouseType;
}


public void setOrganizationCd(String organizationCd){
    this.organizationCd = organizationCd;
}


public String getOrganizationCd(){
    return organizationCd;
}


public QmsMateriel inHouseType(String inHouseType){
    this.inHouseType = inHouseType;
    return this;
}


public String getAbcNumber(){
    return abcNumber;
}


public Integer getPackgeUnitId(){
    return packgeUnitId;
}


public QmsMateriel vesselAmount(Double vesselAmount){
    this.vesselAmount = vesselAmount;
    return this;
}


public QmsMateriel materielTypeId(Integer materielTypeId){
    this.materielTypeId = materielTypeId;
    return this;
}


public QmsMateriel workHours(Double workHours){
    this.workHours = workHours;
    return this;
}


public QmsMateriel checkRate(Integer checkRate){
    this.checkRate = checkRate;
    return this;
}


public QmsMateriel schedulerRoleId(Integer schedulerRoleId){
    this.schedulerRoleId = schedulerRoleId;
    return this;
}


public Long getId(){
    return id;
}


public QmsMateriel remark(String remark){
    this.remark = remark;
    return this;
}


public QmsMateriel reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public void setTexTure(String texTure){
    this.texTure = texTure;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public QmsMateriel propertyType(String propertyType){
    this.propertyType = propertyType;
    return this;
}


public String getIfKey(){
    return ifKey;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public String getInHouseType(){
    return inHouseType;
}


public QmsMateriel conversion(Double conversion){
    this.conversion = conversion;
    return this;
}


public void setIfKey(String ifKey){
    this.ifKey = ifKey;
}


public void setTaredHours(Double taredHours){
    this.taredHours = taredHours;
}


public QmsMateriel reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public QmsMateriel specificationType(String specificationType){
    this.specificationType = specificationType;
    return this;
}


public Double getDensity(){
    return density;
}


public QmsMateriel qualityLevel(String qualityLevel){
    this.qualityLevel = qualityLevel;
    return this;
}


public String getMaterielName(){
    return materielName;
}


public void setDensity(Double density){
    this.density = density;
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


public QmsMateriel materielCd(String materielCd){
    this.materielCd = materielCd;
    return this;
}


public QmsMateriel ifKey(String ifKey){
    this.ifKey = ifKey;
    return this;
}


public String getFigureNumber(){
    return figureNumber;
}


public void setIsCheck(String isCheck){
    this.isCheck = isCheck;
}


public String getFlagStatus(){
    return flagStatus;
}


}
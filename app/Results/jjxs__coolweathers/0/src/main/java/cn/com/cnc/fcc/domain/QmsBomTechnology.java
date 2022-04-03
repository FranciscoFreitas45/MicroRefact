package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "qms_bom_technology")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QmsBomTechnology implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "materiel_id")
 private  Integer materielId;

@Size(max = 10)
@Column(name = "technology_cd", length = 10)
 private  String technologyCd;

@Size(max = 100)
@Column(name = "technology_name", length = 100)
 private  String technologyName;

@Column(name = "order_no")
 private  Integer orderNo;

@Column(name = "before_process_id")
 private  Integer beforeProcessId;

@Column(name = "process_id")
 private  Integer processId;

@Size(max = 10)
@Column(name = "organization_cd", length = 10)
 private  String organizationCd;

@Size(max = 20)
@Column(name = "scheduler_role", length = 20)
 private  String schedulerRole;

@Size(max = 10)
@Column(name = "work_unit", length = 10)
 private  String workUnit;

@Column(name = "work_hours")
 private  Integer workHours;

@Size(max = 10)
@Column(name = "qc_type", length = 10)
 private  String qcType;

@Size(max = 20)
@Column(name = "special_role", length = 20)
 private  String specialRole;

@Size(max = 100)
@Column(name = "proprety", length = 100)
 private  String proprety;

@Size(max = 20)
@Column(name = "machine_center_cd", length = 20)
 private  String machineCenterCd;

@Size(max = 20)
@Column(name = "work_cd", length = 20)
 private  String workCd;

@Size(max = 20)
@Column(name = "deliver_time", length = 20)
 private  String deliverTime;

@Column(name = "integer_num")
 private  Double integerNum;

@Size(max = 10)
@Column(name = "integer_time_unit", length = 10)
 private  String integerTimeUnit;

@Column(name = "integer_time")
 private  Double integerTime;

@Size(max = 20)
@Column(name = "operation_version", length = 20)
 private  String operationVersion;

@Column(name = "work_factor")
 private  Double workFactor;

@Size(max = 1)
@Column(name = "operation_type", length = 1)
 private  String operationType;

@Size(max = 20)
@Column(name = "mutualin_role", length = 20)
 private  String mutualinRole;

@Size(max = 200)
@Column(name = "jhi_describe", length = 200)
 private  String describe;

@Size(max = 20)
@Column(name = "random_role", length = 20)
 private  String randomRole;

@Size(max = 20)
@Column(name = "control_role", length = 20)
 private  String controlRole;

@Size(max = 1)
@Column(name = "is_main", length = 1)
 private  String isMain;

@Size(max = 1)
@Column(name = "is_new_cd", length = 1)
 private  String isNewCd;

@Column(name = "new_cd_materiel_id")
 private  Integer newCdMaterielId;

@Size(max = 20)
@Column(name = "work_group_cd", length = 20)
 private  String workGroupCd;

@Size(max = 1)
@Column(name = "is_control_point", length = 1)
 private  String isControlPoint;

@Size(max = 1)
@Column(name = "is_test", length = 1)
 private  String isTest;

@Size(max = 1)
@Column(name = "is_default", length = 1)
 private  String isDefault;

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


public QmsBomTechnology qcType(String qcType){
    this.qcType = qcType;
    return this;
}


public QmsBomTechnology proprety(String proprety){
    this.proprety = proprety;
    return this;
}


public String getMakeUser(){
    return makeUser;
}


public String getTechnologyCd(){
    return technologyCd;
}


public void setDescribe(String describe){
    this.describe = describe;
}


public QmsBomTechnology integerTimeUnit(String integerTimeUnit){
    this.integerTimeUnit = integerTimeUnit;
    return this;
}


public void setWorkUnit(String workUnit){
    this.workUnit = workUnit;
}


public String getIsNewCd(){
    return isNewCd;
}


public String getIsTest(){
    return isTest;
}


public void setRemark(String remark){
    this.remark = remark;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
}


public void setMutualinRole(String mutualinRole){
    this.mutualinRole = mutualinRole;
}


public String getRemark(){
    return remark;
}


public Integer getProcessId(){
    return processId;
}


public String getDeliverTime(){
    return deliverTime;
}


public QmsBomTechnology workUnit(String workUnit){
    this.workUnit = workUnit;
    return this;
}


public void setProcessId(Integer processId){
    this.processId = processId;
}


public void setIsTest(String isTest){
    this.isTest = isTest;
}


public QmsBomTechnology schedulerRole(String schedulerRole){
    this.schedulerRole = schedulerRole;
    return this;
}


public void setFlagStatus(String flagStatus){
    this.flagStatus = flagStatus;
}


public QmsBomTechnology specialRole(String specialRole){
    this.specialRole = specialRole;
    return this;
}


public QmsBomTechnology workCd(String workCd){
    this.workCd = workCd;
    return this;
}


public void setIsNewCd(String isNewCd){
    this.isNewCd = isNewCd;
}


public void setModifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
}


public String getWorkCd(){
    return workCd;
}


public QmsBomTechnology isNewCd(String isNewCd){
    this.isNewCd = isNewCd;
    return this;
}


public QmsBomTechnology isDefault(String isDefault){
    this.isDefault = isDefault;
    return this;
}


public void setIsDefault(String isDefault){
    this.isDefault = isDefault;
}


public QmsBomTechnology flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public void setSpecialRole(String specialRole){
    this.specialRole = specialRole;
}


public QmsBomTechnology operationType(String operationType){
    this.operationType = operationType;
    return this;
}


public QmsBomTechnology randomRole(String randomRole){
    this.randomRole = randomRole;
    return this;
}


public void setProprety(String proprety){
    this.proprety = proprety;
}


public void setIntegerNum(Double integerNum){
    this.integerNum = integerNum;
}


public Double getWorkFactor(){
    return workFactor;
}


public QmsBomTechnology compPkid(String compPkid){
    this.compPkid = compPkid;
    return this;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
}


public void setTechnologyName(String technologyName){
    this.technologyName = technologyName;
}


public void setNewCdMaterielId(Integer newCdMaterielId){
    this.newCdMaterielId = newCdMaterielId;
}


public String getIsMain(){
    return isMain;
}


public String getWorkUnit(){
    return workUnit;
}


public String getSchedulerRole(){
    return schedulerRole;
}


public void setWorkHours(Integer workHours){
    this.workHours = workHours;
}


public QmsBomTechnology organizationCd(String organizationCd){
    this.organizationCd = organizationCd;
    return this;
}


public QmsBomTechnology reserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
    return this;
}


public String getSpecialRole(){
    return specialRole;
}


public void setCompPkid(String compPkid){
    this.compPkid = compPkid;
}


public QmsBomTechnology machineCenterCd(String machineCenterCd){
    this.machineCenterCd = machineCenterCd;
    return this;
}


public void setOperationType(String operationType){
    this.operationType = operationType;
}


public void setControlRole(String controlRole){
    this.controlRole = controlRole;
}


public String getOperationType(){
    return operationType;
}


public String getReserveThird(){
    return reserveThird;
}


public QmsBomTechnology processId(Integer processId){
    this.processId = processId;
    return this;
}


public void setTechnologyCd(String technologyCd){
    this.technologyCd = technologyCd;
}


public void setOperationVersion(String operationVersion){
    this.operationVersion = operationVersion;
}


public String getReserveSecond(){
    return reserveSecond;
}


public String getCompPkid(){
    return compPkid;
}


public void setWorkFactor(Double workFactor){
    this.workFactor = workFactor;
}


public QmsBomTechnology isControlPoint(String isControlPoint){
    this.isControlPoint = isControlPoint;
    return this;
}


public Double getIntegerNum(){
    return integerNum;
}


public QmsBomTechnology isTest(String isTest){
    this.isTest = isTest;
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
    QmsBomTechnology qmsBomTechnology = (QmsBomTechnology) o;
    if (qmsBomTechnology.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), qmsBomTechnology.getId());
}


@Override
public String toString(){
    return "QmsBomTechnology{" + "id=" + getId() + ", materielId=" + getMaterielId() + ", technologyCd='" + getTechnologyCd() + "'" + ", technologyName='" + getTechnologyName() + "'" + ", orderNo=" + getOrderNo() + ", beforeProcessId=" + getBeforeProcessId() + ", processId=" + getProcessId() + ", organizationCd='" + getOrganizationCd() + "'" + ", schedulerRole='" + getSchedulerRole() + "'" + ", workUnit='" + getWorkUnit() + "'" + ", workHours=" + getWorkHours() + ", qcType='" + getQcType() + "'" + ", specialRole='" + getSpecialRole() + "'" + ", proprety='" + getProprety() + "'" + ", machineCenterCd='" + getMachineCenterCd() + "'" + ", workCd='" + getWorkCd() + "'" + ", deliverTime='" + getDeliverTime() + "'" + ", integerNum=" + getIntegerNum() + ", integerTimeUnit='" + getIntegerTimeUnit() + "'" + ", integerTime=" + getIntegerTime() + ", operationVersion='" + getOperationVersion() + "'" + ", workFactor=" + getWorkFactor() + ", operationType='" + getOperationType() + "'" + ", mutualinRole='" + getMutualinRole() + "'" + ", describe='" + getDescribe() + "'" + ", randomRole='" + getRandomRole() + "'" + ", controlRole='" + getControlRole() + "'" + ", isMain='" + getIsMain() + "'" + ", isNewCd='" + getIsNewCd() + "'" + ", newCdMaterielId=" + getNewCdMaterielId() + ", workGroupCd='" + getWorkGroupCd() + "'" + ", isControlPoint='" + getIsControlPoint() + "'" + ", isTest='" + getIsTest() + "'" + ", isDefault='" + getIsDefault() + "'" + ", flagStatus='" + getFlagStatus() + "'" + ", compPkid='" + getCompPkid() + "'" + ", remark='" + getRemark() + "'" + ", reserveFirst='" + getReserveFirst() + "'" + ", reserveSecond='" + getReserveSecond() + "'" + ", reserveThird='" + getReserveThird() + "'" + ", makeUser='" + getMakeUser() + "'" + ", makeTime='" + getMakeTime() + "'" + ", modifyUser='" + getModifyUser() + "'" + ", modifyTime='" + getModifyTime() + "'" + "}";
}


public QmsBomTechnology describe(String describe){
    this.describe = describe;
    return this;
}


public void setDeliverTime(String deliverTime){
    this.deliverTime = deliverTime;
}


public String getOperationVersion(){
    return operationVersion;
}


public Double getIntegerTime(){
    return integerTime;
}


public QmsBomTechnology integerNum(Double integerNum){
    this.integerNum = integerNum;
    return this;
}


public QmsBomTechnology workFactor(Double workFactor){
    this.workFactor = workFactor;
    return this;
}


public QmsBomTechnology materielId(Integer materielId){
    this.materielId = materielId;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public QmsBomTechnology modifyUser(String modifyUser){
    this.modifyUser = modifyUser;
    return this;
}


public void setRandomRole(String randomRole){
    this.randomRole = randomRole;
}


public void setReserveThird(String reserveThird){
    this.reserveThird = reserveThird;
}


public QmsBomTechnology modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public QmsBomTechnology beforeProcessId(Integer beforeProcessId){
    this.beforeProcessId = beforeProcessId;
    return this;
}


public QmsBomTechnology technologyCd(String technologyCd){
    this.technologyCd = technologyCd;
    return this;
}


public void setId(Long id){
    this.id = id;
}


public QmsBomTechnology workGroupCd(String workGroupCd){
    this.workGroupCd = workGroupCd;
    return this;
}


public void setBeforeProcessId(Integer beforeProcessId){
    this.beforeProcessId = beforeProcessId;
}


public void setWorkGroupCd(String workGroupCd){
    this.workGroupCd = workGroupCd;
}


public Integer getNewCdMaterielId(){
    return newCdMaterielId;
}


public void setMakeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
}


public Integer getOrderNo(){
    return orderNo;
}


public QmsBomTechnology orderNo(Integer orderNo){
    this.orderNo = orderNo;
    return this;
}


public String getControlRole(){
    return controlRole;
}


public void setOrderNo(Integer orderNo){
    this.orderNo = orderNo;
}


public QmsBomTechnology isMain(String isMain){
    this.isMain = isMain;
    return this;
}


public void setMakeUser(String makeUser){
    this.makeUser = makeUser;
}


public String getTechnologyName(){
    return technologyName;
}


public QmsBomTechnology controlRole(String controlRole){
    this.controlRole = controlRole;
    return this;
}


public QmsBomTechnology makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public void setIntegerTime(Double integerTime){
    this.integerTime = integerTime;
}


public QmsBomTechnology operationVersion(String operationVersion){
    this.operationVersion = operationVersion;
    return this;
}


public QmsBomTechnology makeUser(String makeUser){
    this.makeUser = makeUser;
    return this;
}


public QmsBomTechnology integerTime(Double integerTime){
    this.integerTime = integerTime;
    return this;
}


public void setOrganizationCd(String organizationCd){
    this.organizationCd = organizationCd;
}


public void setSchedulerRole(String schedulerRole){
    this.schedulerRole = schedulerRole;
}


public String getIntegerTimeUnit(){
    return integerTimeUnit;
}


public String getMutualinRole(){
    return mutualinRole;
}


public String getOrganizationCd(){
    return organizationCd;
}


public QmsBomTechnology mutualinRole(String mutualinRole){
    this.mutualinRole = mutualinRole;
    return this;
}


public Integer getMaterielId(){
    return materielId;
}


public QmsBomTechnology workHours(Integer workHours){
    this.workHours = workHours;
    return this;
}


public Long getId(){
    return id;
}


public QmsBomTechnology remark(String remark){
    this.remark = remark;
    return this;
}


public String getDescribe(){
    return describe;
}


public QmsBomTechnology reserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
    return this;
}


public void setMachineCenterCd(String machineCenterCd){
    this.machineCenterCd = machineCenterCd;
}


public String getMachineCenterCd(){
    return machineCenterCd;
}


public void setMaterielId(Integer materielId){
    this.materielId = materielId;
}


public void setQcType(String qcType){
    this.qcType = qcType;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public String getIsControlPoint(){
    return isControlPoint;
}


public String getReserveFirst(){
    return reserveFirst;
}


public void setReserveSecond(String reserveSecond){
    this.reserveSecond = reserveSecond;
}


public QmsBomTechnology technologyName(String technologyName){
    this.technologyName = technologyName;
    return this;
}


public QmsBomTechnology deliverTime(String deliverTime){
    this.deliverTime = deliverTime;
    return this;
}


public void setIsControlPoint(String isControlPoint){
    this.isControlPoint = isControlPoint;
}


public String getQcType(){
    return qcType;
}


public QmsBomTechnology reserveThird(String reserveThird){
    this.reserveThird = reserveThird;
    return this;
}


public ZonedDateTime getModifyTime(){
    return modifyTime;
}


public Integer getBeforeProcessId(){
    return beforeProcessId;
}


public String getRandomRole(){
    return randomRole;
}


public String getModifyUser(){
    return modifyUser;
}


public Integer getWorkHours(){
    return workHours;
}


public String getWorkGroupCd(){
    return workGroupCd;
}


public String getIsDefault(){
    return isDefault;
}


public void setIntegerTimeUnit(String integerTimeUnit){
    this.integerTimeUnit = integerTimeUnit;
}


public void setIsMain(String isMain){
    this.isMain = isMain;
}


public QmsBomTechnology newCdMaterielId(Integer newCdMaterielId){
    this.newCdMaterielId = newCdMaterielId;
    return this;
}


public void setWorkCd(String workCd){
    this.workCd = workCd;
}


public String getProprety(){
    return proprety;
}


public String getFlagStatus(){
    return flagStatus;
}


}
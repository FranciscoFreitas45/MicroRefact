package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class QmsBomTechnology implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer materielId;

 private  String technologyCd;

 private  String technologyName;

 private  Integer orderNo;

 private  Integer beforeProcessId;

 private  Integer processId;

 private  String organizationCd;

 private  String schedulerRole;

 private  String workUnit;

 private  Integer workHours;

 private  String qcType;

 private  String specialRole;

 private  String proprety;

 private  String machineCenterCd;

 private  String workCd;

 private  String deliverTime;

 private  Double integerNum;

 private  String integerTimeUnit;

 private  Double integerTime;

 private  String operationVersion;

 private  Double workFactor;

 private  String operationType;

 private  String mutualinRole;

 private  String describe;

 private  String randomRole;

 private  String controlRole;

 private  String isMain;

 private  String isNewCd;

 private  Integer newCdMaterielId;

 private  String workGroupCd;

 private  String isControlPoint;

 private  String isTest;

 private  String isDefault;

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


public QmsBomTechnology integerTimeUnit(String integerTimeUnit){
    this.integerTimeUnit = integerTimeUnit;
    return this;
}


public String getIsNewCd(){
    return isNewCd;
}


public String getIsTest(){
    return isTest;
}


public ZonedDateTime getMakeTime(){
    return makeTime;
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


public void setProcessId(Integer processId){
    this.processId = processId;
}


public QmsBomTechnology schedulerRole(String schedulerRole){
    this.schedulerRole = schedulerRole;
    return this;
}


public QmsBomTechnology specialRole(String specialRole){
    this.specialRole = specialRole;
    return this;
}


public void setIsNewCd(String isNewCd){
    this.isNewCd = isNewCd;
}


public String getWorkCd(){
    return workCd;
}


public QmsBomTechnology isDefault(String isDefault){
    this.isDefault = isDefault;
    return this;
}


public QmsBomTechnology flagStatus(String flagStatus){
    this.flagStatus = flagStatus;
    return this;
}


public QmsBomTechnology operationType(String operationType){
    this.operationType = operationType;
    return this;
}


public void setProprety(String proprety){
    this.proprety = proprety;
}


public Double getWorkFactor(){
    return workFactor;
}


public void setModifyUser(String modifyUser){
    this.modifyUser = modifyUser;
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


public QmsBomTechnology organizationCd(String organizationCd){
    this.organizationCd = organizationCd;
    return this;
}


public String getSpecialRole(){
    return specialRole;
}


public QmsBomTechnology machineCenterCd(String machineCenterCd){
    this.machineCenterCd = machineCenterCd;
    return this;
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


public void setTechnologyCd(String technologyCd){
    this.technologyCd = technologyCd;
}


public String getReserveSecond(){
    return reserveSecond;
}


public String getCompPkid(){
    return compPkid;
}


public QmsBomTechnology isControlPoint(String isControlPoint){
    this.isControlPoint = isControlPoint;
    return this;
}


public Double getIntegerNum(){
    return integerNum;
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


public QmsBomTechnology describe(String describe){
    this.describe = describe;
    return this;
}


public String getOperationVersion(){
    return operationVersion;
}


public Double getIntegerTime(){
    return integerTime;
}


public QmsBomTechnology workFactor(Double workFactor){
    this.workFactor = workFactor;
    return this;
}


public void setReserveFirst(String reserveFirst){
    this.reserveFirst = reserveFirst;
}


public void setRandomRole(String randomRole){
    this.randomRole = randomRole;
}


public QmsBomTechnology modifyTime(ZonedDateTime modifyTime){
    this.modifyTime = modifyTime;
    return this;
}


public QmsBomTechnology technologyCd(String technologyCd){
    this.technologyCd = technologyCd;
    return this;
}


public QmsBomTechnology workGroupCd(String workGroupCd){
    this.workGroupCd = workGroupCd;
    return this;
}


public void setWorkGroupCd(String workGroupCd){
    this.workGroupCd = workGroupCd;
}


public Integer getNewCdMaterielId(){
    return newCdMaterielId;
}


public Integer getOrderNo(){
    return orderNo;
}


public String getControlRole(){
    return controlRole;
}


public QmsBomTechnology isMain(String isMain){
    this.isMain = isMain;
    return this;
}


public String getTechnologyName(){
    return technologyName;
}


public QmsBomTechnology makeTime(ZonedDateTime makeTime){
    this.makeTime = makeTime;
    return this;
}


public QmsBomTechnology operationVersion(String operationVersion){
    this.operationVersion = operationVersion;
    return this;
}


public QmsBomTechnology integerTime(Double integerTime){
    this.integerTime = integerTime;
    return this;
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


public Integer getMaterielId(){
    return materielId;
}


public Long getId(){
    return id;
}


public String getDescribe(){
    return describe;
}


public void setMachineCenterCd(String machineCenterCd){
    this.machineCenterCd = machineCenterCd;
}


public String getMachineCenterCd(){
    return machineCenterCd;
}


public void setQcType(String qcType){
    this.qcType = qcType;
}


public String getIsControlPoint(){
    return isControlPoint;
}


public String getReserveFirst(){
    return reserveFirst;
}


public QmsBomTechnology technologyName(String technologyName){
    this.technologyName = technologyName;
    return this;
}


public void setIsControlPoint(String isControlPoint){
    this.isControlPoint = isControlPoint;
}


public String getQcType(){
    return qcType;
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


public void setIsMain(String isMain){
    this.isMain = isMain;
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
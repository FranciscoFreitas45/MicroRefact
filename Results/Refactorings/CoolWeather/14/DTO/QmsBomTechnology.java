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


public String getMakeUser(){
    return makeUser;
}


public String getTechnologyCd(){
    return technologyCd;
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


public String getWorkCd(){
    return workCd;
}


public Double getWorkFactor(){
    return workFactor;
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


public String getSpecialRole(){
    return specialRole;
}


public String getOperationType(){
    return operationType;
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


public Double getIntegerNum(){
    return integerNum;
}


public String getOperationVersion(){
    return operationVersion;
}


public Double getIntegerTime(){
    return integerTime;
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


public String getTechnologyName(){
    return technologyName;
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


public String getMachineCenterCd(){
    return machineCenterCd;
}


public String getIsControlPoint(){
    return isControlPoint;
}


public String getReserveFirst(){
    return reserveFirst;
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


public String getProprety(){
    return proprety;
}


public String getFlagStatus(){
    return flagStatus;
}


}
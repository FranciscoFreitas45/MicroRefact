package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class RbacUserRightRelation implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer appId;

 private  Integer storeId;

 private  Integer roleId;

 private  Integer userId;

 private  Integer stopFlag;

 private  Integer delFlag;

 private  String insProgarmCd;

 private  String insOperCd;

 private  ZonedDateTime insDateTime;

 private  String updProgarmCd;

 private  String updOperCd;

 private  ZonedDateTime updDateTime;

 private  String delProgarmCd;

 private  String delOperCd;

 private  ZonedDateTime delDateTime;

 private  ZonedDateTime triggerDateTime;


public Integer getStopFlag(){
    return stopFlag;
}


public RbacUserRightRelation appId(Integer appId){
    this.appId = appId;
    return this;
}


public void setDelDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
}


public RbacUserRightRelation delProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
    return this;
}


public RbacUserRightRelation stopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
    return this;
}


public ZonedDateTime getDelDateTime(){
    return delDateTime;
}


public RbacUserRightRelation insOperCd(String insOperCd){
    this.insOperCd = insOperCd;
    return this;
}


public String getInsProgarmCd(){
    return insProgarmCd;
}


public String getDelProgarmCd(){
    return delProgarmCd;
}


public String getUpdProgarmCd(){
    return updProgarmCd;
}


public ZonedDateTime getUpdDateTime(){
    return updDateTime;
}


public Integer getDelFlag(){
    return delFlag;
}


public ZonedDateTime getTriggerDateTime(){
    return triggerDateTime;
}


public Integer getRoleId(){
    return roleId;
}


public Long getId(){
    return id;
}


public String getDelOperCd(){
    return delOperCd;
}


public Integer getAppId(){
    return appId;
}


public void setInsProgarmCd(String insProgarmCd){
    this.insProgarmCd = insProgarmCd;
}


public ZonedDateTime getInsDateTime(){
    return insDateTime;
}


public void setDelProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
}


public Integer getStoreId(){
    return storeId;
}


public RbacUserRightRelation delOperCd(String delOperCd){
    this.delOperCd = delOperCd;
    return this;
}


public RbacUserRightRelation roleId(Integer roleId){
    this.roleId = roleId;
    return this;
}


public void setUpdProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
}


public void setInsDateTime(ZonedDateTime insDateTime){
    this.insDateTime = insDateTime;
}


public RbacUserRightRelation userId(Integer userId){
    this.userId = userId;
    return this;
}


public String getUpdOperCd(){
    return updOperCd;
}


public String getInsOperCd(){
    return insOperCd;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    RbacUserRightRelation rbacUserRightRelation = (RbacUserRightRelation) o;
    if (rbacUserRightRelation.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), rbacUserRightRelation.getId());
}


public RbacUserRightRelation updDateTime(ZonedDateTime updDateTime){
    this.updDateTime = updDateTime;
    return this;
}


public Integer getUserId(){
    return userId;
}


}
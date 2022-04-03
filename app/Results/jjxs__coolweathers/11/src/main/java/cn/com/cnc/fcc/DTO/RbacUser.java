package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class RbacUser implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer appId;

 private  Integer storeId;

 private  String userCode;

 private  String userPassword;

 private  String userName;

 private  String userMobile;

 private  String userMail;

 private  ZonedDateTime userLastLoginTime;

 private  Integer userLoginCount;

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

 private  String organizationCd;


public Integer getStopFlag(){
    return stopFlag;
}


public RbacUser userLoginCount(Integer userLoginCount){
    this.userLoginCount = userLoginCount;
    return this;
}


public RbacUser appId(Integer appId){
    this.appId = appId;
    return this;
}


public String getUserName(){
    return userName;
}


public void setUserLoginCount(Integer userLoginCount){
    this.userLoginCount = userLoginCount;
}


public Integer getUserLoginCount(){
    return userLoginCount;
}


public RbacUser delProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
    return this;
}


public RbacUser stopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
    return this;
}


public ZonedDateTime getDelDateTime(){
    return delDateTime;
}


public RbacUser insOperCd(String insOperCd){
    this.insOperCd = insOperCd;
    return this;
}


public String getInsProgarmCd(){
    return insProgarmCd;
}


public String getDelProgarmCd(){
    return delProgarmCd;
}


public RbacUser updOperCd(String updOperCd){
    this.updOperCd = updOperCd;
    return this;
}


public String getOrganizationCd(){
    return organizationCd;
}


public String getUpdProgarmCd(){
    return updProgarmCd;
}


public String getUserCode(){
    return userCode;
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


public Long getId(){
    return id;
}


public String getUserPassword(){
    return userPassword;
}


public RbacUser delFlag(Integer delFlag){
    this.delFlag = delFlag;
    return this;
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


public void setUserName(String userName){
    this.userName = userName;
}


public ZonedDateTime getInsDateTime(){
    return insDateTime;
}


public String getUserMobile(){
    return userMobile;
}


public Integer getStoreId(){
    return storeId;
}


public RbacUser delOperCd(String delOperCd){
    this.delOperCd = delOperCd;
    return this;
}


public RbacUser insDateTime(ZonedDateTime insDateTime){
    this.insDateTime = insDateTime;
    return this;
}


public void setUpdProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
}


public RbacUser userName(String userName){
    this.userName = userName;
    return this;
}


public String getUserMail(){
    return userMail;
}


public void setAppId(Integer appId){
    this.appId = appId;
}


public String getUpdOperCd(){
    return updOperCd;
}


public void setUserCode(String userCode){
    this.userCode = userCode;
}


public String getInsOperCd(){
    return insOperCd;
}


public void setUserPassword(String userPassword){
    this.userPassword = userPassword;
}


@Override
public String toString(){
    return "RbacUser{" + "id=" + getId() + ", appId=" + getAppId() + ", storeId=" + getStoreId() + ", userCode='" + getUserCode() + "'" + ", userPassword='" + getUserPassword() + "'" + ", userName='" + getUserName() + "'" + ", userMobile='" + getUserMobile() + "'" + ", userMail='" + getUserMail() + "'" + ", userLastLoginTime='" + getUserLastLoginTime() + "'" + ", userLoginCount=" + getUserLoginCount() + ", stopFlag=" + getStopFlag() + ", delFlag=" + getDelFlag() + ", insProgarmCd='" + getInsProgarmCd() + "'" + ", insOperCd='" + getInsOperCd() + "'" + ", insDateTime='" + getInsDateTime() + "'" + ", updProgarmCd='" + getUpdProgarmCd() + "'" + ", updOperCd='" + getUpdOperCd() + "'" + ", updDateTime='" + getUpdDateTime() + "'" + ", delProgarmCd='" + getDelProgarmCd() + "'" + ", delOperCd='" + getDelOperCd() + "'" + ", delDateTime='" + getDelDateTime() + "'" + ", triggerDateTime='" + getTriggerDateTime() + "'" + ", organizationCd='" + getOrganizationCd() + "'" + "}";
}


public ZonedDateTime getUserLastLoginTime(){
    return userLastLoginTime;
}


public void setTriggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
}


}
package cn.com.cnc.fcc.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
public class RbacRole implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Integer appId;

 private  Integer storeId;

 private  String roleCode;

 private  String roleName;

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


public String getRoleCode(){
    return roleCode;
}


public void setId(Long id){
    this.id = id;
}


public RbacRole insProgarmCd(String insProgarmCd){
    this.insProgarmCd = insProgarmCd;
    return this;
}


public RbacRole updProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
    return this;
}


public void setRoleCode(String roleCode){
    this.roleCode = roleCode;
}


public ZonedDateTime getDelDateTime(){
    return delDateTime;
}


public RbacRole insOperCd(String insOperCd){
    this.insOperCd = insOperCd;
    return this;
}


public String getInsProgarmCd(){
    return insProgarmCd;
}


public String getDelProgarmCd(){
    return delProgarmCd;
}


public RbacRole updOperCd(String updOperCd){
    this.updOperCd = updOperCd;
    return this;
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


public RbacRole delOperCd(String delOperCd){
    this.delOperCd = delOperCd;
    return this;
}


public String getRoleName(){
    return roleName;
}


public RbacRole insDateTime(ZonedDateTime insDateTime){
    this.insDateTime = insDateTime;
    return this;
}


public RbacRole storeId(Integer storeId){
    this.storeId = storeId;
    return this;
}


public void setAppId(Integer appId){
    this.appId = appId;
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
    RbacRole rbacRole = (RbacRole) o;
    if (rbacRole.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), rbacRole.getId());
}


public RbacRole updDateTime(ZonedDateTime updDateTime){
    this.updDateTime = updDateTime;
    return this;
}


}
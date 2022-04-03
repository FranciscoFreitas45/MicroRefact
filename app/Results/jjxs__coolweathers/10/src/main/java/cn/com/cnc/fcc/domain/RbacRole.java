package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "rbac_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RbacRole implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "app_id")
 private  Integer appId;

@Column(name = "store_id")
 private  Integer storeId;

@Size(max = 20)
@Column(name = "role_code", length = 20)
 private  String roleCode;

@Size(max = 64)
@Column(name = "role_name", length = 64)
 private  String roleName;

@Column(name = "stop_flag")
 private  Integer stopFlag;

@Column(name = "del_flag")
 private  Integer delFlag;

@Size(max = 20)
@Column(name = "ins_progarm_cd", length = 20)
 private  String insProgarmCd;

@Size(max = 20)
@Column(name = "ins_oper_cd", length = 20)
 private  String insOperCd;

@Column(name = "ins_date_time")
 private  ZonedDateTime insDateTime;

@Size(max = 20)
@Column(name = "upd_progarm_cd", length = 20)
 private  String updProgarmCd;

@Size(max = 20)
@Column(name = "upd_oper_cd", length = 20)
 private  String updOperCd;

@Column(name = "upd_date_time")
 private  ZonedDateTime updDateTime;

@Size(max = 20)
@Column(name = "del_progarm_cd", length = 20)
 private  String delProgarmCd;

@Size(max = 20)
@Column(name = "del_oper_cd", length = 20)
 private  String delOperCd;

@Column(name = "del_date_time")
 private  ZonedDateTime delDateTime;

@Column(name = "trigger_date_time")
 private  ZonedDateTime triggerDateTime;


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public Integer getStopFlag(){
    return stopFlag;
}


public RbacRole appId(Integer appId){
    this.appId = appId;
    return this;
}


public String getRoleCode(){
    return roleCode;
}


public RbacRole roleCode(String roleCode){
    this.roleCode = roleCode;
    return this;
}


public void setId(Long id){
    this.id = id;
}


public void setDelDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
}


public RbacRole insProgarmCd(String insProgarmCd){
    this.insProgarmCd = insProgarmCd;
    return this;
}


public RbacRole delProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
    return this;
}


public RbacRole updProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
    return this;
}


public RbacRole stopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
    return this;
}


public void setRoleCode(String roleCode){
    this.roleCode = roleCode;
}


public RbacRole delDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
    return this;
}


public ZonedDateTime getDelDateTime(){
    return delDateTime;
}


public void setInsOperCd(String insOperCd){
    this.insOperCd = insOperCd;
}


public RbacRole insOperCd(String insOperCd){
    this.insOperCd = insOperCd;
    return this;
}


public void setUpdOperCd(String updOperCd){
    this.updOperCd = updOperCd;
}


public String getInsProgarmCd(){
    return insProgarmCd;
}


public String getDelProgarmCd(){
    return delProgarmCd;
}


public RbacRole roleName(String roleName){
    this.roleName = roleName;
    return this;
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


public RbacRole delFlag(Integer delFlag){
    this.delFlag = delFlag;
    return this;
}


public String getDelOperCd(){
    return delOperCd;
}


public Integer getAppId(){
    return appId;
}


public void setDelFlag(Integer delFlag){
    this.delFlag = delFlag;
}


public void setInsProgarmCd(String insProgarmCd){
    this.insProgarmCd = insProgarmCd;
}


public void setStopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
}


public ZonedDateTime getInsDateTime(){
    return insDateTime;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public void setDelProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
}


public Integer getStoreId(){
    return storeId;
}


public RbacRole triggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
    return this;
}


public RbacRole delOperCd(String delOperCd){
    this.delOperCd = delOperCd;
    return this;
}


public String getRoleName(){
    return roleName;
}


public void setDelOperCd(String delOperCd){
    this.delOperCd = delOperCd;
}


public RbacRole insDateTime(ZonedDateTime insDateTime){
    this.insDateTime = insDateTime;
    return this;
}


public void setUpdProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
}


public RbacRole storeId(Integer storeId){
    this.storeId = storeId;
    return this;
}


public void setInsDateTime(ZonedDateTime insDateTime){
    this.insDateTime = insDateTime;
}


public void setAppId(Integer appId){
    this.appId = appId;
}


public String getUpdOperCd(){
    return updOperCd;
}


public void setUpdDateTime(ZonedDateTime updDateTime){
    this.updDateTime = updDateTime;
}


public String getInsOperCd(){
    return insOperCd;
}


public void setStoreId(Integer storeId){
    this.storeId = storeId;
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


@Override
public String toString(){
    return "RbacRole{" + "id=" + getId() + ", appId=" + getAppId() + ", storeId=" + getStoreId() + ", roleCode='" + getRoleCode() + "'" + ", roleName='" + getRoleName() + "'" + ", stopFlag=" + getStopFlag() + ", delFlag=" + getDelFlag() + ", insProgarmCd='" + getInsProgarmCd() + "'" + ", insOperCd='" + getInsOperCd() + "'" + ", insDateTime='" + getInsDateTime() + "'" + ", updProgarmCd='" + getUpdProgarmCd() + "'" + ", updOperCd='" + getUpdOperCd() + "'" + ", updDateTime='" + getUpdDateTime() + "'" + ", delProgarmCd='" + getDelProgarmCd() + "'" + ", delOperCd='" + getDelOperCd() + "'" + ", delDateTime='" + getDelDateTime() + "'" + ", triggerDateTime='" + getTriggerDateTime() + "'" + "}";
}


public RbacRole updDateTime(ZonedDateTime updDateTime){
    this.updDateTime = updDateTime;
    return this;
}


public void setTriggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
}


}
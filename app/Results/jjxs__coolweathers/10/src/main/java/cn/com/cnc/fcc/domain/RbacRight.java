package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "rbac_right")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RbacRight implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "app_id")
 private  Integer appId;

@Column(name = "store_id")
 private  Integer storeId;

@Size(max = 20)
@Column(name = "right_code", length = 20)
 private  String rightCode;

@Size(max = 64)
@Column(name = "right_name", length = 64)
 private  String rightName;

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


public Integer getStopFlag(){
    return stopFlag;
}


public RbacRight appId(Integer appId){
    this.appId = appId;
    return this;
}


public void setId(Long id){
    this.id = id;
}


public void setDelDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
}


public RbacRight insProgarmCd(String insProgarmCd){
    this.insProgarmCd = insProgarmCd;
    return this;
}


public RbacRight delProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
    return this;
}


public RbacRight updProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
    return this;
}


public RbacRight stopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
    return this;
}


public RbacRight delDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
    return this;
}


public ZonedDateTime getDelDateTime(){
    return delDateTime;
}


public String getRightCode(){
    return rightCode;
}


public RbacRight rightName(String rightName){
    this.rightName = rightName;
    return this;
}


public void setRightName(String rightName){
    this.rightName = rightName;
}


public void setInsOperCd(String insOperCd){
    this.insOperCd = insOperCd;
}


public RbacRight insOperCd(String insOperCd){
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


public RbacRight updOperCd(String updOperCd){
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


public RbacRight delFlag(Integer delFlag){
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


public void setRightCode(String rightCode){
    this.rightCode = rightCode;
}


public ZonedDateTime getInsDateTime(){
    return insDateTime;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public RbacRight rightCode(String rightCode){
    this.rightCode = rightCode;
    return this;
}


public void setDelProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
}


public Integer getStoreId(){
    return storeId;
}


public RbacRight triggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
    return this;
}


public RbacRight delOperCd(String delOperCd){
    this.delOperCd = delOperCd;
    return this;
}


public void setDelOperCd(String delOperCd){
    this.delOperCd = delOperCd;
}


public RbacRight insDateTime(ZonedDateTime insDateTime){
    this.insDateTime = insDateTime;
    return this;
}


public void setUpdProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
}


public RbacRight storeId(Integer storeId){
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


public String getRightName(){
    return rightName;
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
    RbacRight rbacRight = (RbacRight) o;
    if (rbacRight.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), rbacRight.getId());
}


@Override
public String toString(){
    return "RbacRight{" + "id=" + getId() + ", appId=" + getAppId() + ", storeId=" + getStoreId() + ", rightCode='" + getRightCode() + "'" + ", rightName='" + getRightName() + "'" + ", stopFlag=" + getStopFlag() + ", delFlag=" + getDelFlag() + ", insProgarmCd='" + getInsProgarmCd() + "'" + ", insOperCd='" + getInsOperCd() + "'" + ", insDateTime='" + getInsDateTime() + "'" + ", updProgarmCd='" + getUpdProgarmCd() + "'" + ", updOperCd='" + getUpdOperCd() + "'" + ", updDateTime='" + getUpdDateTime() + "'" + ", delProgarmCd='" + getDelProgarmCd() + "'" + ", delOperCd='" + getDelOperCd() + "'" + ", delDateTime='" + getDelDateTime() + "'" + ", triggerDateTime='" + getTriggerDateTime() + "'" + "}";
}


public RbacRight updDateTime(ZonedDateTime updDateTime){
    this.updDateTime = updDateTime;
    return this;
}


public void setTriggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
}


}
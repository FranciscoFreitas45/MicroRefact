import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "rbac_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RbacUser implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "app_id")
 private  Integer appId;

@Column(name = "store_id")
 private  Integer storeId;

@Size(max = 20)
@Column(name = "user_code", length = 20)
 private  String userCode;

@Size(max = 64)
@Column(name = "user_password", length = 64)
 private  String userPassword;

@Size(max = 64)
@Column(name = "user_name", length = 64)
 private  String userName;

@Size(max = 20)
@Column(name = "user_mobile", length = 20)
 private  String userMobile;

@Size(max = 64)
@Column(name = "user_mail", length = 64)
 private  String userMail;

@Column(name = "user_last_login_time")
 private  ZonedDateTime userLastLoginTime;

@Column(name = "user_login_count")
 private  Integer userLoginCount;

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

@Size(max = 10)
@Column(name = "organization_cd", length = 10)
 private  String organizationCd;


public void setUserMail(String userMail){
    this.userMail = userMail;
}


public Integer getStopFlag(){
    return stopFlag;
}


public void setUserMobile(String userMobile){
    this.userMobile = userMobile;
}


public RbacUser userLoginCount(Integer userLoginCount){
    this.userLoginCount = userLoginCount;
    return this;
}


public RbacUser userMobile(String userMobile){
    this.userMobile = userMobile;
    return this;
}


public RbacUser appId(Integer appId){
    this.appId = appId;
    return this;
}


public void setId(Long id){
    this.id = id;
}


public String getUserName(){
    return userName;
}


public void setDelDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
}


public void setUserLoginCount(Integer userLoginCount){
    this.userLoginCount = userLoginCount;
}


public Integer getUserLoginCount(){
    return userLoginCount;
}


public RbacUser insProgarmCd(String insProgarmCd){
    this.insProgarmCd = insProgarmCd;
    return this;
}


public RbacUser delProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
    return this;
}


public RbacUser updProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
    return this;
}


public RbacUser stopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
    return this;
}


public RbacUser delDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
    return this;
}


public ZonedDateTime getDelDateTime(){
    return delDateTime;
}


public void setInsOperCd(String insOperCd){
    this.insOperCd = insOperCd;
}


public RbacUser insOperCd(String insOperCd){
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


public RbacUser userMail(String userMail){
    this.userMail = userMail;
    return this;
}


public RbacUser updOperCd(String updOperCd){
    this.updOperCd = updOperCd;
    return this;
}


public void setOrganizationCd(String organizationCd){
    this.organizationCd = organizationCd;
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


public RbacUser userPassword(String userPassword){
    this.userPassword = userPassword;
    return this;
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


public RbacUser organizationCd(String organizationCd){
    this.organizationCd = organizationCd;
    return this;
}


public RbacUser delFlag(Integer delFlag){
    this.delFlag = delFlag;
    return this;
}


public String getDelOperCd(){
    return delOperCd;
}


public RbacUser userCode(String userCode){
    this.userCode = userCode;
    return this;
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


public void setUserName(String userName){
    this.userName = userName;
}


public ZonedDateTime getInsDateTime(){
    return insDateTime;
}


@Override
public int hashCode(){
    return Objects.hashCode(getId());
}


public String getUserMobile(){
    return userMobile;
}


public void setDelProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
}


public Integer getStoreId(){
    return storeId;
}


public RbacUser triggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
    return this;
}


public RbacUser delOperCd(String delOperCd){
    this.delOperCd = delOperCd;
    return this;
}


public void setDelOperCd(String delOperCd){
    this.delOperCd = delOperCd;
}


public RbacUser insDateTime(ZonedDateTime insDateTime){
    this.insDateTime = insDateTime;
    return this;
}


public void setUserLastLoginTime(ZonedDateTime userLastLoginTime){
    this.userLastLoginTime = userLastLoginTime;
}


public void setUpdProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
}


public RbacUser storeId(Integer storeId){
    this.storeId = storeId;
    return this;
}


public RbacUser userName(String userName){
    this.userName = userName;
    return this;
}


public String getUserMail(){
    return userMail;
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


public void setUserCode(String userCode){
    this.userCode = userCode;
}


public String getInsOperCd(){
    return insOperCd;
}


public void setStoreId(Integer storeId){
    this.storeId = storeId;
}


public void setUserPassword(String userPassword){
    this.userPassword = userPassword;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    RbacUser rbacUser = (RbacUser) o;
    if (rbacUser.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), rbacUser.getId());
}


@Override
public String toString(){
    return "RbacUser{" + "id=" + getId() + ", appId=" + getAppId() + ", storeId=" + getStoreId() + ", userCode='" + getUserCode() + "'" + ", userPassword='" + getUserPassword() + "'" + ", userName='" + getUserName() + "'" + ", userMobile='" + getUserMobile() + "'" + ", userMail='" + getUserMail() + "'" + ", userLastLoginTime='" + getUserLastLoginTime() + "'" + ", userLoginCount=" + getUserLoginCount() + ", stopFlag=" + getStopFlag() + ", delFlag=" + getDelFlag() + ", insProgarmCd='" + getInsProgarmCd() + "'" + ", insOperCd='" + getInsOperCd() + "'" + ", insDateTime='" + getInsDateTime() + "'" + ", updProgarmCd='" + getUpdProgarmCd() + "'" + ", updOperCd='" + getUpdOperCd() + "'" + ", updDateTime='" + getUpdDateTime() + "'" + ", delProgarmCd='" + getDelProgarmCd() + "'" + ", delOperCd='" + getDelOperCd() + "'" + ", delDateTime='" + getDelDateTime() + "'" + ", triggerDateTime='" + getTriggerDateTime() + "'" + ", organizationCd='" + getOrganizationCd() + "'" + "}";
}


public RbacUser userLastLoginTime(ZonedDateTime userLastLoginTime){
    this.userLastLoginTime = userLastLoginTime;
    return this;
}


public ZonedDateTime getUserLastLoginTime(){
    return userLastLoginTime;
}


public RbacUser updDateTime(ZonedDateTime updDateTime){
    this.updDateTime = updDateTime;
    return this;
}


public void setTriggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
}


}
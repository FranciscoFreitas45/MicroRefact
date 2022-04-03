package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "rbac_menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RbacMenu implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "app_id")
 private  Integer appId;

@Column(name = "store_id")
 private  Integer storeId;

@Column(name = "p_menu_id")
 private  Integer pMenuId;

@Size(max = 20)
@Column(name = "menu_code", length = 20)
 private  String menuCode;

@Size(max = 64)
@Column(name = "menu_name", length = 64)
 private  String menuName;

@Size(max = 128)
@Column(name = "menu_url", length = 128)
 private  String menuUrl;

@Size(max = 64)
@Column(name = "menu_icon", length = 64)
 private  String menuIcon;

@Size(max = 64)
@Column(name = "menu_lable", length = 64)
 private  String menuLable;

@Column(name = "menu_mobile_flag")
 private  Integer menuMobileFlag;

@Column(name = "menu_host_slave_flag")
 private  Integer menuHostSlaveFlag;

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


public RbacMenu menuIcon(String menuIcon){
    this.menuIcon = menuIcon;
    return this;
}


public void setMenuMobileFlag(Integer menuMobileFlag){
    this.menuMobileFlag = menuMobileFlag;
}


public RbacMenu pMenuId(Integer pMenuId){
    this.pMenuId = pMenuId;
    return this;
}


public RbacMenu menuName(String menuName){
    this.menuName = menuName;
    return this;
}


public Integer getStopFlag(){
    return stopFlag;
}


public Integer getMenuMobileFlag(){
    return menuMobileFlag;
}


public void setMenuHostSlaveFlag(Integer menuHostSlaveFlag){
    this.menuHostSlaveFlag = menuHostSlaveFlag;
}


public RbacMenu appId(Integer appId){
    this.appId = appId;
    return this;
}


public RbacMenu menuCode(String menuCode){
    this.menuCode = menuCode;
    return this;
}


public void setId(Long id){
    this.id = id;
}


public void setDelDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
}


public RbacMenu menuHostSlaveFlag(Integer menuHostSlaveFlag){
    this.menuHostSlaveFlag = menuHostSlaveFlag;
    return this;
}


public void setMenuName(String menuName){
    this.menuName = menuName;
}


public RbacMenu insProgarmCd(String insProgarmCd){
    this.insProgarmCd = insProgarmCd;
    return this;
}


public RbacMenu delProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
    return this;
}


public void setpMenuId(Integer pMenuId){
    this.pMenuId = pMenuId;
}


public RbacMenu updProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
    return this;
}


public RbacMenu stopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
    return this;
}


public Integer getpMenuId(){
    return pMenuId;
}


public RbacMenu delDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
    return this;
}


public String getMenuUrl(){
    return menuUrl;
}


public String getMenuLable(){
    return menuLable;
}


public ZonedDateTime getDelDateTime(){
    return delDateTime;
}


public void setMenuUrl(String menuUrl){
    this.menuUrl = menuUrl;
}


public void setInsOperCd(String insOperCd){
    this.insOperCd = insOperCd;
}


public RbacMenu menuUrl(String menuUrl){
    this.menuUrl = menuUrl;
    return this;
}


public RbacMenu insOperCd(String insOperCd){
    this.insOperCd = insOperCd;
    return this;
}


public void setUpdOperCd(String updOperCd){
    this.updOperCd = updOperCd;
}


public String getMenuIcon(){
    return menuIcon;
}


public RbacMenu menuMobileFlag(Integer menuMobileFlag){
    this.menuMobileFlag = menuMobileFlag;
    return this;
}


public String getInsProgarmCd(){
    return insProgarmCd;
}


public String getMenuCode(){
    return menuCode;
}


public String getDelProgarmCd(){
    return delProgarmCd;
}


public RbacMenu updOperCd(String updOperCd){
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


public void setMenuLable(String menuLable){
    this.menuLable = menuLable;
}


public Long getId(){
    return id;
}


public RbacMenu delFlag(Integer delFlag){
    this.delFlag = delFlag;
    return this;
}


public RbacMenu menuLable(String menuLable){
    this.menuLable = menuLable;
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


public Integer getMenuHostSlaveFlag(){
    return menuHostSlaveFlag;
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


public String getMenuName(){
    return menuName;
}


public Integer getStoreId(){
    return storeId;
}


public void setMenuCode(String menuCode){
    this.menuCode = menuCode;
}


public RbacMenu triggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
    return this;
}


public RbacMenu delOperCd(String delOperCd){
    this.delOperCd = delOperCd;
    return this;
}


public void setDelOperCd(String delOperCd){
    this.delOperCd = delOperCd;
}


public RbacMenu insDateTime(ZonedDateTime insDateTime){
    this.insDateTime = insDateTime;
    return this;
}


public void setMenuIcon(String menuIcon){
    this.menuIcon = menuIcon;
}


public void setUpdProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
}


public RbacMenu storeId(Integer storeId){
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
    RbacMenu rbacMenu = (RbacMenu) o;
    if (rbacMenu.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), rbacMenu.getId());
}


@Override
public String toString(){
    return "RbacMenu{" + "id=" + getId() + ", appId=" + getAppId() + ", storeId=" + getStoreId() + ", pMenuId=" + getpMenuId() + ", menuCode='" + getMenuCode() + "'" + ", menuName='" + getMenuName() + "'" + ", menuUrl='" + getMenuUrl() + "'" + ", menuIcon='" + getMenuIcon() + "'" + ", menuLable='" + getMenuLable() + "'" + ", menuMobileFlag=" + getMenuMobileFlag() + ", menuHostSlaveFlag=" + getMenuHostSlaveFlag() + ", stopFlag=" + getStopFlag() + ", delFlag=" + getDelFlag() + ", insProgarmCd='" + getInsProgarmCd() + "'" + ", insOperCd='" + getInsOperCd() + "'" + ", insDateTime='" + getInsDateTime() + "'" + ", updProgarmCd='" + getUpdProgarmCd() + "'" + ", updOperCd='" + getUpdOperCd() + "'" + ", updDateTime='" + getUpdDateTime() + "'" + ", delProgarmCd='" + getDelProgarmCd() + "'" + ", delOperCd='" + getDelOperCd() + "'" + ", delDateTime='" + getDelDateTime() + "'" + ", triggerDateTime='" + getTriggerDateTime() + "'" + "}";
}


public RbacMenu updDateTime(ZonedDateTime updDateTime){
    this.updDateTime = updDateTime;
    return this;
}


public void setTriggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
}


}
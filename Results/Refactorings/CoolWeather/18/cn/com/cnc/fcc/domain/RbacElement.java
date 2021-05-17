import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "rbac_element")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RbacElement implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "app_id")
 private  Integer appId;

@Column(name = "store_id")
 private  Integer storeId;

@Size(max = 20)
@Column(name = "element_code", length = 20)
 private  String elementCode;

@Column(name = "menu_id")
 private  Integer menuId;

@Column(name = "is_dis")
 private  Integer isDis;

@Column(name = "stop_flag")
 private  Integer stopFlag;

@Column(name = "del_flage")
 private  Integer delFlage;

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


public void setDelFlage(Integer delFlage){
    this.delFlage = delFlage;
}


public Integer getMenuId(){
    return menuId;
}


public Integer getStopFlag(){
    return stopFlag;
}


public RbacElement delFlage(Integer delFlage){
    this.delFlage = delFlage;
    return this;
}


public RbacElement appId(Integer appId){
    this.appId = appId;
    return this;
}


public void setElementCode(String elementCode){
    this.elementCode = elementCode;
}


public void setId(Long id){
    this.id = id;
}


public RbacElement menuId(Integer menuId){
    this.menuId = menuId;
    return this;
}


public void setDelDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
}


public RbacElement insProgarmCd(String insProgarmCd){
    this.insProgarmCd = insProgarmCd;
    return this;
}


public RbacElement delProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
    return this;
}


public RbacElement updProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
    return this;
}


public RbacElement stopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
    return this;
}


public RbacElement delDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
    return this;
}


public String getElementCode(){
    return elementCode;
}


public RbacElement elementCode(String elementCode){
    this.elementCode = elementCode;
    return this;
}


public ZonedDateTime getDelDateTime(){
    return delDateTime;
}


public void setInsOperCd(String insOperCd){
    this.insOperCd = insOperCd;
}


public RbacElement insOperCd(String insOperCd){
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


public RbacElement updOperCd(String updOperCd){
    this.updOperCd = updOperCd;
    return this;
}


public String getUpdProgarmCd(){
    return updProgarmCd;
}


public ZonedDateTime getUpdDateTime(){
    return updDateTime;
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


public void setStopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
}


public void setMenuId(Integer menuId){
    this.menuId = menuId;
}


public Integer getIsDis(){
    return isDis;
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


public Integer getDelFlage(){
    return delFlage;
}


public Integer getStoreId(){
    return storeId;
}


public RbacElement triggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
    return this;
}


public RbacElement delOperCd(String delOperCd){
    this.delOperCd = delOperCd;
    return this;
}


public void setDelOperCd(String delOperCd){
    this.delOperCd = delOperCd;
}


public RbacElement insDateTime(ZonedDateTime insDateTime){
    this.insDateTime = insDateTime;
    return this;
}


public void setIsDis(Integer isDis){
    this.isDis = isDis;
}


public void setUpdProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
}


public RbacElement storeId(Integer storeId){
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
    RbacElement rbacElement = (RbacElement) o;
    if (rbacElement.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), rbacElement.getId());
}


public RbacElement isDis(Integer isDis){
    this.isDis = isDis;
    return this;
}


@Override
public String toString(){
    return "RbacElement{" + "id=" + getId() + ", appId=" + getAppId() + ", storeId=" + getStoreId() + ", elementCode='" + getElementCode() + "'" + ", menuId=" + getMenuId() + ", isDis=" + getIsDis() + ", stopFlag=" + getStopFlag() + ", delFlage=" + getDelFlage() + ", insProgarmCd='" + getInsProgarmCd() + "'" + ", insOperCd='" + getInsOperCd() + "'" + ", insDateTime='" + getInsDateTime() + "'" + ", updProgarmCd='" + getUpdProgarmCd() + "'" + ", updOperCd='" + getUpdOperCd() + "'" + ", updDateTime='" + getUpdDateTime() + "'" + ", delProgarmCd='" + getDelProgarmCd() + "'" + ", delOperCd='" + getDelOperCd() + "'" + ", delDateTime='" + getDelDateTime() + "'" + ", triggerDateTime='" + getTriggerDateTime() + "'" + "}";
}


public RbacElement updDateTime(ZonedDateTime updDateTime){
    this.updDateTime = updDateTime;
    return this;
}


public void setTriggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
}


}
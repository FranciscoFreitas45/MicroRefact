import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "hst_server_info_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HstServerInfoDetails implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "app_id")
 private  Integer appId;

@Column(name = "store_id")
 private  Integer storeId;

@Column(name = "info_id")
 private  Long infoId;

@Size(max = 50)
@Column(name = "host_name", length = 50)
 private  String hostName;

@Size(max = 50)
@Column(name = "ip_address", length = 50)
 private  String ipAddress;

@Size(max = 50)
@Column(name = "os_user", length = 50)
 private  String osUser;

@Size(max = 50)
@Column(name = "os_name", length = 50)
 private  String osName;

@Size(max = 50)
@Column(name = "os_version", length = 50)
 private  String osVersion;

@Size(max = 50)
@Column(name = "os_arch", length = 50)
 private  String osArch;

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


public HstServerInfoDetails infoId(Long infoId){
    this.infoId = infoId;
    return this;
}


public HstServerInfoDetails osArch(String osArch){
    this.osArch = osArch;
    return this;
}


public Integer getStopFlag(){
    return stopFlag;
}


public HstServerInfoDetails appId(Integer appId){
    this.appId = appId;
    return this;
}


public void setId(Long id){
    this.id = id;
}


public void setDelDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
}


public HstServerInfoDetails insProgarmCd(String insProgarmCd){
    this.insProgarmCd = insProgarmCd;
    return this;
}


public HstServerInfoDetails delProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
    return this;
}


public HstServerInfoDetails updProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
    return this;
}


public HstServerInfoDetails stopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
    return this;
}


public String getHostName(){
    return hostName;
}


public String getOsArch(){
    return osArch;
}


public HstServerInfoDetails delDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
    return this;
}


public HstServerInfoDetails ipAddress(String ipAddress){
    this.ipAddress = ipAddress;
    return this;
}


public HstServerInfoDetails osUser(String osUser){
    this.osUser = osUser;
    return this;
}


public ZonedDateTime getDelDateTime(){
    return delDateTime;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


public void setInsOperCd(String insOperCd){
    this.insOperCd = insOperCd;
}


public HstServerInfoDetails insOperCd(String insOperCd){
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


public String getOsUser(){
    return osUser;
}


public HstServerInfoDetails updOperCd(String updOperCd){
    this.updOperCd = updOperCd;
    return this;
}


public String getUpdProgarmCd(){
    return updProgarmCd;
}


public void setOsName(String osName){
    this.osName = osName;
}


public ZonedDateTime getUpdDateTime(){
    return updDateTime;
}


public HstServerInfoDetails hostName(String hostName){
    this.hostName = hostName;
    return this;
}


public String getIpAddress(){
    return ipAddress;
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


public HstServerInfoDetails delFlag(Integer delFlag){
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


public void setOsVersion(String osVersion){
    this.osVersion = osVersion;
}


public void setInsProgarmCd(String insProgarmCd){
    this.insProgarmCd = insProgarmCd;
}


public void setStopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
}


public HstServerInfoDetails osVersion(String osVersion){
    this.osVersion = osVersion;
    return this;
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


public void setInfoId(Long infoId){
    this.infoId = infoId;
}


public Long getInfoId(){
    return infoId;
}


public HstServerInfoDetails triggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
    return this;
}


public HstServerInfoDetails delOperCd(String delOperCd){
    this.delOperCd = delOperCd;
    return this;
}


public void setOsUser(String osUser){
    this.osUser = osUser;
}


public void setDelOperCd(String delOperCd){
    this.delOperCd = delOperCd;
}


public HstServerInfoDetails insDateTime(ZonedDateTime insDateTime){
    this.insDateTime = insDateTime;
    return this;
}


public void setUpdProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
}


public HstServerInfoDetails storeId(Integer storeId){
    this.storeId = storeId;
    return this;
}


public HstServerInfoDetails osName(String osName){
    this.osName = osName;
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


public void setHostName(String hostName){
    this.hostName = hostName;
}


public String getOsVersion(){
    return osVersion;
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


public void setOsArch(String osArch){
    this.osArch = osArch;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    HstServerInfoDetails hstServerInfoDetails = (HstServerInfoDetails) o;
    if (hstServerInfoDetails.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), hstServerInfoDetails.getId());
}


@Override
public String toString(){
    return "HstServerInfoDetails{" + "id=" + getId() + ", appId=" + getAppId() + ", storeId=" + getStoreId() + ", infoId=" + getInfoId() + ", hostName='" + getHostName() + "'" + ", ipAddress='" + getIpAddress() + "'" + ", osUser='" + getOsUser() + "'" + ", osName='" + getOsName() + "'" + ", osVersion='" + getOsVersion() + "'" + ", osArch='" + getOsArch() + "'" + ", stopFlag=" + getStopFlag() + ", delFlag=" + getDelFlag() + ", insProgarmCd='" + getInsProgarmCd() + "'" + ", insOperCd='" + getInsOperCd() + "'" + ", insDateTime='" + getInsDateTime() + "'" + ", updProgarmCd='" + getUpdProgarmCd() + "'" + ", updOperCd='" + getUpdOperCd() + "'" + ", updDateTime='" + getUpdDateTime() + "'" + ", delProgarmCd='" + getDelProgarmCd() + "'" + ", delOperCd='" + getDelOperCd() + "'" + ", delDateTime='" + getDelDateTime() + "'" + ", triggerDateTime='" + getTriggerDateTime() + "'" + "}";
}


public HstServerInfoDetails updDateTime(ZonedDateTime updDateTime){
    this.updDateTime = updDateTime;
    return this;
}


public void setTriggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
}


public String getOsName(){
    return osName;
}


}
package cn.com.cnc.fcc.domain;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence;
import javax.validation.constraints;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
@Entity
@Table(name = "hst_server_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HstServerInfo implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(name = "app_id")
 private  Integer appId;

@Column(name = "store_id")
 private  Integer storeId;

@Column(name = "host_slave_flag")
 private  Integer hostSlaveFlag;

@Size(max = 20)
@Column(name = "node_id", length = 20)
 private  String nodeId;

@Size(max = 20)
@Column(name = "p_node_id", length = 20)
 private  String pNodeId;

@Size(max = 255)
@Column(name = "node_url", length = 255)
 private  String nodeUrl;

@Column(name = "node_join_time")
 private  ZonedDateTime nodeJoinTime;

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


public String getNodeUrl(){
    return nodeUrl;
}


public String getpNodeId(){
    return pNodeId;
}


public HstServerInfo nodeUrl(String nodeUrl){
    this.nodeUrl = nodeUrl;
    return this;
}


public void setNodeJoinTime(ZonedDateTime nodeJoinTime){
    this.nodeJoinTime = nodeJoinTime;
}


public void setNodeUrl(String nodeUrl){
    this.nodeUrl = nodeUrl;
}


public Integer getStopFlag(){
    return stopFlag;
}


public void setpNodeId(String pNodeId){
    this.pNodeId = pNodeId;
}


public HstServerInfo appId(Integer appId){
    this.appId = appId;
    return this;
}


public void setId(Long id){
    this.id = id;
}


public void setDelDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
}


public HstServerInfo insProgarmCd(String insProgarmCd){
    this.insProgarmCd = insProgarmCd;
    return this;
}


public HstServerInfo delProgarmCd(String delProgarmCd){
    this.delProgarmCd = delProgarmCd;
    return this;
}


public HstServerInfo updProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
    return this;
}


public HstServerInfo stopFlag(Integer stopFlag){
    this.stopFlag = stopFlag;
    return this;
}


public String getNodeId(){
    return nodeId;
}


public HstServerInfo delDateTime(ZonedDateTime delDateTime){
    this.delDateTime = delDateTime;
    return this;
}


public HstServerInfo hostSlaveFlag(Integer hostSlaveFlag){
    this.hostSlaveFlag = hostSlaveFlag;
    return this;
}


public ZonedDateTime getDelDateTime(){
    return delDateTime;
}


public void setHostSlaveFlag(Integer hostSlaveFlag){
    this.hostSlaveFlag = hostSlaveFlag;
}


public void setInsOperCd(String insOperCd){
    this.insOperCd = insOperCd;
}


public HstServerInfo insOperCd(String insOperCd){
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


public HstServerInfo updOperCd(String updOperCd){
    this.updOperCd = updOperCd;
    return this;
}


public HstServerInfo nodeId(String nodeId){
    this.nodeId = nodeId;
    return this;
}


public String getUpdProgarmCd(){
    return updProgarmCd;
}


public ZonedDateTime getUpdDateTime(){
    return updDateTime;
}


public ZonedDateTime getNodeJoinTime(){
    return nodeJoinTime;
}


public HstServerInfo nodeJoinTime(ZonedDateTime nodeJoinTime){
    this.nodeJoinTime = nodeJoinTime;
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


public HstServerInfo delFlag(Integer delFlag){
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


public HstServerInfo pNodeId(String pNodeId){
    this.pNodeId = pNodeId;
    return this;
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


public HstServerInfo triggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
    return this;
}


public HstServerInfo delOperCd(String delOperCd){
    this.delOperCd = delOperCd;
    return this;
}


public void setDelOperCd(String delOperCd){
    this.delOperCd = delOperCd;
}


public HstServerInfo insDateTime(ZonedDateTime insDateTime){
    this.insDateTime = insDateTime;
    return this;
}


public void setUpdProgarmCd(String updProgarmCd){
    this.updProgarmCd = updProgarmCd;
}


public HstServerInfo storeId(Integer storeId){
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


public void setNodeId(String nodeId){
    this.nodeId = nodeId;
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
    HstServerInfo hstServerInfo = (HstServerInfo) o;
    if (hstServerInfo.getId() == null || getId() == null) {
        return false;
    }
    return Objects.equals(getId(), hstServerInfo.getId());
}


public Integer getHostSlaveFlag(){
    return hostSlaveFlag;
}


@Override
public String toString(){
    return "HstServerInfo{" + "id=" + getId() + ", appId=" + getAppId() + ", storeId=" + getStoreId() + ", hostSlaveFlag=" + getHostSlaveFlag() + ", nodeId='" + getNodeId() + "'" + ", pNodeId='" + getpNodeId() + "'" + ", nodeUrl='" + getNodeUrl() + "'" + ", nodeJoinTime='" + getNodeJoinTime() + "'" + ", stopFlag=" + getStopFlag() + ", delFlag=" + getDelFlag() + ", insProgarmCd='" + getInsProgarmCd() + "'" + ", insOperCd='" + getInsOperCd() + "'" + ", insDateTime='" + getInsDateTime() + "'" + ", updProgarmCd='" + getUpdProgarmCd() + "'" + ", updOperCd='" + getUpdOperCd() + "'" + ", updDateTime='" + getUpdDateTime() + "'" + ", delProgarmCd='" + getDelProgarmCd() + "'" + ", delOperCd='" + getDelOperCd() + "'" + ", delDateTime='" + getDelDateTime() + "'" + ", triggerDateTime='" + getTriggerDateTime() + "'" + "}";
}


public HstServerInfo updDateTime(ZonedDateTime updDateTime){
    this.updDateTime = updDateTime;
    return this;
}


public void setTriggerDateTime(ZonedDateTime triggerDateTime){
    this.triggerDateTime = triggerDateTime;
}


}
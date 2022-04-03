package com.sobey.cmop.mvc.entity;
 import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
@Entity
@Table(name = "compute_item", catalog = "cmop")
public class ComputeItem {

 private  Integer id;

 private  Apply apply;

 private  String identifier;

 private  Integer computeType;

 private  Integer osType;

 private  Integer osBit;

 private  Integer serverType;

 private  String remark;

 private  String innerIp;

 private  String oldIp;

 private  String hostName;

 private  String serverAlias;

 private  String hostServerAlias;

 private  String osStorageAlias;

 private  Set<Application> applications;

 private  Set<NetworkEipItem> networkEipItems;

 private  List<StorageItem> storageItemList;

 private  List<NetworkElbItem> networkElbItemList;

 private  List<NetworkEsgItem> networkEsgItemList;

// Constructors
/**
 * default constructor
 */
public ComputeItem() {
}/**
 * minimal constructor
 */
public ComputeItem(Apply apply, String identifier, Integer computeType, Integer osType, Integer osBit, Integer serverType, String remark, String innerIp, String oldIp) {
    this.apply = apply;
    this.identifier = identifier;
    this.computeType = computeType;
    this.osType = osType;
    this.osBit = osBit;
    this.serverType = serverType;
    this.remark = remark;
    this.innerIp = innerIp;
    this.oldIp = oldIp;
}/**
 * full constructor
 */
public ComputeItem(Apply apply, String identifier, Integer computeType, Integer osType, Integer osBit, Integer serverType, String remark, String innerIp, String oldIp, String hostName, String serverAlias, String hostServerAlias, String osStorageAlias, Set<Application> applications, Set<NetworkEipItem> networkEipItems) {
    this.apply = apply;
    this.identifier = identifier;
    this.computeType = computeType;
    this.osType = osType;
    this.osBit = osBit;
    this.serverType = serverType;
    this.remark = remark;
    this.innerIp = innerIp;
    this.oldIp = oldIp;
    this.hostName = hostName;
    this.serverAlias = serverAlias;
    this.hostServerAlias = hostServerAlias;
    this.osStorageAlias = osStorageAlias;
    this.applications = applications;
    this.networkEipItems = networkEipItems;
}
public void setComputeType(Integer computeType){
    this.computeType = computeType;
}


public void setInnerIp(String innerIp){
    this.innerIp = innerIp;
}


public void setNetworkEipItems(Set<NetworkEipItem> networkEipItems){
    this.networkEipItems = networkEipItems;
}


public void setStorageItemList(List<StorageItem> storageItemList){
    this.storageItemList = storageItemList;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


@Column(name = "os_type", nullable = false)
public Integer getOsType(){
    return this.osType;
}


@Column(name = "inner_ip", nullable = false, length = 45)
public String getInnerIp(){
    return this.innerIp;
}


@ManyToMany
@JoinTable(name = "compute_esg_item", joinColumns = { @JoinColumn(name = "compute_item_id") }, inverseJoinColumns = { @JoinColumn(name = "esg_item_id") })
// Fecth策略定义
@Fetch(FetchMode.SUBSELECT)
// 集合按id排序.
@OrderBy("id")
// 集合中对象id的缓存.
@NotFound(action = NotFoundAction.IGNORE)
@JsonIgnore
public List<NetworkEsgItem> getNetworkEsgItemList(){
    return networkEsgItemList;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


public String extractToString(List<NetworkEsgItem> networkEsgItems){
    StringBuilder sb = new StringBuilder();
    for (NetworkEsgItem networkEsgItem : networkEsgItems) {
        sb.append(networkEsgItem.getIdentifier()).append("(").append(networkEsgItem.getDescription()).append(")").append(",");
    }
    String str = sb.toString();
    return str.length() > 0 ? str.substring(0, str.length() - 1) : "";
}


public void setServerType(Integer serverType){
    this.serverType = serverType;
}


@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "apply_id", nullable = false)
public Apply getApply(){
    return this.apply;
}


public void setServerAlias(String serverAlias){
    this.serverAlias = serverAlias;
}


public String getOldIp(){
    return oldIp;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "identifier", nullable = false, length = 45)
public String getIdentifier(){
    return this.identifier;
}


@Column(name = "old_ip", length = 45)
public void setOldIp(String oldIp){
    this.oldIp = oldIp;
}


@JsonIgnore
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "computeItem")
public Set<Application> getApplications(){
    return this.applications;
}


@Column(name = "remark", nullable = false, length = 45)
public String getRemark(){
    return this.remark;
}


public void setNetworkEsgItemList(List<NetworkEsgItem> networkEsgItemList){
    this.networkEsgItemList = networkEsgItemList;
}


public void setId(Integer id){
    this.id = id;
}


public void setApply(Apply apply){
    this.apply = apply;
}


@Column(name = "host_server_alias", length = 45)
public String getHostServerAlias(){
    return this.hostServerAlias;
}


@ManyToMany
@JoinTable(name = "compute_storage_item", joinColumns = { @JoinColumn(name = "compute_item_id") }, inverseJoinColumns = { @JoinColumn(name = "storage_item_id") })
// Fecth策略定义
@Fetch(FetchMode.SUBSELECT)
// 集合按id排序.
@OrderBy("id")
// 集合中对象id的缓存.
@NotFound(action = NotFoundAction.IGNORE)
@JsonIgnore
public List<StorageItem> getStorageItemList(){
    return storageItemList;
}


@JsonIgnore
@OneToMany(fetch = FetchType.LAZY, mappedBy = "computeItem")
public Set<NetworkEipItem> getNetworkEipItems(){
    return networkEipItems;
}


@Column(name = "server_alias", length = 45)
public String getServerAlias(){
    return this.serverAlias;
}


@Column(name = "host_name", length = 45)
public String getHostName(){
    return this.hostName;
}


public void setOsStorageAlias(String osStorageAlias){
    this.osStorageAlias = osStorageAlias;
}


public void setHostServerAlias(String hostServerAlias){
    this.hostServerAlias = hostServerAlias;
}


public void setNetworkElbItemList(List<NetworkElbItem> networkElbItemList){
    this.networkElbItemList = networkElbItemList;
}


@Transient
public String getMountESG(){
    return extractToString(networkEsgItemList);
}


public void setHostName(String hostName){
    this.hostName = hostName;
}


public void setOsType(Integer osType){
    this.osType = osType;
}


@Column(name = "os_storage_alias", length = 45)
public String getOsStorageAlias(){
    return this.osStorageAlias;
}


@Column(name = "compute_type", nullable = false)
public Integer getComputeType(){
    return computeType;
}


@ManyToMany
@JoinTable(name = "compute_elb_item", joinColumns = { @JoinColumn(name = "compute_item_id") }, inverseJoinColumns = { @JoinColumn(name = "elb_item_id") })
// Fecth策略定义
@Fetch(FetchMode.SUBSELECT)
// 集合按id排序.
@OrderBy("id")
// 集合中对象id的缓存.
@NotFound(action = NotFoundAction.IGNORE)
@JsonIgnore
public List<NetworkElbItem> getNetworkElbItemList(){
    return networkElbItemList;
}


@Transient
public String getMountESGDetail(){
    return extractDetailToString(networkEsgItemList);
}


@Column(name = "server_type", nullable = false)
public Integer getServerType(){
    return this.serverType;
}


public void setOsBit(Integer osBit){
    this.osBit = osBit;
}


@Override
public String toString(){
    return ToStringBuilder.reflectionToString(this);
}


@Column(name = "os_bit", nullable = false)
public Integer getOsBit(){
    return this.osBit;
}


public void setApplications(Set<Application> applications){
    this.applications = applications;
}


public String extractDetailToString(List<NetworkEsgItem> networkEsgItems){
    StringBuilder sb = new StringBuilder();
    for (NetworkEsgItem networkEsgItem : networkEsgItems) {
        sb.append("\r\n").append(networkEsgItem.getIdentifier()).append("(").append(networkEsgItem.getDescription()).append(")").append("\r\n");
        for (EsgRuleItem esgRuleItem : networkEsgItem.getEsgRuleItems()) {
            sb.append("协议: ").append(esgRuleItem.getProtocol()).append("\r\n");
            sb.append("端口范围: ").append(esgRuleItem.getPortRange()).append("\r\n");
            sb.append("访问来源IP: ").append(esgRuleItem.getVisitSource()).append("\r\n");
            sb.append("访问目的IP: ").append(esgRuleItem.getVisitTarget()).append("\r\n\r\n");
        }
        sb.append("___________________________________________________________");
    }
    String str = sb.toString();
    return str.length() > 0 ? str.substring(0, str.length() - 1) : "";
}


}
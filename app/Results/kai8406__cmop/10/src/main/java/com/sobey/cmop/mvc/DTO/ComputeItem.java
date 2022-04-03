package com.sobey.cmop.mvc.DTO;
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


@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "apply_id", nullable = false)
public Apply getApply(){
    return this.apply;
}


public String getOldIp(){
    return oldIp;
}


@Column(name = "identifier", nullable = false, length = 45)
public String getIdentifier(){
    return this.identifier;
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


@Transient
public String getMountESG(){
    return extractToString(networkEsgItemList);
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


@Column(name = "os_bit", nullable = false)
public Integer getOsBit(){
    return this.osBit;
}


}
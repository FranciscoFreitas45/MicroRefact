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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
public class NetworkElbItem {

 private  Integer id;

 private  Apply apply;

 private  String identifier;

 private  Boolean keepSession;

 private  String virtualIp;

 private  String oldIp;

 private  Set<ElbPortItem> elbPortItems;

 private  Set<NetworkEipItem> networkEipItems;

 private  MonitorElb monitorElb;

 private  List<ComputeItem> computeItemList;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";

// Constructors
/**
 * default constructor
 */
public NetworkElbItem() {
}/**
 * minimal constructor
 */
public NetworkElbItem(Apply apply, String identifier, Boolean keepSession, String virtualIp, String oldIp) {
    this.apply = apply;
    this.identifier = identifier;
    this.keepSession = keepSession;
    this.virtualIp = virtualIp;
    this.oldIp = oldIp;
}/**
 * full constructor
 */
public NetworkElbItem(Apply apply, String identifier, Boolean keepSession, String virtualIp, String oldIp, MonitorElb monitorElb, Set<ElbPortItem> elbPortItems, Set<NetworkEipItem> networkEipItems) {
    this.apply = apply;
    this.identifier = identifier;
    this.keepSession = keepSession;
    this.virtualIp = virtualIp;
    this.oldIp = oldIp;
    this.elbPortItems = elbPortItems;
    this.monitorElb = monitorElb;
    this.networkEipItems = networkEipItems;
}
@JsonIgnore
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "networkElbItem")
public Set<ElbPortItem> getElbPortItems(){
    return this.elbPortItems;
}


@JsonIgnore
@OneToMany(fetch = FetchType.LAZY, mappedBy = "networkElbItem")
public Set<NetworkEipItem> getNetworkEipItems(){
    return networkEipItems;
}


@Column(name = "keep_session", nullable = false)
public Boolean getKeepSession(){
    return this.keepSession;
}


@ManyToMany
@JoinTable(name = "compute_elb_item", joinColumns = { @JoinColumn(name = "elb_item_id") }, inverseJoinColumns = { @JoinColumn(name = "compute_item_id") })
// Fecth策略定义
@Fetch(FetchMode.SUBSELECT)
// 集合按id排序.
@OrderBy("id")
// 集合中对象id的缓存.
@NotFound(action = NotFoundAction.IGNORE)
public List<ComputeItem> getComputeItemList(){
    return computeItemList;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "apply_id", nullable = false)
public Apply getApply(){
    return this.apply;
}


@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "networkElbItem")
public MonitorElb getMonitorElb(){
    return this.monitorElb;
}


public String getOldIp(){
    return oldIp;
}


@Transient
public String getMountComputes(){
    return StorageItem.extractToString(computeItemList);
}


@Column(name = "identifier", nullable = false, length = 45)
public String getIdentifier(){
    return this.identifier;
}


@Column(name = "virtual_ip", nullable = false, length = 45)
public String getVirtualIp(){
    return this.virtualIp;
}


public void setComputeItemList(List<ComputeItem> computeItemList){
    this.computeItemList = computeItemList;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setComputeItemList"))

.queryParam("computeItemList",computeItemList)
;
restTemplate.put(builder.toUriString(),null);
}


public void setKeepSession(Boolean keepSession){
    this.keepSession = keepSession;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setKeepSession"))

.queryParam("keepSession",keepSession)
;
restTemplate.put(builder.toUriString(),null);
}


}
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.collect.Lists;
public class NetworkEipItem {

 private  Integer id;

 private  Apply apply;

 private  String identifier;

 private  Integer ispType;

 private  String ipAddress;

 private  String oldIp;

 private  NetworkElbItem networkElbItem;

 private  ComputeItem computeItem;

 private  Set<EipPortItem> eipPortItems;

 private  List<NetworkDnsItem> networkDnsItemList;

// Constructors
/**
 * default constructor
 */
public NetworkEipItem() {
}/**
 * minimal constructor
 */
public NetworkEipItem(Apply apply, String identifier, Integer ispType, String ipAddress, String oldIp) {
    this.apply = apply;
    this.identifier = identifier;
    this.ispType = ispType;
    this.ipAddress = ipAddress;
    this.oldIp = oldIp;
}/**
 * full constructor
 */
public NetworkEipItem(Apply apply, String identifier, Integer ispType, String ipAddress, String oldIp, NetworkElbItem networkElbItem, ComputeItem computeItem, Set<EipPortItem> eipPortItems) {
    this.apply = apply;
    this.identifier = identifier;
    this.ispType = ispType;
    this.ipAddress = ipAddress;
    this.oldIp = oldIp;
    this.networkElbItem = networkElbItem;
    this.computeItem = computeItem;
    this.eipPortItems = eipPortItems;
}
@Column(name = "isp_type", nullable = false)
public Integer getIspType(){
    return this.ispType;
}


public void setComputeItem(ComputeItem computeItem){
    this.computeItem = computeItem;
}


public void setIspType(Integer ispType){
    this.ispType = ispType;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "elb_id")
public NetworkElbItem getNetworkElbItem(){
    return this.networkElbItem;
}


@Column(name = "ip_address", nullable = false, length = 45)
public String getIpAddress(){
    return this.ipAddress;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public void setNetworkDnsItemList(List<NetworkDnsItem> networkDnsItemList){
    this.networkDnsItemList = networkDnsItemList;
}


public void setNetworkElbItem(NetworkElbItem networkElbItem){
    this.networkElbItem = networkElbItem;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


public void setEipPortItems(Set<EipPortItem> eipPortItems){
    this.eipPortItems = eipPortItems;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


@JsonBackReference
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


@Column(name = "old_ip", length = 45)
public void setOldIp(String oldIp){
    this.oldIp = oldIp;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "compute_id")
public ComputeItem getComputeItem(){
    return this.computeItem;
}


public void setId(Integer id){
    this.id = id;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "networkEipItem")
public Set<EipPortItem> getEipPortItems(){
    return this.eipPortItems;
}


public void setApply(Apply apply){
    this.apply = apply;
}


@ManyToMany
@JoinTable(name = "dns_eip_item", joinColumns = { @JoinColumn(name = "eip_item_id") }, inverseJoinColumns = { @JoinColumn(name = "dns_item_id") })
// Fecth策略定义
@Fetch(FetchMode.SUBSELECT)
// 集合按id排序.
@OrderBy("id")
// 集合中对象id的缓存.
@NotFound(action = NotFoundAction.IGNORE)
public List<NetworkDnsItem> getNetworkDnsItemList(){
    return networkDnsItemList;
}


}
package com.sobey.cmop.mvc.entity;
 import java.util.List;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.google.common.collect.Lists;
@Entity
@Table(name = "network_dns_item", catalog = "cmop")
public class NetworkDnsItem {

 private  Integer id;

 private  Apply apply;

 private  String identifier;

 private  String domainName;

 private  Integer domainType;

 private  String cnameDomain;

 private  List<NetworkEipItem> networkEipItemList;

// Constructors
/**
 * default constructor
 */
public NetworkDnsItem() {
}/**
 * minimal constructor
 */
public NetworkDnsItem(Apply apply, String identifier, String domainName, Integer domainType) {
    this.apply = apply;
    this.identifier = identifier;
    this.domainName = domainName;
    this.domainType = domainType;
}/**
 * full constructor
 */
public NetworkDnsItem(Apply apply, String identifier, String domainName, Integer domainType, String cnameDomain) {
    this.apply = apply;
    this.identifier = identifier;
    this.domainName = domainName;
    this.domainType = domainType;
    this.cnameDomain = cnameDomain;
}
public void setDomainType(Integer domainType){
    this.domainType = domainType;
}


@ManyToMany
@JoinTable(name = "dns_eip_item", joinColumns = { @JoinColumn(name = "dns_item_id") }, inverseJoinColumns = { @JoinColumn(name = "eip_item_id") })
// Fecth策略定义
@Fetch(FetchMode.SUBSELECT)
// 集合按id排序.
@OrderBy("id")
// 集合中对象id的缓存.
@NotFound(action = NotFoundAction.IGNORE)
public List<NetworkEipItem> getNetworkEipItemList(){
    return networkEipItemList;
}


public void setNetworkEipItemList(List<NetworkEipItem> networkEipItemList){
    this.networkEipItemList = networkEipItemList;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public void setCnameDomain(String cnameDomain){
    this.cnameDomain = cnameDomain;
}


@Column(name = "domain_type", nullable = false)
public Integer getDomainType(){
    return this.domainType;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


public void setDomainName(String domainName){
    this.domainName = domainName;
}


@Column(name = "cname_domain", length = 45)
public String getCnameDomain(){
    return this.cnameDomain;
}


public String extractToString(List<NetworkEipItem> networkEipItems){
    StringBuilder sb = new StringBuilder();
    for (NetworkEipItem networkEipItem : networkEipItems) {
        sb.append(networkEipItem.getIdentifier()).append("(").append(networkEipItem.getIpAddress()).append(")").append(",");
    }
    String str = sb.toString();
    return str.length() > 0 ? str.substring(0, str.length() - 1) : "";
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "apply_id", nullable = false)
public Apply getApply(){
    return this.apply;
}


@Column(name = "identifier", nullable = false, length = 45)
public String getIdentifier(){
    return this.identifier;
}


public void setId(Integer id){
    this.id = id;
}


@Override
public String toString(){
    return ToStringBuilder.reflectionToString(this);
}


public void setApply(Apply apply){
    this.apply = apply;
}


@Column(name = "domain_name", nullable = false, length = 45)
public String getDomainName(){
    return this.domainName;
}


@Transient
public String getMountElbs(){
    return extractToString(networkEipItemList);
}


}
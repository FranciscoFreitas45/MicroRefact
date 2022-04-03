package com.sobey.cmop.mvc.DTO;
 import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class Resources {

 private  Integer id;

 private  User user;

 private  Integer serviceType;

 private  ServiceTag serviceTag;

 private  Integer serviceId;

 private  String serviceIdentifier;

 private  Date createTime;

 private  Integer status;

 private  String ipAddress;

 private  Integer usedby;

 private  String oldIp;

 private  Set<Change> changes;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

// Constructors
/**
 * default constructor
 */
public Resources() {
}/**
 * minimal constructor
 */
public Resources(User user, Integer serviceType, ServiceTag serviceTag, Integer serviceId, String serviceIdentifier, Date createTime, Integer status) {
    this.user = user;
    this.serviceType = serviceType;
    this.serviceTag = serviceTag;
    this.serviceId = serviceId;
    this.serviceIdentifier = serviceIdentifier;
    this.createTime = createTime;
    this.status = status;
}/**
 * full constructor
 */
public Resources(User user, Integer serviceType, ServiceTag serviceTag, Integer serviceId, String serviceIdentifier, Date createTime, Integer status, String ipAddress, Integer usedby, String oldIp, Set<Change> changes) {
    this.user = user;
    this.serviceType = serviceType;
    this.serviceTag = serviceTag;
    this.serviceId = serviceId;
    this.serviceIdentifier = serviceIdentifier;
    this.createTime = createTime;
    this.status = status;
    this.ipAddress = ipAddress;
    this.usedby = usedby;
    this.oldIp = oldIp;
    this.changes = changes;
}
@Column(name = "service_id", nullable = false)
public Integer getServiceId(){
    return this.serviceId;
}


@Column(name = "create_time", nullable = false, length = 19)
public Date getCreateTime(){
    return this.createTime;
}


@Column(name = "ip_address", length = 45)
public String getIpAddress(){
    return ipAddress;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
public User getUser(){
    return user;
}


@Column(name = "usedby")
public Integer getUsedby(){
    return this.usedby;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "service_tag_id")
public ServiceTag getServiceTag(){
    return this.serviceTag;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "status", nullable = false)
public Integer getStatus(){
    return this.status;
}


@Column(name = "service_identifier", length = 45)
public String getServiceIdentifier(){
    return this.serviceIdentifier;
}


@Column(name = "old_ip", length = 45)
public String getOldIp(){
    return oldIp;
}


@JsonIgnore
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "resources")
public Set<Change> getChanges(){
    return changes;
}


@Column(name = "service_type", nullable = false)
public Integer getServiceType(){
    return serviceType;
}


public void setStatus(Integer status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIpAddress"))

.queryParam("ipAddress",ipAddress)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOldIp(String oldIp){
    this.oldIp = oldIp;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOldIp"))

.queryParam("oldIp",oldIp)
;
restTemplate.put(builder.toUriString(),null);
}


}
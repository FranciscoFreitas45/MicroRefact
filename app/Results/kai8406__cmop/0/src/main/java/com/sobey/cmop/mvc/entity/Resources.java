package com.sobey.cmop.mvc.entity;
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
@Entity
@Table(name = "resources", catalog = "cmop")
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


public void setServiceId(Integer serviceId){
    this.serviceId = serviceId;
}


public void setUsedby(Integer usedby){
    this.usedby = usedby;
}


@Column(name = "create_time", nullable = false, length = 19)
public Date getCreateTime(){
    return this.createTime;
}


@Column(name = "ip_address", length = 45)
public String getIpAddress(){
    return ipAddress;
}


public void setServiceType(Integer serviceType){
    this.serviceType = serviceType;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
public User getUser(){
    return user;
}


public void setServiceIdentifier(String serviceIdentifier){
    this.serviceIdentifier = serviceIdentifier;
}


@Column(name = "usedby")
public Integer getUsedby(){
    return this.usedby;
}


public void setChanges(Set<Change> changes){
    this.changes = changes;
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


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Column(name = "status", nullable = false)
public Integer getStatus(){
    return this.status;
}


@Column(name = "service_identifier", length = 45)
public String getServiceIdentifier(){
    return this.serviceIdentifier;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


public void setStatus(Integer status){
    this.status = status;
}


public void setServiceTag(ServiceTag serviceTag){
    this.serviceTag = serviceTag;
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


public void setOldIp(String oldIp){
    this.oldIp = oldIp;
}


public void setId(Integer id){
    this.id = id;
}


public void setUser(User user){
    this.user = user;
}


@Column(name = "service_type", nullable = false)
public Integer getServiceType(){
    return serviceType;
}


}
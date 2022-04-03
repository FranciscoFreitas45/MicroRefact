package com.sobey.cmop.mvc.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
public class IpPool {

 private  Integer id;

 private  Integer poolType;

 private  Vlan vlan;

 private  String ipAddress;

 private  Integer status;

 private  HostServer hostServer;

 private  Date createTime;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";

// Constructors
/**
 * default constructor
 */
public IpPool() {
}/**
 * minimal constructor
 */
public IpPool(Integer poolType, Vlan vlan, String ipAddress, Integer status, Date createTime) {
    this.poolType = poolType;
    this.vlan = vlan;
    this.ipAddress = ipAddress;
    this.status = status;
    this.createTime = createTime;
}/**
 * full constructor
 */
public IpPool(Integer poolType, Vlan vlan, String ipAddress, Integer status, HostServer hostServer, Date createTime) {
    this.poolType = poolType;
    this.vlan = vlan;
    this.ipAddress = ipAddress;
    this.status = status;
    this.hostServer = hostServer;
    this.createTime = createTime;
}
@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "host_server_id")
public HostServer getHostServer(){
    return this.hostServer;
}


@Column(name = "create_time", nullable = false, length = 19)
public Date getCreateTime(){
    return this.createTime;
}


@Column(name = "ip_address", nullable = false, length = 45)
public String getIpAddress(){
    return this.ipAddress;
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


@Column(name = "pool_type", nullable = false)
public Integer getPoolType(){
    return this.poolType;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "vlan_id")
public Vlan getVlan(){
    return this.vlan;
}


public void setStatus(Integer status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setHostServer(HostServer hostServer){
    this.hostServer = hostServer;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setHostServer"))

.queryParam("hostServer",hostServer)
;
restTemplate.put(builder.toUriString(),null);
}


}
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
@Entity
@Table(name = "host_server", catalog = "cmop")
public class HostServer {

 private  Integer id;

 private  ServerModel serverModel;

 private  Integer serverType;

 private  Integer poolType;

 private  String displayName;

 private  String rack;

 private  String rackAlias;

 private  String site;

 private  String height;

 private  String alias;

 private  String locationAlias;

 private  String ipAddress;

 private  Date createTime;

 private  String description;

 private  String switchName;

 private  String switchAlias;

 private  String switchSite;

 private  String managementMac;

 private  Set<IpPool> ipPools;

 private  Set<Nic> nics;

// Constructors
/**
 * default constructor
 */
public HostServer() {
}/**
 * minimal constructor
 */
public HostServer(Integer serverType, Integer poolType, String displayName, Date createTime) {
    this.serverType = serverType;
    this.poolType = poolType;
    this.displayName = displayName;
    this.createTime = createTime;
}/**
 * full constructor
 */
public HostServer(Integer serverType, Integer poolType, String displayName, String rack, String rackAlias, String site, String height, String alias, String locationAlias, Set<IpPool> ipPools, Set<Nic> nics, String ipAddress, Date createTime, String description, String managementMac, ServerModel serverModel) {
    this.serverType = serverType;
    this.poolType = poolType;
    this.displayName = displayName;
    this.rack = rack;
    this.managementMac = managementMac;
    this.rackAlias = rackAlias;
    this.site = site;
    this.height = height;
    this.alias = alias;
    this.locationAlias = locationAlias;
    this.ipAddress = ipAddress;
    this.ipPools = ipPools;
    this.nics = nics;
    this.createTime = createTime;
    this.description = description;
    this.serverModel = serverModel;
}
@Column(name = "create_time", nullable = false, length = 19)
public Date getCreateTime(){
    return this.createTime;
}


@Column(name = "management_mac", length = 45)
public String getManagementMac(){
    return managementMac;
}


public void setRackAlias(String rackAlias){
    this.rackAlias = rackAlias;
}


@Column(name = "ip_address", length = 45)
public String getIpAddress(){
    return this.ipAddress;
}


public void setSwitchAlias(String switchAlias){
    this.switchAlias = switchAlias;
}


@Column(name = "rack", length = 45)
public String getRack(){
    return rack;
}


public void setIpPools(Set<IpPool> ipPools){
    this.ipPools = ipPools;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


public void setDescription(String description){
    this.description = description;
}


public void setDisplayName(String displayName){
    this.displayName = displayName;
}


@Column(name = "description", length = 100)
public String getDescription(){
    return description;
}


@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "hostServer")
public Set<IpPool> getIpPools(){
    return this.ipPools;
}


public void setSwitchName(String switchName){
    this.switchName = switchName;
}


public void setServerType(Integer serverType){
    this.serverType = serverType;
}


@Column(name = "switch_alias", length = 45)
public String getSwitchAlias(){
    return switchAlias;
}


@Column(name = "height", length = 45)
public String getHeight(){
    return height;
}


public void setPoolType(Integer poolType){
    this.poolType = poolType;
}


public void setRack(String rack){
    this.rack = rack;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "pool_type", nullable = false)
public Integer getPoolType(){
    return this.poolType;
}


public void setSite(String site){
    this.site = site;
}


@Column(name = "switch_site", length = 45)
public String getSwitchSite(){
    return switchSite;
}


@Column(name = "rack_alias", length = 45)
public String getRackAlias(){
    return rackAlias;
}


@Column(name = "switch_name", length = 45)
public String getSwitchName(){
    return switchName;
}


public void setNics(Set<Nic> nics){
    this.nics = nics;
}


public void setServerModel(ServerModel serverModel){
    this.serverModel = serverModel;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hostServer")
public Set<Nic> getNics(){
    return nics;
}


public void setManagementMac(String managementMac){
    this.managementMac = managementMac;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setLocationAlias(String locationAlias){
    this.locationAlias = locationAlias;
}


public void setSwitchSite(String switchSite){
    this.switchSite = switchSite;
}


public void setHeight(String height){
    this.height = height;
}


public void setIpAddress(String ipAddress){
    this.ipAddress = ipAddress;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "server_model_id")
public ServerModel getServerModel(){
    return serverModel;
}


@Column(name = "display_name", nullable = false, length = 45)
public String getDisplayName(){
    return this.displayName;
}


@Column(name = "server_type", nullable = false)
public Integer getServerType(){
    return this.serverType;
}


@Column(name = "site", length = 45)
public String getSite(){
    return site;
}


@Column(name = "location_alias", length = 45)
public String getLocationAlias(){
    return this.locationAlias;
}


public void setAlias(String alias){
    this.alias = alias;
}


@Column(name = "alias", length = 45)
public String getAlias(){
    return this.alias;
}


}
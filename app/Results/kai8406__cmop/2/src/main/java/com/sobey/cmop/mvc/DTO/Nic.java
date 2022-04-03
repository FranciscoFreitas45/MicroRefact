package com.sobey.cmop.mvc.DTO;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
public class Nic {

 private  Integer id;

 private  HostServer hostServer;

 private  String mac;

 private  String ipAddress;

 private  String alias;

 private  String site;

// Constructors
/**
 * default constructor
 */
public Nic() {
}/**
 * full constructor
 */
public Nic(HostServer hostServer, String mac, String ipAddress, String alias, String site) {
    this.hostServer = hostServer;
    this.mac = mac;
    this.ipAddress = ipAddress;
    this.alias = alias;
    this.site = site;
}
@Column(name = "mac", length = 45)
public String getMac(){
    return mac;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "host_server_id", nullable = false)
public HostServer getHostServer(){
    return hostServer;
}


@Column(name = "ip_address", length = 45)
public String getIpAddress(){
    return ipAddress;
}


@Column(name = "site", length = 45)
public String getSite(){
    return site;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "alias", length = 45)
public String getAlias(){
    return alias;
}


}
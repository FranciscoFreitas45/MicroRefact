package com.sobey.cmop.mvc.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "eip_port_item", catalog = "cmop")
public class EipPortItem {

 private  Integer id;

 private  NetworkEipItem networkEipItem;

 private  String protocol;

 private  String sourcePort;

 private  String targetPort;

// Constructors
/**
 * default constructor
 */
public EipPortItem() {
}/**
 * full constructor
 */
public EipPortItem(NetworkEipItem networkEipItem, String protocol, String sourcePort, String targetPort) {
    this.networkEipItem = networkEipItem;
    this.protocol = protocol;
    this.sourcePort = sourcePort;
    this.targetPort = targetPort;
}
public void setTargetPort(String targetPort){
    this.targetPort = targetPort;
}


public void setNetworkEipItem(NetworkEipItem networkEipItem){
    this.networkEipItem = networkEipItem;
}


public void setProtocol(String protocol){
    this.protocol = protocol;
}


@Column(name = "target_port", nullable = false, length = 45)
public String getTargetPort(){
    return this.targetPort;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "protocol", nullable = false, length = 45)
public String getProtocol(){
    return this.protocol;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "eip_id", nullable = false)
public NetworkEipItem getNetworkEipItem(){
    return this.networkEipItem;
}


@Column(name = "source_port", nullable = false, length = 45)
public String getSourcePort(){
    return this.sourcePort;
}


public void setSourcePort(String sourcePort){
    this.sourcePort = sourcePort;
}


}
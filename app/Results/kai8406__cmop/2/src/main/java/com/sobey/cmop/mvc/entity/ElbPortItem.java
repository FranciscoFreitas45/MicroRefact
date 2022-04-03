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
@Table(name = "elb_port_item", catalog = "cmop")
public class ElbPortItem {

 private  Integer id;

 private  NetworkElbItem networkElbItem;

 private  String protocol;

 private  String sourcePort;

 private  String targetPort;

// Constructors
/**
 * default constructor
 */
public ElbPortItem() {
}/**
 * full constructor
 */
public ElbPortItem(NetworkElbItem networkElbItem, String protocol, String sourcePort, String targetPort) {
    this.networkElbItem = networkElbItem;
    this.protocol = protocol;
    this.sourcePort = sourcePort;
    this.targetPort = targetPort;
}
public void setTargetPort(String targetPort){
    this.targetPort = targetPort;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "elb_id", nullable = false)
public NetworkElbItem getNetworkElbItem(){
    return this.networkElbItem;
}


public void setProtocol(String protocol){
    this.protocol = protocol;
}


public void setNetworkElbItem(NetworkElbItem networkElbItem){
    this.networkElbItem = networkElbItem;
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


@Column(name = "source_port", nullable = false, length = 45)
public String getSourcePort(){
    return this.sourcePort;
}


public void setSourcePort(String sourcePort){
    this.sourcePort = sourcePort;
}


}
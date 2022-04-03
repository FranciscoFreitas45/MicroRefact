package com.sobey.cmop.mvc.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "elb_id", nullable = false)
public NetworkElbItem getNetworkElbItem(){
    return this.networkElbItem;
}


@Column(name = "target_port", nullable = false, length = 45)
public String getTargetPort(){
    return this.targetPort;
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


}
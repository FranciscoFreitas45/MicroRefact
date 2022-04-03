package com.sobey.cmop.mvc.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "monitor_elb", catalog = "cmop")
public class MonitorElb {

 private  Integer id;

 private  Apply apply;

 private  NetworkElbItem networkElbItem;

 private  String identifier;

// Constructors
/**
 * default constructor
 */
public MonitorElb() {
}/**
 * full constructor
 */
public MonitorElb(Apply apply, NetworkElbItem networkElbItem, String identifier) {
    this.apply = apply;
    this.networkElbItem = networkElbItem;
    this.identifier = identifier;
}
@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "apply_id", nullable = false)
public Apply getApply(){
    return this.apply;
}


@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "elb_id")
public NetworkElbItem getNetworkElbItem(){
    return networkElbItem;
}


@Column(name = "identifier", nullable = false, length = 45)
public String getIdentifier(){
    return this.identifier;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public void setNetworkElbItem(NetworkElbItem networkElbItem){
    this.networkElbItem = networkElbItem;
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


public void setApply(Apply apply){
    this.apply = apply;
}


}
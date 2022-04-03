package com.sobey.cmop.mvc.entity;
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
@Entity
@Table(name = "audit_flow", catalog = "cmop")
public class AuditFlow {

 private  Integer id;

 private  User user;

 private  Integer flowType;

 private  Integer auditOrder;

 private  Boolean isFinal;

 private  Set<Audit> audits;

 private  Set<Apply> applys;

 private  Set<ServiceTag> serviceTags;

// Constructors
/**
 * default constructor
 */
public AuditFlow() {
}/**
 * minimal constructor
 */
public AuditFlow(User user, Integer flowType, Integer auditOrder, Boolean isFinal) {
    this.user = user;
    this.flowType = flowType;
    this.auditOrder = auditOrder;
    this.isFinal = isFinal;
}/**
 * full constructor
 */
public AuditFlow(User user, Integer flowType, Integer auditOrder, Boolean isFinal, Set<Audit> audits, Set<Apply> applys, Set<ServiceTag> serviceTags) {
    this.user = user;
    this.flowType = flowType;
    this.auditOrder = auditOrder;
    this.isFinal = isFinal;
    this.audits = audits;
    this.applys = applys;
    this.serviceTags = serviceTags;
}
public void setFlowType(Integer flowType){
    this.flowType = flowType;
}


public void setServiceTags(Set<ServiceTag> serviceTags){
    this.serviceTags = serviceTags;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
public User getUser(){
    return this.user;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auditFlow")
public Set<Apply> getApplys(){
    return this.applys;
}


public void setAudits(Set<Audit> audits){
    this.audits = audits;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "flow_type", nullable = false)
public Integer getFlowType(){
    return this.flowType;
}


public void setAuditOrder(Integer auditOrder){
    this.auditOrder = auditOrder;
}


@Column(name = "is_final", nullable = false)
public Boolean getIsFinal(){
    return this.isFinal;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auditFlow")
public Set<Audit> getAudits(){
    return this.audits;
}


@Column(name = "audit_order", nullable = false)
public Integer getAuditOrder(){
    return this.auditOrder;
}


public void setApplys(Set<Apply> applys){
    this.applys = applys;
}


public void setId(Integer id){
    this.id = id;
}


public void setUser(User user){
    this.user = user;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auditFlow")
public Set<ServiceTag> getServiceTags(){
    return this.serviceTags;
}


public void setIsFinal(Boolean isFinal){
    this.isFinal = isFinal;
}


}
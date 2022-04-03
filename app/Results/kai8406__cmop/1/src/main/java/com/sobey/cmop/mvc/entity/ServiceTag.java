package com.sobey.cmop.mvc.entity;
 import javax.persistence.GenerationType.IDENTITY;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "service_tag", catalog = "cmop")
public class ServiceTag {

 private  Integer id;

 private  String identifier;

 private  User user;

 private  String name;

 private  Integer priority;

 private  String description;

 private  String serviceStart;

 private  String serviceEnd;

 private  Date createTime;

 private  Integer status;

 private  AuditFlow auditFlow;

 private  String domain;

 private  String contact;

 private  String phonenum;

 private  Integer redmineIssueId;

 private  Set<Audit> audits;

// Constructors
/**
 * default constructor
 */
public ServiceTag() {
}/**
 * minimal constructor
 */
public ServiceTag(String identifier, User user, String name, Integer priority, String description, String serviceStart, String serviceEnd, Date createTime, Integer status) {
    this.identifier = identifier;
    this.user = user;
    this.name = name;
    this.priority = priority;
    this.description = description;
    this.serviceStart = serviceStart;
    this.serviceEnd = serviceEnd;
    this.createTime = createTime;
    this.status = status;
}/**
 * full constructor
 */
public ServiceTag(String identifier, User user, String name, Integer priority, String description, String serviceStart, String serviceEnd, Date createTime, Integer status, AuditFlow auditFlow, String domain, String contact, String phonenum, Integer redmineIssueId, Set<Audit> audits) {
    this.identifier = identifier;
    this.user = user;
    this.name = name;
    this.priority = priority;
    this.description = description;
    this.serviceStart = serviceStart;
    this.serviceEnd = serviceEnd;
    this.createTime = createTime;
    this.status = status;
    this.auditFlow = auditFlow;
    this.domain = domain;
    this.contact = contact;
    this.phonenum = phonenum;
    this.redmineIssueId = redmineIssueId;
    this.audits = audits;
}
public void setName(String name){
    this.name = name;
}


@Column(name = "create_time", nullable = false, length = 19)
public Date getCreateTime(){
    return createTime;
}


@Column(name = "name", nullable = false, length = 45)
public String getName(){
    return this.name;
}


@Column(name = "phonenum")
public String getPhonenum(){
    return phonenum;
}


public void setIdentifier(String identifier){
    this.identifier = identifier;
}


public void setDomain(String domain){
    this.domain = domain;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
public User getUser(){
    return this.user;
}


public void setRedmineIssueId(Integer redmineIssueId){
    this.redmineIssueId = redmineIssueId;
}


public void setAudits(Set<Audit> audits){
    this.audits = audits;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


public void setDescription(String description){
    this.description = description;
}


@Column(name = "status", nullable = false)
public Integer getStatus(){
    return status;
}


@Column(name = "description", nullable = false, length = 500)
public String getDescription(){
    return description;
}


@JsonIgnore
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "serviceTag")
@OrderBy("createTime ASC")
public Set<Audit> getAudits(){
    return this.audits;
}


@Column(name = "identifier", nullable = false, length = 45)
public String getIdentifier(){
    return identifier;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "audit_flow_id")
public AuditFlow getAuditFlow(){
    return this.auditFlow;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "redmine_issue_id")
public Integer getRedmineIssueId(){
    return redmineIssueId;
}


public void setUser(User user){
    this.user = user;
}


@Column(name = "service_start", nullable = false, length = 10)
public String getServiceStart(){
    return serviceStart;
}


public void setAuditFlow(AuditFlow auditFlow){
    this.auditFlow = auditFlow;
}


public void setPhonenum(String phonenum){
    this.phonenum = phonenum;
}


public void setServiceStart(String serviceStart){
    this.serviceStart = serviceStart;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setContact(String contact){
    this.contact = contact;
}


@Column(name = "service_end", nullable = false, length = 10)
public String getServiceEnd(){
    return serviceEnd;
}


public void setStatus(Integer status){
    this.status = status;
}


@Column(name = "contact")
public String getContact(){
    return contact;
}


@Column(name = "domain")
public String getDomain(){
    return domain;
}


public Integer getPriority(){
    return priority;
}


@Column(name = "priority")
public void setPriority(Integer priority){
    this.priority = priority;
}


public void setServiceEnd(String serviceEnd){
    this.serviceEnd = serviceEnd;
}


}
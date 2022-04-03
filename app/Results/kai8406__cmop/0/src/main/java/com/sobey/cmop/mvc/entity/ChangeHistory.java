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
import javax.persistence.Table;
@Entity
@Table(name = "change_history", catalog = "cmop")
public class ChangeHistory {

 private  Integer id;

 private  Audit audit;

 private  String resourcesInfo;

 private  Integer subResourcesId;

 private  Date changeTime;

 private  String description;

 private  Set<ChangeItemHistory> changeItemHistories;

// Constructors
/**
 * default constructor
 */
public ChangeHistory() {
}/**
 * minimal constructor
 */
public ChangeHistory(Audit audit, String resourcesInfo, Date changeTime) {
    this.audit = audit;
    this.resourcesInfo = resourcesInfo;
    this.changeTime = changeTime;
}/**
 * full constructor
 */
public ChangeHistory(Audit audit, String resourcesInfo, Integer subResourcesId, Date changeTime, String description, Set<ChangeItemHistory> changeItemHistories) {
    this.audit = audit;
    this.resourcesInfo = resourcesInfo;
    this.subResourcesId = subResourcesId;
    this.changeTime = changeTime;
    this.description = description;
    this.changeItemHistories = changeItemHistories;
}
public void setAudit(Audit audit){
    this.audit = audit;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "changeHistory")
public Set<ChangeItemHistory> getChangeItemHistories(){
    return changeItemHistories;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "sub_resources_id")
public Integer getSubResourcesId(){
    return this.subResourcesId;
}


public void setDescription(String description){
    this.description = description;
}


public void setChangeTime(Date changeTime){
    this.changeTime = changeTime;
}


@Column(name = "description", length = 200)
public String getDescription(){
    return this.description;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "audit_id", nullable = false)
public Audit getAudit(){
    return this.audit;
}


public void setSubResourcesId(Integer subResourcesId){
    this.subResourcesId = subResourcesId;
}


public void setResourcesInfo(String resourcesInfo){
    this.resourcesInfo = resourcesInfo;
}


@Column(name = "resources_info", nullable = false, length = 100)
public String getResourcesInfo(){
    return resourcesInfo;
}


public void setChangeItemHistories(Set<ChangeItemHistory> changeItemHistories){
    this.changeItemHistories = changeItemHistories;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "change_time", nullable = false, length = 19)
public Date getChangeTime(){
    return this.changeTime;
}


}
package com.sobey.cmop.mvc.entity;
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
@Entity
@Table(name = "failure", catalog = "cmop")
public class Failure {

 private  Integer id;

 private  User user;

 private  String title;

 private  Integer level;

 private  String description;

 private  Integer assignee;

 private  Integer faultType;

 private  String relatedId;

 private  Date createTime;

 private  RedmineIssue redmineIssue;

// Constructors
/**
 * default constructor
 */
public Failure() {
}/**
 * full constructor
 */
public Failure(User user, String title, Integer level, String description, Integer assignee, Integer faultType, String relatedId, Date createTime, RedmineIssue redmineIssue) {
    this.user = user;
    this.title = title;
    this.level = level;
    this.description = description;
    this.assignee = assignee;
    this.faultType = faultType;
    this.relatedId = relatedId;
    this.createTime = createTime;
    this.redmineIssue = redmineIssue;
}
@Column(name = "create_time", nullable = false, length = 19)
public Date getCreateTime(){
    return this.createTime;
}


@Column(name = "related_id", nullable = false, length = 100)
public String getRelatedId(){
    return this.relatedId;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
public User getUser(){
    return this.user;
}


public void setTitle(String title){
    this.title = title;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setDescription(String description){
    this.description = description;
}


@Column(name = "assignee", nullable = false)
public Integer getAssignee(){
    return this.assignee;
}


@Column(name = "description", nullable = false, length = 500)
public String getDescription(){
    return this.description;
}


public void setRelatedId(String relatedId){
    this.relatedId = relatedId;
}


public void setLevel(Integer level){
    this.level = level;
}


@Column(name = "level", nullable = false)
public Integer getLevel(){
    return this.level;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "redmine_issue_id", nullable = false)
public RedmineIssue getRedmineIssue(){
    return this.redmineIssue;
}


@Column(name = "title", nullable = false, length = 45)
public String getTitle(){
    return this.title;
}


@Column(name = "fault_type", nullable = false)
public Integer getFaultType(){
    return this.faultType;
}


public void setRedmineIssue(RedmineIssue redmineIssue){
    this.redmineIssue = redmineIssue;
}


public void setAssignee(Integer assignee){
    this.assignee = assignee;
}


public void setId(Integer id){
    this.id = id;
}


public void setUser(User user){
    this.user = user;
}


public void setFaultType(Integer faultType){
    this.faultType = faultType;
}


}
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
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "change", catalog = "cmop")
public class Change {

 private  Integer id;

 private  Resources resources;

 private  Integer subResourcesId;

 private  User user;

 private  Date changeTime;

 private  String description;

 private  Set<ChangeItem> changeItems;

// Constructors
/**
 * default constructor
 */
public Change() {
}/**
 * minimal constructor
 */
public Change(Resources resources, User user, Date changeTime) {
    this.resources = resources;
    this.user = user;
    this.changeTime = changeTime;
}/**
 * full constructor
 */
public Change(Resources resources, Integer subResourcesId, User user, Date changeTime, String description, Set<ChangeItem> changeItems) {
    this.resources = resources;
    this.subResourcesId = subResourcesId;
    this.user = user;
    this.changeTime = changeTime;
    this.description = description;
    this.changeItems = changeItems;
}
public void setChangeItems(Set<ChangeItem> changeItems){
    this.changeItems = changeItems;
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
public User getUser(){
    return this.user;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "sub_resources_id")
public Integer getSubResourcesId(){
    return subResourcesId;
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


public void setResources(Resources resources){
    this.resources = resources;
}


public void setSubResourcesId(Integer subResourcesId){
    this.subResourcesId = subResourcesId;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "resources_id", nullable = false)
public Resources getResources(){
    return this.resources;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "change")
public Set<ChangeItem> getChangeItems(){
    return changeItems;
}


public void setId(Integer id){
    this.id = id;
}


public void setUser(User user){
    this.user = user;
}


@Column(name = "change_time", nullable = false, length = 19)
public Date getChangeTime(){
    return this.changeTime;
}


}
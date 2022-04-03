package com.sobey.cmop.mvc.DTO;
 import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
public class NetworkEsgItem {

 private  Integer id;

 private  User user;

 private  String identifier;

 private  String description;

 private  Boolean share;

 private  Set<EsgRuleItem> esgRuleItems;

 private  List<ComputeItem> computeItemList;

// Constructors
/**
 * default constructor
 */
public NetworkEsgItem() {
}/**
 * minimal constructor
 */
public NetworkEsgItem(User user, String identifier, String description, Boolean share) {
    this.user = user;
    this.identifier = identifier;
    this.description = description;
    this.share = share;
}/**
 * full constructor
 */
public NetworkEsgItem(User user, String identifier, String description, Boolean share, Set<EsgRuleItem> esgRuleItems) {
    this.user = user;
    this.identifier = identifier;
    this.description = description;
    this.share = share;
    this.esgRuleItems = esgRuleItems;
}
@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
public User getUser(){
    return this.user;
}


@ManyToMany
@JoinTable(name = "compute_esg_item", joinColumns = { @JoinColumn(name = "esg_item_id") }, inverseJoinColumns = { @JoinColumn(name = "compute_item_id") })
// Fecth策略定义
@Fetch(FetchMode.SUBSELECT)
// 集合按id排序.
@OrderBy("id")
// 集合中对象id的缓存.
@NotFound(action = NotFoundAction.IGNORE)
@JsonIgnore
public List<ComputeItem> getComputeItemList(){
    return computeItemList;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "share")
public Boolean getShare(){
    return share;
}


@Column(name = "description", nullable = false, length = 45)
public String getDescription(){
    return this.description;
}


@Column(name = "identifier", nullable = false, length = 45)
public String getIdentifier(){
    return this.identifier;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "networkEsgItem")
public Set<EsgRuleItem> getEsgRuleItems(){
    return this.esgRuleItems;
}


}
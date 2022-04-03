package com.easyshopping.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class Area extends OrderEntity{

 private  long serialVersionUID;

 private  String TREE_PATH_SEPARATOR;

 private  String name;

 private  String fullName;

 private  String treePath;

 private  Area parent;

 private  Set<Area> children;

 private  Set<Member> members;

 private  Set<Receiver> receivers;

 private  Set<Order> orders;

 private  Set<DeliveryCenter> deliveryCenters;


@ManyToOne(fetch = FetchType.LAZY)
public Area getParent(){
    return parent;
}


@NotEmpty
@Length(max = 100)
@Column(nullable = false, length = 100)
public String getName(){
    return name;
}


@OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
public Set<Order> getOrders(){
    return orders;
}


@Column(nullable = false, updatable = false)
public String getTreePath(){
    return treePath;
}


@OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
public Set<Receiver> getReceivers(){
    return receivers;
}


@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("order asc")
public Set<Area> getChildren(){
    return children;
}


@OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
public Set<DeliveryCenter> getDeliveryCenters(){
    return deliveryCenters;
}


@Column(nullable = false, length = 500)
public String getFullName(){
    return fullName;
}


@OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
public Set<Member> getMembers(){
    return members;
}


}
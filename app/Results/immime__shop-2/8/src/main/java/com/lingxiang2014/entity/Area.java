package com.lingxiang2014.entity;
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
@Entity
@Table(name = "lx_area")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_area_sequence")
public class Area extends OrderEntity{

 private  long serialVersionUID;

 private  String TREE_PATH_SEPARATOR;

 private  String name;

 private  String fullName;

 private  String treePath;

 private  Area parent;

 private  Set<Area> children;

 private  Set<Member> members;


public void setName(String name){
    this.name = name;
}


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


@PrePersist
public void prePersist(){
    Area parent = getParent();
    if (parent != null) {
        setFullName(parent.getFullName() + getName());
        setTreePath(parent.getTreePath() + parent.getId() + TREE_PATH_SEPARATOR);
    } else {
        setFullName(getName());
        setTreePath(TREE_PATH_SEPARATOR);
    }
}


public void setTreePath(String treePath){
    this.treePath = treePath;
}


@Column(nullable = false, updatable = false)
public String getTreePath(){
    return treePath;
}


@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("order asc")
public Set<Area> getChildren(){
    return children;
}


public void setFullName(String fullName){
    this.fullName = fullName;
}


public void setMembers(Set<Member> members){
    this.members = members;
}


@Override
public String toString(){
    return getFullName();
}


public void setParent(Area parent){
    this.parent = parent;
}


@PreRemove
public void preRemove(){
    Set<Member> members = getMembers();
    if (members != null) {
        for (Member member : members) {
            member.setArea(null);
        }
    }
}


@Column(nullable = false, length = 500)
public String getFullName(){
    return fullName;
}


public void setChildren(Set<Area> children){
    this.children = children;
}


@PreUpdate
public void preUpdate(){
    Area parent = getParent();
    if (parent != null) {
        setFullName(parent.getFullName() + getName());
    } else {
        setFullName(getName());
    }
}


@OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
public Set<Member> getMembers(){
    return members;
}


}
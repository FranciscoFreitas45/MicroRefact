package com.vino.scaffold.shiro.entity;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.vino.scaffold.entity.base.BaseEntity;
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity<Long>{

@Column(name = "name", length = 100)
 private  String name;

@Column(name = "description", length = 200)
 private  String description;

@Column(name = "available")
 private  Boolean available;

@ManyToMany(targetEntity = Resource.class)
@JoinTable(name = "t_role_resource", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
 private  Set<Resource> resources;

// ˫���ϵ�Ľ��նˣ�mappedByָ���ǹ��������е�ĳ������
@ManyToMany(targetEntity = User.class, mappedBy = "roles")
 private  Set<User> users;

public Role() {
}public Role(String mark, String name) {
    // this.mark = mark;
    this.name = name;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setUsers(Set<User> users){
    this.users = users;
}


public Set<Resource> getResources(){
    return resources;
}


public void setAvailable(Boolean available){
    this.available = available;
}


public Set<User> getUsers(){
    return users;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public Boolean getAvailable(){
    return available;
}


public void setResources(Set<Resource> resources){
    this.resources = resources;
}


}
package com.easyshopping.entity;
 import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_role")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_role_sequence")
public class Role extends BaseEntity{

 private  long serialVersionUID;

 private  String name;

 private  Boolean isSystem;

 private  String description;

 private  List<String> authorities;

 private  Set<Admin> admins;


public void setName(String name){
    this.name = name;
}


public void setAdmins(Set<Admin> admins){
    this.admins = admins;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getName(){
    return name;
}


public void setAuthorities(List<String> authorities){
    this.authorities = authorities;
}


public void setIsSystem(Boolean isSystem){
    this.isSystem = isSystem;
}


public void setDescription(String description){
    this.description = description;
}


@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
public Set<Admin> getAdmins(){
    return admins;
}


@Column(nullable = false, updatable = false)
public Boolean getIsSystem(){
    return isSystem;
}


@Length(max = 200)
public String getDescription(){
    return description;
}


@ElementCollection
@CollectionTable(name = "xx_role_authority")
public List<String> getAuthorities(){
    return authorities;
}


}
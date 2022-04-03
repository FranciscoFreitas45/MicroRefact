package com.evolvingreality.onleave.model;
 import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name = "SECURITY_GROUP")
public class SecurityGroup extends AbstractAuditingEntity{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@NotNull
@Size(max = 100)
@Column(name = "group_name")
 private  String groupName;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "securityGroup")
 private  List<SecurityGroupAuthority> authorities;


public String getGroupName(){
    return groupName;
}


public void setGroupName(String groupName){
    this.groupName = groupName;
}


public void setAuthorities(List<SecurityGroupAuthority> authorities){
    this.authorities = authorities;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public List<SecurityGroupAuthority> getAuthorities(){
    return authorities;
}


}
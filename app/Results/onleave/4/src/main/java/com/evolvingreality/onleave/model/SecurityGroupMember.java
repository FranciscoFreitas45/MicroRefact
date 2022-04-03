package com.evolvingreality.onleave.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.evolvingreality.onleave.Request.UserRequest;
import com.evolvingreality.onleave.Request.Impl.UserRequestImpl;
import com.evolvingreality.onleave.DTO.User;
@Entity
@Table(name = "SECURITY_GROUP_MEMBER")
public class SecurityGroupMember extends AbstractAuditingEntity{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Transient
 private  User user;

@JsonIgnore
@NotNull
@ManyToOne(targetEntity = SecurityGroup.class)
@JoinColumn(name = "security_group_id", referencedColumnName = "id")
 private  SecurityGroup securityGroup;

@Column(name = "id")
 private Long id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public SecurityGroupMember() {
}public SecurityGroupMember(User user, SecurityGroup securityGroup) {
    this.user = user;
    this.securityGroup = securityGroup;
}
public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
}


public void setSecurityGroup(SecurityGroup securityGroup){
    this.securityGroup = securityGroup;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public SecurityGroup getSecurityGroup(){
    return securityGroup;
}


public void setUser(User user){
 userrequest.setUser(user,this.id);
}



}
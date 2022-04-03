package com.evolvingreality.onleave.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class SecurityGroupMember extends AbstractAuditingEntity{

 private  Long id;

 private  User user;

 private  SecurityGroup securityGroup;

public SecurityGroupMember() {
}public SecurityGroupMember(User user, SecurityGroup securityGroup) {
    this.user = user;
    this.securityGroup = securityGroup;
}
public User getUser(){
    return user;
}


public Long getId(){
    return id;
}


public SecurityGroup getSecurityGroup(){
    return securityGroup;
}


}
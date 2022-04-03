package com.evolvingreality.onleave.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import javax.persistence;
import org.hibernate.annotations.Type;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.joda.time.DateTime;
import com.evolvingreality.onleave.Request.SecurityGroupMemberRequest;
import com.evolvingreality.onleave.Request.Impl.SecurityGroupMemberRequestImpl;
import com.evolvingreality.onleave.DTO.SecurityGroupMember;
public class User extends AbstractAuditingEntityimplements Serializable{

 private  Long id;

 private  String password;

 private  String firstName;

 private  String lastName;

 private  String email;

 private  boolean activated;

 private  String langKey;

 private  String activationKey;

 private  String resetKey;

 private  DateTime resetDate;

 private  List<SecurityGroupMember> groupMembers;


public boolean getActivated(){
    return activated;
}


public Long getId(){
    return id;
}


public String getActivationKey(){
    return activationKey;
}


public DateTime getResetDate(){
    return resetDate;
}


public String getLangKey(){
    return langKey;
}


public String getLastName(){
    return lastName;
}


public String getPassword(){
    return password;
}


public String getEmail(){
    return email;
}


public String getFirstName(){
    return firstName;
}


public List<SecurityGroupMember> getGroupMembers(){
  this.groupMembers = securitygroupmemberrequest.getGroupMembers(this.id);
return this.groupMembers;
}


public String getResetKey(){
    return resetKey;
}


}
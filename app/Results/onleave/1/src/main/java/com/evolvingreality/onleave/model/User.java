package com.evolvingreality.onleave.model;
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
@Entity
@Table(name = "APP_USER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends AbstractAuditingEntityimplements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@JsonIgnore
@NotNull
@Size(min = 60, max = 60)
@Column(length = 60)
 private  String password;

@NotNull
@Size(max = 50)
@Column(name = "first_name", length = 50)
 private  String firstName;

@NotNull
@Size(max = 50)
@Column(name = "last_name", length = 50)
 private  String lastName;

@Email
@NotNull
@Size(max = 100)
@Column(length = 100, unique = true)
 private  String email;

@Column(nullable = false)
 private  boolean activated;

@Size(min = 2, max = 5)
@Column(name = "lang_key", length = 5)
 private  String langKey;

@Size(max = 20)
@Column(name = "activation_key", length = 20)
@JsonIgnore
 private  String activationKey;

@Size(max = 20)
@Column(name = "reset_key", length = 20)
 private  String resetKey;

@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
@Column(name = "reset_date", nullable = true)
 private  DateTime resetDate;

@Transient
 private  List<SecurityGroupMember> groupMembers;

@Transient
 private SecurityGroupMemberRequest securitygroupmemberrequest = new SecurityGroupMemberRequestImpl();;


public void setPassword(String password){
    this.password = password;
}


public void setResetDate(DateTime resetDate){
    this.resetDate = resetDate;
}


public boolean getActivated(){
    return activated;
}


public Long getId(){
    return id;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}


@Override
public int hashCode(){
    return email.hashCode();
}


public String getActivationKey(){
    return activationKey;
}


public void setId(Long id){
    this.id = id;
}


public void setActivationKey(String activationKey){
    this.activationKey = activationKey;
}


public void setActivated(boolean activated){
    this.activated = activated;
}


public DateTime getResetDate(){
    return resetDate;
}


public void setResetKey(String resetKey){
    this.resetKey = resetKey;
}


public String getLangKey(){
    return langKey;
}


public void setGroupMembers(List<SecurityGroupMember> groupMembers){
 securitygroupmemberrequest.setGroupMembers(groupMembers,this.id);
}



public void setLangKey(String langKey){
    this.langKey = langKey;
}


public String getLastName(){
    return lastName;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    User user = (User) o;
    if (!email.equals(user.email)) {
        return false;
    }
    return true;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "User{" + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", activated='" + activated + '\'' + ", langKey='" + langKey + '\'' + ", activationKey='" + activationKey + '\'' + "}";
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
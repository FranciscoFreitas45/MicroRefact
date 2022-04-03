package com.easyshopping.entity;
 import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "xx_admin")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_admin_sequence")
public class Admin extends BaseEntity{

 private  long serialVersionUID;

 private  String username;

 private  String password;

 private  String email;

 private  String name;

 private  String department;

 private  Boolean isEnabled;

 private  Boolean isLocked;

 private  Integer loginFailureCount;

 private  Date lockedDate;

 private  Date loginDate;

 private  String loginIp;

 private  Set<Role> roles;

 private  Set<Order> orders;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


@Length(max = 200)
public String getName(){
    return name;
}


public void setIsLocked(Boolean isLocked){
    this.isLocked = isLocked;
}


@Length(max = 200)
public String getDepartment(){
    return department;
}


public void setLoginIp(String loginIp){
    this.loginIp = loginIp;
}


public void setLoginFailureCount(Integer loginFailureCount){
    this.loginFailureCount = loginFailureCount;
}


public void setLoginDate(Date loginDate){
    this.loginDate = loginDate;
}


public void setOrders(Set<Order> orders){
    this.orders = orders;
}


public void setLockedDate(Date lockedDate){
    this.lockedDate = lockedDate;
}


public String getLoginIp(){
    return loginIp;
}


@NotEmpty(groups = Save.class)
@Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
@Length(min = 2, max = 20)
@Column(nullable = false, updatable = false, unique = true, length = 100)
public String getUsername(){
    return username;
}


@Column(nullable = false)
public Boolean getIsLocked(){
    return isLocked;
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
}


@NotEmpty
@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_admin_role")
public Set<Role> getRoles(){
    return roles;
}


public void setUsername(String username){
    this.username = username;
}


public void setDepartment(String department){
    this.department = department;
}


@Column(nullable = false)
public Integer getLoginFailureCount(){
    return loginFailureCount;
}


public Date getLoginDate(){
    return loginDate;
}


@OneToMany(mappedBy = "operator", fetch = FetchType.LAZY)
public Set<Order> getOrders(){
    return orders;
}


@NotNull
@Column(nullable = false)
public Boolean getIsEnabled(){
    return isEnabled;
}


@NotEmpty(groups = Save.class)
@Pattern(regexp = "^[^\\s&\"<>]+$")
@Length(min = 4, max = 20)
@Column(nullable = false)
public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public void setIsEnabled(Boolean isEnabled){
    this.isEnabled = isEnabled;
}


@NotEmpty
@Email
@Length(max = 200)
@Column(nullable = false)
public String getEmail(){
    return email;
}


public Date getLockedDate(){
    return lockedDate;
}


@PreRemove
public void preRemove(){
    Set<Order> orders = getOrders();
    if (orders != null) {
        for (Order order : orders) {
            order.setLockExpire(null);
            order.setOperator(null);
        }
    }
}


}
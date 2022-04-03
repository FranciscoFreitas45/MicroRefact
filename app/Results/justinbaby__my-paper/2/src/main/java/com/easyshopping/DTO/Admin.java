package com.easyshopping.DTO;
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


@Length(max = 200)
public String getName(){
    return name;
}


@Length(max = 200)
public String getDepartment(){
    return department;
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


@NotEmpty
@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_admin_role")
public Set<Role> getRoles(){
    return roles;
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


}
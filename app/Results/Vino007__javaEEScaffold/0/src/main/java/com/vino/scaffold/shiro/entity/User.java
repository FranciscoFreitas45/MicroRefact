package com.vino.scaffold.shiro.entity;
 import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.vino.scaffold.entity.base.BaseEntity;
@Entity
@Table(name = "t_user")
public class User extends BaseEntity<Long>{

@Column(name = "username", length = 100)
 private  String username;

@Column(name = "password", length = 100)
 private  String password;

@Column(name = "user_alias", length = 50)
 private  String userAlias;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "last_login_time")
 private  Date lastLoginTime;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "login_time")
 private  Date loginTime;

@Column(name = "salt", length = 50)
 private  String salt;

@Column(name = "locked")
 private  Boolean locked;

// ��Զ�
@ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
// �����м��
@JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
 private  Set<Role> roles;

public User() {
}public User(String username, String password, String alias) {
    this.username = username;
    this.password = password;
    this.userAlias = alias;
}
public void setPassword(String password){
    this.password = password;
}


public void setUsername(String username){
    this.username = username;
}


public String getSalt(){
    return salt;
}


public Boolean getLocked(){
    return locked;
}


public void setLocked(Boolean locked){
    this.locked = locked;
}


public String getUsername(){
    return username;
}


public String getUserAlias(){
    return userAlias;
}


public void setUserAlias(String userAlias){
    this.userAlias = userAlias;
}


public String getPassword(){
    return password;
}


public Date getLoginTime(){
    return loginTime;
}


public void setLoginTime(Date loginTime){
    this.loginTime = loginTime;
}


public void setLastLoginTime(Date lastLoginTime){
    this.lastLoginTime = lastLoginTime;
}


public void setSalt(String salt){
    this.salt = salt;
}


public Date getLastLoginTime(){
    return lastLoginTime;
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
}


public Set<Role> getRoles(){
    return roles;
}


}
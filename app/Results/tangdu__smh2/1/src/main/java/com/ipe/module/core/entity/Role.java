package com.ipe.module.core.entity;
 import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ipe.common.entity.IDEntity;
import org.hibernate.annotations;
import javax.persistence;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collection;
@JsonIgnoreProperties({ "hibernateLazyInitializer", "authorities", "userRoles" })
@Table(name = "t_cor_role", schema = "", catalog = "db_work")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Role extends IDEntity{

 private  String roleName;

 private  String enabled;

 private  String remark;

 private  Timestamp createdDate;

 private  Timestamp updatedDate;

 private  Collection<Authority> authorities;

 private  Collection<UserRole> userRoles;


@Column(name = "role_name", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
@Basic
public String getRoleName(){
    return roleName;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


@Column(name = "updated_date", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
@Basic
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Timestamp getUpdatedDate(){
    return updatedDate;
}


@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
public Collection<UserRole> getUserRoles(){
    return userRoles;
}


@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
public Collection<Authority> getAuthorities(){
    return authorities;
}


public void setEnabled(String enabled){
    this.enabled = enabled;
}


public void setCreatedDate(Timestamp createdDate){
    this.createdDate = createdDate;
}


public void setUserRoles(Collection<UserRole> userRoles){
    this.userRoles = userRoles;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setAuthorities(Collection<Authority> authorities){
    this.authorities = authorities;
}


@Column(name = "enabled_", nullable = true, insertable = true, updatable = true, length = 2, precision = 0)
@Basic
public String getEnabled(){
    return enabled;
}


@Column(name = "remark_", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
@Basic
public String getRemark(){
    return remark;
}


public void setUpdatedDate(Timestamp updatedDate){
    this.updatedDate = updatedDate;
}


@Column(name = "created_date", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
@Basic
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Timestamp getCreatedDate(){
    return createdDate;
}


}
package com.ipe.DTO;
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


@Column(name = "created_date", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
@Basic
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Timestamp getCreatedDate(){
    return createdDate;
}


}
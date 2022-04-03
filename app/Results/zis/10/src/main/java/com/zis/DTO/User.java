package com.zis.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
public class User {

 private  Integer id;

 private  String userName;

 private  String realName;

 private  String password;

 private  String salt;

 private  String isDelete;

 private  Date createTime;

 private  Date updateTime;

 private  Integer version;

 private  Integer roleId;

 private  Integer companyId;

public User() {
}public User(Integer id, String userName, String realName, String password, String salt, String isDelete, Date createTime, Date updateTime, Integer version, Integer roleId, Integer companyId) {
    this.id = id;
    this.userName = userName;
    this.realName = realName;
    this.password = password;
    this.salt = salt;
    this.isDelete = isDelete;
    this.createTime = createTime;
    this.updateTime = updateTime;
    this.version = version;
    this.roleId = roleId;
    this.companyId = companyId;
}
public Integer getVersion(){
    return version;
}


public Date getCreateTime(){
    return createTime;
}


public String getIsDelete(){
    return isDelete;
}


public String getSalt(){
    return salt;
}


public Integer getRoleId(){
    return roleId;
}


public Integer getId(){
    return id;
}


public String getRealName(){
    return realName;
}


public Date getUpdateTime(){
    return updateTime;
}


public String getPassword(){
    return password;
}


public Integer getCompanyId(){
    return companyId;
}


public String getUserName(){
    return userName;
}


}
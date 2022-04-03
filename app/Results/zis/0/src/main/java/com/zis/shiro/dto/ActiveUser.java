package com.zis.shiro.dto;
 import java.io.Serializable;
import java.util.List;
import com.zis.shiro.bean.Permission;
public class ActiveUser implements Serializable{

 private  long serialVersionUID;

 private  Integer userId;

 private  String userName;

 private  String realName;

 private  List<Permission> permissions;

 private  Integer companyId;

 private  Integer stockId;


public void setRealName(String realName){
    this.realName = realName;
}


public List<Permission> getPermissions(){
    return permissions;
}


public void setStockId(Integer stockId){
    this.stockId = stockId;
}


public void setPermissions(List<Permission> permissions){
    this.permissions = permissions;
}


public String getRealName(){
    return realName;
}


public Integer getStockId(){
    return stockId;
}


public void setUserName(String userName){
    this.userName = userName;
}


public Integer getCompanyId(){
    return companyId;
}


public String getUserName(){
    return userName;
}


@Override
public String toString(){
    return "ActiveUser [userId=" + userId + ", userName=" + userName + ", realName=" + realName + ", permissions=" + permissions + ", companyId=" + companyId + ", stockId=" + stockId + "]";
}


public void setCompanyId(Integer companyId){
    this.companyId = companyId;
}


public Integer getUserId(){
    return userId;
}


public void setUserId(Integer userId){
    this.userId = userId;
}


}
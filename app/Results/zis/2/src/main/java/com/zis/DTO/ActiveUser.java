package com.zis.DTO;
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


public List<Permission> getPermissions(){
    return permissions;
}


public String getRealName(){
    return realName;
}


public Integer getStockId(){
    return stockId;
}


public Integer getCompanyId(){
    return companyId;
}


public String getUserName(){
    return userName;
}


public Integer getUserId(){
    return userId;
}


}
package com.zis.shiro.dto;
 import java.util.Date;
import org.hibernate.validator.constraints.NotBlank;
public class CreatePermissionDto {

@NotBlank(message = "permissionCode不能为空")
 private  String permissionCode;

@NotBlank(message = "permissionName不能为空")
 private  String permissionName;

@NotBlank(message = "permissionDescription不能为空")
 private  String permissionDescription;

@NotBlank(message = "url不能为空")
 private  String url;

@NotBlank(message = "groupName不能为空")
 private  String groupName;

 private  Date createTime;

 private  Date updateTime;


public Date getCreateTime(){
    return createTime;
}


public String getPermissionCode(){
    return permissionCode;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public String getPermissionName(){
    return permissionName;
}


public void setPermissionName(String permissionName){
    this.permissionName = permissionName;
}


public void setUrl(String url){
    this.url = url;
}


public void setPermissionCode(String permissionCode){
    this.permissionCode = permissionCode;
}


public String getUrl(){
    return url;
}


public String getGroupName(){
    return groupName;
}


public void setGroupName(String groupName){
    this.groupName = groupName;
}


public Date getUpdateTime(){
    return updateTime;
}


public void setPermissionDescription(String permissionDescription){
    this.permissionDescription = permissionDescription;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


public String getPermissionDescription(){
    return permissionDescription;
}


}
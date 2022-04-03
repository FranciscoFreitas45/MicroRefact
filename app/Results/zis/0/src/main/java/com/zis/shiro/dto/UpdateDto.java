package com.zis.shiro.dto;
 import org.hibernate.validator.constraints.NotBlank;
public class UpdateDto extends RegistDto{

@NotBlank(message = "删除选项不能为空")
 private  String isDelete;

@NotBlank(message = "删除选项不能为空")
 private  String roleId;


public void setRoleId(String roleId){
    this.roleId = roleId;
}


public void setIsDelete(String isDelete){
    this.isDelete = isDelete;
}


public String getIsDelete(){
    return isDelete;
}


public String getRoleId(){
    return roleId;
}


@Override
public String toString(){
    return "UpdateDto [isDelete=" + isDelete + ", roleId=" + roleId + "]";
}


}
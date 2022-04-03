package com.zis.shiro.dto;
 import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
public class RegistDto extends ShowPermissionDto{

@NotBlank(message = "用户名不能为空")
@Pattern(regexp = "^[A-Za-z0-9]+$", message = "用户名只能为数字与字母的组合")
 private  String userName;

@NotBlank(message = "使用者名称不能为空")
 private  String realName;

@NotBlank(message = "密码不能为空")
 private  String password;

@NotBlank(message = "密码不能为空")
 private  String passwordAgain;

@NotBlank(message = "角色名不能为空")
 private  String roleName;

@NotBlank(message = "角色Code不能为空")
 private  String roleCode;

@NotBlank(message = "角色描述不能为空")
 private  String roleDescription;

@NotNull(message = "权限不能为空")
 private  List<Integer> permissionIds;


public void setRealName(String realName){
    this.realName = realName;
}


public void setPassword(String password){
    this.password = password;
}


public void setRoleCode(String roleCode){
    this.roleCode = roleCode;
}


public String getRoleName(){
    return roleName;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public String getRealName(){
    return realName;
}


public String getRoleDescription(){
    return roleDescription;
}


public void setRoleDescription(String roleDescription){
    this.roleDescription = roleDescription;
}


public String getPasswordAgain(){
    return passwordAgain;
}


public String getPassword(){
    return password;
}


public List<Integer> getPermissionIds(){
    return permissionIds;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getRoleCode(){
    return roleCode;
}


public void setPasswordAgain(String passwordAgain){
    this.passwordAgain = passwordAgain;
}


public String getUserName(){
    return userName;
}


public void setPermissionIds(List<Integer> permissionIds){
    this.permissionIds = permissionIds;
}


@Override
public String toString(){
    return "RegistDto [userName=" + userName + ", realName=" + realName + ", password=" + password + ", passwordAgain=" + passwordAgain + ", roleName=" + roleName + ", roleCode=" + roleCode + ", roleDescription=" + roleDescription + ", permissionIds=" + permissionIds + "]";
}


}
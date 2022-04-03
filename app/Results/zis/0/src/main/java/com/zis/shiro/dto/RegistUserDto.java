package com.zis.shiro.dto;
 import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
public class RegistUserDto {

 private  Integer id;

@NotBlank(message = "用户名不能为空")
@Pattern(regexp = "^[A-Za-z0-9]+$", message = "用户名只能为数字与字母的组合")
 private  String userName;

@NotBlank(message = "使用者名称不能为空")
 private  String realName;

@NotBlank(message = "密码不能为空")
 private  String password;

@NotBlank(message = "密码不能为空")
 private  String passwordAgain;

 private  Integer roleId;

 private  String roleName;

 private  Integer companyId;

 private  String companyName;

public RegistUserDto() {
}public RegistUserDto(Integer id, String userName, String realName, String password, String passwordAgain, Integer roleId, String roleName, Integer companyId, String companyName) {
    this.id = id;
    this.userName = userName;
    this.realName = realName;
    this.password = password;
    this.passwordAgain = passwordAgain;
    this.roleId = roleId;
    this.roleName = roleName;
    this.companyId = companyId;
    this.companyName = companyName;
}
public void setRealName(String realName){
    this.realName = realName;
}


public void setPassword(String password){
    this.password = password;
}


public String getRoleName(){
    return roleName;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
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


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setRoleId(Integer roleId){
    this.roleId = roleId;
}


public String getPassword(){
    return password;
}


public String getPasswordAgain(){
    return passwordAgain;
}


public void setUserName(String userName){
    this.userName = userName;
}


public Integer getCompanyId(){
    return companyId;
}


public void setPasswordAgain(String passwordAgain){
    this.passwordAgain = passwordAgain;
}


public String getCompanyName(){
    return companyName;
}


public void setId(Integer id){
    this.id = id;
}


public String getUserName(){
    return userName;
}


@Override
public String toString(){
    return "RegistUserDto [id=" + id + ", userName=" + userName + ", realName=" + realName + ", password=" + password + ", passwordAgain=" + passwordAgain + ", roleId=" + roleId + ", roleName=" + roleName + ", companyId=" + companyId + ", companyName=" + companyName + "]";
}


public void setCompanyId(Integer companyId){
    this.companyId = companyId;
}


}
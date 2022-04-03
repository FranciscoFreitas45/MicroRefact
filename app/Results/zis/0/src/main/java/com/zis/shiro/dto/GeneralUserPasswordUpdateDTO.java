package com.zis.shiro.dto;
 import org.hibernate.validator.constraints.NotBlank;
public class GeneralUserPasswordUpdateDTO {

@NotBlank(message = "密码不能为空")
 private  String oldPassword;

@NotBlank(message = "新密码不能为空")
 private  String newPassword;

@NotBlank(message = "新密码不能为空")
 private  String newPasswordAgain;


public void setOldPassword(String oldPassword){
    this.oldPassword = oldPassword;
}


public void setNewPassword(String newPassword){
    this.newPassword = newPassword;
}


public String getOldPassword(){
    return oldPassword;
}


public String getNewPasswordAgain(){
    return newPasswordAgain;
}


@Override
public String toString(){
    return "GeneralUserPasswordUpdateDTO [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", newPasswordAgain=" + newPasswordAgain + "]";
}


public String getNewPassword(){
    return newPassword;
}


public void setNewPasswordAgain(String newPasswordAgain){
    this.newPasswordAgain = newPasswordAgain;
}


}
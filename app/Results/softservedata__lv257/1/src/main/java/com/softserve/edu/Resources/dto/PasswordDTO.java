package com.softserve.edu.Resources.dto;
 import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class PasswordDTO {

@NotEmpty(message = "Old Password should not be empty")
@NotNull(message = "Password should not be empty")
@Size(min = 3, max = 20, message = "Password should be from 3 to 20 characters")
 private  String oldPassword;

@NotNull(message = "Password should not be empty")
@Size(min = 3, max = 20, message = "Password should be from 6 to 20 characters")
 private  String newPassword1;

@NotNull(message = "Password should not be empty")
@Size(min = 3, max = 20, message = "Password should be from 6 to 20 characters")
 private  String newPassword2;

public PasswordDTO() {
}
public void setOldPassword(String oldPassword){
    this.oldPassword = oldPassword;
}


public String getOldPassword(){
    return oldPassword;
}


public void setNewPassword1(String newPassword1){
    this.newPassword1 = newPassword1;
}


@Override
public int hashCode(){
    int result = oldPassword != null ? oldPassword.hashCode() : 0;
    result = 31 * result + (newPassword1 != null ? newPassword1.hashCode() : 0);
    result = 31 * result + (newPassword2 != null ? newPassword2.hashCode() : 0);
    return result;
}


public void setNewPassword2(String newPassword2){
    this.newPassword2 = newPassword2;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    PasswordDTO that = (PasswordDTO) o;
    if (oldPassword != null ? !oldPassword.equals(that.oldPassword) : that.oldPassword != null)
        return false;
    if (newPassword1 != null ? !newPassword1.equals(that.newPassword1) : that.newPassword1 != null)
        return false;
    return newPassword2 != null ? newPassword2.equals(that.newPassword2) : that.newPassword2 == null;
}


public String getNewPassword2(){
    return newPassword2;
}


public String getNewPassword1(){
    return newPassword1;
}


@Override
public String toString(){
    return "PasswordDTO{" + "oldPassword='" + oldPassword + '\'' + ", newPassword1='" + newPassword1 + '\'' + ", newPassword2='" + newPassword2 + '\'' + '}';
}


}
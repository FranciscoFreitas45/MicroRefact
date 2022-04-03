package com.softserve.edu.Resources.dto;
 import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class UserDTO {

@NotEmpty(message = "Email should not be empty")
@NotNull
@Email(message = "Invalid email address!")
 private  String email;

@NotNull(message = "Password should not be empty")
@Size(min = 6, max = 20, message = "Password should be from 6 to 20 characters")
 private  String password;

@Transient
@NotEmpty(message = "Confirm Password should not be empty")
@NotNull
 private  String confirmPassword;

 private  boolean isUsing2FA;

 private  Integer role;


public void setPassword(String password){
    this.password = password;
}


public String getConfirmPassword(){
    return confirmPassword;
}


public String getPassword(){
    return password;
}


public void setConfirmPassword(String confirmPassword){
    this.confirmPassword = confirmPassword;
}


public void setEmail(String email){
    this.email = email;
}


public void setRole(Integer role){
    this.role = role;
}


public Integer getRole(){
    return role;
}


public void setUsing2FA(boolean isUsing2FA){
    this.isUsing2FA = isUsing2FA;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    final StringBuilder builder = new StringBuilder();
    builder.append("UserDto [password=").append(password).append(", confirmPassword=").append(confirmPassword).append(", email=").append(email).append(", isUsing2FA=").append(isUsing2FA).append(", role=").append(role).append("]");
    return builder.toString();
}


public boolean isUsing2FA(){
    return isUsing2FA;
}


}
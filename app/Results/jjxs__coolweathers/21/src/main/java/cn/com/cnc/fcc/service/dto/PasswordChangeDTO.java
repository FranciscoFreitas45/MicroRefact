package cn.com.cnc.fcc.service.dto;
 public class PasswordChangeDTO {

 private  String currentPassword;

 private  String newPassword;

public PasswordChangeDTO() {
// Empty constructor needed for Jackson.
}public PasswordChangeDTO(String currentPassword, String newPassword) {
    this.currentPassword = currentPassword;
    this.newPassword = newPassword;
}
public void setCurrentPassword(String currentPassword){
    this.currentPassword = currentPassword;
}


public String getCurrentPassword(){
    return currentPassword;
}


public void setNewPassword(String newPassword){
    this.newPassword = newPassword;
}


public String getNewPassword(){
    return newPassword;
}


}
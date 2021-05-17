public class PasswordChangeDTO {

 private  String currentPassword;

 private  String newPassword;


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
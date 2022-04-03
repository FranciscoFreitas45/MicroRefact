package com.yalcin.dto.request;
 public class ForgotAndChangePasswordForm {

 private  String token;

 private  String newPassword;

 private  String newPasswordConfirmation;

public ForgotAndChangePasswordForm() {
}public ForgotAndChangePasswordForm(String token, String newPassword, String newPasswordConfirmation) {
    this.token = token;
    this.newPassword = newPassword;
    this.newPasswordConfirmation = newPasswordConfirmation;
}
public void setNewPassword(String newPassword){
    this.newPassword = newPassword;
}


public void setToken(String token){
    this.token = token;
}


public String getToken(){
    return this.token;
}


public String getNewPasswordConfirmation(){
    return this.newPasswordConfirmation;
}


public String getNewPassword(){
    return this.newPassword;
}


public void setNewPasswordConfirmation(String newPasswordConfirmation){
    this.newPasswordConfirmation = newPasswordConfirmation;
}


public boolean checkAllFieldsAreGiven(ForgotAndChangePasswordForm ForgotAndChangePasswordForm){
    if (ForgotAndChangePasswordForm.getNewPassword() == null || ForgotAndChangePasswordForm.getNewPasswordConfirmation() == null || ForgotAndChangePasswordForm.getToken() == null) {
        return false;
    }
    return true;
}


}
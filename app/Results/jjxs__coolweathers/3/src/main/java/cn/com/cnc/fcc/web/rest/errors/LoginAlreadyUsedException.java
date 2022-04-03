package cn.com.cnc.fcc.web.rest.errors;
 public class LoginAlreadyUsedException extends BadRequestAlertException{

 private  long serialVersionUID;

public LoginAlreadyUsedException() {
    super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "Login name already used!", "userManagement", "userexists");
}
}
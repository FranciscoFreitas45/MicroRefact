package cn.com.cnc.fcc.web.rest.errors;
 public class EmailAlreadyUsedException extends BadRequestAlertException{

 private  long serialVersionUID;

public EmailAlreadyUsedException() {
    super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Email is already in use!", "userManagement", "emailexists");
}
}
package com.netease.vo;
 public class UserInfoVO {

 private  String name;

 private  String certType;

 private  String certNo;

 private  String checkCode;

 private  String checkAgreement;

 private  String loginName;

 private  String password;

 private  String confirmPassword;

 private  String email;

 private  String mobileTel;

 private  String tcId;

 private  String counttime;

 private  String verifyCode;


public void setName(String name){
    this.name = name;
}


public String getMobileTel(){
    return mobileTel;
}


public void setPassword(String password){
    this.password = password;
}


public String getConfirmPassword(){
    return confirmPassword;
}


public void setVerifyCode(String verifyCode){
    this.verifyCode = verifyCode;
}


public String getName(){
    return name;
}


public void setCheckAgreement(String checkAgreement){
    this.checkAgreement = checkAgreement;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public void setCertType(String certType){
    this.certType = certType;
}


public void setLoginName(String loginName){
    this.loginName = loginName;
}


public String getVerifyCode(){
    return verifyCode;
}


public void setMobileTel(String mobileTel){
    this.mobileTel = mobileTel;
}


public String getCertType(){
    return certType;
}


public void setConfirmPassword(String confirmPassword){
    this.confirmPassword = confirmPassword;
}


public String getTcId(){
    return tcId;
}


public void setCheckCode(String checkCode){
    this.checkCode = checkCode;
}


public String getCounttime(){
    return counttime;
}


public String getLoginName(){
    return loginName;
}


public String getCertNo(){
    return certNo;
}


public String getPassword(){
    return password;
}


public String getCheckAgreement(){
    return checkAgreement;
}


public void setEmail(String email){
    this.email = email;
}


public String getCheckCode(){
    return checkCode;
}


public String getEmail(){
    return email;
}


public void setCounttime(String counttime){
    this.counttime = counttime;
}


public void setTcId(String tcId){
    this.tcId = tcId;
}


}
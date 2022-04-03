package com.fosun.fc.projects.creepers.dto;
 public class CreepersFundBasicDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String accountDt;

 private  String accountNo;

 private  String unit;

 private  String endDt;

 private  String sumAmount;

 private  String monthlyAmount;

 private  String accountStatus;

 private  String mobile;

 private  String certificateStatus;

 private  String memo;

 private  String flag;

 private  String loginName;

 private  String password;


public void setName(String name){
    this.name = name;
}


public void setAccountDt(String accountDt){
    this.accountDt = accountDt;
}


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
}


public String getAccountStatus(){
    return accountStatus;
}


public void setMonthlyAmount(String monthlyAmount){
    this.monthlyAmount = monthlyAmount;
}


public void setSumAmount(String sumAmount){
    this.sumAmount = sumAmount;
}


public void setCertificateStatus(String certificateStatus){
    this.certificateStatus = certificateStatus;
}


public void setAccountNo(String accountNo){
    this.accountNo = accountNo;
}


public void setLoginName(String loginName){
    this.loginName = loginName;
}


public void setUnit(String unit){
    this.unit = unit;
}


public void setAccountStatus(String accountStatus){
    this.accountStatus = accountStatus;
}


public String getFlag(){
    return flag;
}


public String getAccountNo(){
    return accountNo;
}


public String getAccountDt(){
    return accountDt;
}


public void setFlag(String flag){
    this.flag = flag;
}


public void setEndDt(String endDt){
    this.endDt = endDt;
}


public String getCertificateStatus(){
    return certificateStatus;
}


public String getLoginName(){
    return loginName;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public String getMemo(){
    return memo;
}


public String getPassword(){
    return password;
}


public String getEndDt(){
    return endDt;
}


public String getMonthlyAmount(){
    return monthlyAmount;
}


public String getSumAmount(){
    return sumAmount;
}


public String getMobile(){
    return mobile;
}


public String getUnit(){
    return unit;
}


}
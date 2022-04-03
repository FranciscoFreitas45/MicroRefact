package com.fosun.fc.projects.creepers.dto;
 public class CreepersFundExtraDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String name;

 private  String accountDt;

 private  String accountNo;

 private  String unit;

 private  String endDt;

 private  String sumAmount;

 private  String monthlyAmount;

 private  String accountStatus;

 private  String memo;

 private  String loginName;


public void setName(String name){
    this.name = name;
}


public String getAccountDt(){
    return accountDt;
}


public void setAccountDt(String accountDt){
    this.accountDt = accountDt;
}


public String getName(){
    return name;
}


public void setEndDt(String endDt){
    this.endDt = endDt;
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


public String getLoginName(){
    return loginName;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public String getEndDt(){
    return endDt;
}


public void setAccountNo(String accountNo){
    this.accountNo = accountNo;
}


public String getMonthlyAmount(){
    return monthlyAmount;
}


public String getSumAmount(){
    return sumAmount;
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


public String getUnit(){
    return unit;
}


public String getAccountNo(){
    return accountNo;
}


}
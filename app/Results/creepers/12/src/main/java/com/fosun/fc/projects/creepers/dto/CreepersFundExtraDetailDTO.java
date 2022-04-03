package com.fosun.fc.projects.creepers.dto;
 public class CreepersFundExtraDetailDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String loginName;

 private  String operationDt;

 private  String unit;

 private  String amount;

 private  String operationDesc;

 private  String operationReason;

 private  String memo;


public String getOperationReason(){
    return operationReason;
}


public void setOperationReason(String operationReason){
    this.operationReason = operationReason;
}


public void setOperationDt(String operationDt){
    this.operationDt = operationDt;
}


public String getLoginName(){
    return loginName;
}


public String getOperationDt(){
    return operationDt;
}


public String getOperationDesc(){
    return operationDesc;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public void setLoginName(String loginName){
    this.loginName = loginName;
}


public void setUnit(String unit){
    this.unit = unit;
}


public String getUnit(){
    return unit;
}


public void setOperationDesc(String operationDesc){
    this.operationDesc = operationDesc;
}


public void setAmount(String amount){
    this.amount = amount;
}


public String getAmount(){
    return amount;
}


}
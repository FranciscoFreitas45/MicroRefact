package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.Company;
public class Supply {

 private  String supplyId;

 private  String supplyName;

 private  String supplyTel;

 private  String supplyPricipal;

 private  String supplyAddress;

 private  String supplyBank;

 private  String supplyBankAccount;

 private  String supplyBankNo;

 private  String supplyAlipay;

 private  String supplyWeChat;

 private  Date supplyCreatedTime;

 private  String supplyTypeId;

 private  String companyId;

 private  String supplyStatus;

 private  SupplyType supplyType;

 private  Company company;


public void setSupplyTel(String supplyTel){
    this.supplyTel = supplyTel;
}


public void setSupplyWeChat(String supplyWeChat){
    this.supplyWeChat = supplyWeChat;
}


public void setSupplyId(String supplyId){
    this.supplyId = supplyId;
}


public void setSupplyBankAccount(String supplyBankAccount){
    this.supplyBankAccount = supplyBankAccount;
}


public String getSupplyWeChat(){
    return supplyWeChat;
}


public void setSupplyType(SupplyType supplyType){
    this.supplyType = supplyType;
}


public String getSupplyName(){
    return supplyName;
}


public void setSupplyAddress(String supplyAddress){
    this.supplyAddress = supplyAddress;
}


public String getSupplyId(){
    return supplyId;
}


public String getSupplyBank(){
    return supplyBank;
}


public String getSupplyPricipal(){
    return supplyPricipal;
}


public Date getSupplyCreatedTime(){
    return supplyCreatedTime;
}


public void setSupplyCreatedTime(Date supplyCreatedTime){
    this.supplyCreatedTime = supplyCreatedTime;
}


public void setSupplyName(String supplyName){
    this.supplyName = supplyName;
}


public void setSupplyPricipal(String supplyPricipal){
    this.supplyPricipal = supplyPricipal;
}


public void setCompany(Company company){
    this.company = company;
}


public SupplyType getSupplyType(){
    return supplyType;
}


public void setSupplyTypeId(String supplyTypeId){
    this.supplyTypeId = supplyTypeId;
}


public String getSupplyTel(){
    return supplyTel;
}


public String getSupplyBankAccount(){
    return supplyBankAccount;
}


public void setSupplyAlipay(String supplyAlipay){
    this.supplyAlipay = supplyAlipay;
}


public String getSupplyTypeId(){
    return supplyTypeId;
}


public void setSupplyStatus(String supplyStatus){
    this.supplyStatus = supplyStatus;
}


public String getSupplyStatus(){
    return supplyStatus;
}


public String getSupplyBankNo(){
    return supplyBankNo;
}


public Company getCompany(){
    return company;
}


public void setSupplyBankNo(String supplyBankNo){
    this.supplyBankNo = supplyBankNo;
}


public String getCompanyId(){
    return companyId;
}


public String getSupplyAddress(){
    return supplyAddress;
}


public void setSupplyBank(String supplyBank){
    this.supplyBank = supplyBank;
}


public String getSupplyAlipay(){
    return supplyAlipay;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


}
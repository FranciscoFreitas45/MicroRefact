package com.gs.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public String getSupplyWeChat(){
    return supplyWeChat;
}


public String getSupplyName(){
    return supplyName;
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


public SupplyType getSupplyType(){
    return supplyType;
}


public String getSupplyTel(){
    return supplyTel;
}


public String getSupplyBankAccount(){
    return supplyBankAccount;
}


public String getSupplyTypeId(){
    return supplyTypeId;
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


public String getCompanyId(){
    return companyId;
}


public String getSupplyAddress(){
    return supplyAddress;
}


public String getSupplyAlipay(){
    return supplyAlipay;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCompanyId"))

.queryParam("companyId",companyId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSupplyName(String supplyName){
    this.supplyName = supplyName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSupplyName"))

.queryParam("supplyName",supplyName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSupplyTypeId(String supplyTypeId){
    this.supplyTypeId = supplyTypeId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSupplyTypeId"))

.queryParam("supplyTypeId",supplyTypeId)
;
restTemplate.put(builder.toUriString(),null);
}


}
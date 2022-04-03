package com.gs.DTO;
 import java.util.Date;
import com.gs.Interface.Company;
import com.gs.Interface.Supply;
public class Accessories {

 private  String accId;

 private  String accName;

 private  String accCommodityCode;

 private  String accDes;

 private  Double accPrice;

 private  Double accSalePrice;

 private  String accUnit;

 private  Integer accTotal;

 private  Integer accIdle;

 private  Date accUsedTime;

 private  Date accBuyedTime;

 private  String supplyId;

 private  Date accCreatedTime;

 private  String accTypeId;

 private  String companyId;

 private  String accStatus;

 public  int count;

 private  Company company;

 private  AccessoriesType accessoriesType;

 private  Supply supply;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public Integer getAccTotal(){
    return accTotal;
}


public Double getAccSalePrice(){
    return accSalePrice;
}


public String getAccTypeId(){
    return accTypeId;
}


public String getAccCommodityCode(){
    return accCommodityCode;
}


public void setAccBuyedTime(Date accBuyedTime){
    this.accBuyedTime = accBuyedTime;
}


public String getSupplyId(){
    return supplyId;
}


public Date getAccUsedTime(){
    return accUsedTime;
}


public Supply getSupply(){
    return supply;
}


public int getCount(){
    return count;
}


public void setCompany(Company company){
    this.company = company;
}


public void setAccDes(String accDes){
    this.accDes = accDes;
}


public String getAccId(){
    return accId;
}


public String getAccName(){
    return accName;
}


public AccessoriesType getAccessoriesType(){
    return accessoriesType;
}


public void setAccPrice(Double accPrice){
    this.accPrice = accPrice;
}


public Integer getAccIdle(){
    return accIdle;
}


public Company getCompany(){
    return company;
}


public String getCompanyId(){
    return companyId;
}


public String getAccStatus(){
    return accStatus;
}


public String getAccDes(){
    return accDes;
}


@Override
public String toString(){
    return "Accessories{" + "accId='" + accId + '\'' + ", accName='" + accName + '\'' + ", accCommodityCode='" + accCommodityCode + '\'' + ", accDes='" + accDes + '\'' + ", accPrice=" + accPrice + ", accSalePrice=" + accSalePrice + ", accUnit='" + accUnit + '\'' + ", accTotal=" + accTotal + ", accIdle=" + accIdle + ", accUsedTime=" + accUsedTime + ", accBuyedTime=" + accBuyedTime + ", supplyId='" + supplyId + '\'' + ", accCreatedTime=" + accCreatedTime + ", accTypeId='" + accTypeId + '\'' + ", companyId='" + companyId + '\'' + ", accStatus='" + accStatus + '\'' + ", company=" + company + ", accessoriesType=" + accessoriesType + ", supply=" + supply + '}';
}


public String getAccUnit(){
    return accUnit;
}


public Date getAccBuyedTime(){
    return accBuyedTime;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public Double getAccPrice(){
    return accPrice;
}


public Date getAccCreatedTime(){
    return accCreatedTime;
}


public void setAccTypeId(String accTypeId){
    this.accTypeId = accTypeId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAccTypeId"))

.queryParam("accTypeId",accTypeId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSupplyId(String supplyId){
    this.supplyId = supplyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSupplyId"))

.queryParam("supplyId",supplyId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAccName(String accName){
    this.accName = accName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAccName"))

.queryParam("accName",accName)
;
restTemplate.put(builder.toUriString(),null);
}


}
package com.gs.bean;
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


public void setAccCommodityCode(String accCommodityCode){
    this.accCommodityCode = accCommodityCode;
}


public Integer getAccTotal(){
    return accTotal;
}


public void setSupplyId(String supplyId){
    this.supplyId = supplyId;
}


public Double getAccSalePrice(){
    return accSalePrice;
}


public void setAccId(String accId){
    this.accId = accId;
}


public String getAccTypeId(){
    return accTypeId;
}


public String getAccCommodityCode(){
    return accCommodityCode;
}


public void setAccTypeId(String accTypeId){
    this.accTypeId = accTypeId;
}


public void setAccBuyedTime(Date accBuyedTime){
    this.accBuyedTime = accBuyedTime;
}


public String getSupplyId(){
    return supplyId;
}


public void setAccessoriesType(AccessoriesType accessoriesType){
    this.accessoriesType = accessoriesType;
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


public void setCount(int count){
    this.count = count;
}


public void setCompany(Company company){
    this.company = company;
}


public void setSupply(Supply supply){
    this.supply = supply;
}


public void setAccDes(String accDes){
    this.accDes = accDes;
}


public String getAccId(){
    return accId;
}


public void setAccUnit(String accUnit){
    this.accUnit = accUnit;
}


public String getAccName(){
    return accName;
}


public void setAccName(String accName){
    this.accName = accName;
}


public AccessoriesType getAccessoriesType(){
    return accessoriesType;
}


public void setAccStatus(String accStatus){
    this.accStatus = accStatus;
}


public void setAccPrice(Double accPrice){
    this.accPrice = accPrice;
}


public Integer getAccIdle(){
    return accIdle;
}


public void setAccIdle(Integer accIdle){
    this.accIdle = accIdle;
}


public Company getCompany(){
    return company;
}


public void setAccTotal(Integer accTotal){
    this.accTotal = accTotal;
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


public void setAccCreatedTime(Date accCreatedTime){
    this.accCreatedTime = accCreatedTime;
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


public void setAccUsedTime(Date accUsedTime){
    this.accUsedTime = accUsedTime;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public Double getAccPrice(){
    return accPrice;
}


public void setAccSalePrice(Double accSalePrice){
    this.accSalePrice = accSalePrice;
}


public Date getAccCreatedTime(){
    return accCreatedTime;
}


}
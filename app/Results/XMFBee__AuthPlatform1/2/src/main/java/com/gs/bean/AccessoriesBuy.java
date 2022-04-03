package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.Company;
import com.gs.Interface.Supply;
public class AccessoriesBuy {

 private  String accBuyId;

 private  String accId;

 private  String accUnit;

 private  Integer accBuyCount;

 private  Double accBuyPrice;

 private  Double accBuyTotal;

 private  Double accBuyDiscount;

 private  Double accBuyMoney;

 private  Date accBuyTime;

 private  Date accBuyCreatedTime;

 private  String companyId;

 private  String supplyId;

 private  String accTypeId;

 private  String accBuyStatus;

 private  Company company;

 private  Accessories accessories;

 private  Supply supply;

 private  AccessoriesType accessoriesType;

 private  int count;

 private  int week;


public void setSupplyId(String supplyId){
    this.supplyId = supplyId;
}


public Double getAccBuyMoney(){
    return accBuyMoney;
}


public void setAccBuyMoney(Double accBuyMoney){
    this.accBuyMoney = accBuyMoney;
}


public String getAccBuyId(){
    return accBuyId;
}


public void setAccId(String accId){
    this.accId = accId;
}


public Date getAccBuyCreatedTime(){
    return accBuyCreatedTime;
}


public String getAccTypeId(){
    return accTypeId;
}


public void setAccBuyDiscount(Double accBuyDiscount){
    this.accBuyDiscount = accBuyDiscount;
}


public Integer getAccBuyCount(){
    return accBuyCount;
}


public void setAccTypeId(String accTypeId){
    this.accTypeId = accTypeId;
}


public String getSupplyId(){
    return supplyId;
}


public Accessories getAccessories(){
    return accessories;
}


public void setAccessoriesType(AccessoriesType accessoriesType){
    this.accessoriesType = accessoriesType;
}


public Date getAccBuyTime(){
    return accBuyTime;
}


public void setAccBuyPrice(Double accBuyPrice){
    this.accBuyPrice = accBuyPrice;
}


public Supply getSupply(){
    return supply;
}


public int getCount(){
    return count;
}


public Double getAccBuyDiscount(){
    return accBuyDiscount;
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


public String getAccBuyStatus(){
    return accBuyStatus;
}


public void setAccBuyTime(Date accBuyTime){
    this.accBuyTime = accBuyTime;
}


public String getAccId(){
    return accId;
}


public void setAccUnit(String accUnit){
    this.accUnit = accUnit;
}


public void setWeek(int week){
    this.week = week;
}


public AccessoriesType getAccessoriesType(){
    return accessoriesType;
}


public void setAccBuyCreatedTime(Date accBuyCreatedTime){
    this.accBuyCreatedTime = accBuyCreatedTime;
}


public void setAccBuyTotal(Double accBuyTotal){
    this.accBuyTotal = accBuyTotal;
}


public void setAccBuyStatus(String accBuyStatus){
    this.accBuyStatus = accBuyStatus;
}


public int getWeek(){
    return week;
}


public void setAccBuyCount(Integer accBuyCount){
    this.accBuyCount = accBuyCount;
}


public Company getCompany(){
    return company;
}


public void setAccBuyId(String accBuyId){
    this.accBuyId = accBuyId;
}


public String getCompanyId(){
    return companyId;
}


public Double getAccBuyPrice(){
    return accBuyPrice;
}


public Double getAccBuyTotal(){
    return accBuyTotal;
}


@Override
public String toString(){
    return "AccessoriesBuy{" + "accBuyId='" + accBuyId + '\'' + ", accId='" + accId + '\'' + ", accUnit='" + accUnit + '\'' + ", accBuyCount=" + accBuyCount + ", accBuyPrice=" + accBuyPrice + ", accBuyTotal=" + accBuyTotal + ", accBuyDiscount=" + accBuyDiscount + ", accBuyMoney=" + accBuyMoney + ", accBuyTime=" + accBuyTime + ", accBuyCreatedTime=" + accBuyCreatedTime + ", companyId='" + companyId + '\'' + ", supplyId='" + supplyId + '\'' + ", accTypeId='" + accTypeId + '\'' + ", accBuyStatus='" + accBuyStatus + '\'' + ", company=" + company + ", accessories=" + accessories + ", supply=" + supply + ", accessoriesType=" + accessoriesType + '}';
}


public String getAccUnit(){
    return accUnit;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setAccessories(Accessories accessories){
    this.accessories = accessories;
}


}
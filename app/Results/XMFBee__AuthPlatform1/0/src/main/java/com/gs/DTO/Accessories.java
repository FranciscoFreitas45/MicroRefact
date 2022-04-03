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


public String getAccId(){
    return accId;
}


public String getAccName(){
    return accName;
}


public AccessoriesType getAccessoriesType(){
    return accessoriesType;
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


public String getAccUnit(){
    return accUnit;
}


public Date getAccBuyedTime(){
    return accBuyedTime;
}


public Double getAccPrice(){
    return accPrice;
}


public Date getAccCreatedTime(){
    return accCreatedTime;
}


}
package com.gs.bean;
 import com.gs.Interface.Company;
public class SupplyType {

 private  String supplyTypeId;

 private  String supplyTypeName;

 private  String supplyTypeDes;

 private  String companyId;

 private  String supplyTypeStatus;

 private  Company company;


public void setSupplyTypeId(String supplyTypeId){
    this.supplyTypeId = supplyTypeId;
}


public void setSupplyTypeName(String supplyTypeName){
    this.supplyTypeName = supplyTypeName;
}


public Company getCompany(){
    return company;
}


public String getCompanyId(){
    return companyId;
}


public String getSupplyTypeId(){
    return supplyTypeId;
}


public String getSupplyTypeName(){
    return supplyTypeName;
}


public String getSupplyTypeStatus(){
    return supplyTypeStatus;
}


public void setSupplyTypeStatus(String supplyTypeStatus){
    this.supplyTypeStatus = supplyTypeStatus;
}


public void setSupplyTypeDes(String supplyTypeDes){
    this.supplyTypeDes = supplyTypeDes;
}


public String getSupplyTypeDes(){
    return supplyTypeDes;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setCompany(Company company){
    this.company = company;
}


}
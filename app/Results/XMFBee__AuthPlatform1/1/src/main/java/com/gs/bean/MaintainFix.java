package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.Company;
import com.gs.Interface.Accessories;
import com.gs.Interface.AccessoriesType;
public class MaintainFix {

 private  String maintainId;

 private  String maintainName;

 private  Double maintainHour;

 private  Double maintainMoney;

 private  Double maintainManHourFee;

 private  String maintainOrFix;

 private  String maintainDes;

 private  String maintainStatus;

 private  String companyId;

 private  int count;

 private  Date mdCreatedTime;

 private  Company company;

 private  MaintainFixAcc maintainFixAcc;

 private  Accessories accessories;

 private  AccessoriesType accessoriesType;


public Double getMaintainMoney(){
    return maintainMoney;
}


public String getMaintainName(){
    return maintainName;
}


public void setMaintainName(String maintainName){
    this.maintainName = maintainName;
}


public String getMaintainId(){
    return maintainId;
}


public Date getMdCreatedTime(){
    return mdCreatedTime;
}


public void setMaintainManHourFee(Double maintainManHourFee){
    this.maintainManHourFee = maintainManHourFee;
}


public void setMaintainId(String maintainId){
    this.maintainId = maintainId;
}


public void setMaintainDes(String maintainDes){
    this.maintainDes = maintainDes;
}


public void setMaintainOrFix(String maintainOrFix){
    this.maintainOrFix = maintainOrFix;
}


public Accessories getAccessories(){
    return accessories;
}


public void setAccessoriesType(AccessoriesType accessoriesType){
    this.accessoriesType = accessoriesType;
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


public String getMaintainDes(){
    return maintainDes;
}


public Double getMaintainHour(){
    return maintainHour;
}


public void setMdCreatedTime(Date mdCreatedTime){
    this.mdCreatedTime = mdCreatedTime;
}


public Double getMaintainManHourFee(){
    return maintainManHourFee;
}


public void setMaintainMoney(Double maintainMoney){
    this.maintainMoney = maintainMoney;
}


public AccessoriesType getAccessoriesType(){
    return accessoriesType;
}


public void setMaintainStatus(String maintainStatus){
    this.maintainStatus = maintainStatus;
}


public String getMaintainOrFix(){
    return maintainOrFix;
}


public String getMaintainStatus(){
    return maintainStatus;
}


public void setMaintainFixAcc(MaintainFixAcc maintainFixAcc){
    this.maintainFixAcc = maintainFixAcc;
}


public Company getCompany(){
    return company;
}


public MaintainFixAcc getMaintainFixAcc(){
    return maintainFixAcc;
}


public String getCompanyId(){
    return companyId;
}


@Override
public String toString(){
    return "MaintainFix{" + "maintainId='" + maintainId + '\'' + ", maintainName='" + maintainName + '\'' + ", maintainHour=" + maintainHour + ", maintainMoney=" + maintainMoney + ", maintainManHourFee=" + maintainManHourFee + ", maintainOrFix='" + maintainOrFix + '\'' + ", maintainDes='" + maintainDes + '\'' + ", maintainStatus='" + maintainStatus + '\'' + ", company=" + company + ", maintainFixAcc=" + maintainFixAcc + ", accessories=" + accessories + ", accessoriesType=" + accessoriesType + '}';
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setAccessories(Accessories accessories){
    this.accessories = accessories;
}


public void setMaintainHour(Double maintainHour){
    this.maintainHour = maintainHour;
}


}
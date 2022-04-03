package com.gs.bean;
 import com.gs.Interface.Company;
import com.gs.Interface.Accessories;
public class MaintainFixAcc {

 private  String mainAccId;

 private  String maintainId;

 private  String accId;

 private  Integer accCount;

 private  Company company;

 private  Accessories accessories;

 private  MaintainFix maintainFix;


public MaintainFix getMaintainFix(){
    return maintainFix;
}


public String getMainAccId(){
    return mainAccId;
}


public String getAccId(){
    return accId;
}


public String getMaintainId(){
    return maintainId;
}


public void setAccId(String accId){
    this.accId = accId;
}


public Integer getAccCount(){
    return accCount;
}


public void setAccCount(Integer accCount){
    this.accCount = accCount;
}


public Company getCompany(){
    return company;
}


public void setMaintainId(String maintainId){
    this.maintainId = maintainId;
}


public Accessories getAccessories(){
    return accessories;
}


@Override
public String toString(){
    return "MaintainFixAcc{" + "mainAccId='" + mainAccId + '\'' + ", maintainId='" + maintainId + '\'' + ", accId='" + accId + '\'' + ", accCount=" + accCount + '}';
}


public void setAccessories(Accessories accessories){
    this.accessories = accessories;
}


public void setMainAccId(String mainAccId){
    this.mainAccId = mainAccId;
}


public void setMaintainFix(MaintainFix maintainFix){
    this.maintainFix = maintainFix;
}


public void setCompany(Company company){
    this.company = company;
}


}
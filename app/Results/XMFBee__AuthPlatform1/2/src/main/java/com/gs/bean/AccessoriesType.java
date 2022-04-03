package com.gs.bean;
 import com.gs.Interface.Company;
public class AccessoriesType {

 private  String accTypeId;

 private  String accTypeName;

 private  String accTypeDes;

 private  String companyId;

 private  String accTypeStatus;

 private  Company company;


public void setAccTypeName(String accTypeName){
    this.accTypeName = accTypeName;
}


public String getAccTypeId(){
    return accTypeId;
}


public String getAccTypeStatus(){
    return accTypeStatus;
}


public String getAccTypeName(){
    return accTypeName;
}


public String getAccTypeDes(){
    return accTypeDes;
}


public Company getCompany(){
    return company;
}


public void setAccTypeId(String accTypeId){
    this.accTypeId = accTypeId;
}


public String getCompanyId(){
    return companyId;
}


public void setAccTypeStatus(String accTypeStatus){
    this.accTypeStatus = accTypeStatus;
}


public void setAccTypeDes(String accTypeDes){
    this.accTypeDes = accTypeDes;
}


@Override
public String toString(){
    return "AccessoriesType{" + "accTypeId='" + accTypeId + '\'' + ", accTypeName='" + accTypeName + '\'' + ", accTypeDes='" + accTypeDes + '\'' + ", companyId='" + companyId + '\'' + ", accTypeStatus='" + accTypeStatus + '\'' + ", company=" + company + '}';
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setCompany(Company company){
    this.company = company;
}


}
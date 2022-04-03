package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.Company;
public class IncomingType {

 private  String inTypeId;

 private  String inTypeName;

 private  String companyId;

 private  String inTypeStatus;

 private  Date createTime;

 private  Company company;


public Date getCreateTime(){
    return createTime;
}


public Company getCompany(){
    return company;
}


public String getCompanyId(){
    return companyId;
}


public String getInTypeStatus(){
    return inTypeStatus;
}


public String getInTypeId(){
    return inTypeId;
}


public void setInTypeStatus(String inTypeStatus){
    this.inTypeStatus = inTypeStatus;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setInTypeId(String inTypeId){
    this.inTypeId = inTypeId;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setCompany(Company company){
    this.company = company;
}


public String getInTypeName(){
    return inTypeName;
}


public void setInTypeName(String inTypeName){
    this.inTypeName = inTypeName;
}


}
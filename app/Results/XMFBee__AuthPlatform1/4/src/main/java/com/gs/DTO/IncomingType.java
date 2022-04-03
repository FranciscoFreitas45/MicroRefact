package com.gs.DTO;
 import java.util.Date;
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


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public String getInTypeName(){
    return inTypeName;
}


}
package com.gs.DTO;
 import java.util.Date;
public class OutgoingType {

 private  String outTypeId;

 private  String outTypeName;

 private  String outTypeStatus;

 private  Date createTime;

 private  String companyId;

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


public String getOutTypeId(){
    return outTypeId;
}


public String getOutTypeName(){
    return outTypeName;
}


public String getOutTypeStatus(){
    return outTypeStatus;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setOutTypeName(String outTypeName){
    this.outTypeName = outTypeName;
}


}
package com.gs.bean;
 import java.util.Date;
import com.gs.Interface.Company;
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


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public String getOutTypeStatus(){
    return outTypeStatus;
}


public void setOutTypeStatus(String outTypeStatus){
    this.outTypeStatus = outTypeStatus;
}


public void setCompanyId(String companyId){
    this.companyId = companyId;
}


public void setCompany(Company company){
    this.company = company;
}


public void setOutTypeName(String outTypeName){
    this.outTypeName = outTypeName;
}


public void setOutTypeId(String outTypeId){
    this.outTypeId = outTypeId;
}


}
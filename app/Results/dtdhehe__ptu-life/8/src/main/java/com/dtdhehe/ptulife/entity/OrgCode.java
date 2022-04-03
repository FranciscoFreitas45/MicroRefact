package com.dtdhehe.ptulife.entity;
 import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class OrgCode {

@Id
 private  Integer id;

 private  String orgStatus;

 private  String orgName;

 private  String remark;


public String getOrgName(){
    return orgName;
}


public void setOrgStatus(String orgStatus){
    this.orgStatus = orgStatus;
}


public String getOrgStatus(){
    return orgStatus;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setOrgName(String orgName){
    this.orgName = orgName;
}


public String getRemark(){
    return remark;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


}
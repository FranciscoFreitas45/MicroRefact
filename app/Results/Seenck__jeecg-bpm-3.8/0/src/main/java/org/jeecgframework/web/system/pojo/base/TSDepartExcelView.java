package org.jeecgframework.web.system.pojo.base;
 import org.jeecgframework.poi.excel.annotation.Excel;
public class TSDepartExcelView {

 private  long serialVersionUID;

@Excel(name = "机构ID", width = 50)
 private  String id;

@Excel(name = "机构名称", width = 20)
 private  String departName;

@Excel(name = "机构描述")
 private  String description;

@Excel(name = "机构父ID", width = 50)
 private  String parentId;

@Excel(name = "机构类型")
 private  String orgType;

@Excel(name = "电话", width = 20)
 private  String mobile;

@Excel(name = "传真", width = 20)
 private  String fax;

@Excel(name = "地址", width = 20)
 private  String address;


public void setDepartName(String departName){
    this.departName = departName;
}


public void setAddress(String address){
    this.address = address;
}


public String getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getFax(){
    return fax;
}


public void setOrgType(String orgType){
    this.orgType = orgType;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public String getOrgType(){
    return orgType;
}


public String getDepartName(){
    return departName;
}


public void setId(String id){
    this.id = id;
}


public void setParentId(String parentId){
    this.parentId = parentId;
}


public String getMobile(){
    return mobile;
}


public void setFax(String fax){
    this.fax = fax;
}


public String getAddress(){
    return address;
}


public String getParentId(){
    return parentId;
}


}
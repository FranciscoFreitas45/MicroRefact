package com.zis.shiro.dto;
 public class ShowPermissionDto {

 private  String registList;

 private  String purchaseList;

 private  String requirementList;

 private  String bookInfoList;

 private  String toolkitList;


public void setRegistList(String registList){
    this.registList = registList;
}


public void setToolkitList(String toolkitList){
    this.toolkitList = toolkitList;
}


public String getPurchaseList(){
    return purchaseList;
}


public void setRequirementList(String requirementList){
    this.requirementList = requirementList;
}


public String getRegistList(){
    return registList;
}


public String getBookInfoList(){
    return bookInfoList;
}


public String getRequirementList(){
    return requirementList;
}


public void setPurchaseList(String purchaseList){
    this.purchaseList = purchaseList;
}


public void setBookInfoList(String bookInfoList){
    this.bookInfoList = bookInfoList;
}


public String getToolkitList(){
    return toolkitList;
}


}
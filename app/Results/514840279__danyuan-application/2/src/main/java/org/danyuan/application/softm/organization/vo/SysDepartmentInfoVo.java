package org.danyuan.application.softm.organization.vo;
 public class SysDepartmentInfoVo {

 public  int pageNumber;

 public  int pageSize;

 public  String organizationId;


public int getPageNumber(){
    return pageNumber;
}


public int getPageSize(){
    return pageSize;
}


public String getOrganizationId(){
    return organizationId;
}


public void setPageNumber(int pageNumber){
    this.pageNumber = pageNumber;
}


public void setOrganizationId(String organizationId){
    this.organizationId = organizationId;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
}


}
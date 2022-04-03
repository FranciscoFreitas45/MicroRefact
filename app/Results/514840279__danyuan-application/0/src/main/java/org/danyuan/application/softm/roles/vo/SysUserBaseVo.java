package org.danyuan.application.softm.roles.vo;
 import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.resume.user.po.SysUserBaseInfo;
public class SysUserBaseVo extends Pagination<SysUserBaseInfo>{

 private String uuid;


@Override
public String getUuid(){
    return uuid;
}


public void setPageNumber(int pageNumber){
    this.pageNumber = pageNumber;
}


@Override
public void setSearchText(String searchText){
    this.searchText = searchText;
}


@Override
public void setUuid(String uuid){
    this.uuid = uuid;
}


@Override
public String getSearchText(){
    return searchText;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
}


}
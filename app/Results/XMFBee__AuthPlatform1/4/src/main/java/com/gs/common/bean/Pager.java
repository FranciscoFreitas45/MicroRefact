package com.gs.common.bean;
 import com.gs.bean.User;
import com.gs.Interface.User;
public class Pager {

 private  int pageSize;

 private  int pageNo;

 private  int totalRecords;

 private  User user;

public Pager() {
}public Pager(int pageNo, int pageSize, int totalRecords) {
    this.pageNo = pageNo;
    this.pageSize = pageSize;
    this.totalRecords = totalRecords;
}
public void setPageNo(int pageNo){
    this.pageNo = pageNo;
}


public int getPageSize(){
    return pageSize;
}


public void setTotalRecords(int totalRecords){
    this.totalRecords = totalRecords;
}


public User getUser(){
    return user;
}


public int getTotalRecords(){
    return totalRecords;
}


public int getPageNo(){
    return pageNo;
}


public void setUser(User user){
    this.user = user;
}


public int getBeginIndex(){
    return (pageNo - 1) * pageSize;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
}


public int getTotalPages(){
    return getTotalRecords() % pageSize == 0 ? getTotalRecords() / pageSize : getTotalRecords() / pageSize + 1;
}


}
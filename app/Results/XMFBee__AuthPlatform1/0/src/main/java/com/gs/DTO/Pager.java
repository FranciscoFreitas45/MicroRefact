package com.gs.DTO;
 import com.gs.bean.User;
import com.gs.Interface.User;
public class Pager {

 private  int pageSize;

 private  int pageNo;

 private  int totalRecords;

 private  User user;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";

public Pager() {
}public Pager(int pageNo, int pageSize, int totalRecords) {
    this.pageNo = pageNo;
    this.pageSize = pageSize;
    this.totalRecords = totalRecords;
}
public int getPageSize(){
    return pageSize;
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


public int getBeginIndex(){
    return (pageNo - 1) * pageSize;
}


public int getTotalPages(){
    return getTotalRecords() % pageSize == 0 ? getTotalRecords() / pageSize : getTotalRecords() / pageSize + 1;
}


public void setPageNo(int pageNo){
    this.pageNo = pageNo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPageNo"))

.queryParam("pageNo",pageNo)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPageSize"))

.queryParam("pageSize",pageSize)
;
restTemplate.put(builder.toUriString(),null);
}


}
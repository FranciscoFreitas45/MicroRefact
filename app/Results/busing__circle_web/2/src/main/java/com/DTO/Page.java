package com.DTO;
 import java.util.Map;
public class Page {

 public  int POP_ROWS;

 private  int totalRow;

 private  int currentPage;

 private  int totalPage;

 private  int rowsPerPage;

 private  Map<String,String> searchParam;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";

public Page() {
}/**
 * 构造器 初始化当前页面数
 *
 * @param curPage
 *            当前页面数
 */
public Page(int curPage) {
    this.currentPage = curPage;
}public Page(int curPage, int totalRow) {
    this.currentPage = curPage;
    this.totalRow = totalRow;
    this.count();
}
public int getCurrentPage(){
    return (this.currentPage);
}


public int getRowsPerPage(){
    return (this.rowsPerPage);
}


public Map<String,String> getSearchParam(){
    return searchParam;
}


public int getTotalRow(){
    return (this.totalRow);
}


public int getTotalPage(){
    return this.totalPage;
}


public void setSearchParam(Map<String,String> searchParam){
    this.searchParam = searchParam;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSearchParam"))

.queryParam("searchParam",searchParam)
;
restTemplate.put(builder.toUriString(),null);
}


}
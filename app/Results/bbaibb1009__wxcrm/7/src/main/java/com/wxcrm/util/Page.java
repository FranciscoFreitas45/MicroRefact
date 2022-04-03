package com.wxcrm.util;
 import java.util.List;
import java.util.Map;
public class Page {

 private  int PAGE_SIZE;

 private  int currentPage;

 private  int previousPage;

 private  int nextPage;

 private  int totalPages;

 private  int totalRows;

 private  int pageSize;

 private  int pageIndex;

 private  int maxRows;

 private  String sql;

 private  String sqlCnt;

 private  Object[] queryPara;

 private  List<Map<String,Object>> resultList;

public Page(String sql, String sqlCnt, String currentPage, String pageSize, Object[] queryPara) {
    this.sql = sql;
    this.sqlCnt = sqlCnt;
    this.queryPara = queryPara;
    this.currentPage = (currentPage == null || currentPage.length() == 0 || Integer.parseInt(currentPage) == 0) ? 1 : Integer.parseInt(currentPage);
    this.pageSize = (pageSize == null || pageSize.length() == 0 || Integer.parseInt(pageSize) == 0) ? PAGE_SIZE : Integer.parseInt(pageSize);
}public Page(String sql, String sqlCnt, String currentPage, String pageSize) {
    this(sql, sqlCnt, currentPage, pageSize, null);
}public Page() {
}
public int getTotalRows(){
    return totalRows;
}


public int getNextPage(){
    return nextPage;
}


public void setPreviousPage(int previousPage){
    this.previousPage = previousPage;
}


public void setCurrentPage(int currentPage){
    this.currentPage = currentPage;
}


public void setSqlCnt(String sqlCnt){
    this.sqlCnt = sqlCnt;
}


public Object[] getQueryPara(){
    return queryPara;
}


public void setQueryPara(Object[] queryPara){
    this.queryPara = queryPara;
}


public int getPreviousPage(){
    return previousPage;
}


public void setTotalRows(int totalRows){
    this.totalRows = totalRows;
    if (this.totalRows > 0) {
        this.totalPages = (this.totalRows % this.pageSize == 0 ? (this.totalRows / this.pageSize) : (this.totalRows / this.pageSize + 1));
        this.currentPage = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;
        this.previousPage = this.currentPage - 1;
        this.nextPage = this.currentPage + 1;
        this.pageIndex = (this.currentPage - 1) * this.pageSize;
        this.maxRows = this.currentPage * this.pageSize;
    }
}


public void setSql(String sql){
    this.sql = sql;
}


public int getPageIndex(){
    return pageIndex;
}


public int getTotalPages(){
    return totalPages;
}


public String getSqlCnt(){
    return sqlCnt;
}


public String getSql(){
    return sql;
}


public int getPageSize(){
    return pageSize;
}


public void setNextPage(int nextPage){
    this.nextPage = nextPage;
}


public List<Map<String,Object>> getResultList(){
    return resultList;
}


public void setResultList(List<Map<String,Object>> resultList){
    this.resultList = resultList;
}


public void setPageIndex(int pageIndex){
    this.pageIndex = pageIndex;
}


public void setMaxRows(int maxRows){
    this.maxRows = maxRows;
}


public int getCurrentPage(){
    return currentPage;
}


public int getMaxRows(){
    return maxRows;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
}


public void setTotalPages(int totalPages){
    this.totalPages = totalPages;
}


}
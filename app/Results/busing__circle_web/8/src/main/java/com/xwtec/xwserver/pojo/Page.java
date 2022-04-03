package com.xwtec.xwserver.pojo;
 import java.util.Map;
public class Page {

 public  int POP_ROWS;

 private  int totalRow;

 private  int currentPage;

 private  int totalPage;

 private  int rowsPerPage;

 private  Map<String,String> searchParam;

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
public void setTotalRow(int totalRow){
    this.totalRow = totalRow;
    this.count();
}


public void setTotalPage(int totalPage){
    this.totalPage = totalPage;
}


public void count(){
    if ((totalRow != 0) && (this.totalRow % this.rowsPerPage == 0)) {
        this.totalPage = this.totalRow / this.rowsPerPage;
    } else {
        this.totalPage = this.totalRow / this.rowsPerPage + 1;
    }
    if (this.currentPage > this.totalPage) {
        this.currentPage = this.totalPage;
    }
}


public int getCurrentPage(){
    return (this.currentPage);
}


public void setSearchParam(Map<String,String> searchParam){
    this.searchParam = searchParam;
}


public void setCurrentPage(int currentPage){
    this.currentPage = currentPage;
}


public int getRowsPerPage(){
    return (this.rowsPerPage);
}


public Map<String,String> getSearchParam(){
    return searchParam;
}


public void setRowsPerPage(int rowsPerPage){
    this.rowsPerPage = rowsPerPage;
}


public int getTotalRow(){
    return (this.totalRow);
}


public int getTotalPage(){
    return this.totalPage;
}


}
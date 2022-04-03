package com.gs.common.bean;
 import java.util.List;
public class Pager4EasyUI {

 private  int pageSize;

 private  int pageNo;

 private  int beginIndex;

 private  int total;

 private  List<T> rows;

public Pager4EasyUI() {
}public Pager4EasyUI(int total, List<T> rows) {
    this.total = total;
    this.rows = rows;
}
public void setPageNo(int pageNo){
    this.pageNo = pageNo;
}


public void setTotal(int total){
    this.total = total;
}


public int getPageSize(){
    return pageSize;
}


public void setRows(List<T> rows){
    this.rows = rows;
}


public int getTotal(){
    return total;
}


public int getPageNo(){
    return pageNo;
}


public List<T> getRows(){
    return rows;
}


public int getBeginIndex(){
    if (pageNo == 0) {
        pageNo = 1;
    }
    return (pageNo - 1) * pageSize;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
}


}
package com.uec.imonitor.es.bean.params;
 public class SortParams {

 private  String sortField;

 private  String sort;

public SortParams() {
}public SortParams(String sortField, String sort) {
    this.sort = sort;
    this.sortField = sortField;
}
public void setSort(String sort){
    this.sort = sort;
}


public String getSort(){
    return sort;
}


public String getSortField(){
    return sortField;
}


public void setSortField(String sortField){
    this.sortField = sortField;
}


}
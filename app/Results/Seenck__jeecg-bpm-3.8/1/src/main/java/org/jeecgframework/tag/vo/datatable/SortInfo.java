package org.jeecgframework.tag.vo.datatable;
 public class SortInfo {

 private  Integer columnId;

 private  SortDirection sortOrder;


public Integer getColumnId(){
    return columnId;
}


public void setSortOrder(SortDirection sortOrder){
    this.sortOrder = sortOrder;
}


public void setColumnId(Integer columnId){
    this.columnId = columnId;
}


public SortDirection getSortOrder(){
    return sortOrder;
}


}
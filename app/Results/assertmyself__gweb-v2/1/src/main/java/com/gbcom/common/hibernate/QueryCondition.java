package com.gbcom.common.hibernate;
 public class QueryCondition {

 private  Group group;

 private  String orderColumn;

 private  String orderType;

public QueryCondition() {
}
public void setGroup(Group group){
    this.group = group;
}


public String getOrderType(){
    return orderType;
}


public Group getGroup(){
    return group;
}


public void setOrderColumn(String orderColumn){
    this.orderColumn = orderColumn;
}


public void setOrderType(String orderType){
    this.orderType = orderType;
}


public String getOrderColumn(){
    return orderColumn;
}


}
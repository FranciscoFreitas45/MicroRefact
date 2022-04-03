package org.gliderwiki.framework.orm.sql.util;
 public class Search {

 public  String SEARCH_TYPE_SUBJECT;

 public  String SEARCH_TYPE_CONTENT;

 public  String SEARCH_TYPE_NICKNAME;

 private  String orderQuery;

 private  Object parameter;


public String getOrderQuery(){
    return orderQuery;
}


public void setParameter(Object parameter){
    this.parameter = parameter;
}


public void setOrderQuery(String orderQuery){
    this.orderQuery = orderQuery;
}


public Object getParameter(){
    return parameter;
}


}
package com.ukefu.webim.web.handler.api.request;
 import java.io.Serializable;
public class RequestValues implements Serializable{

 private  long serialVersionUID;

 private  QueryParams query;

 private  T data;


public void setData(T data){
    this.data = data;
}


public void setQuery(QueryParams query){
    this.query = query;
}


public QueryParams getQuery(){
    return query;
}


public T getData(){
    return data;
}


}
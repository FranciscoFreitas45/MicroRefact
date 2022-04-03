package com.gbcom.common.hibernate;
 public class Rule {

 private  String field;

 private  String op;

 private  String data;

 private  String searchtype;

public Rule() {
}public Rule(String field, String op, String data, String searchtype) {
    this.field = field;
    this.op = op;
    this.data = data;
    this.searchtype = searchtype;
}
public void setField(String field){
    this.field = field;
}


public void setData(String data){
    this.data = data;
}


public void setOp(String op){
    this.op = op;
}


public String getField(){
    return field;
}


public String getOp(){
    return op;
}


public String getData(){
    return data;
}


public String getSearchtype(){
    return searchtype;
}


public void setSearchtype(String searchtype){
    this.searchtype = searchtype;
}


}
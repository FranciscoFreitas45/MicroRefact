package org.danyuan.application.dbms.tabs.vo;
 public class MulteityParam {

 private  String operator;

 private  String userDesc;

 private  String userIndex;

 private  String value;


public String getUserIndex(){
    return userIndex;
}


public void setUserIndex(String userIndex){
    this.userIndex = userIndex;
}


public String getValue(){
    return value;
}


public String getUserDesc(){
    return userDesc;
}


public void setUserDesc(String userDesc){
    this.userDesc = userDesc;
}


public void setValue(String value){
    this.value = value;
}


public void setOperator(String operator){
    this.operator = operator;
}


@Override
public String toString(){
    return "MulteityParam [operator=" + operator + ", userDesc=" + userDesc + ", userIndex=" + userIndex + ", value=" + value + "]";
}


public String getOperator(){
    return operator;
}


}
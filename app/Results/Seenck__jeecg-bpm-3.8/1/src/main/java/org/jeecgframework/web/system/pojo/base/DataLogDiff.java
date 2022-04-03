package org.jeecgframework.web.system.pojo.base;
 public class DataLogDiff {

 private  long serialVersionUID;

 private  String name;

 private  String value1;

 private  String value2;

 private  String diff;


public void setName(String name){
    this.name = name;
}


public void setValue2(String value2){
    this.value2 = value2;
}


public String getName(){
    return name;
}


public void setValue1(String value1){
    this.value1 = value1;
}


public String getDiff(){
    return diff;
}


public String getValue1(){
    return value1;
}


public String getValue2(){
    return value2;
}


public void setDiff(String diff){
    this.diff = diff;
}


}
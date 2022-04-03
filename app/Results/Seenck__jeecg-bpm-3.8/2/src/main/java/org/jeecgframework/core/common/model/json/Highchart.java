package org.jeecgframework.core.common.model.json;
 import java.util.List;
public class Highchart {

 private  String name;

 private  String type;

 private  List data;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setData(List data){
    this.data = data;
}


public String getType(){
    return type;
}


public void setType(String type){
    this.type = type;
}


public List getData(){
    return data;
}


}
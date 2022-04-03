package com.ipe.module.core.web.util.RestRequest;
 import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
public class Sort {

 private  String property;

 private  String direction;


public String getProperty(){
    return property;
}


public String getDirection(){
    return direction;
}


public void setProperty(String property){
    this.property = property;
}


public void setDirection(String direction){
    this.direction = direction;
}


}
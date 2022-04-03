package com.zis.purchase.bean;
 import java.util.HashMap;
import java.util.Map;
public class InwarehouseBizType {

 public  String PURCHASE;

 public  String RETURN;

 public  String DIRECT;

 public  Map<String,String> map;


public String getDisplay(String inwarehouseBizType){
    return map.get(inwarehouseBizType);
}


}
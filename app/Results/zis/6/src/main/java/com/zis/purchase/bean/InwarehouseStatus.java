package com.zis.purchase.bean;
 import java.util.HashMap;
import java.util.Map;
public class InwarehouseStatus {

 public  String PROCESSING;

 public  String SUCCESS;

 public  String CANCEL;

 private  Map<String,String> map;


public String getDisplay(String inwarehouseStatus){
    return map.get(inwarehouseStatus);
}


}
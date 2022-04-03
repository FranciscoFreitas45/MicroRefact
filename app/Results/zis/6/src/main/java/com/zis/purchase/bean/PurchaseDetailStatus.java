package com.zis.purchase.bean;
 import java.util.HashMap;
import java.util.Map;
public class PurchaseDetailStatus {

 public  String PURCHASED;

 public  String CHECKED;

 public  String USELESS;

 private  Map<String,String> map;


public String getDisplay(String purchaseOrderStatus){
    return map.get(purchaseOrderStatus);
}


}
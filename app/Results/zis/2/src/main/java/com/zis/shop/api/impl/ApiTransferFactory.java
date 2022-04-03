package com.zis.shop.api.impl;
 import java.util.Map;
import com.zis.shop.api.ApiTransfer;
public class ApiTransferFactory {

 private  Map<String,ApiTransfer> apiMap;


public void setApiMap(Map<String,ApiTransfer> apiMap){
    this.apiMap = apiMap;
}


public ApiTransfer getInstance(String type){
    return apiMap.get(type);
}


}
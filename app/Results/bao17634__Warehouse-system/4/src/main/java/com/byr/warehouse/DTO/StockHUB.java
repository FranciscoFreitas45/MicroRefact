package com.byr.warehouse.DTO;
 import cn.afterturn.easypoi.excel.annotation.Excel;
public class StockHUB {

 private  String supplierName;

 private  String vendorCode;

 private  String supplyMateriCode;

 private  String weixinCode;

 private  String prodate;

 private  String goodNum;

 private  String storageDate;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";

public StockHUB(String supplierName, String vendorCode, String supplyMateriCode, String weixinCode, String prodate, String goodNum, String storageDate) {
    this.supplierName = supplierName;
    this.vendorCode = vendorCode;
    this.supplyMateriCode = supplyMateriCode;
    this.weixinCode = weixinCode;
    this.prodate = prodate;
    this.goodNum = goodNum;
    this.storageDate = storageDate;
}public StockHUB() {
}
public String getWeixinCode(){
    return weixinCode;
}


public String getSupplierName(){
    return supplierName;
}


public String getStorageDate(){
    return storageDate;
}


public String getVendorCode(){
    return vendorCode;
}


public String getSupplyMateriCode(){
    return supplyMateriCode;
}


public String getGoodNum(){
    return goodNum;
}


public String getProdate(){
    return prodate;
}


public void setSupplierName(String supplierName){
    this.supplierName = supplierName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSupplierName"))

.queryParam("supplierName",supplierName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setGoodNum(String goodNum){
    this.goodNum = goodNum;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setGoodNum"))

.queryParam("goodNum",goodNum)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStorageDate(String storageDate){
    this.storageDate = storageDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStorageDate"))

.queryParam("storageDate",storageDate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSupplyMateriCode(String supplyMateriCode){
    this.supplyMateriCode = supplyMateriCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSupplyMateriCode"))

.queryParam("supplyMateriCode",supplyMateriCode)
;
restTemplate.put(builder.toUriString(),null);
}


public void setProdate(String prodate){
    this.prodate = prodate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setProdate"))

.queryParam("prodate",prodate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWeixinCode(String weixinCode){
    this.weixinCode = weixinCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWeixinCode"))

.queryParam("weixinCode",weixinCode)
;
restTemplate.put(builder.toUriString(),null);
}


public void setVendorCode(String vendorCode){
    this.vendorCode = vendorCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVendorCode"))

.queryParam("vendorCode",vendorCode)
;
restTemplate.put(builder.toUriString(),null);
}


}
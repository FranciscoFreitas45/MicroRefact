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


public void setVendorCode(String vendorCode){
    this.vendorCode = vendorCode;
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


public void setProdate(String prodate){
    this.prodate = prodate;
}


public String getProdate(){
    return prodate;
}


}
package com.byr.warehouse.pojo;
 import cn.afterturn.easypoi.excel.annotation.Excel;
public class StockTheWay {

@Excel(name = "供应商名称")
 private  String supplierName;

@Excel(name = "VendorCode")
 private  String vendorCode;

@Excel(name = "供应商料号")
 private  String supplyMateriCode;

@Excel(name = "维信料号")
 private  String weixinCode;

@Excel(name = "生产日期")
 private  String prodate;

@Excel(name = "在途库存")
 private  String stockTheway;


public void setSupplyMateriCode(String supplyMateriCode){
    this.supplyMateriCode = supplyMateriCode;
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


public void setStockTheway(String stockTheway){
    this.stockTheway = stockTheway;
}


public void setSupplierName(String supplierName){
    this.supplierName = supplierName;
}


public String getStockTheway(){
    return stockTheway;
}


public String getVendorCode(){
    return vendorCode;
}


public String getSupplyMateriCode(){
    return supplyMateriCode;
}


public void setProdate(String prodate){
    this.prodate = prodate;
}


@Override
public String toString(){
    return "StockTheWay{" + "supplierName='" + supplierName + '\'' + ", vendorCode='" + vendorCode + '\'' + ", supplyMateriCode='" + supplyMateriCode + '\'' + ", weixinCode='" + weixinCode + '\'' + ", prodate='" + prodate + '\'' + ", stockTheway='" + stockTheway + '\'' + '}';
}


public void setWeixinCode(String weixinCode){
    this.weixinCode = weixinCode;
}


public String getProdate(){
    return prodate;
}


}
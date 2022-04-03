package com.gs.bean;
 public class CarBrand {

 private  String brandId;

 private  String brandName;

 private  String brandDes;

 private  String brandStatus;


public String getBrandName(){
    return brandName;
}


public void setBrandName(String brandName){
    this.brandName = brandName;
}


public void setBrandDes(String brandDes){
    this.brandDes = brandDes;
}


public void setBrandId(String brandId){
    this.brandId = brandId;
}


public String getBrandDes(){
    return brandDes;
}


public void setBrandStatus(String brandStatus){
    this.brandStatus = brandStatus;
}


public String getBrandStatus(){
    return brandStatus;
}


public String getBrandId(){
    return brandId;
}


}
package com.zis.bookinfo.dto;
 public class ShopItemInfoDTO {

 private  Integer bookId;

 private  String shopStatus;

 private  String shopName;

 private  String taobaoTitle;

 private  Integer taobaoCatagoryId;

 private  Boolean taobaoForbidden;


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public String getShopStatus(){
    return shopStatus;
}


public void setShopStatus(String shopStatus){
    this.shopStatus = shopStatus;
}


public String getShopName(){
    return shopName;
}


public String getTaobaoTitle(){
    return taobaoTitle;
}


public void setTaobaoForbidden(Boolean taobaoForbidden){
    this.taobaoForbidden = taobaoForbidden;
}


public Integer getBookId(){
    return bookId;
}


public void setTaobaoCatagoryId(Integer taobaoCatagoryId){
    this.taobaoCatagoryId = taobaoCatagoryId;
}


public void setShopName(String shopName){
    this.shopName = shopName;
}


public Integer getTaobaoCatagoryId(){
    return taobaoCatagoryId;
}


public Boolean getTaobaoForbidden(){
    return taobaoForbidden;
}


public void setTaobaoTitle(String taobaoTitle){
    this.taobaoTitle = taobaoTitle;
}


}
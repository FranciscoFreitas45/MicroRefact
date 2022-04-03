package com.zis.DTO;
 public class ShopItemInfoDTO {

 private  Integer bookId;

 private  String shopStatus;

 private  String shopName;

 private  String taobaoTitle;

 private  Integer taobaoCatagoryId;

 private  Boolean taobaoForbidden;


public String getShopStatus(){
    return shopStatus;
}


public String getShopName(){
    return shopName;
}


public String getTaobaoTitle(){
    return taobaoTitle;
}


public Integer getBookId(){
    return bookId;
}


public Integer getTaobaoCatagoryId(){
    return taobaoCatagoryId;
}


public Boolean getTaobaoForbidden(){
    return taobaoForbidden;
}


}
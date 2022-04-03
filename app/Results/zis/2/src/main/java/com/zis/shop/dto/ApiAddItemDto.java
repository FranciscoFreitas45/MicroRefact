package com.zis.shop.dto;
 import com.zis.bookinfo.dto.BookInfoAndDetailV2DTO;
import com.zis.shop.bean.ShopItemMapping;
public class ApiAddItemDto extends BookInfoAndDetailV2DTO{

 private  long serialVersionUID;

 private  ShopItemMapping shopItemMapping;


public ShopItemMapping getShopItemMapping(){
    return shopItemMapping;
}


public void setShopItemMapping(ShopItemMapping shopItemMapping){
    this.shopItemMapping = shopItemMapping;
}


}
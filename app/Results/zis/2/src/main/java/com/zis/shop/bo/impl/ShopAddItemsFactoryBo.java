package com.zis.shop.bo.impl;
 import java.util.Map;
import com.zis.shop.bo.ShopAddItemsBo;
public class ShopAddItemsFactoryBo {

 private  Map<String,ShopAddItemsBo> shopAddItemsBoMap;


public void setShopAddItemsBoMap(Map<String,ShopAddItemsBo> shopAddItemsBoMap){
    this.shopAddItemsBoMap = shopAddItemsBoMap;
}


public ShopAddItemsBo getInstance(String type){
    return shopAddItemsBoMap.get(type);
}


}
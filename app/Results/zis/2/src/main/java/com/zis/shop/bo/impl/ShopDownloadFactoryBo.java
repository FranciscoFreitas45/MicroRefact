package com.zis.shop.bo.impl;
 import java.util.Map;
import com.zis.shop.bo.ShopDownloadBo;
public class ShopDownloadFactoryBo {

 private  Map<String,ShopDownloadBo> shopDownloadItemsBoMap;


public void setShopDownloadItemsBoMap(Map<String,ShopDownloadBo> shopDownloadItemsBoMap){
    this.shopDownloadItemsBoMap = shopDownloadItemsBoMap;
}


public ShopDownloadBo getInstance(String type){
    return shopDownloadItemsBoMap.get(type);
}


}
package com.zis.shop.bo.impl;
 import java.util.List;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopItemMapping;
import com.zis.shop.dto.ShopDownloadInterfaceDto;
import com.zis.shop.dto.ShopDownloadYouZanDto;
public class YouZanShopDownloadBo extends AbstractShopDownloadBo{


@Override
public List<ShopItemMapping> downloadItems(ShopDownloadInterfaceDto dto,ShopInfo shop){
    ShopDownloadYouZanDto youzan = (ShopDownloadYouZanDto) dto;
    List<ShopItemMapping> listMapping = youzan.getApiQueryItemsDto().getShopItemMappings();
    return listMapping;
}


}
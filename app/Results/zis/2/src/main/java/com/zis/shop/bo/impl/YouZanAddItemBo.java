package com.zis.shop.bo.impl;
 import java.util.List;
import com.zis.shop.api.ApiTransfer;
import com.zis.shop.api.impl.ApiTransferFactory;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.dto.ApiAddItemDto;
public class YouZanAddItemBo extends AbstractShopAddItemBo{

 private  ApiTransferFactory factory;


public void setFactory(ApiTransferFactory factory){
    this.factory = factory;
}


public ApiTransferFactory getFactory(){
    return factory;
}


@Override
public void addItems2Shop(List<ApiAddItemDto> list,ShopInfo shop){
    ApiTransfer api = factory.getInstance(shop.getpName());
    for (ApiAddItemDto apiAddItemDto : list) {
        try {
            Long numIid = api.addItem(apiAddItemDto, shop);
            successUpdateMapping(numIid, apiAddItemDto.getShopItemMapping());
        } catch (Exception e) {
            failUpdateMapping(e.getMessage(), apiAddItemDto.getShopItemMapping());
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}


}
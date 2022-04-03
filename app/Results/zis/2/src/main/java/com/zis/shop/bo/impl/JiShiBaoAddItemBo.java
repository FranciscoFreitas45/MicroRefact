package com.zis.shop.bo.impl;
 import java.util.ArrayList;
import java.util.List;
import com.zis.bookinfo.bo.TaobaoCsvDataGenerateBO;
import com.zis.bookinfo.dto.BookInfoAndDetailV2DTO;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.dto.ApiAddItemDto;
import com.zis.Interface.TaobaoCsvDataGenerateBO;
public class JiShiBaoAddItemBo extends AbstractShopAddItemBo{

 private  TaobaoCsvDataGenerateBO taobaoCsvDataGenerateBO;


public void setTaobaoCsvDataGenerateBO(TaobaoCsvDataGenerateBO taobaoCsvDataGenerateBO){
    this.taobaoCsvDataGenerateBO = taobaoCsvDataGenerateBO;
}


@Override
public void addItems2Shop(List<ApiAddItemDto> list,ShopInfo shop){
    String[] emails = { shop.getEmails() };
    List<BookInfoAndDetailV2DTO> list1 = new ArrayList<BookInfoAndDetailV2DTO>();
    list1.addAll(list);
    try {
        taobaoCsvDataGenerateBO.generateV2(list1, emails);
        for (ApiAddItemDto apiAddItemDto : list) {
            successUpdateMapping(apiAddItemDto.getShopItemMapping());
        }
    } catch (Exception e) {
        for (ApiAddItemDto apiAddItemDto1 : list) {
            failUpdateMapping(e.getMessage(), apiAddItemDto1.getShopItemMapping());
        }
        throw new RuntimeException(e.getMessage(), e);
    }
}


}
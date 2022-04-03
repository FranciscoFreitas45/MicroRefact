package com.zis.shop.bo;
 import java.util.List;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.dto.ApiAddItemDto;
public interface ShopAddItemsBo {


public void addItems2Shop(List<ApiAddItemDto> list,ShopInfo shop)
;

}
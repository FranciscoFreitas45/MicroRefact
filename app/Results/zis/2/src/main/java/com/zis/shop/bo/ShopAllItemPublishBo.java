package com.zis.shop.bo;
 import java.util.List;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopItemMapping;
import com.zis.shop.dto.ApiAddItemDto;
public interface ShopAllItemPublishBo {


public List<ApiAddItemDto> allItemPublish(List<ShopItemMapping> bookList,ShopInfo shop)
;

}
package com.zis.shop.bo;
 import java.util.List;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopItemMapping;
public interface ShopBo {


public void stockChangeToUpdateOrAddItems(ShopItemMapping mapping,ShopInfo shop,Integer amount)
;

public void abstractAddAllProcessingItems(ShopInfo shop)
;

public void addProcessingItems(List<Integer> mappingIds,ShopInfo shop)
;

}
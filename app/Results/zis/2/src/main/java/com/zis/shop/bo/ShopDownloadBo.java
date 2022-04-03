package com.zis.shop.bo;
 import java.util.List;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopItemMapping;
import com.zis.shop.dto.ShopDownloadInterfaceDto;
public interface ShopDownloadBo {


public List<ShopItemMapping> downloadItems(ShopDownloadInterfaceDto dto,ShopInfo shop)
;

}
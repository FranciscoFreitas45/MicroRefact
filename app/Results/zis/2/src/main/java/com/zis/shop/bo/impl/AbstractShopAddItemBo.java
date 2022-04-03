package com.zis.shop.bo.impl;
 import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.shop.bean.ShopItemMapping;
import com.zis.shop.bean.ShopItemMapping.ShopItemMappingSystemStatus;
import com.zis.shop.bo.ShopAddItemsBo;
import com.zis.shop.repository.ShopItemMappingDao;
public class AbstractShopAddItemBo implements ShopAddItemsBo{

@Autowired
 private  ShopItemMappingDao dao;


public void failUpdateMapping(String msg,ShopItemMapping mapping){
    mapping.setUpdateTime(new Date());
    mapping.setSystemStatus(ShopItemMappingSystemStatus.FAIL.getValue());
    mapping.setFailReason(msg);
    this.dao.save(mapping);
}


public void successUpdateMapping(ShopItemMapping mapping){
    mapping.setUpdateTime(new Date());
    mapping.setUploadTime(new Date());
    mapping.setSystemStatus(ShopItemMappingSystemStatus.SUCCESS.getValue());
    // mapping.setFailReason(ShopItemMappingSystemStatus.WAIT_DOWNLOAD.getName());
    this.dao.save(mapping);
}


}
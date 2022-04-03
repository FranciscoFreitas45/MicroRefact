package com.zis.shop.api;
 import java.util.Date;
import java.util.List;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.dto.ApiAddItemDto;
import com.zis.shop.dto.ApiQueryItemsDto;
import com.zis.shop.dto.ApiUpdateItemDto;
import com.zis.shop.dto.ApplyRefundDTO;
import com.zis.shop.dto.LogisticsOfflineSendDTO;
import com.zis.trade.dto.CreateTradeOrderDTO;
public interface ApiTransfer {

 private  String value;

 private  String name;


public void setName(String name){
    this.name = name;
}
;

public Long addItem(ApiAddItemDto apiAddItemDto,ShopInfo shop)
;

public String getValue(){
    return value;
}
;

public boolean logisticsOfflineSend(ShopInfo shop,LogisticsOfflineSendDTO logisticsOfflineSendDTO)
;

public String getName(){
    return name;
}
;

public ApiQueryItemsDto queryItemsOnsale(ShopInfo shop,Long page)
;

public ApiQueryItemsDto queryItemsInventory(ShopInfo shop,String type,Long page)
;

public void setValue(String value){
    this.value = value;
}
;

public boolean updateItem(ApiUpdateItemDto apiUpdateItemDto,ShopInfo shop)
;

public List<ApplyRefundDTO> queryApplyRefundForDate(ShopInfo shop,Date startTime,Date endTime)
;

public List<CreateTradeOrderDTO> queryTradeForDate(ShopInfo shop,Date startTime,Date endTime)
;

}
package com.zis.shop.api.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.youzan.open.sdk.client.auth.Sign;
import com.youzan.open.sdk.client.core.DefaultKDTClient;
import com.youzan.open.sdk.client.core.KDTClient;
import com.youzan.open.sdk.gen.v1_0_0.api.KdtItemAdd;
import com.youzan.open.sdk.gen.v1_0_0.api.KdtItemUpdate;
import com.youzan.open.sdk.gen.v1_0_0.api.KdtItemUpdateListing;
import com.youzan.open.sdk.gen.v1_0_0.model.KdtItemAddParams;
import com.youzan.open.sdk.gen.v1_0_0.model.KdtItemAddResult;
import com.youzan.open.sdk.gen.v1_0_0.model.KdtItemUpdateListingParams;
import com.youzan.open.sdk.gen.v1_0_0.model.KdtItemUpdateParams;
import com.youzan.open.sdk.gen.v1_0_0.model.KdtItemUpdateResult;
import com.youzan.open.sdk.gen.v1_0_0.model.KdtItemsInventoryGetParams;
import com.youzan.open.sdk.gen.v1_0_0.model.KdtItemsOnsaleGetParams;
import com.youzan.open.sdk.model.ByteWrapper;
import com.zis.common.util.TextClearUtils;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopItemMapping;
import com.zis.shop.dto.ApiAddItemDto;
import com.zis.shop.dto.ApiQueryItemsDto;
import com.zis.shop.dto.ApiUpdateItemDto;
import com.zis.shop.dto.ApplyRefundDTO;
import com.zis.shop.dto.LogisticsOfflineSendDTO;
import com.zis.trade.dto.CreateTradeOrderDTO;
import com.zis.youzan.response.KdtItemsInventoryGetNew;
import com.zis.youzan.response.KdtItemsInventoryGetResultNew;
import com.zis.youzan.response.KdtItemsOnsaleGetNew;
import com.zis.youzan.response.KdtItemsOnsaleGetResultNew;
public class YouZanApiTransfer extends AbstractApiTransfer{

 private  Logger logger;


@Override
public Long addItem(ApiAddItemDto apiItemDto,ShopInfo shop){
    KDTClient client = new DefaultKDTClient(getSign(shop));
    KdtItemAddParams kdtItemAddParams = new KdtItemAddParams();
    String title = TextClearUtils.buildYouZanTitle(apiItemDto);
    Double price = apiItemDto.getBookPrice();
    Float currentPrice = (float) (price * shop.getDiscount());
    String outId = apiItemDto.getIsbn() + "-" + apiItemDto.getId();
    String desc = buildDesc(apiItemDto);
    kdtItemAddParams.setTitle(title);
    kdtItemAddParams.setDesc(desc);
    kdtItemAddParams.setPrice(currentPrice);
    kdtItemAddParams.setQuantity(apiItemDto.getStockBalance().toString());
    kdtItemAddParams.setPostFee(10f);
    kdtItemAddParams.setOuterId(outId);
    // TODO 上线前修改为1L为默认上架
    kdtItemAddParams.setIsDisplay(0L);
    kdtItemAddParams.setDeliveryTemplateId((long) shop.getDeliveryTemplateId());
    kdtItemAddParams.setTemplateId(shop.getTemplateId() + "");
    ByteWrapper[] byteWrappers = new ByteWrapper[1];
    // 文件被包装成ByteWrapper
    ByteWrapper byteWrapper = new ByteWrapper(outId + "page.jpg", getInputStream(apiItemDto.getImageUrl()));
    byteWrappers[0] = byteWrapper;
    kdtItemAddParams.setImages(byteWrappers);
    KdtItemAdd kdtItemAdd = new KdtItemAdd();
    kdtItemAdd.setAPIParams(kdtItemAddParams);
    KdtItemAddResult result = null;
    try {
        result = client.invoke(kdtItemAdd);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage());
    }
    return result.getItem().getNumIid();
}


@Override
public boolean logisticsOfflineSend(ShopInfo shop,LogisticsOfflineSendDTO logisticsOfflineSendDTO){
    // TODO Auto-generated method stub
    return false;
}


public ApiQueryItemsDto buildOnsaleItems(com.youzan.open.sdk.gen.v1_0_0.model.KdtItemsOnsaleGetResult.GoodsDetail[] result,Long totalResults,ShopInfo shop){
    ApiQueryItemsDto dto = new ApiQueryItemsDto();
    List<ShopItemMapping> list = new ArrayList<ShopItemMapping>();
    dto.setTotalResults(totalResults);
    for (com.youzan.open.sdk.gen.v1_0_0.model.KdtItemsOnsaleGetResult.GoodsDetail goods : result) {
        ShopItemMapping shopMapping = new ShopItemMapping();
        shopMapping.setItemOutNum(goods.getOuterId());
        shopMapping.setShopId(shop.getShopId());
        shopMapping.setCreateTime(new Date());
        shopMapping.setpItemId(goods.getNumIid());
        shopMapping.setSystemStatus(ShopItemMapping.ShopItemMappingSystemStatus.WAIT.getValue());
        shopMapping.setTitle(goods.getTitle());
        shopMapping.setUpdateTime(new Date());
        shopMapping.setUploadTime(new Date());
        list.add(shopMapping);
    }
    dto.setShopItemMappings(list);
    return dto;
}


@Override
public ApiQueryItemsDto queryItemsOnsale(ShopInfo shop,Long page){
    KDTClient client = new DefaultKDTClient(getSign(shop));
    KdtItemsOnsaleGetParams kdtItemsOnsaleGetParams = new KdtItemsOnsaleGetParams();
    KdtItemsOnsaleGetNew kdtItemsOnsaleGet = new KdtItemsOnsaleGetNew();
    kdtItemsOnsaleGet.setAPIParams(kdtItemsOnsaleGetParams);
    kdtItemsOnsaleGetParams.setPageSize(50L);
    kdtItemsOnsaleGetParams.setPageNo(page);
    kdtItemsOnsaleGetParams.setFields("title,num_iid,outer_id");
    kdtItemsOnsaleGetParams.setOrderBy("desc");
    KdtItemsOnsaleGetResultNew result = null;
    try {
        result = client.invoke(kdtItemsOnsaleGet);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException("download 在售商品 失败" + e.getMessage());
    }
    ApiQueryItemsDto dto = buildOnsaleItems(result.getItems(), result.getTotalResults(), shop);
    return dto;
}


public Sign getSign(ShopInfo shop){
    return new Sign(shop.getAppId().trim(), shop.getAppSecret().trim());
}


@Override
public ApiQueryItemsDto queryItemsInventory(ShopInfo shop,String type,Long page){
    KDTClient client = new DefaultKDTClient(getSign(shop));
    KdtItemsInventoryGetParams kdtItemsInventoryGetParams = new KdtItemsInventoryGetParams();
    KdtItemsInventoryGetNew kdtItemsInventoryGet = new KdtItemsInventoryGetNew();
    kdtItemsInventoryGet.setAPIParams(kdtItemsInventoryGetParams);
    kdtItemsInventoryGetParams.setPageSize(50L);
    kdtItemsInventoryGetParams.setPageNo(page);
    kdtItemsInventoryGetParams.setFields("title,num_iid,outer_id");
    kdtItemsInventoryGetParams.setOrderBy("desc");
    kdtItemsInventoryGetParams.setBanner(type);
    KdtItemsInventoryGetResultNew result = null;
    try {
        result = client.invoke(kdtItemsInventoryGet);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException("download 库存商品 失败" + e.getMessage());
    }
    ApiQueryItemsDto dto = buildInventoryItems(result.getItems(), result.getTotalResults(), shop);
    return dto;
}


public ApiQueryItemsDto buildInventoryItems(com.youzan.open.sdk.gen.v1_0_0.model.KdtItemsInventoryGetResult.GoodsDetail[] result,Long totalResults,ShopInfo shop){
    ApiQueryItemsDto dto = new ApiQueryItemsDto();
    List<ShopItemMapping> list = new ArrayList<ShopItemMapping>();
    dto.setTotalResults(totalResults);
    for (com.youzan.open.sdk.gen.v1_0_0.model.KdtItemsInventoryGetResult.GoodsDetail goods : result) {
        ShopItemMapping shopMapping = new ShopItemMapping();
        shopMapping.setItemOutNum(goods.getOuterId());
        shopMapping.setShopId(shop.getShopId());
        shopMapping.setCreateTime(new Date());
        shopMapping.setpItemId(goods.getNumIid());
        shopMapping.setSystemStatus(ShopItemMapping.ShopItemMappingSystemStatus.WAIT.getValue());
        shopMapping.setTitle(goods.getTitle());
        shopMapping.setUpdateTime(new Date());
        shopMapping.setUploadTime(new Date());
        list.add(shopMapping);
    }
    dto.setShopItemMappings(list);
    return dto;
}


@Override
public boolean updateItem(ApiUpdateItemDto apiUpdateItemDto,ShopInfo shop){
    KDTClient client = new DefaultKDTClient(getSign(shop));
    KdtItemUpdateParams kdtItemUpdateParams = new KdtItemUpdateParams();
    KdtItemUpdate kdtItemUpdate = new KdtItemUpdate();
    kdtItemUpdate.setAPIParams(kdtItemUpdateParams);
    kdtItemUpdateParams.setQuantity(apiUpdateItemDto.getAmount().toString());
    kdtItemUpdateParams.setNumIid(apiUpdateItemDto.getpItemId());
    KdtItemUpdateResult result = null;
    try {
        result = client.invoke(kdtItemUpdate);
    } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
    }
    // 下架商品强制上架
    if (!result.getItem().getIsListing()) {
        upLoadItem(apiUpdateItemDto, shop);
    }
    return true;
}


public boolean upLoadItem(ShopItemMapping shopItemMapping,ShopInfo shop){
    KDTClient client = new DefaultKDTClient(getSign(shop));
    KdtItemUpdateListingParams kdtItemUpdateListParams = new KdtItemUpdateListingParams();
    KdtItemUpdateListing kdtItemUpdateListing = new KdtItemUpdateListing();
    kdtItemUpdateListing.setAPIParams(kdtItemUpdateListParams);
    kdtItemUpdateListParams.setNumIid(shopItemMapping.getpItemId());
    try {
        client.invoke(kdtItemUpdateListing);
    } catch (Exception e) {
        throw new RuntimeException("商品上架失败：" + e.getMessage());
    }
    return true;
}


@Override
public List<ApplyRefundDTO> queryApplyRefundForDate(ShopInfo shop,Date startTime,Date endTime){
    // TODO Auto-generated method stub
    return null;
}


@Override
public List<CreateTradeOrderDTO> queryTradeForDate(ShopInfo shop,Date startTime,Date endTime){
    // TODO Auto-generated method stub
    return null;
}


}
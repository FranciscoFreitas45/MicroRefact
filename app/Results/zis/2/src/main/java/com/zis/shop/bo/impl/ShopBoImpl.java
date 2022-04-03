package com.zis.shop.bo.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoDetail;
import com.zis.bookinfo.service.BookService;
import com.zis.shop.api.ApiTransfer;
import com.zis.shop.api.ApiTransfer.NotItem;
import com.zis.shop.api.impl.ApiTransferFactory;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopItemMapping;
import com.zis.shop.bean.ShopItemMapping.ShopItemMappingSystemStatus;
import com.zis.shop.bean.UpdateItemLog;
import com.zis.shop.bean.UpdateItemLog.UpdateItemLogStatus;
import com.zis.shop.bo.ShopAddItemsBo;
import com.zis.shop.bo.ShopAllItemPublishBo;
import com.zis.shop.bo.ShopBo;
import com.zis.shop.dto.ApiAddItemDto;
import com.zis.shop.dto.ApiUpdateItemDto;
import com.zis.shop.repository.ShopItemMappingDao;
import com.zis.shop.repository.UpdateItemLogDao;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.repository.StorageProductDao;
import com.zis.storage.util.StorageUtil;
import com.zis.Interface.BookService;
import com.zis.Interface.StorageProductDao;
import com.zis.DTO.BookService;
import com.zis.DTO.StorageProduct;
@Component
public class ShopBoImpl implements ShopBo{

 private  Logger logger;

@Autowired
 private  ShopAllItemPublishBo shopAllItemPublishBo;

@Autowired
 private  ShopItemMappingDao shopItemMappingDao;

@Autowired
 private  BookService bookService;

@Autowired
 private  ThreadPoolTaskExecutor taskExecutor;

@Autowired
 private  ShopAddItemsFactoryBo addFactory;

@Autowired
 private  ApiTransferFactory apiFactory;

@Autowired
 private  UpdateItemLogDao updateItemLogDao;

@Autowired
 private  StorageProductDao storageProductDao;


public ApiAddItemDto getDto2DBorNet(ShopItemMapping mapping,ShopInfo shop){
    ApiAddItemDto dto = new ApiAddItemDto();
    dto.setShopItemMapping(mapping);
    dto.setDeliveryTemplateId(shop.getDeliveryTemplateId());
    Bookinfo book = this.bookService.findBookById(mapping.getBookId());
    BookinfoDetail detail = bookService.findBookInfoDetailByBookId(book.getId());
    // 如果没有图书详情，则从网上采集
    if (detail == null) {
        try {
            detail = bookService.captureBookInfoDetailFromNet(book.getId());
        } catch (Exception e) {
            // 单条错误不能影响全部记录
            String errorMsg = "[数据采集] 采集过程中发生错误，bookId=" + book.getId();
            logger.error(errorMsg, e);
            saveFailShopItemMapping(mapping, errorMsg);
        }
    }
    // 如果没有采集到图书详情，则跳过此条记录
    if (detail == null) {
        return null;
    }
    BeanUtils.copyProperties(book, dto);
    BeanUtils.copyProperties(detail, dto);
    return dto;
}


public Integer queryStockBalance(Integer bookId){
    StorageProduct storageProduct = storageProductDao.findBySkuIdAndRepoId(bookId, StorageUtil.getRepoId());
    if (storageProduct != null && storageProduct.getStockAvailable() != null) {
        return storageProduct.getStockAvailable();
    } else {
        return 0;
    }
}


public String apiUpdateItem(ShopItemMapping mapping,ShopInfo shop,Integer amount){
    ApiUpdateItemDto dto = new ApiUpdateItemDto();
    BeanUtils.copyProperties(mapping, dto);
    dto.setAmount(amount);
    ApiTransfer api = apiFactory.getInstance(shop.getpName());
    try {
        api.updateItem(dto, shop);
        return "";
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        return e.getMessage();
    }
}


public void saveFailShopItemMapping(ShopItemMapping mapping,String errorMsg){
    mapping.setSystemStatus(ShopItemMappingSystemStatus.FAIL.getValue());
    mapping.setUpdateTime(new Date());
    mapping.setFailReason(errorMsg);
    this.shopItemMappingDao.save(mapping);
}


@Override
public void stockChangeToUpdateOrAddItems(ShopItemMapping mapping,ShopInfo shop,Integer amount){
    // 如果是发布成功数据
    if (ShopItemMappingSystemStatus.SUCCESS.getValue().equals(mapping.getSystemStatus()) && mapping.getpItemId() != null) {
        updateItems(mapping, shop, amount);
    } else {
    // 其他状态不做处理
    }
}


public void saveUpdateItemLog(ShopItemMapping mapping,Integer amount,String msg){
    UpdateItemLog log = new UpdateItemLog();
    log.setAmount(amount);
    log.setDescription(msg);
    log.setBookId(mapping.getBookId());
    log.setShopId(mapping.getShopId());
    log.setCreateTime(new Date());
    log.setMappingId(mapping.getId());
    log.setUpdateTime(new Date());
    if (StringUtils.isBlank(msg)) {
        log.setStatus(UpdateItemLogStatus.SUCCESS.getValue());
    } else {
        log.setStatus(UpdateItemLogStatus.FAIL.getValue());
    }
    this.updateItemLogDao.save(log);
}


@Override
public void abstractAddAllProcessingItems(ShopInfo shop){
    Thread task = new Thread(new Runnable() {

        public void run() {
            Pageable page = new PageRequest(0, 500);
            Page<ShopItemMapping> pageList = null;
            do {
                pageList = shopItemMappingDao.findByShopIdAndSystemStatus(shop.getShopId(), ShopItemMappingSystemStatus.PROCESSING.getValue(), page);
                List<ShopItemMapping> bookList = pageList.getContent();
                List<ApiAddItemDto> apiList = shopAllItemPublishBo.allItemPublish(bookList, shop);
                ShopAddItemsBo bo = addFactory.getInstance(shop.getpName());
                bo.addItems2Shop(apiList, shop);
                page = pageList.nextPageable();
            } while (pageList.hasNext());
        }
    });
    taskExecutor.execute(task);
}


public List<ApiAddItemDto> queryApiAddItemDtos(List<ShopItemMapping> mappings,ShopInfo shop){
    List<ApiAddItemDto> resultList = new ArrayList<ApiAddItemDto>();
    for (ShopItemMapping s : mappings) {
        ApiAddItemDto dto = getDto2DBorNet(s, shop);
        if (dto == null) {
            continue;
        }
        // 查询库存量
        Integer stockBalance = queryStockBalance(s.getBookId());
        if (stockBalance > 0) {
            dto.setStockBalance(stockBalance);
        } else {
            // 无库存量 状态更新回wait
            saveWaitShopItemMapping(s);
            continue;
        }
        resultList.add(dto);
    }
    return resultList;
}


public void run(){
    Pageable page = new PageRequest(0, 500);
    Page<ShopItemMapping> pageList = null;
    do {
        pageList = shopItemMappingDao.findByShopIdAndSystemStatus(shop.getShopId(), ShopItemMappingSystemStatus.PROCESSING.getValue(), page);
        List<ShopItemMapping> bookList = pageList.getContent();
        List<ApiAddItemDto> apiList = shopAllItemPublishBo.allItemPublish(bookList, shop);
        ShopAddItemsBo bo = addFactory.getInstance(shop.getpName());
        bo.addItems2Shop(apiList, shop);
        page = pageList.nextPageable();
    } while (pageList.hasNext());
}


public void saveWaitShopItemMapping(ShopItemMapping mapping){
    mapping.setSystemStatus(ShopItemMappingSystemStatus.WAIT.getValue());
    mapping.setUpdateTime(new Date());
    this.shopItemMappingDao.save(mapping);
}


public void updateItems(ShopItemMapping mapping,ShopInfo shop,Integer amount){
    String apiMsg = apiUpdateItem(mapping, shop, amount);
    saveUpdateItemLog(mapping, amount, apiMsg);
    boolean youzanDelete = NotItem.YOU_ZAN.getValue().equals(apiMsg);
    boolean taobaoDelete = NotItem.TAO_BAO.getValue().equals(apiMsg);
    if (youzanDelete || taobaoDelete) {
        mapping.setSystemStatus(ShopItemMappingSystemStatus.DELETE.getValue());
        mapping.setUpdateTime(new Date());
        this.shopItemMappingDao.save(mapping);
    } else {
    // 其他状态不做处理
    }
}


@Override
public void addProcessingItems(List<Integer> mappingIds,ShopInfo shop){
    List<ShopItemMapping> list = this.shopItemMappingDao.findByShopIdAndSystemStatusAndIdIn(shop.getShopId(), ShopItemMappingSystemStatus.PROCESSING.getValue(), mappingIds);
    List<ApiAddItemDto> apiList = queryApiAddItemDtos(list, shop);
    ShopAddItemsBo bo = addFactory.getInstance(shop.getpName());
    bo.addItems2Shop(apiList, shop);
}


}
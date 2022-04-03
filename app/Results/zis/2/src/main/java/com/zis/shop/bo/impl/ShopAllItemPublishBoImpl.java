package com.zis.shop.bo.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoDetail;
import com.zis.bookinfo.service.BookService;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopItemMapping;
import com.zis.shop.bean.ShopItemMapping.ShopItemMappingSystemStatus;
import com.zis.shop.bo.ShopAllItemPublishBo;
import com.zis.shop.dto.ApiAddItemDto;
import com.zis.shop.repository.ShopItemMappingDao;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.repository.StorageProductDao;
import com.zis.storage.util.StorageUtil;
import com.zis.Interface.StorageProductDao;
import com.zis.Interface.BookService;
@Component
public class ShopAllItemPublishBoImpl implements ShopAllItemPublishBo{

@Autowired
 private  StorageProductDao storageProductDao;

@Autowired
 private  ShopItemMappingDao shopItemMappingDao;

@Autowired
 private  BookService bookService;


public Integer queryStockBalance(Integer bookId){
    StorageProduct storageProduct = storageProductDao.findBySkuIdAndRepoId(bookId, StorageUtil.getRepoId());
    if (storageProduct != null && storageProduct.getStockAvailable() != null) {
        return storageProduct.getStockAvailable();
    } else {
        return 0;
    }
}


public void saveFailShopItemMapping(ShopItemMapping mapping,String errorMsg){
    mapping.setSystemStatus(ShopItemMappingSystemStatus.FAIL.getValue());
    mapping.setUpdateTime(new Date());
    mapping.setFailReason(errorMsg);
    this.shopItemMappingDao.save(mapping);
}


public ApiAddItemDto getDto2DB(ShopItemMapping mapping,ShopInfo shop){
    ApiAddItemDto dto = new ApiAddItemDto();
    dto.setShopItemMapping(mapping);
    dto.setDeliveryTemplateId(shop.getDeliveryTemplateId());
    Bookinfo book = this.bookService.findBookById(mapping.getBookId());
    BookinfoDetail detail = bookService.findBookInfoDetailByBookId(book.getId());
    // 如果没有采集到图书详情，则跳过此条记录
    if (detail == null) {
        return null;
    }
    BeanUtils.copyProperties(book, dto);
    BeanUtils.copyProperties(detail, dto);
    return dto;
}


@Override
@Transactional
public List<ApiAddItemDto> allItemPublish(List<ShopItemMapping> bookList,ShopInfo shop){
    List<ApiAddItemDto> apiList = buildApiAddItemDtos(bookList, shop);
    return apiList;
}


public void saveWaitShopItemMapping(ShopItemMapping mapping){
    mapping.setSystemStatus(ShopItemMappingSystemStatus.WAIT.getValue());
    mapping.setUpdateTime(new Date());
    this.shopItemMappingDao.save(mapping);
}


public List<ApiAddItemDto> buildApiAddItemDtos(List<ShopItemMapping> mappings,ShopInfo shop){
    List<ApiAddItemDto> resultList = new ArrayList<ApiAddItemDto>();
    for (ShopItemMapping s : mappings) {
        ApiAddItemDto dto = getDto2DB(s, shop);
        if (dto == null) {
            saveFailShopItemMapping(s, "图书详情缺失");
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


}
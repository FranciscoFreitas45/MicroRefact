package com.zis.shop.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.shop.bean.ShopItemMapping;
public interface ShopItemMappingDao extends PagingAndSortingRepository<ShopItemMapping, Integer>{

 public  String SHOP_STATUS_DELETE;

 public  String SHOP_STATUS_WAIT;

 public  String SHOP_STATUS_PROCESSING;

 public  String SHOP_STATUS_FAIL;


public ShopItemMapping findByShopIdAndBookIdAndSystemStatus(Integer shopId,Integer bookId,String systemStatus)
;

public Page<ShopItemMapping> findByShopIdAndSystemStatus(Integer shopId,String systemStatus,Pageable page)
;

public ShopItemMapping findByShopIdAndBookId(Integer shopId,Integer bookId)
;

public Page<ShopItemMapping> findByShopIdAndSystemStatusAndBookIdIn(Integer shopId,String systemStatus,List<Integer> bookIds,Pageable page)
;

public List<ShopItemMapping> findByShopIdAndSystemStatusAndIdIn(Integer shopId,String systemStatus,List<Integer> ids)
;

@Modifying
@Query(value = "UPDATE ShopItemMapping s SET s.systemStatus = '" + SHOP_STATUS_PROCESSING + "' WHERE s.shopId =:shopId AND s.systemStatus = '" + SHOP_STATUS_WAIT + "'")
public int updateShopStatusWaitToProcessing(Integer shopId)
;

@Modifying
@Query(value = "UPDATE ShopItemMapping s SET s.systemStatus = '" + SHOP_STATUS_PROCESSING + "' WHERE s.shopId =:shopId AND s.systemStatus = '" + SHOP_STATUS_FAIL + "'")
public int updateShopStatusFailToProcessing(Integer shopId)
;

public Page<ShopItemMapping> findByShopId(Integer shopId,Pageable page)
;

}
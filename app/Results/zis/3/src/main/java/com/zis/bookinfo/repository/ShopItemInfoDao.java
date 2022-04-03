package com.zis.bookinfo.repository;
 import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.zis.bookinfo.bean.ShopItemInfo;
public interface ShopItemInfoDao extends CrudRepository<ShopItemInfo, Integer>{


public List<ShopItemInfo> findByShopNameAndBookId(String shopName,Integer bookId)
;

}
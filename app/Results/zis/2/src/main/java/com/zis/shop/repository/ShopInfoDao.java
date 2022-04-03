package com.zis.shop.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.shop.bean.ShopInfo;
public interface ShopInfoDao extends PagingAndSortingRepository<ShopInfo, Integer>{

 final  String NORMAL;

 final  String DELETE;

 final  String NEW;


@Query(value = "FROM ShopInfo WHERE shopId = :shopId AND companyId = :companyId AND status <> '" + DELETE + "'")
public ShopInfo findByCompanyIdAndShopId(Integer companyId,Integer shopId)
;

@Query(value = "FROM ShopInfo WHERE pName = :pName AND companyId = :companyId AND shopName = :shopName AND status <> '" + DELETE + "'")
public ShopInfo findByShopNameAndPNameAndCompanyId(String shopName,String pName,Integer companyId)
;

@Query(value = "FROM ShopInfo WHERE companyId = :companyId AND status <> '" + DELETE + "'")
public List<ShopInfo> findByCompanyId(Integer companyId)
;

}
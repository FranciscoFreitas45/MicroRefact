package com.easyshopping.dao;
 import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.ProductNotify;
public interface ProductNotifyDao extends BaseDao<ProductNotify, Long>{


public Long count(Member member,Boolean isMarketable,Boolean isOutOfStock,Boolean hasSent)
;

public boolean exists(Product product,String email)
;

public Page<ProductNotify> findPage(Member member,Boolean isMarketable,Boolean isOutOfStock,Boolean hasSent,Pageable pageable)
;

}
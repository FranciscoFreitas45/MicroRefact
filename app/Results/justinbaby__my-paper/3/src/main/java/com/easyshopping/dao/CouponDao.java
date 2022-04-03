package com.easyshopping.dao;
 import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Coupon;
public interface CouponDao extends BaseDao<Coupon, Long>{


public Page<Coupon> findPage(Boolean isEnabled,Boolean isExchange,Boolean hasExpired,Pageable pageable)
;

}
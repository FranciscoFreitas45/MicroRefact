package com.easyshopping.service;
 import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Coupon;
public interface CouponService extends BaseService<Coupon, Long>{


public Page<Coupon> findPage(Boolean isEnabled,Boolean isExchange,Boolean hasExpired,Pageable pageable)
;

}
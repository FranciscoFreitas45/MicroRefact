package com.easyshopping.Interface;
public interface CouponCodeService {

   public Long count(Coupon coupon,Member member,Boolean hasBegun,Boolean hasExpired,Boolean isUsed);
}
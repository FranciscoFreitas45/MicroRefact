package com.easyshopping.Interface;
public interface CouponCodeDao {

   public Object lock(Object Object);
   public Object merge(Object Object);
   public List<CouponCode> build(Coupon coupon,Member member,Integer count);
}
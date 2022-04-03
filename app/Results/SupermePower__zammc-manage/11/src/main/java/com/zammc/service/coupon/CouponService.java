package com.zammc.service.coupon;
 import com.zammc.domain.coupon.CouponEntity;
import com.zammc.page.PageBean;
public interface CouponService {


public void deleteCoupon(CouponEntity couponEntity)
;

public void addCoupon(CouponEntity couponEntity)
;

public void queryCouponPage(PageBean pageBean)
;

}
package com.easyshopping.dao;
 import java.util.List;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Coupon;
import com.easyshopping.entity.CouponCode;
import com.easyshopping.entity.Member;
public interface CouponCodeDao extends BaseDao<CouponCode, Long>{


public boolean codeExists(String code)
;

public CouponCode findByCode(String code)
;

public List<CouponCode> build(Coupon coupon,Member member,Integer count)
;

public Long count(Coupon coupon,Member member,Boolean hasBegun,Boolean hasExpired,Boolean isUsed)
;

public Page<CouponCode> findPage(Member member,Pageable pageable)
;

}
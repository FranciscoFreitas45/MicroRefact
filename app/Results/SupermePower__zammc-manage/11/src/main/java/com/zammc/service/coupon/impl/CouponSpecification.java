package com.zammc.service.coupon.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.coupon.CouponEntity;
import org.springframework.data.jpa.domain.Specification;
public class CouponSpecification {


public Specification where(){
    return Specifications.<CouponEntity>and().eq("dataStatus", "0").build();
}


}
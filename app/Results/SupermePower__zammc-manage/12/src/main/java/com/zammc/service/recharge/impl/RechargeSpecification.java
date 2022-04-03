package com.zammc.service.recharge.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.recharge.RechargeOrderEntity;
import org.springframework.data.jpa.domain.Specification;
public class RechargeSpecification {


public Specification<RechargeOrderEntity> where(RechargeOrderEntity request){
    return Specifications.<RechargeOrderEntity>and().eq("dataStatus", "0").build();
}


}
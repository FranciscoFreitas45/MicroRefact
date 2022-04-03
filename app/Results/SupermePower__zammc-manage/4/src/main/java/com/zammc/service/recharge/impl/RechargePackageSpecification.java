package com.zammc.service.recharge.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.recharge.RechargePackageEntity;
import org.springframework.data.jpa.domain.Specification;
public class RechargePackageSpecification {


public Specification<RechargePackageEntity> where(RechargePackageEntity request){
    return Specifications.<RechargePackageEntity>and().eq("dataStatus", "0").build();
}


}
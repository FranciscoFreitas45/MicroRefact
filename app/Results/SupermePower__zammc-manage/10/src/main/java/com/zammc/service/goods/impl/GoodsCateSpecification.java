package com.zammc.service.goods.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.goods.GoodsCateEntity;
import org.springframework.data.jpa.domain.Specification;
public class GoodsCateSpecification {


public Specification<GoodsCateEntity> where(GoodsCateEntity request){
    if (null != request.getCateName()) {
        return Specifications.<GoodsCateEntity>and().eq("dataStatus", "0").eq("orderId", request.getCateName()).build();
    } else if (null == request.getCateName()) {
        return Specifications.<GoodsCateEntity>and().eq("dataStatus", "0").build();
    } else {
        return null;
    }
}


}
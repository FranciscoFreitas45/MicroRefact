package com.zammc.service.order.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.order.OrderInfoEntity;
import org.springframework.data.jpa.domain.Specification;
public class OrderInfoSpecification {


public Specification<OrderInfoEntity> where(OrderInfoEntity request){
    // 全部
    if (request.getOrderId() != 0) {
        return Specifications.<OrderInfoEntity>and().eq("dataStatus", "0").eq("orderId", request.getOrderId()).eq("isConfirm", "1").build();
    } else if (request.getOrderId() == 0) {
        return Specifications.<OrderInfoEntity>and().eq("dataStatus", "0").eq("isConfirm", "1").build();
    } else {
        return null;
    }
}


}
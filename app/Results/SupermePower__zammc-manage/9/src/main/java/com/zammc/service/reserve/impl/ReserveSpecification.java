package com.zammc.service.reserve.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.reserve.ReserveEntity;
import org.springframework.data.jpa.domain.Specification;
public class ReserveSpecification {


public Specification<ReserveEntity> where(ReserveEntity request){
    if (null != request.getMobile()) {
        return Specifications.<ReserveEntity>and().eq("dataStatus", "0").eq("mobile", request.getMobile()).build();
    } else if (null == request.getMobile()) {
        return Specifications.<ReserveEntity>and().eq("dataStatus", "0").build();
    } else {
        return null;
    }
}


}
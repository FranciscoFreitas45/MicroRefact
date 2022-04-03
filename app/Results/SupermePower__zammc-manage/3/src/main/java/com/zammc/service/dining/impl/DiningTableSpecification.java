package com.zammc.service.dining.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.table.DiningTableEntity;
import org.springframework.data.jpa.domain.Specification;
public class DiningTableSpecification {


public Specification<DiningTableEntity> where(DiningTableEntity request){
    if (null != request.getTableCode()) {
        return Specifications.<DiningTableEntity>and().eq("dataStatus", "0").eq("tableCode", request.getTableCode()).build();
    } else if (null == request.getTableCode()) {
        return Specifications.<DiningTableEntity>and().eq("dataStatus", "0").build();
    } else {
        return null;
    }
}


}
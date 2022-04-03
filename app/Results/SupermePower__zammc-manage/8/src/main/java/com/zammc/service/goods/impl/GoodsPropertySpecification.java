package com.zammc.service.goods.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.goods.GoodsPropertyEntity;
import org.springframework.data.jpa.domain.Specification;
public class GoodsPropertySpecification {


public Specification<GoodsPropertyEntity> where(){
    return Specifications.<GoodsPropertyEntity>and().eq("dataStatus", "0").build();
}


}
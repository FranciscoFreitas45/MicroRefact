package com.zammc.service.restaurant.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.restaurant.RestaurantPropertyEntity;
import org.springframework.data.jpa.domain.Specification;
public class RestaurantDetailSpecification {


public Specification<RestaurantPropertyEntity> where(RestaurantPropertyEntity propertyEntity){
    return Specifications.<RestaurantPropertyEntity>and().eq("dataStatus", "0").build();
}


}
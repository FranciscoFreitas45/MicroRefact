package com.zammc.service.activity.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.activity.ActivityEntity;
import org.springframework.data.jpa.domain.Specification;
public class ActivitySpecification {


public Specification<ActivityEntity> where(ActivityEntity request){
    return Specifications.<ActivityEntity>and().eq("dataStatus", "0").build();
}


}
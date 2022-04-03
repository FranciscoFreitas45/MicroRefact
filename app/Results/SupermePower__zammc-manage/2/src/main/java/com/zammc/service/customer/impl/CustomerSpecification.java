package com.zammc.service.customer.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.user.UserInfoEntity;
import org.springframework.data.jpa.domain.Specification;
public class CustomerSpecification {


public Specification<UserInfoEntity> where(UserInfoEntity request){
    if (null != request.getNickName()) {
        return Specifications.<UserInfoEntity>and().eq("dataStatus", "0").eq("nickName", request.getNickName()).build();
    } else if (null == request.getNickName()) {
        return Specifications.<UserInfoEntity>and().eq("dataStatus", "0").build();
    } else {
        return null;
    }
}


}
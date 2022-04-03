package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.entity.MemberAttribute;
public interface MemberAttributeService extends BaseService<MemberAttribute, Long>{


public List<MemberAttribute> findList(String cacheRegion)
;

public Integer findUnusedPropertyIndex()
;

}
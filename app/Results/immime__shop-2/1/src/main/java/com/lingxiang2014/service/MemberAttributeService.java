package com.lingxiang2014.service;
 import java.util.List;
import com.lingxiang2014.entity.MemberAttribute;
public interface MemberAttributeService extends BaseService<MemberAttribute, Long>{


public List<MemberAttribute> findList(String cacheRegion)
;

public Integer findUnusedPropertyIndex()
;

}
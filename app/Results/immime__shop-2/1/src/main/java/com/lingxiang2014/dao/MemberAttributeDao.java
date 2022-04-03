package com.lingxiang2014.dao;
 import java.util.List;
import com.lingxiang2014.entity.MemberAttribute;
public interface MemberAttributeDao extends BaseDao<MemberAttribute, Long>{


public List<MemberAttribute> findList()
;

public Integer findUnusedPropertyIndex()
;

}
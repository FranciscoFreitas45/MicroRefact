package com.easyshopping.dao;
 import java.util.List;
import com.easyshopping.entity.MemberAttribute;
public interface MemberAttributeDao extends BaseDao<MemberAttribute, Long>{


public List<MemberAttribute> findList()
;

public Integer findUnusedPropertyIndex()
;

}
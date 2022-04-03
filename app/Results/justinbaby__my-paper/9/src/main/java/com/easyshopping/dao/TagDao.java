package com.easyshopping.dao;
 import java.util.List;
import com.easyshopping.entity.Tag;
import com.easyshopping.entity.Tag.Type;
public interface TagDao extends BaseDao<Tag, Long>{


public List<Tag> findList(Type type)
;

}
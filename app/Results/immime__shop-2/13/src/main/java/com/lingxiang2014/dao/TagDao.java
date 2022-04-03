package com.lingxiang2014.dao;
 import java.util.List;
import com.lingxiang2014.entity.Tag;
import com.lingxiang2014.entity.Tag.Type;
public interface TagDao extends BaseDao<Tag, Long>{


public List<Tag> findList(Type type)
;

}
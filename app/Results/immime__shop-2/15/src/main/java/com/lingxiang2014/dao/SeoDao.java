package com.lingxiang2014.dao;
 import com.lingxiang2014.entity.Seo;
import com.lingxiang2014.entity.Seo.Type;
public interface SeoDao extends BaseDao<Seo, Long>{


public Seo find(Type type)
;

}
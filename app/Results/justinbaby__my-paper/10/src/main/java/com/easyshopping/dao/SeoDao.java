package com.easyshopping.dao;
 import com.easyshopping.entity.Seo;
import com.easyshopping.entity.Seo.Type;
public interface SeoDao extends BaseDao<Seo, Long>{


public Seo find(Type type)
;

}
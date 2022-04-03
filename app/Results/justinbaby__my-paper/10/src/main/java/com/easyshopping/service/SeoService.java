package com.easyshopping.service;
 import com.easyshopping.entity.Seo;
import com.easyshopping.entity.Seo.Type;
public interface SeoService extends BaseService<Seo, Long>{


public Seo find(Type type,String cacheRegion)
;

}
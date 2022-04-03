package com.lingxiang2014.service;
 import com.lingxiang2014.entity.Seo;
import com.lingxiang2014.entity.Seo.Type;
public interface SeoService extends BaseService<Seo, Long>{


public Seo find(Type type,String cacheRegion)
;

}
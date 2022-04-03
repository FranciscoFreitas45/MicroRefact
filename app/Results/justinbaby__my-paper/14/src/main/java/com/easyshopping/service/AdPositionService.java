package com.easyshopping.service;
 import com.easyshopping.entity.AdPosition;
public interface AdPositionService extends BaseService<AdPosition, Long>{


public AdPosition find(Long id,String cacheRegion)
;

}
package com.lingxiang2014.service;
 import com.lingxiang2014.entity.AdPosition;
public interface AdPositionService extends BaseService<AdPosition, Long>{


public AdPosition find(Long id,String cacheRegion)
;

}
package com.oa.service;
 import com.oa.entity.OaHallEntity;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
public interface OaHallServiceI extends CommonService{


public Serializable save(OaHallEntity entity)
;

public void delete(OaHallEntity entity)
;

public void saveOrUpdate(OaHallEntity entity)
;

}
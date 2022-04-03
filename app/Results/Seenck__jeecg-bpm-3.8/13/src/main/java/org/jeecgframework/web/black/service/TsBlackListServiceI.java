package org.jeecgframework.web.black.service;
 import org.jeecgframework.web.black.entity.TsBlackListEntity;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
public interface TsBlackListServiceI extends CommonService{


public Serializable save(TsBlackListEntity entity)
;

public void delete(TsBlackListEntity entity)
;

public void saveOrUpdate(TsBlackListEntity entity)
;

}
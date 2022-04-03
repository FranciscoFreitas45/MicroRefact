package org.jeecgframework.web.system.service;
 import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSCompanyPositionEntity;
public interface TSCompanyPositionServiceI extends CommonService{


public Serializable save(TSCompanyPositionEntity entity)
;

public void delete(TSCompanyPositionEntity entity)
;

public void saveOrUpdate(TSCompanyPositionEntity entity)
;

}
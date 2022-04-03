package org.jeecgframework.web.system.service;
 import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSDictTableConfigEntity;
public interface TSDictTableConfigServiceI extends CommonService{


public Object getDictText(String dictionary,String dictCondition,String value)
;

public boolean checkDictAuth(String dictionary,String dictCondition)
;

public Serializable save(TSDictTableConfigEntity entity)
;

public void delete(TSDictTableConfigEntity entity)
;

public void saveOrUpdate(TSDictTableConfigEntity entity)
;

}
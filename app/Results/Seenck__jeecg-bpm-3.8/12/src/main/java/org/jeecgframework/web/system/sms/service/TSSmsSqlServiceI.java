package org.jeecgframework.web.system.sms.service;
 import org.jeecgframework.web.system.sms.entity.TSSmsSqlEntity;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
import java.util.Map;
public interface TSSmsSqlServiceI extends CommonService{


public Serializable save(T entity)
;

public boolean doDelSql(TSSmsSqlEntity t)
;

public boolean doUpdateSql(TSSmsSqlEntity t)
;

public boolean doAddSql(TSSmsSqlEntity t)
;

public void delete(T entity)
;

public void saveOrUpdate(T entity)
;

public Map<String,Object> getMap(String sql,Map<String,Object> map)
;

}
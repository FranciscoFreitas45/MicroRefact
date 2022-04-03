package org.jeecgframework.web.system.sms.service;
 import org.jeecgframework.web.system.sms.entity.TSSmsTemplateSqlEntity;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
public interface TSSmsTemplateSqlServiceI extends CommonService{


public Serializable save(T entity)
;

public boolean doDelSql(TSSmsTemplateSqlEntity t)
;

public boolean doUpdateSql(TSSmsTemplateSqlEntity t)
;

public boolean doAddSql(TSSmsTemplateSqlEntity t)
;

public void delete(T entity)
;

public void saveOrUpdate(T entity)
;

}
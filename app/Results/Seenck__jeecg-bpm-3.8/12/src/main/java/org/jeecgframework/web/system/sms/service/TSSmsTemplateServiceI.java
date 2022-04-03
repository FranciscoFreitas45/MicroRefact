package org.jeecgframework.web.system.sms.service;
 import org.jeecgframework.web.system.sms.entity.TSSmsTemplateEntity;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
public interface TSSmsTemplateServiceI extends CommonService{


public Serializable save(T entity)
;

public boolean doDelSql(TSSmsTemplateEntity t)
;

public boolean doUpdateSql(TSSmsTemplateEntity t)
;

public boolean doAddSql(TSSmsTemplateEntity t)
;

public void delete(T entity)
;

public void saveOrUpdate(T entity)
;

}
package org.jeecgframework.web.system.sms.service;
 import org.jeecgframework.web.system.sms.entity.TSSmsEntity;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
public interface TSSmsServiceI extends CommonService{


public List<TSSmsEntity> getMsgsList(String curUser,String curDate)
;

public Serializable save(T entity)
;

public boolean doDelSql(TSSmsEntity t)
;

public boolean doUpdateSql(TSSmsEntity t)
;

public boolean doAddSql(TSSmsEntity t)
;

public void delete(T entity)
;

public void send()
;

public void saveOrUpdate(T entity)
;

}
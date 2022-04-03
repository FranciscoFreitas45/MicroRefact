package org.jeecgframework.web.cgform.service.config;
 import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormIndexEntity;
public interface CgFormIndexServiceI extends CommonService{


public Serializable save(T entity)
;

public boolean doDelSql(CgFormIndexEntity t)
;

public boolean doUpdateSql(CgFormIndexEntity t)
;

public boolean doAddSql(CgFormIndexEntity t)
;

public void delete(T entity)
;

public void saveOrUpdate(T entity)
;

public boolean updateIndexes(CgFormHeadEntity cgFormHead)
;

public void createIndexes(CgFormHeadEntity cgFormHead)
;

}
package org.jeecgframework.web.superquery.service;
 import org.jeecgframework.web.superquery.entity.SuperQueryFieldEntity;
import org.jeecgframework.web.superquery.entity.SuperQueryMainEntity;
import org.jeecgframework.web.superquery.entity.SuperQueryTableEntity;
import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
public interface SuperQueryMainServiceI extends CommonService{


public boolean doDelSql(SuperQueryMainEntity t)
;

public void updateMain(SuperQueryMainEntity superQueryMain,List<SuperQueryTableEntity> superQueryTableList,List<SuperQueryFieldEntity> superQueryFieldList)
;

public void delMain(SuperQueryMainEntity superQueryMain)
;

public boolean doUpdateSql(SuperQueryMainEntity t)
;

public void addMain(SuperQueryMainEntity superQueryMain,List<SuperQueryTableEntity> superQueryTableList,List<SuperQueryFieldEntity> superQueryFieldList)
;

public boolean doAddSql(SuperQueryMainEntity t)
;

public void delete(T entity)
;

}
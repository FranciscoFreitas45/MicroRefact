package org.jeecgframework.web.cgreport.service.core;
 import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigHeadEntity;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigParamEntity;
import java.io.Serializable;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigItemEntity;
public interface CgreportConfigHeadServiceI extends CommonService{


public boolean doDelSql(CgreportConfigHeadEntity t)
;

public void updateMain(CgreportConfigHeadEntity cgreportConfigHead,List<CgreportConfigItemEntity> cgreportConfigItemList,List<CgreportConfigParamEntity> cgreportConfigParamList)
;

public void delMain(CgreportConfigHeadEntity cgreportConfigHead)
;

public boolean doUpdateSql(CgreportConfigHeadEntity t)
;

public void addMain(CgreportConfigHeadEntity cgreportConfigHead,List<CgreportConfigItemEntity> cgreportConfigItemList,List<CgreportConfigParamEntity> cgreportConfigParamList)
;

public boolean doAddSql(CgreportConfigHeadEntity t)
;

public void delete(T entity)
;

}
package org.jeecgframework.web.graphreport.service.core;
 import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.graphreport.entity.core.JformGraphreportHeadEntity;
import org.jeecgframework.web.graphreport.entity.core.JformGraphreportItemEntity;
import java.io.Serializable;
public interface JformGraphreportHeadServiceI extends CommonService{


public boolean doDelSql(JformGraphreportHeadEntity t)
;

public void updateMain(JformGraphreportHeadEntity jformGraphreportHead,List<JformGraphreportItemEntity> jformGraphreportItemList)
;

public void delMain(JformGraphreportHeadEntity jformGraphreportHead)
;

public boolean doUpdateSql(JformGraphreportHeadEntity t)
;

public void addMain(JformGraphreportHeadEntity jformGraphreportHead,List<JformGraphreportItemEntity> jformGraphreportItemList)
;

public boolean doAddSql(JformGraphreportHeadEntity t)
;

public void delete(T entity)
;

}
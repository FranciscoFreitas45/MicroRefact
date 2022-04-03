package org.jeecgframework.web.cgdynamgraph.service.core;
 import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigHeadEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigItemEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigParamEntity;
public interface CgDynamGraphConfigHeadServiceI extends CommonService{


public boolean doDelSql(CgDynamGraphConfigHeadEntity t)
;

public void updateMain(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead,List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList,List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList)
;

public void delMain(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead)
;

public boolean doUpdateSql(CgDynamGraphConfigHeadEntity t)
;

public void addMain(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead,List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList,List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList)
;

public boolean doAddSql(CgDynamGraphConfigHeadEntity t)
;

public void delete(T entity)
;

}
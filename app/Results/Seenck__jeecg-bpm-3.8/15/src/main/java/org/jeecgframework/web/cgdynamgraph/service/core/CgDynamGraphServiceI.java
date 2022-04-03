package org.jeecgframework.web.cgdynamgraph.service.core;
 import java.util.List;
import java.util.Map;
import org.jeecgframework.core.common.service.CommonService;
public interface CgDynamGraphServiceI extends CommonService{


public Map<String,Object> queryCgDynamGraphConfig(String reportId)
;

public long countQueryByCgDynamGraphSql(String sql,Map params,Map<String,Object> paramData)
;

public List<Map<String,Object>> queryCgDynamGraphItems(String reportId)
;

public List<String> getSqlFields(String sql)
;

public Map<String,Object> queryCgDynamGraphMainConfig(String reportId)
;

public List<Map<String,Object>> queryByCgDynamGraphSql(String sql,Map params,Map<String,Object> paramData)
;

}
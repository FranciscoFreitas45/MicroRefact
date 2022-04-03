package org.jeecgframework.web.cgreport.service.core;
 import java.util.List;
import java.util.Map;
import org.jeecgframework.core.common.service.CommonService;
public interface CgReportServiceI extends CommonService{


public List<Map<String,Object>> queryByCgReportSql(String sql,Map params,Map paramData,int page,int rows)
;

public List<Map<String,Object>> queryCgReportItems(String reportId)
;

public List<String> getFields(String sql,String dbKey)
;

public void dealDic(List<Map<String,Object>> result,List<Map<String,Object>> items)
;

public Map<String,Object> queryCgReportMainConfig(String reportId)
;

public void loadDic(Map<String,Object> fl)
;

public void dealReplace(List<Map<String,Object>> result,List<Map<String,Object>> items)
;

public List<String> getSqlFields(String sql)
;

public long countQueryByCgReportSql(String sql,Map params,Map paramData)
;

public Map<String,Object> queryCgReportConfig(String reportId)
;

public List<String> getSqlParams(String sql)
;

}
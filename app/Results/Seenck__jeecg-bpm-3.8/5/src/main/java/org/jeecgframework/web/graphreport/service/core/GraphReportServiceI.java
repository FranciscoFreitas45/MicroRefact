package org.jeecgframework.web.graphreport.service.core;
 import java.util.List;
import java.util.Map;
import org.jeecgframework.core.common.service.CommonService;
public interface GraphReportServiceI extends CommonService{


public List<Map<String,Object>> queryByCgReportSql(String sql,Map params,Map<String,Object> paramData,int page,int rows)
;

public List<String> getSqlFields(String sql)
;

public long countQueryByCgReportSql(String sql,Map params)
;

public Map<String,Object> queryCgReportConfig(String reportId)
;

}
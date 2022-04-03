package org.jeecgframework.web.cgform.service.autolist;
 import java.util.List;
import java.util.Map;
public interface CgTableServiceI {


public List querySingle(String table,String field,Map params,String sort,String order,int page,int rows)
;

public boolean delete(String table,Object id)
;

public boolean deleteBatch(String table,String[] ids)
;

public Long getQuerySingleSize(String table,String field,Map params)
;

public void treeFromResultHandle(String table,String parentIdFieldName,String parentIdFieldType,List<Map<String,Object>> result)
;

}
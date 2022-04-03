package org.jeecgframework.web.cgform.service.build;
 import java.util.List;
import java.util.Map;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;
public interface DataBaseService {


public void executeSqlExtend(String formId,String buttonCode,Map<String,Object> data)
;

public Map<String,Object> findOneForJdbc(String tableName,String id)
;

public void insertTable(String tableName,Map<String,Object> data)
;

public void executeJavaExtend(String formId,String buttonCode,Map<String,Object> data)
;

public Object getPkValue(String tableName)
;

public int updateTable(String tableName,Object id,Map<String,Object> data)
;

public boolean updateTableMore(Map<String,List<Map<String,Object>>> mapMore,String mainTableName)
;

public List<CgformEnhanceJavaEntity> getCgformEnhanceJavaEntityByFormId(String formId)
;

public Map<String,Object> insertTableMore(Map<String,List<Map<String,Object>>> mapMore,String mainTableName)
;

}
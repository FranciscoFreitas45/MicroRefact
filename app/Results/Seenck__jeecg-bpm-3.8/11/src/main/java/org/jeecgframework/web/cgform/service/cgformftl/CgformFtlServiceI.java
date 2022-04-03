package org.jeecgframework.web.cgform.service.cgformftl;
 import org.jeecgframework.core.common.service.CommonService;
import java.util.Map;
public interface CgformFtlServiceI extends CommonService{


public Map<String,Object> getCgformFtlByTableName(String tableName)
;

public boolean hasActive(String cgformId)
;

public int getNextVarsion(String cgformId)
;

public String getUserFormFtl(String id)
;

}
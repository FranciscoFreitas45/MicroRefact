package org.jeecgframework.web.cgform.service.button;
 import java.util.List;
import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
import org.jeecgframework.web.cgform.entity.button.CgformButtonSqlEntity;
import org.jeecgframework.core.common.service.CommonService;
public interface CgformButtonSqlServiceI extends CommonService{


public CgformButtonSqlEntity getCgformButtonSqlByCodeFormId(String buttonCode,String formId)
;

public List<CgformButtonSqlEntity> checkCgformButtonSql(CgformButtonSqlEntity cgformButtonSqlEntity)
;

}
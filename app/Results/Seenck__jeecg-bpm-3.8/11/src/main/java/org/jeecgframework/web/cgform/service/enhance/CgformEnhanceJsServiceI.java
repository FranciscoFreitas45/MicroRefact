package org.jeecgframework.web.cgform.service.enhance;
 import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
import org.jeecgframework.core.common.service.CommonService;
public interface CgformEnhanceJsServiceI extends CommonService{


public CgformEnhanceJsEntity getCgformEnhanceJsByTypeFormId(String cgJsType,String formId)
;

}
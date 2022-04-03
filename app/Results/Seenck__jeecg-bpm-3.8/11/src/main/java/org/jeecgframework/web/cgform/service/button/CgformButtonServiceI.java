package org.jeecgframework.web.cgform.service.button;
 import java.util.List;
import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
import org.jeecgframework.core.common.service.CommonService;
public interface CgformButtonServiceI extends CommonService{


public List<CgformButtonEntity> checkCgformButton(CgformButtonEntity cgformButtonEntity)
;

public List<CgformButtonEntity> getCgformButtonListByFormId(String formId)
;

}
package org.jeecgframework.web.cgform.service.fillrule;
 import org.jeecgframework.web.cgform.entity.fillrule.TSFillRuleEntity;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;
public interface TSFillRuleServiceI extends CommonService{


public Serializable save(TSFillRuleEntity entity)
;

public void delete(TSFillRuleEntity entity)
;

public void saveOrUpdate(TSFillRuleEntity entity)
;

}
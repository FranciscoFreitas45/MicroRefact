package org.jeecgframework.web.cgform.util;
 import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.IFillRuleHandler;
import org.jeecgframework.web.cgform.entity.fillrule.TSFillRuleEntity;
import org.jeecgframework.web.cgform.service.fillrule.TSFillRuleServiceI;
public class FillRuleUtil {


public Object executeRule(String ruleCode){
    if (StringUtil.isEmpty(ruleCode))
        return null;
    Object obj = null;
    try {
        TSFillRuleServiceI ruleServiceI = ApplicationContextUtil.getContext().getBean(TSFillRuleServiceI.class);
        TSFillRuleEntity ruleEntity = ruleServiceI.findUniqueByProperty(TSFillRuleEntity.class, "ruleCode", ruleCode);
        if (ruleEntity != null) {
            IFillRuleHandler ruleHandler = (IFillRuleHandler) Class.forName(ruleEntity.getRuleClass()).newInstance();
            obj = ruleHandler.execute(ruleEntity.getRuleParam());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return obj;
}


}
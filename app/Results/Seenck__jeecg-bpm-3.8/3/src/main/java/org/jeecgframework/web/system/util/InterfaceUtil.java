package org.jeecgframework.web.system.util;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.extend.hqlsearch.parse.ObjectParseUtil;
import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlRuleEnum;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.jwt.def.JwtConstants;
import org.jeecgframework.web.system.enums.InterfaceEnum;
import org.jeecgframework.web.system.pojo.base.InterfaceRuleDto;
import org.jeecgframework.web.system.pojo.base.TSInterfaceDdataRuleEntity;
import org.jeecgframework.web.system.service.TSInterfaceServiceI;
public class InterfaceUtil {

 private  Logger logger;


public InterfaceRuleDto getInterfaceRuleDto(HttpServletRequest request,InterfaceEnum interfaceEnum){
    // 获取用户id
    // = "interfaceuser";//
    String userName = (String) request.getAttribute(JwtConstants.CURRENT_USER_NAME);
    logger.info(interfaceEnum.toString() + "--------" + userName);
    // 根据用户账号和接口编码查询用户的接口权限
    TSInterfaceServiceI interfaceService = ApplicationContextUtil.getContext().getBean(TSInterfaceServiceI.class);
    InterfaceRuleDto interfaceRuleDto = interfaceService.getInterfaceRuleByUserNameAndCode(userName, interfaceEnum);
    System.out.println(interfaceRuleDto);
    logger.info(interfaceEnum.toString() + "--------" + interfaceRuleDto);
    return interfaceRuleDto;
}


public void addRuleToCriteria(CriteriaQuery cq,TSInterfaceDdataRuleEntity tsDataRule){
    String aliasName = tsDataRule.getRuleColumn();
    HqlRuleEnum rule = HqlRuleEnum.getByValue(tsDataRule.getRuleConditions());
    if (rule.equals(HqlRuleEnum.IN)) {
        String[] values = tsDataRule.getRuleValue().split(",");
        Object[] objs = new Object[values.length];
        objs = values;
        ObjectParseUtil.addCriteria(cq, aliasName, rule, objs);
    } else {
        ObjectParseUtil.addCriteria(cq, aliasName, rule, converRuleValue(tsDataRule.getRuleValue()));
    }
}


public String getQL(InterfaceRuleDto interfaceRuleDto,InterfaceEnum interfaceEnum){
    // InterfaceRuleDto interfaceRuleDto = getInterfaceRuleDto(request,interfaceEnum);
    StringBuilder sb = new StringBuilder();
    if (interfaceRuleDto != null) {
        List<TSInterfaceDdataRuleEntity> interfaceDataRule = interfaceRuleDto.getInterfaceDataRule();
        if (interfaceDataRule != null && interfaceDataRule.size() > 0) {
            for (TSInterfaceDdataRuleEntity rule : interfaceDataRule) {
                addRuleToQL(sb, rule);
            }
            logger.info(interfaceEnum.toString() + "----getQL----" + sb.toString());
            return sb.toString();
        }
    }
    return null;
}


public void addRuleToQL(StringBuilder sb,TSInterfaceDdataRuleEntity dataRule){
    if (dataRule == null)
        return;
    HqlRuleEnum ruleEnum = HqlRuleEnum.getByValue(dataRule.getRuleConditions());
    if (ruleEnum == HqlRuleEnum.SQL_RULES) {
        sb.append(" and (" + dataRule.getRuleValue() + ")");
        return;
    }
    // -----------------------------------------------------------------------
    // #{sys_user_code}%
    String valueTemp = dataRule.getRuleValue();
    String moshi = "";
    if (valueTemp.indexOf("}") != -1) {
        moshi = valueTemp.substring(valueTemp.indexOf("}") + 1);
    }
    // 针对特殊标示处理#{sysOrgCode}，判断替换
    if (valueTemp.contains("#{")) {
        valueTemp = valueTemp.substring(2, valueTemp.indexOf("}"));
    }
    // -----------------------------------------------------------------------
    String tempValue = null;
    tempValue = ResourceUtil.converRuleValue(valueTemp);
    if (tempValue != null) {
        tempValue = tempValue + moshi;
    } else {
        tempValue = valueTemp;
    }
    switch(ruleEnum) {
        case GT:
            sb.append(" and " + dataRule.getRuleColumn() + " >'" + tempValue + "'");
            break;
        case GE:
            sb.append(" and " + dataRule.getRuleColumn() + " >='" + tempValue + "'");
            break;
        case LT:
            sb.append(" and " + dataRule.getRuleColumn() + " <'" + tempValue + "'");
            break;
        case LE:
            sb.append(" and " + dataRule.getRuleColumn() + " <= '" + tempValue + "'");
            break;
        case EQ:
            sb.append(" and " + dataRule.getRuleColumn() + " ='" + tempValue + "'");
            break;
        case LIKE:
            sb.append(" and " + dataRule.getRuleColumn() + " like '%" + tempValue + "%'");
            break;
        case LEFT_LIKE:
            sb.append(" and " + dataRule.getRuleColumn() + " like '%" + tempValue + "'");
            break;
        case RIGHT_LIKE:
            sb.append(" and " + dataRule.getRuleColumn() + " like '" + tempValue + "%'");
            break;
        case NE:
            sb.append(" and " + dataRule.getRuleColumn() + " !='" + tempValue + "'");
            break;
        case IN:
            sb.append(" and " + dataRule.getRuleColumn() + " IN('" + tempValue + "')");
        default:
            break;
    }
    return;
}


public void installCriteriaQuery(CriteriaQuery cq,InterfaceRuleDto interfaceRuleDto,InterfaceEnum interfaceEnum){
    // InterfaceRuleDto interfaceRuleDto = getInterfaceRuleDto(request,interfaceEnum);
    if (interfaceRuleDto != null) {
        List<TSInterfaceDdataRuleEntity> interfaceDataRule = interfaceRuleDto.getInterfaceDataRule();
        if (interfaceDataRule != null && interfaceDataRule.size() > 0) {
            for (TSInterfaceDdataRuleEntity rule : interfaceDataRule) {
                addRuleToCriteria(cq, rule);
                cq.getCriterionList();
            }
            logger.info(interfaceEnum.toString() + "----installCriteriaQuery----" + cq.getCriterionList());
        }
    }
}


public String converRuleValue(String ruleValue){
    // 这个方法建议去掉，直接调用ResourceUtil.converRuleValue(ruleValue)
    String value = ResourceUtil.converRuleValue(ruleValue);
    return value != null ? value : ruleValue;
}


}
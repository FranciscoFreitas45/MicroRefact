package org.jeecgframework.core.extend.hqlsearch.parse;
 import java.util.Arrays;
import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlParseEnum;
import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlRuleEnum;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.ResourceUtil;
public class PageValueConvertRuleEnum {


public Object replaceValue(HqlRuleEnum rule,Object value){
    if (rule == null) {
        return null;
    }
    if (!(value instanceof String)) {
        return value;
    }
    String val = (value + "").toString().trim();
    if (rule == HqlRuleEnum.LIKE) {
        value = val.substring(1, val.length() - 1);
    } else if (rule == HqlRuleEnum.LEFT_LIKE || rule == HqlRuleEnum.NE) {
        value = val.substring(1);
    } else if (rule == HqlRuleEnum.RIGHT_LIKE) {
        value = val.substring(0, val.length() - 1);
    } else if (rule != HqlRuleEnum.IN) {
        value = val.replace(rule.getValue(), HqlParseEnum.SUFFIX_NULL_STRING.getValue());
    } else {
        value = val.split(",");
    }
    return value;
}


public HqlRuleEnum convert(Object value){
    // 避免空数据
    if (value == null) {
        return null;
    }
    String val = (value + "").toString().trim();
    if (val.length() == 0) {
        return null;
    }
    // step 1 .> <
    HqlRuleEnum rule = HqlRuleEnum.getByValue(val.substring(0, 1));
    // step 2 .>= =<
    if (rule == null && val.length() >= 2) {
        rule = HqlRuleEnum.getByValue(val.substring(0, 2));
    }
    // step 3 like
    if (rule == null && val.contains(HqlParseEnum.SUFFIX_ASTERISK.getValue())) {
        if (val.startsWith(HqlParseEnum.SUFFIX_ASTERISK.getValue()) && val.endsWith(HqlParseEnum.SUFFIX_ASTERISK.getValue())) {
            rule = HqlRuleEnum.LIKE;
        } else if (val.startsWith(HqlParseEnum.SUFFIX_ASTERISK.getValue())) {
            rule = HqlRuleEnum.LEFT_LIKE;
        } else {
            rule = HqlRuleEnum.RIGHT_LIKE;
        }
    }
    // step 4 in
    if (rule == null && val.contains(HqlParseEnum.SUFFIX_COMMA.getValue())) {
        rule = HqlRuleEnum.IN;
    }
    // step 5 !=
    if (rule == null && val.startsWith(HqlParseEnum.SUFFIX_NOT_EQUAL.getValue())) {
        rule = HqlRuleEnum.NE;
    }
    // update-begin--Author:zzl  Date:20151123 for：加入配置属性可默认进行模糊查询
    // if(rule==null&&ResourceUtil.fuzzySearch){
    // rule = HqlRuleEnum.LIKE;
    // }
    // update-end--Author:zzl  Date:20151123 for：加入配置属性可默认进行模糊查询
    return rule != null ? rule : HqlRuleEnum.EQ;
}


}
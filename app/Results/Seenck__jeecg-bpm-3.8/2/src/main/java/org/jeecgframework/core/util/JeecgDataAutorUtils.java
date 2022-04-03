package org.jeecgframework.core.util;
 import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.pojo.base.TSDataRule;
import org.springframework.util.StringUtils;
public class JeecgDataAutorUtils {


public void installDataSearchConditon(HttpServletRequest request,String MENU_DATA_AUTHOR_RULE_SQL){
    // 1.先从request获取MENU_DATA_AUTHOR_RULE_SQL，如果存则获取到sql串
    String ruleSql = (String) loadDataSearchConditonSQLString();
    if (!StringUtils.hasText(ruleSql)) {
        // 2.如果不存在，则new一个sql串
        ruleSql += MENU_DATA_AUTHOR_RULE_SQL;
    }
    request.setAttribute(Globals.MENU_DATA_AUTHOR_RULE_SQL, // 3.往sql串里面增量拼新的条件
    MENU_DATA_AUTHOR_RULE_SQL);
}


@SuppressWarnings("unchecked")
public List<TSDataRule> loadDataSearchConditonSQL(){
    return (List<TSDataRule>) ContextHolderUtils.getRequest().getAttribute(Globals.MENU_DATA_AUTHOR_RULES);
}


public String loadDataSearchConditonSQLString(){
    return (String) ContextHolderUtils.getRequest().getAttribute(Globals.MENU_DATA_AUTHOR_RULE_SQL);
}


}
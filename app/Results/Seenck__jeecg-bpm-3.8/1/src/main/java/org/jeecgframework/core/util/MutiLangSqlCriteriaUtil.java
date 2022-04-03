package org.jeecgframework.core.util;
 import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MutiLangSqlCriteriaUtil {


public void assembleCondition(List<String> fieldLangKeyList,CriteriaQuery cq,String fieldName,String fieldValue){
    Map<String, String> fieldLangMap = new HashMap<String, String>();
    for (String nameKey : fieldLangKeyList) {
        String name = MutiLangUtil.getLang(nameKey);
        fieldLangMap.put(nameKey, name);
    }
    if ("*".equals(fieldValue)) {
        fieldValue = "**";
    }
    List<String> paramValueList = new ArrayList<String>();
    for (Map.Entry<String, String> entry : fieldLangMap.entrySet()) {
        String fieldLangKey = entry.getKey();
        String fieldLangValue = entry.getValue();
        if (fieldValue.startsWith("*") && fieldValue.endsWith("*")) {
            if (fieldLangValue.contains(fieldValue.substring(1, fieldValue.length() - 1))) {
                paramValueList.add(fieldLangKey);
            }
        } else if (fieldValue.startsWith("*")) {
            if (fieldLangValue.endsWith(fieldValue.substring(1))) {
                paramValueList.add(fieldLangKey);
            }
        } else if (fieldValue.endsWith("*")) {
            if (fieldLangValue.startsWith(fieldValue.substring(0, fieldValue.length() - 1))) {
                paramValueList.add(fieldLangKey);
            }
        } else {
            if (fieldLangValue.equals(fieldValue)) {
                paramValueList.add(fieldLangKey);
            }
        }
    }
    if (paramValueList.size() == 0) {
        // 设置一个错误的key值。
        paramValueList.add("~!@#$%_()*&^");
    }
    cq.in(fieldName, paramValueList.toArray());
    cq.add();
}


}
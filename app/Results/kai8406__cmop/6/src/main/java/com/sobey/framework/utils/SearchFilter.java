package com.sobey.framework.utils;
 import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.Maps;
public class SearchFilter {

 public  String fieldName;

 public  Object value;

 public  Operator operator;

/**
 * 关联路径转换.
 *
 * <pre>
 * 如Apply下名为"user.name"的fileName,转换为 Apply.user.name 属性
 * 当此对象作为Map中的value时:
 *
 * map.put("audit.auditFlow.user.id", new SearchFilter("auditFlow.user.id",
 * Operator.EQ, userId));
 * key必须给出value的上级关联对象.
 * </pre>
 *
 * @param fieldName
 * @param operator
 * @param value
 */
public SearchFilter(String fieldName, Operator operator, Object value) {
    this.fieldName = fieldName;
    this.value = value;
    this.operator = operator;
}
public Map<String,SearchFilter> parse(Map<String,Object> searchParams){
    Map<String, SearchFilter> filters = Maps.newHashMap();
    for (Entry<String, Object> entry : searchParams.entrySet()) {
        // 过滤掉空值
        String key = entry.getKey();
        Object value = entry.getValue();
        if (StringUtils.isBlank((String) value)) {
            continue;
        }
        // 拆分operator与filedAttribute
        String[] names = StringUtils.split(key, "_");
        if (names.length != 2) {
            throw new IllegalArgumentException(key + " is not a valid search filter name");
        }
        String filedName = names[1];
        Operator operator = Operator.valueOf(names[0]);
        // 创建searchFilter
        SearchFilter filter = new SearchFilter(filedName, operator, value);
        filters.put(key, filter);
    }
    return filters;
}


}
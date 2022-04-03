package com.gbcom.common.hibernate;
 import com.hc.core.orm.hibernate.Page;
import com.hc.core.ui.UIHelper;
import com.hc.core.utils.StringHelper;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import java.lang.reflect.InvocationTargetException;
import java.util;
public class GridJq {


public String toJSON(List list,String columnNames){
    List<Map> mapList = getGridValue(list, columnNames);
    return new JSONSerializer().serialize(mapList);
}


public Map<String,String> getRowValue(Object obj,String[] columnNames){
    if (obj instanceof Map) {
        return (Map<String, String>) obj;
    }
    Map<String, String> map = new LinkedHashMap<String, String>();
    for (String columnName : columnNames) {
        map.put(columnName, getColumnValue(obj, columnName));
    }
    return map;
}


public String getColumnValue(Object obj,String columnName){
    String rel = "";
    columnName = columnName.trim();
    Object value = null;
    try {
        value = PropertyUtils.getNestedProperty(obj, columnName);
    } catch (IllegalAccessException e) {
    // e.printStackTrace();
    } catch (InvocationTargetException e) {
    // e.printStackTrace();
    } catch (NoSuchMethodException e) {
    // e.printStackTrace();
    } catch (NestedNullException e) {
        value = null;
    }
    if (value != null) {
        rel = value.toString();
    }
    // 注意以下这段是与ext的显示有关系
    if (!StringHelper.isEmpty(rel)) {
        // 把回车换行做一个替换，到页面要plugin再替换过来
        rel = rel.replaceAll("\r\n", "<br/>");
        rel = rel.replaceAll("\r", "<br/>");
        rel = rel.replaceAll("\n", "<br/>");
        // 把数据里的单引号替换成反斜杠加单引号，这样ext可以直接显示为单引号
        rel = StringHelper.findAndReplace(rel, "\'", "\\\'");
    }
    return rel;
}


public Map addPageInfo(List list,Page page){
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("total", page.getTotalPages());
    map.put("page", page.getPage());
    map.put("records", page.getRecords());
    map.put("rows", list);
    return map;
}


@SuppressWarnings("unchecked")
public List<Map> getGridValue(Collection col,String columnNames){
    String[] colNames = UIHelper.string2Array(columnNames);
    List<Map> valueList = new ArrayList<Map>();
    for (Object aList : col) {
        valueList.add(getRowValue(aList, colNames));
    }
    return valueList;
}


}
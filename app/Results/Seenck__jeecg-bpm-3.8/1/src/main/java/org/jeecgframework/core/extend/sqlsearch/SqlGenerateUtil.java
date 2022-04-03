package org.jeecgframework.core.extend.sqlsearch;
 import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Table;
import org.apache.commons.beanutils.PropertyUtils;
import org.jeecgframework.codegenerate.util.CodeResourceUtil;
import org.jeecgframework.core.util.StringUtil;
public class SqlGenerateUtil {

 private  String END;

 private  String BEGIN;


public String getDbNameByFieldName(PropertyDescriptor propertyDescriptor){
    String propertyName = propertyDescriptor.getName();
    Column column = null;
    Method readMethod = propertyDescriptor.getReadMethod();
    if (readMethod != null) {
        column = readMethod.getAnnotation(Column.class);
        if (column == null) {
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod != null) {
                column = writeMethod.getAnnotation(Column.class);
            }
        }
    }
    // 如果找不到@column，或者@column的name为空，那么数据库列名就是属性名
    if (column == null || StringUtil.isEmpty(column.name())) {
        return propertyName;
    } else {
        return column.name();
    }
}


@SuppressWarnings({ "unchecked", "rawtypes" })
public StringBuffer generateDBFields(Object searchObj,String fields,List dealfields){
    StringBuffer dbFields = new StringBuffer();
    PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(searchObj.getClass());
    String[] fileNames = fields.split(",");
    for (int i = 0; i < fileNames.length; i++) {
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            if (fileNames[i].equals(propertyName)) {
                dbFields.append(getDbNameByFieldName(propertyDescriptor) + ((i == fileNames.length - 1) ? "" : ","));
                dealfields.add(fileNames[i]);
                break;
            }
        }
    }
    return dbFields;
}


public String generateTable(Object searchObj){
    Table table = searchObj.getClass().getAnnotation(Table.class);
    if (StringUtil.isEmpty(table.name())) {
        return searchObj.getClass().getSimpleName();
    } else {
        return table.name();
    }
}


public String getValueForType(String name,Object value,String type){
    SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    if ("class java.lang.Integer".equals(type) || "class java.lang.Double".equals(type) || "class java.lang.Long".equals(type) || type.contains("class java.math")) {
        return String.valueOf(value);
    } else if ("class java.util.Date".equals(type)) {
        String dbType = CodeResourceUtil.DATABASE_TYPE;
        if ("oracle".equals(dbType)) {
            if (name.contains(BEGIN)) {
                String beginValue = (String) value;
                if (beginValue.length() == 19) {
                    return "'date" + beginValue + "'";
                } else {
                    return "'date" + beginValue + " 00:00:00'";
                }
            } else if (name.contains(END)) {
                String endValue = (String) value;
                if (endValue.length() == 19) {
                    return "'date" + endValue + "'";
                } else {
                    return "'date" + endValue + " 23:59:59'";
                }
            } else {
                return "date'" + time.format(value) + "'";
            }
        } else {
            if (name.contains(BEGIN)) {
                String beginValue = (String) value;
                if (beginValue.length() == 19) {
                    return "'" + beginValue + "'";
                } else {
                    return "'" + beginValue + " 00:00:00'";
                }
            } else if (name.contains(END)) {
                String endValue = (String) value;
                if (endValue.length() == 19) {
                    return "'" + endValue + "'";
                } else {
                    return "'" + endValue + " 23:59:59'";
                }
            } else {
                return "'" + time.format(value) + "'";
            }
        }
    } else if ("class java.lang.String".equals(type)) {
        return String.valueOf("'" + value + "'");
    } else {
        return null;
    }
}


public StringBuffer generateWhere(Object searchObj,Map<String,String[]> parameterMap){
    StringBuffer whereSql = new StringBuffer(" where 1=1 ");
    try {
        PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(searchObj.getClass());
        // 拼接sql查询的条件
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            // 属性类型
            String propertyType = propertyDescriptor.getPropertyType().toString();
            // 属性名称
            String propertyName = propertyDescriptor.getName();
            // 属性值
            Object propertyValue = PropertyUtils.getSimpleProperty(searchObj, propertyName);
            // 对应的数据库中的列名
            String dbColumnName = getDbNameByFieldName(propertyDescriptor);
            // 添加 判断是否有区间值
            String beginValue = null;
            String endValue = null;
            if (parameterMap != null && (parameterMap.containsKey(propertyName + BEGIN) || parameterMap.containsKey(propertyName + END))) {
                if (parameterMap != null && parameterMap.containsKey(propertyName + BEGIN)) {
                    beginValue = parameterMap.get(propertyName + BEGIN)[0].trim();
                    if (StringUtil.isNotEmpty(beginValue)) {
                        String beginValueReturn = getValueForType(propertyName + BEGIN, beginValue, propertyType);
                        if (StringUtil.isNotEmpty(beginValueReturn)) {
                            whereSql.append("and " + dbColumnName + ">=" + beginValueReturn + " ");
                        }
                    }
                }
                if (parameterMap != null && parameterMap.containsKey(propertyName + END)) {
                    endValue = parameterMap.get(propertyName + END)[0].trim();
                    if (StringUtil.isNotEmpty(endValue)) {
                        String endValueReturn = getValueForType(propertyName + END, endValue, propertyType);
                        if (StringUtil.isNotEmpty(endValueReturn)) {
                            whereSql.append("and " + dbColumnName + "<=" + endValueReturn + " ");
                        }
                    }
                }
            } else {
                if (StringUtil.isNotEmpty(propertyValue)) {
                    String propertyValueReturn = getValueForType(propertyName, propertyValue, propertyType);
                    if (StringUtil.isNotEmpty(propertyValueReturn)) {
                        whereSql.append("and " + dbColumnName + "=" + propertyValueReturn + " ");
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return whereSql;
}


}
package DTO;
 import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import javax.persistence.Transient;
import org.apache.log4j.Logger;
public class ReflectHelper {

 private  Logger logger;

 private  Class cls;

 private  Object obj;

 private  Hashtable<String,Method> getMethods;

 private  Hashtable<String,Method> setMethods;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

/**
 * 定义构造方法 -- 一般来说是个pojo
 *
 * @param o
 *            目标对象
 */
public ReflectHelper(Object o) {
    obj = o;
    initMethods();
}
public String[] getFiledName(Object o){
    Field[] fields = o.getClass().getDeclaredFields();
    String[] fieldNames = new String[fields.length];
    for (int i = 0; i < fields.length; i++) {
        // System.out.println(fields[i].getType());
        fieldNames[i] = fields[i].getName();
    }
    return fieldNames;
}


public Object[] getFiledValues(Object o){
    String[] fieldNames = getFiledName(o);
    Object[] value = new Object[fieldNames.length];
    for (int i = 0; i < fieldNames.length; i++) {
        value[i] = getFieldValueByName(fieldNames[i], o);
    }
    return value;
}


public Object getMethodValue(String property){
    Object value = null;
    Method m = getMethods.get(property.toLowerCase());
    if (m != null) {
        try {
            /**
             * 调用obj类的setter函数
             */
            value = m.invoke(obj, new Object[] {});
        } catch (Exception ex) {
            logger.info("invoke getter on " + property + " error: " + ex.toString());
        }
    }
    return value;
}


public List<Map> getFiledsInfo(Object o){
    Field[] fields = o.getClass().getDeclaredFields();
    String[] fieldNames = new String[fields.length];
    List<Map> list = new ArrayList<Map>();
    Map<String, Object> infoMap = null;
    for (int i = 0; i < fields.length; i++) {
        infoMap = new HashMap<String, Object>();
        infoMap.put("type", fields[i].getType().toString());
        infoMap.put("name", fields[i].getName());
        infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
        list.add(infoMap);
    }
    return list;
}


public Object getFieldValueByName(String fieldName,Object o){
    try {
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String getter = "get" + firstLetter + fieldName.substring(1);
        Method method = o.getClass().getMethod(getter, new Class[] {});
        Object value = method.invoke(o, new Object[] {});
        return value;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


public boolean setMethodValue(String property,Object object){
    Method m = setMethods.get(property.toLowerCase());
    if (m != null) {
        try {
            // 调用目标类的setter函数
            m.invoke(obj, object);
            return true;
        } catch (Exception ex) {
            logger.info("invoke getter on " + property + " error: " + ex.toString());
            return false;
        }
    }
    return false;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMethodValue"))

.queryParam("property",property)
.queryParam("object",object)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}
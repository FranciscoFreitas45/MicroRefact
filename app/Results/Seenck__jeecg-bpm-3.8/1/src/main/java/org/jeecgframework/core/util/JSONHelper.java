package org.jeecgframework.core.util;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JSONHelper {

 private  Logger logger;


public HashMap toHashMap(Object object){
    HashMap<String, Object> data = new HashMap<String, Object>();
    JSONObject jsonObject = JSONHelper.toJSONObject(object);
    Iterator it = jsonObject.keys();
    while (it.hasNext()) {
        String key = String.valueOf(it.next());
        Object value = jsonObject.get(key);
        data.put(key, value);
    }
    return data;
}


public String toJSONString(JSONObject jsonObject){
    return jsonObject.toString();
}


public List toArrayList(Object object){
    List arrayList = new ArrayList();
    JSONArray jsonArray = JSONArray.fromObject(object);
    Iterator it = jsonArray.iterator();
    while (it.hasNext()) {
        JSONObject jsonObject = (JSONObject) it.next();
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            Object key = keys.next();
            Object value = jsonObject.get(key);
            arrayList.add(value);
        }
    }
    return arrayList;
}


public JSONArray toJSONArray(Object object){
    return JSONArray.fromObject(object);
}


public T toBean(String jsonString,Class<T> mainClass,HashMap<String,Class> detailClass){
    JSONObject jsonObject = JSONObject.fromObject(jsonString);
    T mainEntity = JSONHelper.toBean(jsonObject, mainClass);
    for (Object key : detailClass.keySet()) {
        try {
            Class value = (Class) detailClass.get(key);
            BeanUtils.setProperty(mainEntity, key.toString(), value);
        } catch (Exception ex) {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }
    }
    return mainEntity;
}


public String listtojson(String[] fields,int total,List list){
    Object[] values = new Object[fields.length];
    String jsonTemp = "{\"total\":" + total + ",\"rows\":[";
    for (int j = 0; j < list.size(); j++) {
        jsonTemp = jsonTemp + "{\"state\":\"closed\",";
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].toString();
            values[i] = org.jeecgframework.tag.core.easyui.TagUtil.fieldNametoValues(fieldName, list.get(j));
            jsonTemp = jsonTemp + "\"" + fieldName + "\"" + ":\"" + values[i] + "\"";
            if (i != fields.length - 1) {
                jsonTemp = jsonTemp + ",";
            }
        }
        if (j != list.size() - 1) {
            jsonTemp = jsonTemp + "},";
        } else {
            jsonTemp = jsonTemp + "}";
        }
    }
    jsonTemp = jsonTemp + "]}";
    return jsonTemp;
}


public String bean2json(Object object){
    JSONObject jsonObject = JSONObject.fromObject(object);
    return jsonObject.toString();
}


public Map<String,List<Map<String,Object>>> json2MapList(String jsonStr){
    Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String, Object>>>();
    // 将json字符串转换成jsonObject
    JSONObject jsonObject = JSONObject.fromObject(jsonStr);
    Iterator it = jsonObject.keys();
    // 遍历jsonObject数据，添加到Map对象
    while (it.hasNext()) {
        String key = String.valueOf(it.next());
        Object value = jsonObject.get(key);
        List<Map<String, Object>> list = toList(value);
        data.put(key, list);
    }
    return data;
}


public String collection2json(Object object){
    JSONArray jsonArray = JSONArray.fromObject(object);
    return jsonArray.toString();
}


public Map<String,Object> json2Map(String jsonStr){
    Map<String, Object> data = new HashMap<String, Object>();
    // 将json字符串转换成jsonObject
    JSONObject jsonObject = JSONObject.fromObject(jsonStr);
    Iterator it = jsonObject.keys();
    // 遍历jsonObject数据，添加到Map对象
    while (it.hasNext()) {
        String key = String.valueOf(it.next());
        Object value = jsonObject.get(key);
        data.put(key, value);
    }
    return data;
}


public List<T> toList(Object object,Class<T> objectClass){
    JSONArray jsonArray = JSONArray.fromObject(object);
    return JSONArray.toList(jsonArray, objectClass);
}


public String string2json(String key,String value){
    JSONObject object = new JSONObject();
    object.put(key, value);
    return object.toString();
}


public String map2json(Object object){
    JSONObject jsonObject = JSONObject.fromObject(object);
    return jsonObject.toString();
}


public Object json2Object(String json,Class beanClz){
    return JSONObject.toBean(JSONObject.fromObject(json), beanClz);
}


public String json2String(String json,String key){
    JSONObject jsonObject = JSONObject.fromObject(json);
    return jsonObject.get(key).toString();
}


public T fromJsonToObject(String json,Class<T> valueType){
    ObjectMapper mapper = new ObjectMapper();
    try {
        return mapper.readValue(json, valueType);
    } catch (JsonParseException e) {
        logger.error("JsonParseException: ", e);
    } catch (JsonMappingException e) {
        logger.error("JsonMappingException: ", e);
    } catch (IOException e) {
        logger.error("IOException: ", e);
    }
    return null;
}


public JSONObject toJSONObject(Object object){
    return JSONObject.fromObject(object);
}


public String array2json(Object object){
    JSONArray jsonArray = JSONArray.fromObject(object);
    return jsonArray.toString();
}


public Object json2Array(String json,Class valueClz){
    JSONArray jsonArray = JSONArray.fromObject(json);
    return JSONArray.toArray(jsonArray, valueClz);
}


}
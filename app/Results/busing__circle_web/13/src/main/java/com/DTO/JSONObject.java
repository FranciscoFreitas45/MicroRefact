package com.DTO;
 import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.array.ObjectArrayMorpher;
import net.sf.ezmorph.bean.BeanMorpher;
import net.sf.ezmorph.object.IdentityObjectMorpher;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.xwtec.xwserver.util.json.processors.JsonBeanProcessor;
import com.xwtec.xwserver.util.json.processors.JsonValueProcessor;
import com.xwtec.xwserver.util.json.processors.JsonVerifier;
import com.xwtec.xwserver.util.json.processors.PropertyNameProcessor;
import com.xwtec.xwserver.util.json.regexp.RegexpUtils;
import com.xwtec.xwserver.util.json.util.CycleDetectionStrategy;
import com.xwtec.xwserver.util.json.util.JSONTokener;
import com.xwtec.xwserver.util.json.util.JSONUtils;
import com.xwtec.xwserver.util.json.util.PropertyFilter;
import com.xwtec.xwserver.util.json.util.PropertySetStrategy;
public class JSONObject extends AbstractJSONimplements Comparable,JSON,Map{

 private  Log log;

 private  boolean nullObject;

 private  Map properties;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://15";

/**
 * Construct an empty JSONObject.
 */
public JSONObject() {
    this.properties = new ListOrderedMap();
}/**
 * Creates a JSONObject that is null.
 */
public JSONObject(boolean isNull) {
    this();
    this.nullObject = isNull;
}
public JSONArray getJSONArray(String key){
    verifyIsNull();
    Object o = get(key);
    if (o != null && o instanceof JSONArray) {
        return (JSONArray) o;
    }
    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] is not a JSONArray.");
}


public int getInt(String key){
    verifyIsNull();
    Object o = get(key);
    if (o != null) {
        return o instanceof Number ? ((Number) o).intValue() : (int) getDouble(key);
    }
    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] is not a number.");
}


public double getDouble(String key){
    verifyIsNull();
    Object o = get(key);
    if (o != null) {
        try {
            return o instanceof Number ? ((Number) o).doubleValue() : Double.parseDouble((String) o);
        } catch (Exception e) {
            throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] is not a number.");
        }
    }
    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] is not a number.");
}


public Object get(String key){
    verifyIsNull();
    return this.properties.get(key);
}


public boolean getBoolean(String key){
    verifyIsNull();
    Object o = get(key);
    if (o != null) {
        if (o.equals(Boolean.FALSE) || (o instanceof String && ((String) o).equalsIgnoreCase("false"))) {
            return false;
        } else if (o.equals(Boolean.TRUE) || (o instanceof String && ((String) o).equalsIgnoreCase("true"))) {
            return true;
        }
    }
    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] is not a Boolean.");
}


public String getString(String key){
    verifyIsNull();
    Object o = get(key);
    if (o != null) {
        return o.toString();
    }
    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] not found.");
}


public long getLong(String key){
    verifyIsNull();
    Object o = get(key);
    if (o != null) {
        return o instanceof Number ? ((Number) o).longValue() : (long) getDouble(key);
    }
    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] is not a number.");
}


public JSONObject getJSONObject(String key){
    verifyIsNull();
    Object o = get(key);
    if (JSONNull.getInstance().equals(o)) {
        return new JSONObject(true);
    } else if (o instanceof JSONObject) {
        return (JSONObject) o;
    }
    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] is not a JSONObject.");
}


public Object put(Object key,Object value){
    if (key == null) {
        throw new IllegalArgumentException("key is null.");
    }
    Object previous = properties.get(key);
    element(String.valueOf(key), value);
    return previous;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/put"))

.queryParam("key",key)
.queryParam("value",value)
;
Object aux = restTemplate.getForObject(builder.toUriString(),Object.class);
return aux;
}


}
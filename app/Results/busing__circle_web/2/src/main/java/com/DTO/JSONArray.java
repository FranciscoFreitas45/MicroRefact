package com.DTO;
 import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.object.IdentityObjectMorpher;
import org.apache.commons.lang.StringUtils;
import com.xwtec.xwserver.util.json.processors.JsonValueProcessor;
import com.xwtec.xwserver.util.json.processors.JsonVerifier;
import com.xwtec.xwserver.util.json.util.JSONTokener;
import com.xwtec.xwserver.util.json.util.JSONUtils;
public class JSONArray extends AbstractJSONimplements Comparable,JSON,List{

 private  List elements;

 private  boolean expandElements;

 private int currentIndex;

 private int lastIndex;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://15";

/**
 * Construct an empty JSONArray.
 */
public JSONArray() {
    this.elements = new ArrayList();
}
public JSONArray getJSONArray(int index){
    Object o = get(index);
    if (o != null && o instanceof JSONArray) {
        return (JSONArray) o;
    }
    throw new JSONException("JSONArray[" + index + "] is not a JSONArray.");
}


public int[] getDimensions(JSONArray jsonArray){
    // short circuit for empty arrays
    if (jsonArray == null || jsonArray.isEmpty()) {
        return new int[] { 0 };
    }
    List dims = new ArrayList();
    processArrayDimensions(jsonArray, dims, 0);
    int[] dimensions = new int[dims.size()];
    int j = 0;
    for (Iterator i = dims.iterator(); i.hasNext(); ) {
        dimensions[j++] = ((Integer) i.next()).intValue();
    }
    return dimensions;
}


public int getInt(int index){
    Object o = get(index);
    if (o != null) {
        return o instanceof Number ? ((Number) o).intValue() : (int) getDouble(index);
    }
    throw new JSONException("JSONArray[" + index + "] is not a number.");
}


public double getDouble(int index){
    Object o = get(index);
    if (o != null) {
        try {
            return o instanceof Number ? ((Number) o).doubleValue() : Double.parseDouble((String) o);
        } catch (Exception e) {
            throw new JSONException("JSONArray[" + index + "] is not a number.");
        }
    }
    throw new JSONException("JSONArray[" + index + "] is not a number.");
}


public Class[] getCollectionType(PropertyDescriptor pd,boolean useGetter){
    Type type;
    if (useGetter) {
        Method m = pd.getReadMethod();
        type = m.getGenericReturnType();
    } else {
        Method m = pd.getWriteMethod();
        Type[] gpts = m.getGenericParameterTypes();
        if (1 != gpts.length) {
            throw new JSONException("method " + m + " is not a standard setter");
        }
        type = gpts[0];
    }
    if (!(type instanceof ParameterizedType)) {
        return null;
    // throw new JSONException("type not instanceof ParameterizedType:
    // "+type.getClass());
    }
    ParameterizedType pType = (ParameterizedType) type;
    Type[] actualTypes = pType.getActualTypeArguments();
    Class[] ret = new Class[actualTypes.length];
    for (int i = 0; i < ret.length; i++) {
        ret[i] = (Class) actualTypes[i];
    }
    return ret;
}


public Object get(int index){
    /*
       * Object o = opt( index ); if( o == null ){ throw new JSONException(
       * "JSONArray[" + index + "] not found." ); } return o;
       */
    return this.elements.get(index);
}


public boolean getBoolean(int index){
    Object o = get(index);
    if (o != null) {
        if (o.equals(Boolean.FALSE) || (o instanceof String && ((String) o).equalsIgnoreCase("false"))) {
            return false;
        } else if (o.equals(Boolean.TRUE) || (o instanceof String && ((String) o).equalsIgnoreCase("true"))) {
            return true;
        }
    }
    throw new JSONException("JSONArray[" + index + "] is not a Boolean.");
}


public String getString(int index){
    Object o = get(index);
    if (o != null) {
        return o.toString();
    }
    throw new JSONException("JSONArray[" + index + "] not found.");
}


public long getLong(int index){
    Object o = get(index);
    if (o != null) {
        return o instanceof Number ? ((Number) o).longValue() : (long) getDouble(index);
    }
    throw new JSONException("JSONArray[" + index + "] is not a number.");
}


public JSONObject getJSONObject(int index){
    Object o = get(index);
    if (JSONNull.getInstance().equals(o)) {
        return new JSONObject(true);
    } else if (o instanceof JSONObject) {
        return (JSONObject) o;
    }
    throw new JSONException("JSONArray[" + index + "] is not a JSONObject.");
}


public JSONArray fromObject(Object object,JsonConfig jsonConfig){
    if (object instanceof JSONString) {
        return _fromJSONString((JSONString) object, jsonConfig);
    } else if (object instanceof JSONArray) {
        return _fromJSONArray((JSONArray) object, jsonConfig);
    } else if (object instanceof Collection) {
        return _fromCollection((Collection) object, jsonConfig);
    } else if (object instanceof JSONTokener) {
        return _fromJSONTokener((JSONTokener) object, jsonConfig);
    } else if (object instanceof String) {
        return _fromString((String) object, jsonConfig);
    } else if (object != null && object.getClass().isArray()) {
        Class type = object.getClass().getComponentType();
        if (!type.isPrimitive()) {
            return _fromArray((Object[]) object, jsonConfig);
        } else {
            if (type == Boolean.TYPE) {
                return _fromArray((boolean[]) object, jsonConfig);
            } else if (type == Byte.TYPE) {
                return _fromArray((byte[]) object, jsonConfig);
            } else if (type == Short.TYPE) {
                return _fromArray((short[]) object, jsonConfig);
            } else if (type == Integer.TYPE) {
                return _fromArray((int[]) object, jsonConfig);
            } else if (type == Long.TYPE) {
                return _fromArray((long[]) object, jsonConfig);
            } else if (type == Float.TYPE) {
                return _fromArray((float[]) object, jsonConfig);
            } else if (type == Double.TYPE) {
                return _fromArray((double[]) object, jsonConfig);
            } else if (type == Character.TYPE) {
                return _fromArray((char[]) object, jsonConfig);
            } else {
                throw new JSONException("Unsupported type");
            }
        }
    } else if (JSONUtils.isBoolean(object) || JSONUtils.isFunction(object) || JSONUtils.isNumber(object) || JSONUtils.isNull(object) || JSONUtils.isString(object) || object instanceof JSON) {
        fireArrayStartEvent(jsonConfig);
        JSONArray jsonArray = new JSONArray().element(object, jsonConfig);
        fireElementAddedEvent(0, jsonArray.get(0), jsonConfig);
        fireArrayStartEvent(jsonConfig);
        return jsonArray;
    } else if (object instanceof Enum) {
        return _fromArray((Enum) object, jsonConfig);
    } else if (object instanceof Annotation || (object != null && object.getClass().isAnnotation())) {
        throw new JSONException("Unsupported type");
    } else if (JSONUtils.isObject(object)) {
        fireArrayStartEvent(jsonConfig);
        JSONArray jsonArray = new JSONArray().element(JSONObject.fromObject(object, jsonConfig));
        fireElementAddedEvent(0, jsonArray.get(0), jsonConfig);
        fireArrayStartEvent(jsonConfig);
        return jsonArray;
    } else {
        throw new JSONException("Unsupported type");
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/fromObject"))

.queryParam("object",object)
.queryParam("jsonConfig",jsonConfig)
;
JSONArray aux = restTemplate.getForObject(builder.toUriString(),JSONArray.class);
return aux;
}


}
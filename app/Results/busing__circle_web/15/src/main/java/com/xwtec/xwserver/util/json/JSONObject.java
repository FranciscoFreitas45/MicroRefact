package com.xwtec.xwserver.util.json;
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
import com.DTO.PropertyFilter;
import com.DTO.PropertyFilter;
import com.DTO.PropertyFilter;
import com.DTO.PropertyFilter;
import com.DTO.PropertyFilter;
import com.DTO.PropertyFilter;
import com.DTO.PropertyFilter;
import com.DTO.PropertyFilter;
@SuppressWarnings({ "unchecked", "serial" })
public class JSONObject extends AbstractJSONimplements Comparable,JSON,Map{

 private  Log log;

 private  boolean nullObject;

 private  Map properties;

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
public JSONObject discard(String key){
    verifyIsNull();
    this.properties.remove(key);
    return this;
}


public JSONObject _fromString(String str,JsonConfig jsonConfig){
    if (str == null || "null".equals(str)) {
        fireObjectStartEvent(jsonConfig);
        fireObjectEndEvent(jsonConfig);
        return new JSONObject(true);
    }
    return _fromJSONTokener(new JSONTokener(str), jsonConfig);
}


public JSONArray toJSONArray(JSONArray names){
    verifyIsNull();
    if (names == null || names.size() == 0) {
        return null;
    }
    JSONArray ja = new JSONArray();
    for (int i = 0; i < names.size(); i += 1) {
        ja.element(this.opt(names.getString(i)));
    }
    return ja;
}


public JSONObject _fromDynaBean(DynaBean bean,JsonConfig jsonConfig){
    if (bean == null) {
        fireObjectStartEvent(jsonConfig);
        fireObjectEndEvent(jsonConfig);
        return new JSONObject(true);
    }
    if (!addInstance(bean)) {
        try {
            return jsonConfig.getCycleDetectionStrategy().handleRepeatedReferenceAsObject(bean);
        } catch (JSONException jsone) {
            removeInstance(bean);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        } catch (RuntimeException e) {
            removeInstance(bean);
            JSONException jsone = new JSONException(e);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        }
    }
    fireObjectStartEvent(jsonConfig);
    JSONObject jsonObject = new JSONObject();
    try {
        DynaProperty[] props = bean.getDynaClass().getDynaProperties();
        Collection exclusions = jsonConfig.getMergedExcludes();
        PropertyFilter jsonPropertyFilter = jsonConfig.getJsonPropertyFilter();
        for (int i = 0; i < props.length; i++) {
            boolean bypass = false;
            DynaProperty dynaProperty = props[i];
            String key = dynaProperty.getName();
            if (exclusions.contains(key)) {
                continue;
            }
            Class type = dynaProperty.getType();
            Object value = bean.get(dynaProperty.getName());
            if (jsonPropertyFilter != null && jsonPropertyFilter.apply(bean, key, value)) {
                continue;
            }
            JsonValueProcessor jsonValueProcessor = jsonConfig.findJsonValueProcessor(type, key);
            if (jsonValueProcessor != null) {
                value = jsonValueProcessor.processObjectValue(key, value, jsonConfig);
                bypass = true;
                if (!JsonVerifier.isValidJsonValue(value)) {
                    throw new JSONException("Value is not a valid JSON value. " + value);
                }
            }
            setValue(jsonObject, key, value, type, jsonConfig, bypass);
        }
    } catch (JSONException jsone) {
        removeInstance(bean);
        fireErrorEvent(jsone, jsonConfig);
        throw jsone;
    } catch (RuntimeException e) {
        removeInstance(bean);
        JSONException jsone = new JSONException(e);
        fireErrorEvent(jsone, jsonConfig);
        throw jsone;
    }
    removeInstance(bean);
    fireObjectEndEvent(jsonConfig);
    return jsonObject;
}


public int compareTo(Object obj){
    if (obj != null && (obj instanceof JSONObject)) {
        JSONObject other = (JSONObject) obj;
        int size1 = size();
        int size2 = other.size();
        if (size1 < size2) {
            return -1;
        } else if (size1 > size2) {
            return 1;
        } else if (this.equals(other)) {
            return 0;
        }
    }
    return -1;
}


public Object processValue(String key,Object value,JsonConfig jsonConfig){
    if (value != null) {
        JsonValueProcessor processor = jsonConfig.findJsonValueProcessor(value.getClass(), key);
        if (processor != null) {
            value = processor.processObjectValue(null, value, jsonConfig);
            if (!JsonVerifier.isValidJsonValue(value)) {
                throw new JSONException("Value is not a valid JSON value. " + value);
            }
        }
    }
    return _processValue(value, jsonConfig);
}


public boolean containsValue(Object value,JsonConfig jsonConfig){
    try {
        value = processValue(value, jsonConfig);
    } catch (JSONException e) {
        return false;
    }
    return properties.containsValue(value);
}


public Object put(Object key,Object value){
    if (key == null) {
        throw new IllegalArgumentException("key is null.");
    }
    Object previous = properties.get(key);
    element(String.valueOf(key), value);
    return previous;
}


public JSONArray getJSONArray(String key){
    verifyIsNull();
    Object o = get(key);
    if (o != null && o instanceof JSONArray) {
        return (JSONArray) o;
    }
    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] is not a JSONArray.");
}


public boolean optBoolean(String key,boolean defaultValue){
    verifyIsNull();
    try {
        return getBoolean(key);
    } catch (Exception e) {
        return defaultValue;
    }
}


public String optString(String key,String defaultValue){
    verifyIsNull();
    Object o = opt(key);
    return o != null ? o.toString() : defaultValue;
}


public boolean has(String key){
    verifyIsNull();
    return this.properties.containsKey(key);
}


public JSONObject _accumulate(String key,Object value,JsonConfig jsonConfig){
    if (isNullObject()) {
        throw new JSONException("Can't accumulate on null object");
    }
    if (!has(key)) {
        setInternal(key, value, jsonConfig);
    } else {
        Object o = opt(key);
        if (o instanceof JSONArray) {
            ((JSONArray) o).element(value, jsonConfig);
        } else {
            setInternal(key, new JSONArray().element(o).element(value, jsonConfig), jsonConfig);
        }
    }
    return this;
}


public Writer write(Writer writer){
    try {
        if (isNullObject()) {
            writer.write(JSONNull.getInstance().toString());
            return writer;
        }
        boolean b = false;
        Iterator keys = keys();
        writer.write('{');
        while (keys.hasNext()) {
            if (b) {
                writer.write(',');
            }
            Object k = keys.next();
            writer.write(JSONUtils.quote(k.toString()));
            writer.write(':');
            Object v = this.properties.get(k);
            if (v instanceof JSONObject) {
                ((JSONObject) v).write(writer);
            } else if (v instanceof JSONArray) {
                ((JSONArray) v).write(writer);
            } else {
                writer.write(JSONUtils.valueToString(v));
            }
            b = true;
        }
        writer.write('}');
        return writer;
    } catch (IOException e) {
        throw new JSONException(e);
    }
}


public JSONObject element(String key,Object value,JsonConfig jsonConfig){
    verifyIsNull();
    if (key == null) {
        throw new JSONException("Null key.");
    }
    /**
     * modified by houxu 2013-09-02
     * 增加处理JSONNull对象的处理
     * 如果出现null值,保存为空字符串
     *      if( value != null ){
     *         value = processValue( key, value, jsonConfig );
     *         _setInternal( key, value, jsonConfig );
     *      }else{
     *         remove( key );
     *      }
     */
    if ((value instanceof JSONNull) || value == null)
        value = "";
    value = processValue(key, value, jsonConfig);
    _setInternal(key, value, jsonConfig);
    return this;
}


public Set entrySet(){
    return Collections.unmodifiableSet(properties.entrySet());
}


public boolean containsKey(Object key){
    return properties.containsKey(key);
}


public JSONObject optJSONObject(String key){
    verifyIsNull();
    Object o = opt(key);
    return o instanceof JSONObject ? (JSONObject) o : null;
}


public JSONObject setInternal(String key,Object value,JsonConfig jsonConfig){
    return _setInternal(key, processValue(key, value, jsonConfig), jsonConfig);
}


public JSONObject _fromJSONString(JSONString string,JsonConfig jsonConfig){
    return _fromJSONTokener(new JSONTokener(string.toJSONString()), jsonConfig);
}


public JSONObject _fromMap(Map map,JsonConfig jsonConfig){
    if (map == null) {
        fireObjectStartEvent(jsonConfig);
        fireObjectEndEvent(jsonConfig);
        return new JSONObject(true);
    }
    if (!addInstance(map)) {
        try {
            return jsonConfig.getCycleDetectionStrategy().handleRepeatedReferenceAsObject(map);
        } catch (JSONException jsone) {
            removeInstance(map);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        } catch (RuntimeException e) {
            removeInstance(map);
            JSONException jsone = new JSONException(e);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        }
    }
    fireObjectStartEvent(jsonConfig);
    Collection exclusions = jsonConfig.getMergedExcludes();
    JSONObject jsonObject = new JSONObject();
    PropertyFilter jsonPropertyFilter = jsonConfig.getJsonPropertyFilter();
    try {
        for (Iterator entries = map.entrySet().iterator(); entries.hasNext(); ) {
            boolean bypass = false;
            Map.Entry entry = (Map.Entry) entries.next();
            Object k = entry.getKey();
            if (k == null) {
                throw new JSONException("JSON keys cannot be null.");
            }
            if (!(k instanceof String) && !jsonConfig.isAllowNonStringKeys()) {
                throw new ClassCastException("JSON keys must be strings.");
            }
            String key = String.valueOf(k);
            if ("null".equals(key)) {
                throw new NullPointerException("JSON keys must not be null nor the 'null' string.");
            }
            if (exclusions.contains(key)) {
                continue;
            }
            Object value = entry.getValue();
            if (jsonPropertyFilter != null && jsonPropertyFilter.apply(map, key, value)) {
                continue;
            }
            if (value != null) {
                JsonValueProcessor jsonValueProcessor = jsonConfig.findJsonValueProcessor(value.getClass(), key);
                if (jsonValueProcessor != null) {
                    value = jsonValueProcessor.processObjectValue(key, value, jsonConfig);
                    bypass = true;
                    if (!JsonVerifier.isValidJsonValue(value)) {
                        throw new JSONException("Value is not a valid JSON value. " + value);
                    }
                }
                setValue(jsonObject, key, value, value.getClass(), jsonConfig, bypass);
            } else {
                if (jsonObject.properties.containsKey(key)) {
                    jsonObject.accumulate(key, JSONNull.getInstance());
                    firePropertySetEvent(key, JSONNull.getInstance(), true, jsonConfig);
                } else {
                    jsonObject.element(key, JSONNull.getInstance());
                    firePropertySetEvent(key, JSONNull.getInstance(), false, jsonConfig);
                }
            }
        }
    } catch (JSONException jsone) {
        removeInstance(map);
        fireErrorEvent(jsone, jsonConfig);
        throw jsone;
    } catch (RuntimeException e) {
        removeInstance(map);
        JSONException jsone = new JSONException(e);
        fireErrorEvent(jsone, jsonConfig);
        throw jsone;
    }
    removeInstance(map);
    fireObjectEndEvent(jsonConfig);
    return jsonObject;
}


public int getInt(String key){
    verifyIsNull();
    Object o = get(key);
    if (o != null) {
        return o instanceof Number ? ((Number) o).intValue() : (int) getDouble(key);
    }
    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] is not a number.");
}


public Object opt(String key){
    verifyIsNull();
    return key == null ? null : this.properties.get(key);
}


public boolean isTransientField(Field field){
    return (field.getModifiers() & Modifier.TRANSIENT) == Modifier.TRANSIENT;
}


public int size(){
    // verifyIsNull();
    return this.properties.size();
}


public Collection convertPropertyValueToCollection(String key,Object value,JsonConfig jsonConfig,String name,Map classMap,Class collectionType){
    Class targetClass = findTargetClass(key, classMap);
    targetClass = targetClass == null ? findTargetClass(name, classMap) : targetClass;
    JsonConfig jsc = jsonConfig.copy();
    jsc.setRootClass(targetClass);
    jsc.setClassMap(classMap);
    jsc.setCollectionType(collectionType);
    return JSONArray.toCollection((JSONArray) value, jsc);
}


public boolean isNullObject(){
    return nullObject;
}


public long optLong(String key,long defaultValue){
    verifyIsNull();
    try {
        return getLong(key);
    } catch (Exception e) {
        return defaultValue;
    }
}


public void setProperty(Object bean,String key,Object value,JsonConfig jsonConfig){
    PropertySetStrategy propertySetStrategy = jsonConfig.getPropertySetStrategy() != null ? jsonConfig.getPropertySetStrategy() : PropertySetStrategy.DEFAULT;
    propertySetStrategy.setProperty(bean, key, value, jsonConfig);
}


public boolean isArray(){
    return false;
}


public double optDouble(String key,double defaultValue){
    verifyIsNull();
    try {
        Object o = opt(key);
        return o instanceof Number ? ((Number) o).doubleValue() : new Double((String) o).doubleValue();
    } catch (Exception e) {
        return defaultValue;
    }
}


public void accumulateAll(Map map,JsonConfig jsonConfig){
    if (map instanceof JSONObject) {
        for (Iterator entries = map.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            accumulate(key, value, jsonConfig);
        }
    } else {
        for (Iterator entries = map.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = String.valueOf(entry.getKey());
            Object value = entry.getValue();
            accumulate(key, value, jsonConfig);
        }
    }
}


public JSONObject fromObject(Object object,JsonConfig jsonConfig){
    if (object == null || JSONUtils.isNull(object)) {
        return new JSONObject(true);
    } else if (object instanceof JSONObject) {
        return _fromJSONObject((JSONObject) object, jsonConfig);
    } else if (object instanceof DynaBean) {
        return _fromDynaBean((DynaBean) object, jsonConfig);
    } else if (object instanceof JSONTokener) {
        return _fromJSONTokener((JSONTokener) object, jsonConfig);
    } else if (object instanceof JSONString) {
        return _fromJSONString((JSONString) object, jsonConfig);
    } else if (object instanceof Map) {
        return _fromMap((Map) object, jsonConfig);
    } else if (object instanceof String) {
        return _fromString((String) object, jsonConfig);
    } else if (JSONUtils.isNumber(object) || JSONUtils.isBoolean(object) || JSONUtils.isString(object)) {
        return new JSONObject();
    } else if (JSONUtils.isArray(object)) {
        throw new JSONException("'object' is an array. Use JSONArray instead");
    } else {
        return _fromBean(object, jsonConfig);
    }
}


public JSONObject elementOpt(String key,Object value,JsonConfig jsonConfig){
    verifyIsNull();
    if (key != null && value != null) {
        element(key, value, jsonConfig);
    }
    return this;
}


public Iterator keys(){
    verifyIsNull();
    return keySet().iterator();
}


public JSONObject _fromJSONTokener(JSONTokener tokener,JsonConfig jsonConfig){
    try {
        char c;
        String key;
        Object value;
        if (tokener.matches("null.*")) {
            fireObjectStartEvent(jsonConfig);
            fireObjectEndEvent(jsonConfig);
            return new JSONObject(true);
        }
        if (tokener.nextClean() != '{') {
            throw tokener.syntaxError("A JSONObject text must begin with '{'");
        }
        fireObjectStartEvent(jsonConfig);
        Collection exclusions = jsonConfig.getMergedExcludes();
        PropertyFilter jsonPropertyFilter = jsonConfig.getJsonPropertyFilter();
        JSONObject jsonObject = new JSONObject();
        for (; ; ) {
            c = tokener.nextClean();
            switch(c) {
                case 0:
                    throw tokener.syntaxError("A JSONObject text must end with '}'");
                case '}':
                    fireObjectEndEvent(jsonConfig);
                    return jsonObject;
                default:
                    tokener.back();
                    key = tokener.nextValue(jsonConfig).toString();
            }
            /*
             * The key is followed by ':'. We will also tolerate '=' or '=>'.
             */
            c = tokener.nextClean();
            if (c == '=') {
                if (tokener.next() != '>') {
                    tokener.back();
                }
            } else if (c != ':') {
                throw tokener.syntaxError("Expected a ':' after a key");
            }
            char peek = tokener.peek();
            boolean quoted = peek == '"' || peek == '\'';
            Object v = tokener.nextValue(jsonConfig);
            if (quoted || !JSONUtils.isFunctionHeader(v)) {
                if (exclusions.contains(key)) {
                    switch(tokener.nextClean()) {
                        case ';':
                        case ',':
                            if (tokener.nextClean() == '}') {
                                fireObjectEndEvent(jsonConfig);
                                return jsonObject;
                            }
                            tokener.back();
                            break;
                        case '}':
                            fireObjectEndEvent(jsonConfig);
                            return jsonObject;
                        default:
                            throw tokener.syntaxError("Expected a ',' or '}'");
                    }
                    continue;
                }
                if (jsonPropertyFilter == null || !jsonPropertyFilter.apply(tokener, key, v)) {
                    if (quoted && v instanceof String && (JSONUtils.mayBeJSON((String) v) || JSONUtils.isFunction(v))) {
                        v = JSONUtils.DOUBLE_QUOTE + v + JSONUtils.DOUBLE_QUOTE;
                    }
                    if (jsonObject.properties.containsKey(key)) {
                        jsonObject.accumulate(key, v, jsonConfig);
                        firePropertySetEvent(key, v, true, jsonConfig);
                    } else {
                        jsonObject.element(key, v, jsonConfig);
                        firePropertySetEvent(key, v, false, jsonConfig);
                    }
                }
            } else {
                // read params if any
                String params = JSONUtils.getFunctionParams((String) v);
                // read function text
                int i = 0;
                StringBuffer sb = new StringBuffer();
                for (; ; ) {
                    char ch = tokener.next();
                    if (ch == 0) {
                        break;
                    }
                    if (ch == '{') {
                        i++;
                    }
                    if (ch == '}') {
                        i--;
                    }
                    sb.append(ch);
                    if (i == 0) {
                        break;
                    }
                }
                if (i != 0) {
                    throw tokener.syntaxError("Unbalanced '{' or '}' on prop: " + v);
                }
                // trim '{' at start and '}' at end
                String text = sb.toString();
                text = text.substring(1, text.length() - 1).trim();
                value = new JSONFunction((params != null) ? StringUtils.split(params, ",") : null, text);
                if (jsonPropertyFilter == null || !jsonPropertyFilter.apply(tokener, key, value)) {
                    if (jsonObject.properties.containsKey(key)) {
                        jsonObject.accumulate(key, value, jsonConfig);
                        firePropertySetEvent(key, value, true, jsonConfig);
                    } else {
                        jsonObject.element(key, value, jsonConfig);
                        firePropertySetEvent(key, value, false, jsonConfig);
                    }
                }
            }
            /*
             * Pairs are separated by ','. We will also tolerate ';'.
             */
            switch(tokener.nextClean()) {
                case ';':
                case ',':
                    if (tokener.nextClean() == '}') {
                        fireObjectEndEvent(jsonConfig);
                        return jsonObject;
                    }
                    tokener.back();
                    break;
                case '}':
                    fireObjectEndEvent(jsonConfig);
                    return jsonObject;
                default:
                    throw tokener.syntaxError("Expected a ',' or '}'");
            }
        }
    } catch (JSONException jsone) {
        fireErrorEvent(jsone, jsonConfig);
        throw jsone;
    }
}


public Collection values(){
    return Collections.unmodifiableCollection(properties.values());
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


public Object _processValue(Object value,JsonConfig jsonConfig){
    if (value instanceof JSONTokener) {
        return _fromJSONTokener((JSONTokener) value, jsonConfig);
    }
    return super._processValue(value, jsonConfig);
}


public Object remove(String key){
    verifyIsNull();
    return this.properties.remove(key);
}


public void verifyIsNull(){
    if (isNullObject()) {
        throw new JSONException("null object");
    }
}


public Object morphPropertyValue(String key,Object value,Class type,Class targetType){
    Morpher morpher = JSONUtils.getMorpherRegistry().getMorpherFor(targetType);
    if (IdentityObjectMorpher.getInstance().equals(morpher)) {
        log.warn("Can't transform property '" + key + "' from " + type.getName() + " into " + targetType.getName() + ". Will register a default BeanMorpher");
        JSONUtils.getMorpherRegistry().registerMorpher(new BeanMorpher(targetType, JSONUtils.getMorpherRegistry()));
    }
    value = JSONUtils.getMorpherRegistry().morph(targetType, value);
    return value;
}


public int hashCode(){
    int hashcode = 19;
    if (isNullObject()) {
        return hashcode + JSONNull.getInstance().hashCode();
    }
    for (Iterator entries = properties.entrySet().iterator(); entries.hasNext(); ) {
        Map.Entry entry = (Map.Entry) entries.next();
        Object key = entry.getKey();
        Object value = entry.getValue();
        hashcode += key.hashCode() + JSONUtils.hashCode(value);
    }
    return hashcode;
}


public Object get(String key){
    verifyIsNull();
    return this.properties.get(key);
}


public void putAll(Map map,JsonConfig jsonConfig){
    if (map instanceof JSONObject) {
        for (Iterator entries = map.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            this.properties.put(key, value);
        }
    } else {
        for (Iterator entries = map.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = String.valueOf(entry.getKey());
            Object value = entry.getValue();
            element(key, value, jsonConfig);
        }
    }
}


public JSONObject accumulate(String key,Object value,JsonConfig jsonConfig){
    return _accumulate(key, value, jsonConfig);
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


public Set keySet(){
    return Collections.unmodifiableSet(properties.keySet());
}


public Class findTargetClass(String key,Map classMap){
    // try get first
    Class targetClass = (Class) classMap.get(key);
    if (targetClass == null) {
        // try with regexp
        // this will hit performance as it must iterate over all the keys
        // and create a RegexpMatcher for each key
        for (Iterator i = classMap.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry entry = (Map.Entry) i.next();
            if (RegexpUtils.getMatcher((String) entry.getKey()).matches(key)) {
                targetClass = (Class) entry.getValue();
                break;
            }
        }
    }
    return targetClass;
}


public Object toBean(JSONObject jsonObject,Object root,JsonConfig jsonConfig){
    if (jsonObject == null || jsonObject.isNullObject() || root == null) {
        return root;
    }
    Class rootClass = root.getClass();
    if (rootClass.isInterface()) {
        throw new JSONException("Root bean is an interface. " + rootClass);
    }
    Map classMap = jsonConfig.getClassMap();
    if (classMap == null) {
        classMap = Collections.EMPTY_MAP;
    }
    Map props = JSONUtils.getProperties(jsonObject);
    PropertyFilter javaPropertyFilter = jsonConfig.getJavaPropertyFilter();
    for (Iterator entries = jsonObject.names(jsonConfig).iterator(); entries.hasNext(); ) {
        String name = (String) entries.next();
        Class type = (Class) props.get(name);
        Object value = jsonObject.get(name);
        if (javaPropertyFilter != null && javaPropertyFilter.apply(root, name, value)) {
            continue;
        }
        String key = JSONUtils.convertToJavaIdentifier(name, jsonConfig);
        try {
            PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(root, key);
            if (pd != null && pd.getWriteMethod() == null) {
                log.info("Property '" + key + "' of " + root.getClass() + " has no write method. SKIPPED.");
                continue;
            }
            if (!JSONUtils.isNull(value)) {
                if (value instanceof JSONArray) {
                    if (pd == null || List.class.isAssignableFrom(pd.getPropertyType())) {
                        Class targetClass = findTargetClass(key, classMap);
                        targetClass = targetClass == null ? findTargetClass(name, classMap) : targetClass;
                        Object newRoot = jsonConfig.getNewBeanInstanceStrategy().newInstance(targetClass, null);
                        List list = JSONArray.toList((JSONArray) value, newRoot, jsonConfig);
                        setProperty(root, key, list, jsonConfig);
                    } else {
                        Class innerType = JSONUtils.getInnerComponentType(pd.getPropertyType());
                        Class targetInnerType = findTargetClass(key, classMap);
                        if (innerType.equals(Object.class) && targetInnerType != null && !targetInnerType.equals(Object.class)) {
                            innerType = targetInnerType;
                        }
                        Object newRoot = jsonConfig.getNewBeanInstanceStrategy().newInstance(innerType, null);
                        Object array = JSONArray.toArray((JSONArray) value, newRoot, jsonConfig);
                        if (innerType.isPrimitive() || JSONUtils.isNumber(innerType) || Boolean.class.isAssignableFrom(innerType) || JSONUtils.isString(innerType)) {
                            array = JSONUtils.getMorpherRegistry().morph(Array.newInstance(innerType, 0).getClass(), array);
                        } else if (!array.getClass().equals(pd.getPropertyType())) {
                            if (!pd.getPropertyType().equals(Object.class)) {
                                Morpher morpher = JSONUtils.getMorpherRegistry().getMorpherFor(Array.newInstance(innerType, 0).getClass());
                                if (IdentityObjectMorpher.getInstance().equals(morpher)) {
                                    ObjectArrayMorpher beanMorpher = new ObjectArrayMorpher(new BeanMorpher(innerType, JSONUtils.getMorpherRegistry()));
                                    JSONUtils.getMorpherRegistry().registerMorpher(beanMorpher);
                                }
                                array = JSONUtils.getMorpherRegistry().morph(Array.newInstance(innerType, 0).getClass(), array);
                            }
                        }
                        setProperty(root, key, array, jsonConfig);
                    }
                } else if (String.class.isAssignableFrom(type) || JSONUtils.isBoolean(type) || JSONUtils.isNumber(type) || JSONUtils.isString(type) || JSONFunction.class.isAssignableFrom(type)) {
                    if (pd != null) {
                        if (jsonConfig.isHandleJettisonEmptyElement() && "".equals(value)) {
                            setProperty(root, key, null, jsonConfig);
                        } else if (!pd.getPropertyType().isInstance(value)) {
                            Morpher morpher = JSONUtils.getMorpherRegistry().getMorpherFor(pd.getPropertyType());
                            if (IdentityObjectMorpher.getInstance().equals(morpher)) {
                                log.warn("Can't transform property '" + key + "' from " + type.getName() + " into " + pd.getPropertyType().getName() + ". Will register a default BeanMorpher");
                                JSONUtils.getMorpherRegistry().registerMorpher(new BeanMorpher(pd.getPropertyType(), JSONUtils.getMorpherRegistry()));
                            }
                            setProperty(root, key, JSONUtils.getMorpherRegistry().morph(pd.getPropertyType(), value), jsonConfig);
                        } else {
                            setProperty(root, key, value, jsonConfig);
                        }
                    } else if (root instanceof Map) {
                        setProperty(root, key, value, jsonConfig);
                    } else {
                        log.warn("Tried to assign property " + key + ":" + type.getName() + " to bean of class " + root.getClass().getName());
                    }
                } else {
                    if (pd != null) {
                        Class targetClass = pd.getPropertyType();
                        if (jsonConfig.isHandleJettisonSingleElementArray()) {
                            JSONArray array = new JSONArray().element(value, jsonConfig);
                            Class newTargetClass = findTargetClass(key, classMap);
                            newTargetClass = newTargetClass == null ? findTargetClass(name, classMap) : newTargetClass;
                            Object newRoot = jsonConfig.getNewBeanInstanceStrategy().newInstance(newTargetClass, null);
                            if (targetClass.isArray()) {
                                setProperty(root, key, JSONArray.toArray(array, newRoot, jsonConfig), jsonConfig);
                            } else if (Collection.class.isAssignableFrom(targetClass)) {
                                setProperty(root, key, JSONArray.toList(array, newRoot, jsonConfig), jsonConfig);
                            } else if (JSONArray.class.isAssignableFrom(targetClass)) {
                                setProperty(root, key, array, jsonConfig);
                            } else {
                                setProperty(root, key, toBean((JSONObject) value, newRoot, jsonConfig), jsonConfig);
                            }
                        } else {
                            if (targetClass == Object.class) {
                                targetClass = findTargetClass(key, classMap);
                                targetClass = targetClass == null ? findTargetClass(name, classMap) : targetClass;
                            }
                            Object newRoot = jsonConfig.getNewBeanInstanceStrategy().newInstance(targetClass, null);
                            setProperty(root, key, toBean((JSONObject) value, newRoot, jsonConfig), jsonConfig);
                        }
                    } else if (root instanceof Map) {
                        Class targetClass = findTargetClass(key, classMap);
                        targetClass = targetClass == null ? findTargetClass(name, classMap) : targetClass;
                        Object newRoot = jsonConfig.getNewBeanInstanceStrategy().newInstance(targetClass, null);
                        setProperty(root, key, toBean((JSONObject) value, newRoot, jsonConfig), jsonConfig);
                    } else {
                        log.warn("Tried to assign property " + key + ":" + type.getName() + " to bean of class " + rootClass.getName());
                    }
                }
            } else {
                if (type.isPrimitive()) {
                    // assume assigned default value
                    log.warn("Tried to assign null value to " + key + ":" + type.getName());
                    setProperty(root, key, JSONUtils.getMorpherRegistry().morph(type, null), jsonConfig);
                } else {
                    setProperty(root, key, null, jsonConfig);
                }
            }
        } catch (JSONException jsone) {
            throw jsone;
        } catch (Exception e) {
            throw new JSONException("Error while setting property=" + name + " type " + type, e);
        }
    }
    return root;
}


public void clear(){
    properties.clear();
}


public boolean isEmpty(){
    // verifyIsNull();
    return this.properties.isEmpty();
}


public Object convertPropertyValueToArray(String key,Object value,Class targetType,JsonConfig jsonConfig,Map classMap){
    Class innerType = JSONUtils.getInnerComponentType(targetType);
    Class targetInnerType = findTargetClass(key, classMap);
    if (innerType.equals(Object.class) && targetInnerType != null && !targetInnerType.equals(Object.class)) {
        innerType = targetInnerType;
    }
    JsonConfig jsc = jsonConfig.copy();
    jsc.setRootClass(innerType);
    jsc.setClassMap(classMap);
    Object array = JSONArray.toArray((JSONArray) value, jsc);
    if (innerType.isPrimitive() || JSONUtils.isNumber(innerType) || Boolean.class.isAssignableFrom(innerType) || JSONUtils.isString(innerType)) {
        array = JSONUtils.getMorpherRegistry().morph(Array.newInstance(innerType, 0).getClass(), array);
    } else if (!array.getClass().equals(targetType)) {
        if (!targetType.equals(Object.class)) {
            Morpher morpher = JSONUtils.getMorpherRegistry().getMorpherFor(Array.newInstance(innerType, 0).getClass());
            if (IdentityObjectMorpher.getInstance().equals(morpher)) {
                ObjectArrayMorpher beanMorpher = new ObjectArrayMorpher(new BeanMorpher(innerType, JSONUtils.getMorpherRegistry()));
                JSONUtils.getMorpherRegistry().registerMorpher(beanMorpher);
            }
            array = JSONUtils.getMorpherRegistry().morph(Array.newInstance(innerType, 0).getClass(), array);
        }
    }
    return array;
}


public String getString(String key){
    verifyIsNull();
    Object o = get(key);
    if (o != null) {
        return o.toString();
    }
    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] not found.");
}


public JSONArray optJSONArray(String key){
    verifyIsNull();
    Object o = opt(key);
    return o instanceof JSONArray ? (JSONArray) o : null;
}


public JSONObject _fromJSONObject(JSONObject object,JsonConfig jsonConfig){
    if (object == null || object.isNullObject()) {
        fireObjectStartEvent(jsonConfig);
        fireObjectEndEvent(jsonConfig);
        return new JSONObject(true);
    }
    if (!addInstance(object)) {
        try {
            return jsonConfig.getCycleDetectionStrategy().handleRepeatedReferenceAsObject(object);
        } catch (JSONException jsone) {
            removeInstance(object);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        } catch (RuntimeException e) {
            removeInstance(object);
            JSONException jsone = new JSONException(e);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        }
    }
    fireObjectStartEvent(jsonConfig);
    JSONArray sa = object.names(jsonConfig);
    Collection exclusions = jsonConfig.getMergedExcludes();
    JSONObject jsonObject = new JSONObject();
    PropertyFilter jsonPropertyFilter = jsonConfig.getJsonPropertyFilter();
    for (Iterator i = sa.iterator(); i.hasNext(); ) {
        Object k = i.next();
        if (k == null) {
            throw new JSONException("JSON keys cannot be null.");
        }
        if (!(k instanceof String) && !jsonConfig.isAllowNonStringKeys()) {
            throw new ClassCastException("JSON keys must be strings.");
        }
        String key = String.valueOf(k);
        if ("null".equals(key)) {
            throw new NullPointerException("JSON keys must not be null nor the 'null' string.");
        }
        if (exclusions.contains(key)) {
            continue;
        }
        Object value = object.opt(key);
        if (jsonPropertyFilter != null && jsonPropertyFilter.apply(object, key, value)) {
            continue;
        }
        if (jsonObject.properties.containsKey(key)) {
            jsonObject.accumulate(key, value, jsonConfig);
            firePropertySetEvent(key, value, true, jsonConfig);
        } else {
            jsonObject.setInternal(key, value, jsonConfig);
            firePropertySetEvent(key, value, false, jsonConfig);
        }
    }
    removeInstance(object);
    fireObjectEndEvent(jsonConfig);
    return jsonObject;
}


public long getLong(String key){
    verifyIsNull();
    Object o = get(key);
    if (o != null) {
        return o instanceof Number ? ((Number) o).longValue() : (long) getDouble(key);
    }
    throw new JSONException("JSONObject[" + JSONUtils.quote(key) + "] is not a number.");
}


public JSONArray names(JsonConfig jsonConfig){
    verifyIsNull();
    JSONArray ja = new JSONArray();
    Iterator keys = keys();
    while (keys.hasNext()) {
        ja.element(keys.next(), jsonConfig);
    }
    return ja;
}


public JSONObject _fromBean(Object bean,JsonConfig jsonConfig){
    if (!addInstance(bean)) {
        try {
            return jsonConfig.getCycleDetectionStrategy().handleRepeatedReferenceAsObject(bean);
        } catch (JSONException jsone) {
            removeInstance(bean);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        } catch (RuntimeException e) {
            removeInstance(bean);
            JSONException jsone = new JSONException(e);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        }
    }
    fireObjectStartEvent(jsonConfig);
    JsonBeanProcessor processor = jsonConfig.findJsonBeanProcessor(bean.getClass());
    if (processor != null) {
        JSONObject json = null;
        try {
            json = processor.processBean(bean, jsonConfig);
            if (json == null) {
                json = (JSONObject) jsonConfig.findDefaultValueProcessor(bean.getClass()).getDefaultValue(bean.getClass());
                if (json == null) {
                    json = new JSONObject(true);
                }
            }
            removeInstance(bean);
            fireObjectEndEvent(jsonConfig);
        } catch (JSONException jsone) {
            removeInstance(bean);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        } catch (RuntimeException e) {
            removeInstance(bean);
            JSONException jsone = new JSONException(e);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        }
        return json;
    }
    Class beanClass = bean.getClass();
    PropertyNameProcessor propertyNameProcessor = jsonConfig.findJsonPropertyNameProcessor(beanClass);
    Collection exclusions = jsonConfig.getMergedExcludes(beanClass);
    JSONObject jsonObject = new JSONObject();
    try {
        PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean);
        PropertyFilter jsonPropertyFilter = jsonConfig.getJsonPropertyFilter();
        for (int i = 0; i < pds.length; i++) {
            boolean bypass = false;
            String key = pds[i].getName();
            if (exclusions.contains(key)) {
                continue;
            }
            if (jsonConfig.isIgnoreTransientFields() && isTransientField(key, beanClass)) {
                continue;
            }
            Class type = pds[i].getPropertyType();
            try {
                pds[i].getReadMethod();
            } catch (Exception e) {
                // bug 2565295
                String warning = "Property '" + key + "' of " + beanClass + " has no read method. SKIPPED";
                fireWarnEvent(warning, jsonConfig);
                log.info(warning);
                continue;
            }
            if (pds[i].getReadMethod() != null) {
                Object value = PropertyUtils.getProperty(bean, key);
                if (jsonPropertyFilter != null && jsonPropertyFilter.apply(bean, key, value)) {
                    continue;
                }
                JsonValueProcessor jsonValueProcessor = jsonConfig.findJsonValueProcessor(beanClass, type, key);
                if (jsonValueProcessor != null) {
                    value = jsonValueProcessor.processObjectValue(key, value, jsonConfig);
                    bypass = true;
                    if (!JsonVerifier.isValidJsonValue(value)) {
                        throw new JSONException("Value is not a valid JSON value. " + value);
                    }
                }
                if (propertyNameProcessor != null) {
                    key = propertyNameProcessor.processPropertyName(beanClass, key);
                }
                setValue(jsonObject, key, value, type, jsonConfig, bypass);
            } else {
                String warning = "Property '" + key + "' of " + beanClass + " has no read method. SKIPPED";
                fireWarnEvent(warning, jsonConfig);
                log.info(warning);
            }
        }
        // inspect public fields, this operation may fail under
        // a SecurityManager so we will eat all exceptions
        try {
            if (!jsonConfig.isIgnorePublicFields()) {
                Field[] fields = beanClass.getFields();
                for (int i = 0; i < fields.length; i++) {
                    boolean bypass = false;
                    Field field = fields[i];
                    String key = field.getName();
                    if (exclusions.contains(key)) {
                        continue;
                    }
                    if (jsonConfig.isIgnoreTransientFields() && isTransientField(field)) {
                        continue;
                    }
                    Class type = field.getType();
                    Object value = field.get(bean);
                    if (jsonPropertyFilter != null && jsonPropertyFilter.apply(bean, key, value)) {
                        continue;
                    }
                    JsonValueProcessor jsonValueProcessor = jsonConfig.findJsonValueProcessor(beanClass, type, key);
                    if (jsonValueProcessor != null) {
                        value = jsonValueProcessor.processObjectValue(key, value, jsonConfig);
                        bypass = true;
                        if (!JsonVerifier.isValidJsonValue(value)) {
                            throw new JSONException("Value is not a valid JSON value. " + value);
                        }
                    }
                    if (propertyNameProcessor != null) {
                        key = propertyNameProcessor.processPropertyName(beanClass, key);
                    }
                    setValue(jsonObject, key, value, type, jsonConfig, bypass);
                }
            }
        } catch (Exception e) {
            log.trace("Couldn't read public fields.", e);
        }
    } catch (JSONException jsone) {
        removeInstance(bean);
        fireErrorEvent(jsone, jsonConfig);
        throw jsone;
    } catch (Exception e) {
        removeInstance(bean);
        JSONException jsone = new JSONException(e);
        fireErrorEvent(jsone, jsonConfig);
        throw jsone;
    }
    removeInstance(bean);
    fireObjectEndEvent(jsonConfig);
    return jsonObject;
}


public int optInt(String key,int defaultValue){
    verifyIsNull();
    try {
        return getInt(key);
    } catch (Exception e) {
        return defaultValue;
    }
}


public void setValue(JSONObject jsonObject,String key,Object value,Class type,JsonConfig jsonConfig,boolean bypass){
    boolean accumulated = false;
    if (value == null) {
        value = jsonConfig.findDefaultValueProcessor(type).getDefaultValue(type);
        if (!JsonVerifier.isValidJsonValue(value)) {
            throw new JSONException("Value is not a valid JSON value. " + value);
        }
    }
    if (jsonObject.properties.containsKey(key)) {
        if (String.class.isAssignableFrom(type)) {
            Object o = jsonObject.opt(key);
            if (o instanceof JSONArray) {
                ((JSONArray) o).addString((String) value);
            } else {
                jsonObject.properties.put(key, new JSONArray().element(o).addString((String) value));
            }
        } else {
            jsonObject.accumulate(key, value, jsonConfig);
        }
        accumulated = true;
    } else {
        if (bypass || String.class.isAssignableFrom(type)) {
            jsonObject.properties.put(key, value);
        } else {
            jsonObject.setInternal(key, value, jsonConfig);
        }
    }
    value = jsonObject.opt(key);
    if (accumulated) {
        JSONArray array = (JSONArray) value;
        value = array.get(array.size() - 1);
    }
    firePropertySetEvent(key, value, accumulated, jsonConfig);
}


public boolean equals(Object obj){
    if (obj == this) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (!(obj instanceof JSONObject)) {
        return false;
    }
    JSONObject other = (JSONObject) obj;
    if (isNullObject()) {
        if (other.isNullObject()) {
            return true;
        } else {
            return false;
        }
    } else {
        if (other.isNullObject()) {
            return false;
        }
    }
    if (other.size() != size()) {
        return false;
    }
    for (Iterator keys = properties.keySet().iterator(); keys.hasNext(); ) {
        String key = (String) keys.next();
        if (!other.properties.containsKey(key)) {
            return false;
        }
        Object o1 = properties.get(key);
        Object o2 = other.properties.get(key);
        if (JSONNull.getInstance().equals(o1)) {
            if (JSONNull.getInstance().equals(o2)) {
                continue;
            } else {
                return false;
            }
        } else {
            if (JSONNull.getInstance().equals(o2)) {
                return false;
            }
        }
        if (o1 instanceof String && o2 instanceof JSONFunction) {
            if (!o1.equals(String.valueOf(o2))) {
                return false;
            }
        } else if (o1 instanceof JSONFunction && o2 instanceof String) {
            if (!o2.equals(String.valueOf(o1))) {
                return false;
            }
        } else if (o1 instanceof JSONObject && o2 instanceof JSONObject) {
            if (!o1.equals(o2)) {
                return false;
            }
        } else if (o1 instanceof JSONArray && o2 instanceof JSONArray) {
            if (!o1.equals(o2)) {
                return false;
            }
        } else if (o1 instanceof JSONFunction && o2 instanceof JSONFunction) {
            if (!o1.equals(o2)) {
                return false;
            }
        } else {
            if (o1 instanceof String) {
                if (!o1.equals(String.valueOf(o2))) {
                    return false;
                }
            } else if (o2 instanceof String) {
                if (!o2.equals(String.valueOf(o1))) {
                    return false;
                }
            } else {
                Morpher m1 = JSONUtils.getMorpherRegistry().getMorpherFor(o1.getClass());
                Morpher m2 = JSONUtils.getMorpherRegistry().getMorpherFor(o2.getClass());
                if (m1 != null && m1 != IdentityObjectMorpher.getInstance()) {
                    if (!o1.equals(JSONUtils.getMorpherRegistry().morph(o1.getClass(), o2))) {
                        return false;
                    }
                } else if (m2 != null && m2 != IdentityObjectMorpher.getInstance()) {
                    if (!JSONUtils.getMorpherRegistry().morph(o1.getClass(), o1).equals(o2)) {
                        return false;
                    }
                } else {
                    if (!o1.equals(o2)) {
                        return false;
                    }
                }
            }
        }
    }
    return true;
}


public String toString(int indentFactor,int indent){
    if (isNullObject()) {
        return JSONNull.getInstance().toString();
    }
    int i;
    int n = size();
    if (n == 0) {
        return "{}";
    }
    if (indentFactor == 0) {
        return this.toString();
    }
    Iterator keys = keys();
    StringBuffer sb = new StringBuffer("{");
    int newindent = indent + indentFactor;
    Object o;
    if (n == 1) {
        o = keys.next();
        sb.append(JSONUtils.quote(o.toString()));
        sb.append(": ");
        sb.append(JSONUtils.valueToString(this.properties.get(o), indentFactor, indent));
    } else {
        while (keys.hasNext()) {
            o = keys.next();
            if (sb.length() > 1) {
                sb.append(",\n");
            } else {
                sb.append('\n');
            }
            for (i = 0; i < newindent; i += 1) {
                sb.append(' ');
            }
            sb.append(JSONUtils.quote(o.toString()));
            sb.append(": ");
            sb.append(JSONUtils.valueToString(this.properties.get(o), indentFactor, newindent));
        }
        if (sb.length() > 1) {
            sb.append('\n');
            for (i = 0; i < indent; i += 1) {
                sb.append(' ');
            }
        }
        for (i = 0; i < indent; i += 1) {
            sb.insert(0, ' ');
        }
    }
    sb.append('}');
    return sb.toString();
}


public JSONObject _setInternal(String key,Object value,JsonConfig jsonConfig){
    verifyIsNull();
    if (key == null) {
        throw new JSONException("Null key.");
    }
    if (JSONUtils.isString(value) && JSONUtils.mayBeJSON(String.valueOf(value))) {
        this.properties.put(key, value);
    } else {
        /*
         Object jo = _processValue( value, jsonConfig );
         if( CycleDetectionStrategy.IGNORE_PROPERTY_OBJ == jo
               || CycleDetectionStrategy.IGNORE_PROPERTY_ARR == jo ){
            // do nothing
         }else{
            this.properties.put( key, jo );
         }
         */
        if (CycleDetectionStrategy.IGNORE_PROPERTY_OBJ == value || CycleDetectionStrategy.IGNORE_PROPERTY_ARR == value) {
        // do nothing
        } else {
            this.properties.put(key, value);
        }
    }
    return this;
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


}
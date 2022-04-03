package com.xwtec.xwserver.util.json;
 import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.xwtec.xwserver.util.json.util.JSONUtils;
import com.xwtec.xwserver.util.json.util.JsonEventListener;
@SuppressWarnings("unchecked")
public class AbstractJSON {

 private  CycleSet cycleSet;

 private  Log log;


public void fireArrayEndEvent(JsonConfig jsonConfig){
    if (jsonConfig.isEventTriggeringEnabled()) {
        for (Iterator listeners = jsonConfig.getJsonEventListeners().iterator(); listeners.hasNext(); ) {
            JsonEventListener listener = (JsonEventListener) listeners.next();
            try {
                listener.onArrayEnd();
            } catch (RuntimeException e) {
                log.warn(e);
            }
        }
    }
}


public Set getCycleSet(){
    return cycleSet.getSet();
}


public Set getSet(){
    Set set = (Set) ((SoftReference) get()).get();
    if (set == null) {
        set = new HashSet();
        set(new SoftReference(set));
    }
    return set;
}


public Object _processValue(Object value,JsonConfig jsonConfig){
    if (JSONNull.getInstance().equals(value)) {
        return JSONNull.getInstance();
    } else if (Class.class.isAssignableFrom(value.getClass()) || value instanceof Class) {
        return ((Class) value).getName();
    } else if (JSONUtils.isFunction(value)) {
        if (value instanceof String) {
            value = JSONFunction.parse((String) value);
        }
        return value;
    } else if (value instanceof JSONString) {
        return JSONSerializer.toJSON((JSONString) value, jsonConfig);
    } else if (value instanceof JSON) {
        return JSONSerializer.toJSON(value, jsonConfig);
    } else if (JSONUtils.isArray(value)) {
        return JSONArray.fromObject(value, jsonConfig);
    } else if (JSONUtils.isString(value)) {
        String str = String.valueOf(value);
        if (JSONUtils.hasQuotes(str)) {
            str = JSONUtils.stripQuotes(str);
            if (JSONUtils.isFunction(str)) {
                return JSONUtils.DOUBLE_QUOTE + str + JSONUtils.DOUBLE_QUOTE;
            }
            return str;
        } else if (JSONUtils.isJsonKeyword(str, jsonConfig)) {
            if (jsonConfig.isJavascriptCompliant() && "undefined".equals(str)) {
                return JSONNull.getInstance();
            }
            return str;
        } else if (JSONUtils.mayBeJSON(str)) {
            try {
                return JSONSerializer.toJSON(str, jsonConfig);
            } catch (JSONException jsone) {
                return str;
            }
        }
        return str;
    } else if (JSONUtils.isNumber(value)) {
        JSONUtils.testValidity(value);
        return JSONUtils.transformNumber((Number) value);
    } else if (JSONUtils.isBoolean(value)) {
        return value;
    } else {
        JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
        if (jsonObject.isNullObject()) {
            return JSONNull.getInstance();
        } else {
            return jsonObject;
        }
    }
}


public void fireWarnEvent(String warning,JsonConfig jsonConfig){
    if (jsonConfig.isEventTriggeringEnabled()) {
        for (Iterator listeners = jsonConfig.getJsonEventListeners().iterator(); listeners.hasNext(); ) {
            JsonEventListener listener = (JsonEventListener) listeners.next();
            try {
                listener.onWarning(warning);
            } catch (RuntimeException e) {
                log.warn(e);
            }
        }
    }
}


public void fireElementAddedEvent(int index,Object element,JsonConfig jsonConfig){
    if (jsonConfig.isEventTriggeringEnabled()) {
        for (Iterator listeners = jsonConfig.getJsonEventListeners().iterator(); listeners.hasNext(); ) {
            JsonEventListener listener = (JsonEventListener) listeners.next();
            try {
                listener.onElementAdded(index, element);
            } catch (RuntimeException e) {
                log.warn(e);
            }
        }
    }
}


public void fireObjectEndEvent(JsonConfig jsonConfig){
    if (jsonConfig.isEventTriggeringEnabled()) {
        for (Iterator listeners = jsonConfig.getJsonEventListeners().iterator(); listeners.hasNext(); ) {
            JsonEventListener listener = (JsonEventListener) listeners.next();
            try {
                listener.onObjectEnd();
            } catch (RuntimeException e) {
                log.warn(e);
            }
        }
    }
}


public boolean addInstance(Object instance){
    return getCycleSet().add(instance);
}


public void fireErrorEvent(JSONException jsone,JsonConfig jsonConfig){
    if (jsonConfig.isEventTriggeringEnabled()) {
        for (Iterator listeners = jsonConfig.getJsonEventListeners().iterator(); listeners.hasNext(); ) {
            JsonEventListener listener = (JsonEventListener) listeners.next();
            try {
                listener.onError(jsone);
            } catch (RuntimeException e) {
                log.warn(e);
            }
        }
    }
}


public void removeInstance(Object instance){
    getCycleSet().remove(instance);
}


public void fireObjectStartEvent(JsonConfig jsonConfig){
    if (jsonConfig.isEventTriggeringEnabled()) {
        for (Iterator listeners = jsonConfig.getJsonEventListeners().iterator(); listeners.hasNext(); ) {
            JsonEventListener listener = (JsonEventListener) listeners.next();
            try {
                listener.onObjectStart();
            } catch (RuntimeException e) {
                log.warn(e);
            }
        }
    }
}


public void firePropertySetEvent(String key,Object value,boolean accumulated,JsonConfig jsonConfig){
    if (jsonConfig.isEventTriggeringEnabled()) {
        for (Iterator listeners = jsonConfig.getJsonEventListeners().iterator(); listeners.hasNext(); ) {
            JsonEventListener listener = (JsonEventListener) listeners.next();
            try {
                listener.onPropertySet(key, value, accumulated);
            } catch (RuntimeException e) {
                log.warn(e);
            }
        }
    }
}


public Object initialValue(){
    return new SoftReference(new HashSet());
}


public void fireArrayStartEvent(JsonConfig jsonConfig){
    if (jsonConfig.isEventTriggeringEnabled()) {
        for (Iterator listeners = jsonConfig.getJsonEventListeners().iterator(); listeners.hasNext(); ) {
            JsonEventListener listener = (JsonEventListener) listeners.next();
            try {
                listener.onArrayStart();
            } catch (RuntimeException e) {
                log.warn(e);
            }
        }
    }
}


}
package com.xwtec.xwserver.util.json.groovy;
 import groovy.lang.Closure;
import groovy.lang.GString;
import groovy.lang.GroovyObjectSupport;
import groovy.lang.MissingMethodException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import com.xwtec.xwserver.util.json.JSON;
import com.xwtec.xwserver.util.json.JSONArray;
import com.xwtec.xwserver.util.json.JSONException;
import com.xwtec.xwserver.util.json.JSONObject;
import com.xwtec.xwserver.util.json.JSONSerializer;
@SuppressWarnings("unchecked")
public class JsonGroovyBuilder extends GroovyObjectSupport{

 private  String JSON;

 private  JSON current;

 private  Map properties;

 private  Stack stack;

public JsonGroovyBuilder() {
    stack = new Stack();
    properties = new HashMap();
}
public JSON createArray(List list){
    JSONArray array = new JSONArray();
    stack.push(array);
    for (Iterator elements = list.iterator(); elements.hasNext(); ) {
        Object element = elements.next();
        if (element instanceof Closure) {
            element = createObject((Closure) element);
        } else if (element instanceof Map) {
            element = createObject((Map) element);
        } else if (element instanceof List) {
            element = createArray((List) element);
        }
        array.element(element);
    }
    stack.pop();
    return array;
}


public Object getProperty(String name){
    if (!stack.isEmpty()) {
        Object top = stack.peek();
        if (top instanceof JSONObject) {
            JSONObject json = (JSONObject) top;
            if (json.containsKey(name)) {
                return json.get(name);
            } else {
                return _getProperty(name);
            }
        } else {
            return _getProperty(name);
        }
    } else {
        return _getProperty(name);
    }
}


public void setProperty(String name,Object value){
    if (value instanceof GString) {
        value = value.toString();
        try {
            value = JSONSerializer.toJSON(value);
        } catch (JSONException jsone) {
        // its a String literal
        }
    } else if (value instanceof Closure) {
        value = createObject((Closure) value);
    } else if (value instanceof Map) {
        value = createObject((Map) value);
    } else if (value instanceof List) {
        value = createArray((List) value);
    }
    append(name, value);
}


public Object _getProperty(String name){
    if (properties.containsKey(name)) {
        return properties.get(name);
    } else {
        return super.getProperty(name);
    }
}


public Object invokeMethod(String name,Object arg){
    if (JSON.equals(name) && stack.isEmpty()) {
        return createObject(name, arg);
    }
    Object[] args = (Object[]) arg;
    if (args.length == 0) {
        throw new MissingMethodException(name, getClass(), args);
    }
    Object value = null;
    if (args.length > 1) {
        JSONArray array = new JSONArray();
        stack.push(array);
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Closure) {
                append(name, createObject((Closure) args[i]));
            } else if (args[i] instanceof Map) {
                append(name, createObject((Map) args[i]));
            } else if (args[i] instanceof List) {
                append(name, createArray((List) args[i]));
            } else {
                _append(name, args[i], (JSON) stack.peek());
            }
        }
        stack.pop();
    } else {
        if (args[0] instanceof Closure) {
            value = createObject((Closure) args[0]);
        } else if (args[0] instanceof Map) {
            value = createObject((Map) args[0]);
        } else if (args[0] instanceof List) {
            value = createArray((List) args[0]);
        }
    }
    if (stack.isEmpty()) {
        JSONObject object = new JSONObject();
        object.accumulate(name, current);
        current = object;
    } else {
        JSON top = (JSON) stack.peek();
        if (top instanceof JSONObject) {
            append(name, current == null ? value : current);
        }
    }
    return current;
}


public void _append(String key,Object value,JSON target){
    if (target instanceof JSONObject) {
        ((JSONObject) target).accumulate(key, value);
    } else if (target instanceof JSONArray) {
        ((JSONArray) target).element(value);
    }
}


public void append(String key,Object value){
    Object target = null;
    if (!stack.isEmpty()) {
        target = stack.peek();
        current = (JSON) target;
        _append(key, value, current);
    } else {
        properties.put(key, value);
    }
}


public JSON createObject(String name,Object arg){
    Object[] args = (Object[]) arg;
    if (args.length == 0) {
        throw new MissingMethodException(name, getClass(), args);
    }
    if (args.length == 1) {
        if (args[0] instanceof Closure) {
            return createObject((Closure) args[0]);
        } else if (args[0] instanceof Map) {
            return createObject((Map) args[0]);
        } else if (args[0] instanceof List) {
            return createArray((List) args[0]);
        } else {
            throw new JSONException("Unsupported type");
        }
    } else {
        JSONArray array = new JSONArray();
        stack.push(array);
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Closure) {
                append(name, createObject((Closure) args[i]));
            } else if (args[i] instanceof Map) {
                append(name, createObject((Map) args[i]));
            } else if (args[i] instanceof List) {
                append(name, createArray((List) args[i]));
            } else {
                _append(name, args[i], (JSON) stack.peek());
            }
        }
        stack.pop();
        return array;
    }
}


}
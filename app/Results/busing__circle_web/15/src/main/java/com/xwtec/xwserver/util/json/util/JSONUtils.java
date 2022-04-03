package com.xwtec.xwserver.util.json.util;
 import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.ezmorph.bean.MorphDynaClass;
import com.xwtec.xwserver.util.json.JSON;
import com.xwtec.xwserver.util.json.JSONArray;
import com.xwtec.xwserver.util.json.JSONException;
import com.xwtec.xwserver.util.json.JSONFunction;
import com.xwtec.xwserver.util.json.JSONNull;
import com.xwtec.xwserver.util.json.JSONObject;
import com.xwtec.xwserver.util.json.JSONString;
import com.xwtec.xwserver.util.json.JsonConfig;
import com.xwtec.xwserver.util.json.regexp.RegexpUtils;
import org.apache.commons.beanutils.DynaBean;
@SuppressWarnings("unchecked")
public class JSONUtils {

 public  String DOUBLE_QUOTE;

 public  String SINGLE_QUOTE;

 private  String FUNCTION_BODY_PATTERN;

 private  String FUNCTION_HEADER_PATTERN;

 private  String FUNCTION_PARAMS_PATTERN;

 private  String FUNCTION_PATTERN;

 private  String FUNCTION_PREFIX;

 private  MorpherRegistry morpherRegistry;

private JSONUtils() {
    super();
}
public String stripQuotes(String input){
    if (input.length() < 2) {
        return input;
    } else if (input.startsWith(SINGLE_QUOTE) && input.endsWith(SINGLE_QUOTE)) {
        return input.substring(1, input.length() - 1);
    } else if (input.startsWith(DOUBLE_QUOTE) && input.endsWith(DOUBLE_QUOTE)) {
        return input.substring(1, input.length() - 1);
    } else {
        return input;
    }
}


public DynaBean newDynaBean(JSONObject jsonObject,JsonConfig jsonConfig){
    Map props = getProperties(jsonObject);
    for (Iterator entries = props.entrySet().iterator(); entries.hasNext(); ) {
        Map.Entry entry = (Map.Entry) entries.next();
        String key = (String) entry.getKey();
        if (!JSONUtils.isJavaIdentifier(key)) {
            String parsedKey = JSONUtils.convertToJavaIdentifier(key, jsonConfig);
            if (parsedKey.compareTo(key) != 0) {
                props.put(parsedKey, props.remove(key));
            }
        }
    }
    MorphDynaClass dynaClass = new MorphDynaClass(props);
    MorphDynaBean dynaBean = null;
    try {
        dynaBean = (MorphDynaBean) dynaClass.newInstance();
        dynaBean.setDynaBeanClass(dynaClass);
    } catch (Exception e) {
        throw new JSONException(e);
    }
    return dynaBean;
}


public String getFunctionBody(String function){
    return RegexpUtils.getMatcher(FUNCTION_BODY_PATTERN, true).getGroupIfMatches(function, 1);
}


public String doubleToString(double d){
    if (Double.isInfinite(d) || Double.isNaN(d)) {
        return "null";
    }
    // Shave off trailing zeros and decimal point, if possible.
    String s = Double.toString(d);
    if (s.indexOf('.') > 0 && s.indexOf('e') < 0 && s.indexOf('E') < 0) {
        while (s.endsWith("0")) {
            s = s.substring(0, s.length() - 1);
        }
        if (s.endsWith(".")) {
            s = s.substring(0, s.length() - 1);
        }
    }
    return s;
}


public MorpherRegistry getMorpherRegistry(){
    return morpherRegistry;
}


public boolean isBigInteger(Number n){
    if (n instanceof BigInteger) {
        return true;
    }
    try {
        new BigInteger(String.valueOf(n));
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}


public String valueToString(Object value,int indentFactor,int indent){
    if (value == null || isNull(value)) {
        return "null";
    }
    if (value instanceof JSONFunction) {
        return ((JSONFunction) value).toString();
    }
    if (value instanceof JSONString) {
        return ((JSONString) value).toJSONString();
    }
    if (value instanceof Number) {
        return numberToString((Number) value);
    }
    if (value instanceof Boolean) {
        return value.toString();
    }
    if (value instanceof JSONObject) {
        return ((JSONObject) value).toString(indentFactor, indent);
    }
    if (value instanceof JSONArray) {
        return ((JSONArray) value).toString(indentFactor, indent);
    }
    return quote(value.toString());
}


public Class getInnerComponentType(Class type){
    if (!type.isArray()) {
        return type;
    }
    return getInnerComponentType(type.getComponentType());
}


public boolean isBoolean(Object obj){
    if ((obj instanceof Boolean) || (obj != null && obj.getClass() == Boolean.TYPE)) {
        return true;
    }
    return false;
}


public boolean isInteger(Number n){
    if (n instanceof Integer) {
        return true;
    }
    try {
        Integer.parseInt(String.valueOf(n));
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}


public String convertToJavaIdentifier(String key,JsonConfig jsonConfig){
    try {
        return jsonConfig.getJavaIdentifierTransformer().transformToJavaIdentifier(key);
    } catch (JSONException jsone) {
        throw jsone;
    } catch (Exception e) {
        throw new JSONException(e);
    }
}


public String quote(String string){
    if (isFunction(string)) {
        return string;
    }
    if (string == null || string.length() == 0) {
        return "\"\"";
    }
    char b;
    char c = 0;
    int i;
    int len = string.length();
    StringBuffer sb = new StringBuffer(len * 2);
    String t;
    char[] chars = string.toCharArray();
    char[] buffer = new char[1030];
    int bufferIndex = 0;
    sb.append('"');
    for (i = 0; i < len; i += 1) {
        if (bufferIndex > 1024) {
            sb.append(buffer, 0, bufferIndex);
            bufferIndex = 0;
        }
        b = c;
        c = chars[i];
        switch(c) {
            case '\\':
            case '"':
                buffer[bufferIndex++] = '\\';
                buffer[bufferIndex++] = c;
                break;
            case '/':
                if (b == '<') {
                    buffer[bufferIndex++] = '\\';
                }
                buffer[bufferIndex++] = c;
                break;
            default:
                if (c < ' ') {
                    switch(c) {
                        case '\b':
                            buffer[bufferIndex++] = '\\';
                            buffer[bufferIndex++] = 'b';
                            break;
                        case '\t':
                            buffer[bufferIndex++] = '\\';
                            buffer[bufferIndex++] = 't';
                            break;
                        case '\n':
                            buffer[bufferIndex++] = '\\';
                            buffer[bufferIndex++] = 'n';
                            break;
                        case '\f':
                            buffer[bufferIndex++] = '\\';
                            buffer[bufferIndex++] = 'f';
                            break;
                        case '\r':
                            buffer[bufferIndex++] = '\\';
                            buffer[bufferIndex++] = 'r';
                            break;
                        default:
                            t = "000" + Integer.toHexString(c);
                            int tLength = t.length();
                            buffer[bufferIndex++] = '\\';
                            buffer[bufferIndex++] = 'u';
                            buffer[bufferIndex++] = t.charAt(tLength - 4);
                            buffer[bufferIndex++] = t.charAt(tLength - 3);
                            buffer[bufferIndex++] = t.charAt(tLength - 2);
                            buffer[bufferIndex++] = t.charAt(tLength - 1);
                    }
                } else {
                    buffer[bufferIndex++] = c;
                }
        }
    }
    sb.append(buffer, 0, bufferIndex);
    sb.append('"');
    return sb.toString();
}


public int hashCode(Object value){
    if (value == null) {
        return JSONNull.getInstance().hashCode();
    } else if (value instanceof JSON || value instanceof String || value instanceof JSONFunction) {
        return value.hashCode();
    } else {
        return String.valueOf(value).hashCode();
    }
}


public boolean isNumber(Object obj){
    if ((obj != null && obj.getClass() == Byte.TYPE) || (obj != null && obj.getClass() == Short.TYPE) || (obj != null && obj.getClass() == Integer.TYPE) || (obj != null && obj.getClass() == Long.TYPE) || (obj != null && obj.getClass() == Float.TYPE) || (obj != null && obj.getClass() == Double.TYPE)) {
        return true;
    }
    return obj instanceof Number;
}


public String numberToString(Number n){
    if (n == null) {
        throw new JSONException("Null pointer");
    }
    testValidity(n);
    // Shave off trailing zeros and decimal point, if possible.
    String s = n.toString();
    if (s.indexOf('.') > 0 && s.indexOf('e') < 0 && s.indexOf('E') < 0) {
        while (s.endsWith("0")) {
            s = s.substring(0, s.length() - 1);
        }
        if (s.endsWith(".")) {
            s = s.substring(0, s.length() - 1);
        }
    }
    return s;
}


public boolean hasQuotes(String input){
    if (input == null || input.length() < 2) {
        return false;
    }
    return input.startsWith(SINGLE_QUOTE) && input.endsWith(SINGLE_QUOTE) || input.startsWith(DOUBLE_QUOTE) && input.endsWith(DOUBLE_QUOTE);
}


public boolean isString(Object obj){
    if ((obj instanceof String) || (obj instanceof Character) || (obj != null && (obj.getClass() == Character.TYPE || String.class.isAssignableFrom(obj.getClass())))) {
        return true;
    }
    return false;
}


public String getFunctionParams(String function){
    return RegexpUtils.getMatcher(FUNCTION_PARAMS_PATTERN, true).getGroupIfMatches(function, 1);
}


public boolean isBigDecimal(Number n){
    if (n instanceof BigDecimal) {
        return true;
    }
    try {
        new BigDecimal(String.valueOf(n));
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}


public boolean isLong(Number n){
    if (n instanceof Long) {
        return true;
    }
    try {
        Long.parseLong(String.valueOf(n));
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}


public boolean isObject(Object obj){
    return !isNumber(obj) && !isString(obj) && !isBoolean(obj) && !isArray(obj) && !isFunction(obj) || isNull(obj);
}


public boolean isFunction(Object obj){
    if (obj instanceof String) {
        String str = (String) obj;
        return str.startsWith(FUNCTION_PREFIX) && RegexpUtils.getMatcher(FUNCTION_PATTERN, true).matches(str);
    }
    if (obj instanceof JSONFunction) {
        return true;
    }
    return false;
}


public Map getProperties(JSONObject jsonObject){
    Map properties = new HashMap();
    for (Iterator keys = jsonObject.keys(); keys.hasNext(); ) {
        String key = (String) keys.next();
        /*
          * String parsedKey = key; if( !JSONUtils.isJavaIdentifier( parsedKey ) ){
          * parsedKey = JSONUtils.convertToJavaIdentifier( key ); }
          */
        properties.put(key, getTypeClass(jsonObject.get(key)));
    }
    return properties;
}


public boolean isJsonKeyword(String input,JsonConfig jsonConfig){
    if (input == null) {
        return false;
    }
    return "null".equals(input) || "true".equals(input) || "false".equals(input) || (jsonConfig.isJavascriptCompliant() && "undefined".equals(input));
}


public boolean mayBeJSON(String string){
    return string != null && ("null".equals(string) || (string.startsWith("[") && string.endsWith("]")) || (string.startsWith("{") && string.endsWith("}")));
}


public boolean isFloat(Number n){
    if (n instanceof Float) {
        return true;
    }
    try {
        float f = Float.parseFloat(String.valueOf(n));
        return !Float.isInfinite(f);
    } catch (NumberFormatException e) {
        return false;
    }
}


public Number transformNumber(Number input){
    if (input instanceof Float) {
        return new Double(input.toString());
    } else if (input instanceof Short) {
        return new Integer(input.intValue());
    } else if (input instanceof Byte) {
        return new Integer(input.intValue());
    } else if (input instanceof Long) {
        Long max = new Long(Integer.MAX_VALUE);
        if (input.longValue() <= max.longValue() && input.longValue() >= Integer.MIN_VALUE) {
            return new Integer(input.intValue());
        }
    }
    return input;
}


public boolean isNull(Object obj){
    if (obj instanceof JSONObject) {
        return ((JSONObject) obj).isNullObject();
    }
    return JSONNull.getInstance().equals(obj);
}


public void testValidity(Object o){
    if (o != null) {
        if (o instanceof Double) {
            if (((Double) o).isInfinite() || ((Double) o).isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers");
            }
        } else if (o instanceof Float) {
            if (((Float) o).isInfinite() || ((Float) o).isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        } else if (o instanceof BigDecimal || o instanceof BigInteger) {
            // ok
            return;
        }
    }
}


public boolean isArray(Object obj){
    if ((obj != null && obj.getClass().isArray()) || (obj instanceof Collection) || (obj instanceof JSONArray)) {
        return true;
    }
    return false;
}


public boolean isFunctionHeader(Object obj){
    if (obj instanceof String) {
        String str = (String) obj;
        return str.startsWith(FUNCTION_PREFIX) && RegexpUtils.getMatcher(FUNCTION_HEADER_PATTERN, true).matches(str);
    }
    return false;
}


public Class getTypeClass(Object obj){
    if (isNull(obj)) {
        return Object.class;
    } else if (isArray(obj)) {
        return List.class;
    } else if (isFunction(obj)) {
        return JSONFunction.class;
    } else if (isBoolean(obj)) {
        return Boolean.class;
    } else if (isNumber(obj)) {
        Number n = (Number) obj;
        if (isInteger(n)) {
            return Integer.class;
        } else if (isLong(n)) {
            return Long.class;
        } else if (isFloat(n)) {
            return Float.class;
        } else if (isBigInteger(n)) {
            return BigInteger.class;
        } else if (isBigDecimal(n)) {
            return BigDecimal.class;
        } else if (isDouble(n)) {
            return Double.class;
        } else {
            throw new JSONException("Unsupported type");
        }
    } else if (isString(obj)) {
        return String.class;
    } else if (isObject(obj)) {
        return Object.class;
    } else {
        throw new JSONException("Unsupported type");
    }
}


public boolean isDouble(Number n){
    if (n instanceof Double) {
        return true;
    }
    try {
        double d = Double.parseDouble(String.valueOf(n));
        return !Double.isInfinite(d);
    } catch (NumberFormatException e) {
        return false;
    }
}


public boolean isJavaIdentifier(String str){
    if (str.length() == 0 || !Character.isJavaIdentifierStart(str.charAt(0))) {
        return false;
    }
    for (int i = 1; i < str.length(); i++) {
        if (!Character.isJavaIdentifierPart(str.charAt(i))) {
            return false;
        }
    }
    return true;
}


}
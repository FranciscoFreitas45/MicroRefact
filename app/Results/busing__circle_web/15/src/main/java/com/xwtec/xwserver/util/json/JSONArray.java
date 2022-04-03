package com.xwtec.xwserver.util.json;
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
@SuppressWarnings({ "unchecked", "serial" })
public class JSONArray extends AbstractJSONimplements Comparable,JSON,List{

 private  List elements;

 private  boolean expandElements;

 private int currentIndex;

 private int lastIndex;

/**
 * Construct an empty JSONArray.
 */
public JSONArray() {
    this.elements = new ArrayList();
}
public JSONArray discard(Object o){
    elements.remove(o);
    return this;
}


public JSONArray _fromString(String string,JsonConfig jsonConfig){
    return _fromJSONTokener(new JSONTokener(string), jsonConfig);
}


public List toList(JSONArray jsonArray,Object root,JsonConfig jsonConfig){
    if (jsonArray.size() == 0 || root == null) {
        return new ArrayList();
    }
    List list = new ArrayList();
    int size = jsonArray.size();
    for (int i = 0; i < size; i++) {
        Object value = jsonArray.get(i);
        if (JSONUtils.isNull(value)) {
            list.add(null);
        } else {
            Class type = value.getClass();
            if (JSONArray.class.isAssignableFrom(type)) {
                list.add(toList((JSONArray) value, root, jsonConfig));
            } else if (String.class.isAssignableFrom(type) || Boolean.class.isAssignableFrom(type) || JSONUtils.isNumber(type) || Character.class.isAssignableFrom(type) || JSONFunction.class.isAssignableFrom(type)) {
                list.add(value);
            } else {
                try {
                    Object newRoot = jsonConfig.getNewBeanInstanceStrategy().newInstance(root.getClass(), null);
                    list.add(JSONObject.toBean((JSONObject) value, newRoot, jsonConfig));
                } catch (JSONException jsone) {
                    throw jsone;
                } catch (Exception e) {
                    throw new JSONException(e);
                }
            }
        }
    }
    return list;
}


public int compareTo(Object obj){
    if (obj != null && (obj instanceof JSONArray)) {
        JSONArray other = (JSONArray) obj;
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


public Object processValue(Object value,JsonConfig jsonConfig){
    if (value != null) {
        JsonValueProcessor jsonValueProcessor = jsonConfig.findJsonValueProcessor(value.getClass());
        if (jsonValueProcessor != null) {
            value = jsonValueProcessor.processArrayValue(value, jsonConfig);
            if (!JsonVerifier.isValidJsonValue(value)) {
                throw new JSONException("Value is not a valid JSON value. " + value);
            }
        }
    }
    return _processValue(value, jsonConfig);
}


public JSONArray getJSONArray(int index){
    Object o = get(index);
    if (o != null && o instanceof JSONArray) {
        return (JSONArray) o;
    }
    throw new JSONException("JSONArray[" + index + "] is not a JSONArray.");
}


public boolean optBoolean(int index,boolean defaultValue){
    try {
        return getBoolean(index);
    } catch (Exception e) {
        return defaultValue;
    }
}


public Iterator iterator(){
    return new JSONArrayListIterator();
}


public boolean removeAll(Collection collection,JsonConfig jsonConfig){
    return elements.removeAll(fromObject(collection, jsonConfig));
}


public JSONArray _fromCollection(Collection collection,JsonConfig jsonConfig){
    if (!addInstance(collection)) {
        try {
            return jsonConfig.getCycleDetectionStrategy().handleRepeatedReferenceAsArray(collection);
        } catch (JSONException jsone) {
            removeInstance(collection);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        } catch (RuntimeException e) {
            removeInstance(collection);
            JSONException jsone = new JSONException(e);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        }
    }
    fireArrayStartEvent(jsonConfig);
    JSONArray jsonArray = new JSONArray();
    try {
        int i = 0;
        for (Iterator elements = collection.iterator(); elements.hasNext(); ) {
            Object element = elements.next();
            jsonArray.addValue(element, jsonConfig);
            fireElementAddedEvent(i, jsonArray.get(i++), jsonConfig);
        }
    } catch (JSONException jsone) {
        removeInstance(collection);
        fireErrorEvent(jsone, jsonConfig);
        throw jsone;
    } catch (RuntimeException e) {
        removeInstance(collection);
        JSONException jsone = new JSONException(e);
        fireErrorEvent(jsone, jsonConfig);
        throw jsone;
    }
    removeInstance(collection);
    fireArrayEndEvent(jsonConfig);
    return jsonArray;
}


public Object[] toArray(Object[] array){
    return elements.toArray(array);
}


public String optString(int index,String defaultValue){
    Object o = opt(index);
    return o != null ? o.toString() : defaultValue;
}


public JSONObject toJSONObject(JSONArray names){
    if (names == null || names.size() == 0 || size() == 0) {
        return null;
    }
    JSONObject jo = new JSONObject();
    for (int i = 0; i < names.size(); i++) {
        jo.element(names.getString(i), this.opt(i));
    }
    return jo;
}


public String join(String separator,boolean stripQuotes){
    int len = size();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < len; i += 1) {
        if (i > 0) {
            sb.append(separator);
        }
        String value = JSONUtils.valueToString(this.elements.get(i));
        sb.append(stripQuotes ? JSONUtils.stripQuotes(value) : value);
    }
    return sb.toString();
}


public JSONArray _addValue(Object value,JsonConfig jsonConfig){
    this.elements.add(value);
    return this;
}


public int indexOf(Object o){
    return elements.indexOf(o);
}


public Writer write(Writer writer){
    try {
        boolean b = false;
        int len = size();
        writer.write('[');
        for (int i = 0; i < len; i += 1) {
            if (b) {
                writer.write(',');
            }
            Object v = this.elements.get(i);
            if (v instanceof JSONObject) {
                ((JSONObject) v).write(writer);
            } else if (v instanceof JSONArray) {
                ((JSONArray) v).write(writer);
            } else {
                writer.write(JSONUtils.valueToString(v));
            }
            b = true;
        }
        writer.write(']');
        return writer;
    } catch (IOException e) {
        throw new JSONException(e);
    }
}


public JSONArray element(String value,JsonConfig jsonConfig){
    if (value == null) {
        this.elements.add("");
    } else if (JSONUtils.hasQuotes(value)) {
        this.elements.add(value);
    } else if (JSONNull.getInstance().equals(value)) {
        this.elements.add(JSONNull.getInstance());
    } else if (JSONUtils.isJsonKeyword(value, jsonConfig)) {
        if (jsonConfig.isJavascriptCompliant() && "undefined".equals(value)) {
            this.elements.add(JSONNull.getInstance());
        } else {
            this.elements.add(value);
        }
    } else if (JSONUtils.mayBeJSON(value)) {
        try {
            this.elements.add(JSONSerializer.toJSON(value, jsonConfig));
        } catch (JSONException jsone) {
            this.elements.add(value);
        }
    } else {
        this.elements.add(value);
    }
    return this;
}


public JSONArray addString(String str){
    if (str != null) {
        elements.add(str);
    }
    return this;
}


public JSONArray addValue(Object value,JsonConfig jsonConfig){
    return _addValue(processValue(value, jsonConfig), jsonConfig);
}


public void add(Object obj){
    try {
        JSONArray.this.add(currentIndex++, obj);
        lastIndex = -1;
    } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
    }
}


public JSONObject optJSONObject(int index){
    Object o = opt(index);
    return o instanceof JSONObject ? (JSONObject) o : null;
}


public boolean hasNext(){
    return currentIndex != size();
}


public JSONArray _fromJSONArray(JSONArray array,JsonConfig jsonConfig){
    if (!addInstance(array)) {
        try {
            return jsonConfig.getCycleDetectionStrategy().handleRepeatedReferenceAsArray(array);
        } catch (JSONException jsone) {
            removeInstance(array);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        } catch (RuntimeException e) {
            removeInstance(array);
            JSONException jsone = new JSONException(e);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        }
    }
    fireArrayStartEvent(jsonConfig);
    JSONArray jsonArray = new JSONArray();
    int index = 0;
    for (Iterator elements = array.iterator(); elements.hasNext(); ) {
        Object element = elements.next();
        jsonArray.addValue(element, jsonConfig);
        fireElementAddedEvent(index++, element, jsonConfig);
    }
    removeInstance(array);
    fireArrayEndEvent(jsonConfig);
    return jsonArray;
}


public JSONArray _fromJSONString(JSONString string,JsonConfig jsonConfig){
    return _fromJSONTokener(new JSONTokener(string.toJSONString()), jsonConfig);
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


public boolean contains(Object o,JsonConfig jsonConfig){
    return elements.contains(processValue(o, jsonConfig));
}


public Object opt(int index){
    return (index < 0 || index >= size()) ? null : this.elements.get(index);
}


public int size(){
    return this.elements.size();
}


public long optLong(int index,long defaultValue){
    try {
        return getLong(index);
    } catch (Exception e) {
        return defaultValue;
    }
}


public JSONArray _fromArray(short[] array,JsonConfig jsonConfig){
    if (!addInstance(array)) {
        try {
            return jsonConfig.getCycleDetectionStrategy().handleRepeatedReferenceAsArray(array);
        } catch (JSONException jsone) {
            removeInstance(array);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        } catch (RuntimeException e) {
            removeInstance(array);
            JSONException jsone = new JSONException(e);
            fireErrorEvent(jsone, jsonConfig);
            throw jsone;
        }
    }
    fireArrayStartEvent(jsonConfig);
    JSONArray jsonArray = new JSONArray();
    for (int i = 0; i < array.length; i++) {
        Number n = JSONUtils.transformNumber(new Short(array[i]));
        jsonArray.addValue(n, jsonConfig);
        fireElementAddedEvent(i, n, jsonConfig);
    }
    removeInstance(array);
    fireArrayEndEvent(jsonConfig);
    return jsonArray;
}


public ListIterator listIterator(int index){
    if (index < 0 || index > size())
        throw new IndexOutOfBoundsException("Index: " + index);
    return new JSONArrayListIterator(index);
}


public boolean isArray(){
    return true;
}


public double optDouble(int index,double defaultValue){
    try {
        return getDouble(index);
    } catch (Exception e) {
        return defaultValue;
    }
}


public Object next(){
    try {
        Object next = get(currentIndex);
        lastIndex = currentIndex++;
        return next;
    } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
    }
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
}


public void processArrayDimensions(JSONArray jsonArray,List dims,int index){
    if (dims.size() <= index) {
        dims.add(new Integer(jsonArray.size()));
    } else {
        int i = ((Integer) dims.get(index)).intValue();
        if (jsonArray.size() > i) {
            dims.set(index, new Integer(jsonArray.size()));
        }
    }
    for (Iterator i = jsonArray.iterator(); i.hasNext(); ) {
        Object item = i.next();
        if (item instanceof JSONArray) {
            processArrayDimensions((JSONArray) item, dims, index + 1);
        }
    }
}


public JSONArray _fromJSONTokener(JSONTokener tokener,JsonConfig jsonConfig){
    JSONArray jsonArray = new JSONArray();
    int index = 0;
    try {
        if (tokener.nextClean() != '[') {
            throw tokener.syntaxError("A JSONArray text must start with '['");
        }
        fireArrayStartEvent(jsonConfig);
        if (tokener.nextClean() == ']') {
            fireArrayEndEvent(jsonConfig);
            return jsonArray;
        }
        tokener.back();
        for (; ; ) {
            if (tokener.nextClean() == ',') {
                tokener.back();
                jsonArray.elements.add(JSONNull.getInstance());
                fireElementAddedEvent(index, jsonArray.get(index++), jsonConfig);
            } else {
                tokener.back();
                Object v = tokener.nextValue(jsonConfig);
                if (!JSONUtils.isFunctionHeader(v)) {
                    if (v instanceof String && JSONUtils.mayBeJSON((String) v)) {
                        jsonArray.addValue(JSONUtils.DOUBLE_QUOTE + v + JSONUtils.DOUBLE_QUOTE, jsonConfig);
                    } else {
                        jsonArray.addValue(v, jsonConfig);
                    }
                    fireElementAddedEvent(index, jsonArray.get(index++), jsonConfig);
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
                    jsonArray.addValue(new JSONFunction((params != null) ? StringUtils.split(params, ",") : null, text), jsonConfig);
                    fireElementAddedEvent(index, jsonArray.get(index++), jsonConfig);
                }
            }
            switch(tokener.nextClean()) {
                case ';':
                case ',':
                    if (tokener.nextClean() == ']') {
                        fireArrayEndEvent(jsonConfig);
                        return jsonArray;
                    }
                    tokener.back();
                    break;
                case ']':
                    fireArrayEndEvent(jsonConfig);
                    return jsonArray;
                default:
                    throw tokener.syntaxError("Expected a ',' or ']'");
            }
        }
    } catch (JSONException jsone) {
        fireErrorEvent(jsone, jsonConfig);
        throw jsone;
    }
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


public Object _processValue(Object value,JsonConfig jsonConfig){
    if (value instanceof JSONTokener) {
        return _fromJSONTokener((JSONTokener) value, jsonConfig);
    } else if (value != null && Enum.class.isAssignableFrom(value.getClass())) {
        return ((Enum) value).name();
    } else if (value instanceof Annotation || (value != null && value.getClass().isAnnotation())) {
        throw new JSONException("Unsupported type");
    }
    return super._processValue(value, jsonConfig);
}


public int nextIndex(){
    return currentIndex;
}


public void setExpandElements(boolean expandElements){
    this.expandElements = expandElements;
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


public void remove(){
    if (lastIndex == -1)
        throw new IllegalStateException();
    try {
        JSONArray.this.remove(lastIndex);
        if (lastIndex < currentIndex) {
            currentIndex--;
        }
        lastIndex = -1;
    } catch (IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
    }
}


public Collection toCollection(JSONArray jsonArray,JsonConfig jsonConfig){
    Collection collection = null;
    Class collectionType = jsonConfig.getCollectionType();
    if (collectionType.isInterface()) {
        if (collectionType.equals(List.class)) {
            collection = new ArrayList();
        } else if (collectionType.equals(Set.class)) {
            collection = new HashSet();
        } else {
            throw new JSONException("unknown interface: " + collectionType);
        }
    } else {
        try {
            collection = (Collection) collectionType.newInstance();
        } catch (InstantiationException e) {
            throw new JSONException(e);
        } catch (IllegalAccessException e) {
            throw new JSONException(e);
        }
    }
    Class objectClass = jsonConfig.getRootClass();
    Map classMap = jsonConfig.getClassMap();
    int size = jsonArray.size();
    for (int i = 0; i < size; i++) {
        Object value = jsonArray.get(i);
        if (JSONUtils.isNull(value)) {
            collection.add(null);
        } else {
            Class type = value.getClass();
            if (JSONArray.class.isAssignableFrom(value.getClass())) {
                collection.add(toCollection((JSONArray) value, jsonConfig));
            } else if (String.class.isAssignableFrom(type) || Boolean.class.isAssignableFrom(type) || JSONUtils.isNumber(type) || Character.class.isAssignableFrom(type) || JSONFunction.class.isAssignableFrom(type)) {
                if (!value.getClass().isAssignableFrom(type)) {
                    throw new JSONException("can't assign value " + value + " of type " + value.getClass() + " to Collection of type " + type);
                }
                collection.add(value);
            } else {
                if (objectClass != null) {
                    JsonConfig jsc = jsonConfig.copy();
                    jsc.setRootClass(objectClass);
                    jsc.setClassMap(classMap);
                    collection.add(JSONObject.toBean((JSONObject) value, jsc));
                } else {
                    collection.add(JSONObject.toBean((JSONObject) value));
                }
            }
        }
    }
    return collection;
}


public int hashCode(){
    int hashcode = 29;
    for (Iterator e = elements.iterator(); e.hasNext(); ) {
        Object element = e.next();
        hashcode += JSONUtils.hashCode(element);
    }
    return hashcode;
}


public Object get(int index){
    /*
       * Object o = opt( index ); if( o == null ){ throw new JSONException(
       * "JSONArray[" + index + "] not found." ); } return o;
       */
    return this.elements.get(index);
}


public boolean hasPrevious(){
    return currentIndex != 0;
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


public List subList(int fromIndex,int toIndex){
    return elements.subList(fromIndex, toIndex);
}


public void set(Object obj){
    if (lastIndex == -1) {
        throw new IllegalStateException();
    }
    try {
        JSONArray.this.set(lastIndex, obj);
    } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
    }
}


public Object previous(){
    try {
        int index = currentIndex - 1;
        Object previous = get(index);
        lastIndex = currentIndex = index;
        return previous;
    } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
    }
}


public boolean containsAll(Collection collection,JsonConfig jsonConfig){
    return elements.containsAll(fromObject(collection, jsonConfig));
}


public boolean isExpandElements(){
    return expandElements;
}


public void clear(){
    elements.clear();
}


public boolean isEmpty(){
    return this.elements.isEmpty();
}


public String getString(int index){
    Object o = get(index);
    if (o != null) {
        return o.toString();
    }
    throw new JSONException("JSONArray[" + index + "] not found.");
}


public JSONArray optJSONArray(int index){
    Object o = opt(index);
    return o instanceof JSONArray ? (JSONArray) o : null;
}


public long getLong(int index){
    Object o = get(index);
    if (o != null) {
        return o instanceof Number ? ((Number) o).longValue() : (long) getDouble(index);
    }
    throw new JSONException("JSONArray[" + index + "] is not a number.");
}


public int lastIndexOf(Object o){
    return elements.lastIndexOf(o);
}


public boolean addAll(int index,Collection collection,JsonConfig jsonConfig){
    if (collection == null || collection.size() == 0) {
        return false;
    }
    int offset = 0;
    for (Iterator i = collection.iterator(); i.hasNext(); ) {
        this.elements.add(index + (offset++), processValue(i.next(), jsonConfig));
    }
    return true;
}


public int optInt(int index,int defaultValue){
    try {
        return getInt(index);
    } catch (Exception e) {
        return defaultValue;
    }
}


public boolean equals(Object obj){
    if (obj == this) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (!(obj instanceof JSONArray)) {
        return false;
    }
    JSONArray other = (JSONArray) obj;
    if (other.size() != size()) {
        return false;
    }
    int max = size();
    for (int i = 0; i < max; i++) {
        Object o1 = get(i);
        Object o2 = other.get(i);
        // handle nulls
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
        if (o1 instanceof JSONArray && o2 instanceof JSONArray) {
            JSONArray e = (JSONArray) o1;
            JSONArray a = (JSONArray) o2;
            if (!a.equals(e)) {
                return false;
            }
        } else {
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
    }
    return true;
}


public String toString(int indentFactor,int indent){
    int len = size();
    if (len == 0) {
        return "[]";
    }
    if (indentFactor == 0) {
        return this.toString();
    }
    int i;
    StringBuffer sb = new StringBuffer("[");
    if (len == 1) {
        sb.append(JSONUtils.valueToString(this.elements.get(0), indentFactor, indent));
    } else {
        int newindent = indent + indentFactor;
        sb.append('\n');
        for (i = 0; i < len; i += 1) {
            if (i > 0) {
                sb.append(",\n");
            }
            for (int j = 0; j < newindent; j += 1) {
                sb.append(' ');
            }
            sb.append(JSONUtils.valueToString(this.elements.get(i), indentFactor, newindent));
        }
        sb.append('\n');
        for (i = 0; i < indent; i += 1) {
            sb.append(' ');
        }
        for (i = 0; i < indent; i += 1) {
            sb.insert(0, ' ');
        }
    }
    sb.append(']');
    return sb.toString();
}


public int previousIndex(){
    return currentIndex - 1;
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


public boolean retainAll(Collection collection,JsonConfig jsonConfig){
    return elements.retainAll(fromObject(collection, jsonConfig));
}


}
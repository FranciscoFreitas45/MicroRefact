package com.xwtec.xwserver.util;
 import java.util.Collection;
import java.util.Map;
public class Assert {


public void isTrue(boolean expression){
    isTrue(expression, "[Assertion failed] - this expression must be true");
}


public void notNull(Object object){
    notNull(object, "[Assertion failed] - this argument is required; it must not be null");
}


public void isNull(Object object){
    isNull(object, "[Assertion failed] - the object argument must be null");
}


public void noNullElements(Collection<?> collection){
    noNullElements(collection, "[Assertion failed] - this array must not contain any null elements");
}


public void isEmpty(String string){
    isEmpty(string, "[Assertion failed] - the string argument must be empty [e.g. null、\"\"、\"null\"]");
}


public void isInstanceOf(Class<?> type,Object obj,String message){
    notNull(type, "Type to check against must not be null");
    if (!type.isInstance(obj)) {
        throw new IllegalArgumentException(message + "Object of class [" + (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type);
    }
}


public void notEmpty(Map<?,?> map){
    notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
}


}
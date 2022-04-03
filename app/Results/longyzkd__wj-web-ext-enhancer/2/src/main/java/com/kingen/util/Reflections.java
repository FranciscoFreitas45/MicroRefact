package com.kingen.util;
 import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Reflections {

 private  String SETTER_PREFIX;

 private  String GETTER_PREFIX;

 private  String CGLIB_CLASS_SEPARATOR;

 private  Logger logger;


public Method getAccessibleMethod(Object obj,String methodName,Class<?> parameterTypes){
    Validate.notNull(obj, "object can't be null");
    Validate.notBlank(methodName, "methodName can't be blank");
    for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
        try {
            Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
            makeAccessible(method);
            return method;
        } catch (NoSuchMethodException e) {
        // Method不在当前类定义,继续向上转型
        }
    }
    return null;
}


public void setFieldValue(Object obj,String fieldName,Object value){
    Field field = getAccessibleField(obj, fieldName);
    if (field == null) {
        throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
    }
    try {
        field.set(obj, value);
    } catch (IllegalAccessException e) {
        logger.error("不可能抛出的异常:{}", e.getMessage());
    }
}


public RuntimeException convertReflectionExceptionToUnchecked(Exception e){
    if ((e instanceof IllegalAccessException) || (e instanceof IllegalArgumentException) || (e instanceof NoSuchMethodException)) {
        return new IllegalArgumentException(e);
    } else if (e instanceof InvocationTargetException) {
        return new RuntimeException(((InvocationTargetException) e).getTargetException());
    } else if (e instanceof RuntimeException) {
        return (RuntimeException) e;
    }
    return new RuntimeException("Unexpected Checked Exception.", e);
}


public Object invokeGetter(Object obj,String propertyName){
    String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(propertyName);
    return invokeMethod(obj, getterMethodName, new Class[] {}, new Object[] {});
}


public Class<?> getUserClass(Object instance){
    Validate.notNull(instance, "Instance must not be null");
    Class clazz = instance.getClass();
    if ((clazz != null) && clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
        Class<?> superClass = clazz.getSuperclass();
        if ((superClass != null) && !Object.class.equals(superClass)) {
            return superClass;
        }
    }
    return clazz;
}


public Object invokeMethodByName(Object obj,String methodName,Object[] args){
    Method method = getAccessibleMethodByName(obj, methodName);
    if (method == null) {
        throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
    }
    try {
        return method.invoke(obj, args);
    } catch (Exception e) {
        throw convertReflectionExceptionToUnchecked(e);
    }
}


public Class getClassGenricType(Class clazz,int index){
    Type genType = clazz.getGenericSuperclass();
    if (!(genType instanceof ParameterizedType)) {
        logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
        return Object.class;
    }
    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
    if ((index >= params.length) || (index < 0)) {
        logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
        return Object.class;
    }
    if (!(params[index] instanceof Class)) {
        logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
        return Object.class;
    }
    return (Class) params[index];
}


public void invokeSetter(Object obj,String propertyName,Object value){
    String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(propertyName);
    invokeMethodByName(obj, setterMethodName, new Object[] { value });
}


public Object getFieldValue(Object obj,String fieldName){
    Field field = getAccessibleField(obj, fieldName);
    if (field == null) {
        throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
    }
    Object result = null;
    try {
        result = field.get(obj);
    } catch (IllegalAccessException e) {
        logger.error("不可能抛出的异常{}", e.getMessage());
    }
    return result;
}


public Method getAccessibleMethodByName(Object obj,String methodName){
    Validate.notNull(obj, "object can't be null");
    Validate.notBlank(methodName, "methodName can't be blank");
    for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
        Method[] methods = searchType.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                makeAccessible(method);
                return method;
            }
        }
    }
    return null;
}


public Object invokeMethod(Object obj,String methodName,Class<?>[] parameterTypes,Object[] args){
    Method method = getAccessibleMethod(obj, methodName, parameterTypes);
    if (method == null) {
        throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
    }
    try {
        return method.invoke(obj, args);
    } catch (Exception e) {
        throw convertReflectionExceptionToUnchecked(e);
    }
}


public void makeAccessible(Field field){
    if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
        field.setAccessible(true);
    }
}


public Field getAccessibleField(Object obj,String fieldName){
    Validate.notNull(obj, "object can't be null");
    Validate.notBlank(fieldName, "fieldName can't be blank");
    for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
        try {
            Field field = superClass.getDeclaredField(fieldName);
            makeAccessible(field);
            return field;
        } catch (NoSuchFieldException e) {
        // NOSONAR
        // Field不在当前类定义,继续向上转型
        }
    }
    return null;
}


}
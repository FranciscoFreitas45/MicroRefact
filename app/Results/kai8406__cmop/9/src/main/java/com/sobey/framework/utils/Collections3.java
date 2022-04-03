package com.sobey.framework.utils;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
public class Collections3 {


@SuppressWarnings({ "rawtypes", "unchecked" })
public List extractToList(Collection collection,String propertyName){
    List list = new ArrayList(collection.size());
    try {
        for (Object obj : collection) {
            list.add(PropertyUtils.getProperty(obj, propertyName));
        }
    } catch (Exception e) {
        throw Reflections.convertReflectionExceptionToUnchecked(e);
    }
    return list;
}


public T getFirst(Collection<T> collection){
    if (isEmpty(collection)) {
        return null;
    }
    return collection.iterator().next();
}


public List<T> subtract(Collection<T> a,Collection<T> b){
    List<T> list = new ArrayList<T>(a);
    for (T element : b) {
        list.remove(element);
    }
    return list;
}


public List<T> intersection(Collection<T> a,Collection<T> b){
    List<T> list = new ArrayList<T>();
    for (T element : a) {
        if (b.contains(element)) {
            list.add(element);
        }
    }
    return list;
}


@SuppressWarnings("rawtypes")
public boolean isEmpty(Collection collection){
    return (collection == null || collection.isEmpty());
}


@SuppressWarnings("rawtypes")
public String convertToString(Collection collection,String prefix,String postfix){
    StringBuilder builder = new StringBuilder();
    for (Object o : collection) {
        builder.append(prefix).append(o).append(postfix);
    }
    return builder.toString();
}


@SuppressWarnings("rawtypes")
public boolean isNotEmpty(Collection collection){
    return (collection != null && !(collection.isEmpty()));
}


@SuppressWarnings({ "rawtypes", "unchecked" })
public Map extractToMap(Collection collection,String keyPropertyName,String valuePropertyName){
    Map map = new HashMap(collection.size());
    try {
        for (Object obj : collection) {
            map.put(PropertyUtils.getProperty(obj, keyPropertyName), PropertyUtils.getProperty(obj, valuePropertyName));
        }
    } catch (Exception e) {
        throw Reflections.convertReflectionExceptionToUnchecked(e);
    }
    return map;
}


public List<T> union(Collection<T> a,Collection<T> b){
    List<T> result = new ArrayList<T>(a);
    result.addAll(b);
    return result;
}


public T getLast(Collection<T> collection){
    if (isEmpty(collection)) {
        return null;
    }
    // 当类型为List时，直接取得最后一个元素 .
    if (collection instanceof List) {
        List<T> list = (List<T>) collection;
        return list.get(list.size() - 1);
    }
    // 其他类型通过iterator滚动到最后一个元素.
    Iterator<T> iterator = collection.iterator();
    while (true) {
        T current = iterator.next();
        if (!iterator.hasNext()) {
            return current;
        }
    }
}


@SuppressWarnings("rawtypes")
public String extractToString(Collection collection,String propertyName,String separator){
    List list = extractToList(collection, propertyName);
    return StringUtils.join(list, separator);
}


}
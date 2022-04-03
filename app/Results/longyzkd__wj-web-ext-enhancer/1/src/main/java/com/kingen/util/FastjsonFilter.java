package com.kingen.util;
 import java.util.HashSet;
import java.util.Set;
import org.hibernate.collection.internal.PersistentSet;
import com.alibaba.fastjson.serializer.PropertyFilter;
public class FastjsonFilter implements PropertyFilter{

 private  Set<String> includes;

 private  Set<String> excludes;


public boolean apply(Object source,String name,Object value){
    if (value != null && (value instanceof PersistentSet)) {
        // 避免hibernate对象循环引用，一切Set属性不予序列化
        return false;
    }
    if (excludes.contains(name)) {
        return false;
    }
    if (excludes.contains(source.getClass().getSimpleName() + "." + name)) {
        return false;
    }
    if (includes.size() == 0 || includes.contains(name)) {
        return true;
    }
    if (includes.contains(source.getClass().getSimpleName() + "." + name)) {
        return true;
    }
    return false;
}


public Set<String> getIncludes(){
    return includes;
}


public Set<String> getExcludes(){
    return excludes;
}


}
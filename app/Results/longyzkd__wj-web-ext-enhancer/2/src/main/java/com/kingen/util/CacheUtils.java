package com.kingen.util;
 import java.util.Iterator;
import java.util.Set;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CacheUtils {

 private  Logger logger;

 private  CacheManager cacheManager;

 private  String SYS_CACHE;


public String getKey(String key){
    // String dsName = DataSourceHolder.getDataSourceName();
    // if (StringUtils.isNotBlank(dsName)){
    // return dsName + "_" + key;
    // }
    return key;
}


public void removeAll(String cacheName){
    Cache<String, Object> cache = getCache(cacheName);
    Set<String> keys = cache.keys();
    for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
        cache.remove(it.next());
    }
    logger.info("清理缓存： {} => {}", cacheName, keys);
}


public Cache<String,Object> getCache(String cacheName){
    Cache<String, Object> cache = cacheManager.getCache(cacheName);
    if (cache == null) {
        throw new RuntimeException("当前系统中没有定义“" + cacheName + "”这个缓存。");
    }
    return cache;
}


public Object get(String cacheName,String key,Object defaultValue){
    Object value = get(cacheName, getKey(key));
    return value != null ? value : defaultValue;
}


public void put(String cacheName,String key,Object value){
    getCache(cacheName).put(getKey(key), value);
}


public void remove(String cacheName,String key){
    getCache(cacheName).remove(getKey(key));
}


}
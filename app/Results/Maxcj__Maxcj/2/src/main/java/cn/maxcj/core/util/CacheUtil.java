package cn.maxcj.core.util;
 import cn.stylefeng.roses.core.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import java.util.List;
@Slf4j
public class CacheUtil {

 private  Object LOCKER;


public void removeAll(String cacheName){
    getOrAddCache(cacheName).removeAll();
}


@SuppressWarnings("all")
public T get(String cacheName,Object key){
    Element element = getOrAddCache(cacheName).get(key);
    if (element == null) {
        return null;
    } else {
        Object objectValue = element.getObjectValue();
        return (T) objectValue;
    }
}


public Cache getOrAddCache(String cacheName){
    CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);
    Cache cache = cacheManager.getCache(cacheName);
    if (cache == null) {
        synchronized (LOCKER) {
            cache = cacheManager.getCache(cacheName);
            if (cache == null) {
                cacheManager.addCacheIfAbsent(cacheName);
                cache = cacheManager.getCache(cacheName);
            }
        }
    }
    return cache;
}


public List getKeys(String cacheName){
    return getOrAddCache(cacheName).getKeys();
}


public void put(String cacheName,Object key,Object value){
    getOrAddCache(cacheName).put(new Element(key, value));
}


public void remove(String cacheName,Object key){
    getOrAddCache(cacheName).remove(key);
}


}
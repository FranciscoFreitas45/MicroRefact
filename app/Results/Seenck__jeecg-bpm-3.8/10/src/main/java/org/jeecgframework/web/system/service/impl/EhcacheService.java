package org.jeecgframework.web.system.service.impl;
 import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class EhcacheService implements CacheServiceI{

 private  Logger log;

 public  CacheManager manager;


@Override
public Object get(String cacheName,Object key){
    log.debug("  EhcacheService  get cacheName: [{}] , key: [{}]", cacheName, key);
    Cache cache = manager.getCache(cacheName);
    if (cache != null) {
        Element element = cache.get(key);
        if (element != null) {
            return element.getObjectValue();
        }
    }
    return null;
}


@Override
public void clean(String cacheName){
    log.info("  EhcacheService  clean cacheName：[{}] ", cacheName);
    Cache eCache = manager.getCache(cacheName);
    if (eCache != null) {
        eCache.removeAll();
    }
}


@Override
public void put(String cacheName,Object key,Object value){
    log.debug("  EhcacheService  put cacheName: [{}] , key: [{}]", cacheName, key);
    Cache cache = manager.getCache(cacheName);
    if (cache != null) {
        cache.put(new Element(key, value));
    }
}


@Override
public boolean remove(String cacheName,Object key){
    log.debug("  EhcacheService  remove cacheName: [{}] , key: [{}]", cacheName, key);
    Cache cache = manager.getCache(cacheName);
    if (cache != null) {
        return cache.remove(key);
    }
    return false;
}


}
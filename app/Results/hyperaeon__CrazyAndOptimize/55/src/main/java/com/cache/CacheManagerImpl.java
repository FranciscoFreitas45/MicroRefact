package com.cache;
 import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
public class CacheManagerImpl implements ICacheManager{

 private  Map<String,Cache> caches;


@Override
public void clearByKey(String key){
    if (this.isContains(key)) {
        caches.remove(key);
    }
}


@Override
public Map<String,Cache> getAllCache(){
    return caches;
}


@Override
public Object getDataByKey(String key){
    if (this.isContains(key)) {
        return caches.get(key).getData();
    }
    return null;
}


@Override
public Set<String> getAllKeys(){
    return caches.keySet();
}


@Override
public boolean isTimeout(String key){
    if (!this.isContains(key)) {
        return true;
    }
    Cache cache = caches.get(key);
    long timeOut = cache.getTimeOut();
    long lastRefreshTime = cache.getLastRefreshTime();
    return !(timeOut == 0 || System.currentTimeMillis() - lastRefreshTime >= timeOut);
}


@Override
public Cache getCacheByKey(String key){
    if (this.isContains(key)) {
        return caches.get(key);
    }
    return null;
}


@Override
public boolean isContains(String key){
    return caches.containsKey(key);
}


@Override
public void clearAll(){
    caches.clear();
}


@Override
public void putCache(String key,Object data,long timeOut){
    timeOut = timeOut > 0 ? timeOut : 0;
    caches.put(key, new Cache(data, timeOut, System.currentTimeMillis()));
}


}
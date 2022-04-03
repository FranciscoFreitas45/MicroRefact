package com.ukefu.webim.service.cache;
 import java.util.Collection;
import java.util.concurrent.locks.Lock;
import com.hazelcast.com.eclipsesource.json.JsonObject;
public interface CacheBean {


public void setAtomicLong(String cacheName,long start)
;

public Collection<?> getAllCacheObject(String orgi)
;

public Lock getLock(String lock,String orgi)
;

public void clear(String orgi)
;

public void update(String key,String orgi,Object object)
;

public Object getCacheObject(String key,String orgi,Object defaultValue)
;

public Object delete(String key,String orgi)
;

public void put(String key,Object value,String orgi)
;

public Object getCache()
;

public long getSize()
;

public CacheBean getCacheInstance(String cacheName)
;

public JsonObject getStatics()
;

public long getAtomicLong(String cacheName)
;

}
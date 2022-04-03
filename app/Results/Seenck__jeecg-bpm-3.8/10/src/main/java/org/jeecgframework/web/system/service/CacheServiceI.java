package org.jeecgframework.web.system.service;
 public interface CacheServiceI {

 public  String SYSTEM_BASE_CACHE;

 public  String TAG_CACHE;

 public  String FOREVER_CACHE;

 public  String SYS_AUTH_CACHE;


public Object get(String cacheName,Object key)
;

public void clean(String cacheName)
;

public void put(String cacheName,Object key,Object value)
;

public boolean remove(String cacheName,Object key)
;

}
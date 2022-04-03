package com.cache;
 import java.util.Map;
import java.util.Set;
public interface ICacheManager {


public void clearByKey(String key)
;

public Map<String,Cache> getAllCache()
;

public Object getDataByKey(String key)
;

public Set<String> getAllKeys()
;

public boolean isTimeout(String key)
;

public Cache getCacheByKey(String key)
;

public boolean isContains(String key)
;

public void clearAll()
;

public void putCache(String key,Object data,long timeOut)
;

}
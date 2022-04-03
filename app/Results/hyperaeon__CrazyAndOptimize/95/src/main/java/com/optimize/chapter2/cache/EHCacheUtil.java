package com.optimize.chapter2.cache;
 import java.io.Serializable;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
public class EHCacheUtil {

 private  CacheManager manager;

 private  String configfile;


public Serializable get(String cacheName,Serializable key){
    try {
        Element e = manager.getCache(cacheName).get(key);
        if (e == null) {
            return null;
        }
        return e.getValue();
    } catch (IllegalStateException e) {
        e.printStackTrace();
    } catch (CacheException e) {
        e.printStackTrace();
    }
    return null;
}


public void put(String cahceName,Serializable key,Serializable value){
    manager.getCache(cahceName).put(new Element(key, value));
}


}
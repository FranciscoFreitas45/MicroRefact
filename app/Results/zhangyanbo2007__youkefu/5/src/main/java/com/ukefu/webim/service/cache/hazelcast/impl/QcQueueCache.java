package com.ukefu.webim.service.cache.hazelcast.impl;
 import java.util.Collection;
import java.util.concurrent.locks.Lock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hazelcast.com.eclipsesource.json.JsonObject;
import com.hazelcast.core.HazelcastInstance;
import com.ukefu.webim.service.cache.CacheBean;
@Service("qcqueue_cache")
public class QcQueueCache implements CacheBean{

@Autowired
 public  HazelcastInstance hazelcastInstance;

 private  String cacheName;


@Override
public void setAtomicLong(String cacheName,long start){
    getInstance().getAtomicLong(getName()).set(start);
}


@Override
public Collection<?> getAllCacheObject(String orgi){
    return null;
}


public String getName(){
    return cacheName;
}


@Override
public Lock getLock(String lock,String orgi){
    // TODO Auto-generated method stub
    return getInstance().getLock(lock);
}


@Override
public void clear(String orgi){
    getInstance().getQueue(getName()).clear();
}


@Override
public void update(String key,String orgi,Object value){
}


@Override
public Object getCacheObject(String key,String orgi,Object defaultValue){
    return getCacheObject(key, orgi);
}


@Override
public Object delete(String key,String orgi){
    return null;
}


@Override
public void put(String key,Object value,String orgi){
    getInstance().getQueue(getName()).offer(value);
}


@Override
public Object getCache(){
    return getInstance().getQueue(cacheName);
}


@Override
public long getSize(){
    return getInstance().getMap(getName()).size();
}


public CacheBean getCacheInstance(String cacheName){
    this.cacheName = cacheName;
    return this;
}


public void service(){
// TODO Auto-generated method stub
}


@Override
public JsonObject getStatics(){
    // TODO Auto-generated method stub
    return getInstance().getMap(getName()).getLocalMapStats().toJson();
}


@Override
public long getAtomicLong(String cacheName){
    return getInstance().getAtomicLong(getName()).incrementAndGet();
}


public HazelcastInstance getInstance(){
    return hazelcastInstance;
}


}
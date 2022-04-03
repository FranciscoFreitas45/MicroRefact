package org.jeecgframework.web.system.service.impl;
 import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import javax.annotation.Resource;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import freemarker.template.Template;
public class RedisCacheService implements CacheServiceI{

 private  Logger log;

@Resource
 private  RedisTemplate redisTemplate;


public byte[] serialize(Object obj){
    ObjectOutputStream obi = null;
    ByteArrayOutputStream bai = null;
    try {
        bai = new ByteArrayOutputStream();
        obi = new ObjectOutputStream(bai);
        obi.writeObject(obj);
        byte[] byt = bai.toByteArray();
        return byt;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}


public Object unserizlize(byte[] byt){
    ObjectInputStream oii = null;
    ByteArrayInputStream bis = null;
    bis = new ByteArrayInputStream(byt);
    try {
        oii = new ObjectInputStream(bis);
        Object obj = oii.readObject();
        return obj;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


@Override
public Object get(String cacheName,Object key){
    Object obj = redisTemplate.boundValueOps(cacheName + "_" + key).get();
    log.debug("  RedisCacheService  get cacheName: [{}] , key: [{}]", cacheName, key);
    if (obj != null && obj instanceof byte[]) {
        obj = unserizlize((byte[]) obj);
        return obj;
    }
    return obj;
}


@Override
public void clean(String cacheName){
    log.info("  RedisCacheService  clean cacheName：[{}] ", cacheName);
    Set dictKeys = redisTemplate.keys(cacheName + "*");
    if (dictKeys != null && !dictKeys.isEmpty())
        redisTemplate.delete(dictKeys);
}


@Override
public void put(String cacheName,Object key,Object value){
    log.debug("  RedisCacheService  put cacheName: [{}] , key: [{}]", cacheName, key);
    if (value instanceof Template) {
        redisTemplate.boundValueOps(cacheName + "_" + key).set(serialize(value));
    } else
        redisTemplate.boundValueOps(cacheName + "_" + key).set(value);
}


@Override
public boolean remove(String cacheName,Object key){
    log.debug("  RedisCacheService  remove cacheName: [{}] , key: [{}]", cacheName, key);
    if (redisTemplate.hasKey(cacheName + "_" + key)) {
        redisTemplate.delete(cacheName + "_" + key);
        return true;
    }
    return false;
}


}
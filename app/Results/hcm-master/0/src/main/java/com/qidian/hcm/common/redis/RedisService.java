package com.qidian.hcm.common.redis;
 import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@Service
@Slf4j
public class RedisService {

@Autowired
 private  RedisTemplate redisTemplate;


public Long rightPush(String k,Object v){
    return redisTemplate.opsForList().rightPush(k, v);
}


public void set(String key,Object value,Long expireTime){
    redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
}


public void zsetAdd(String key,Object value,double source){
    redisTemplate.opsForZSet().add(key, value, source);
}


public Long leftPush(String k,Object v){
    return redisTemplate.opsForList().leftPush(k, v);
}


public void remove(String key){
    redisTemplate.delete(key);
}


public void batchRemove(String keys){
    redisTemplate.delete(keys);
}


public void hmSet(String key,Object hashKey,Object value){
    redisTemplate.opsForHash().put(key, hashKey, value);
}


public Long addSet(String key,Object value){
    return redisTemplate.opsForSet().add(key, value);
}


public void removePattern(String pattern){
    Set<Serializable> keys = redisTemplate.keys(pattern);
    if (!CollectionUtils.isEmpty(keys)) {
        redisTemplate.delete(keys);
    }
}


public void expire(String key,long expireTime){
    redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
}


public Object get(String key){
    return redisTemplate.opsForValue().get(key);
}


public Boolean exists(String key){
    return redisTemplate.hasKey(key);
}


public Set<Object> rangeByScore(String key,double source,double source1){
    return redisTemplate.opsForZSet().rangeByScore(key, source, source1);
}


public Object hmGet(String key,Object hashKey){
    return redisTemplate.opsForHash().get(key, hashKey);
}


}
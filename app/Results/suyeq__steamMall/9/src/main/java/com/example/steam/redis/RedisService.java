package com.example.steam.redis;
 import com.alibaba.fastjson.JSON;
import com.example.steam.utils.RankScoreValue;
import com.example.steam.vo.SpecialGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import java.io.IOException;
import java.util;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
import com.example.steam.DTO.RedisPrefixKey;
@Service
public class RedisService {

 private  double MIN_SEED;

 private Logger log;

@Autowired
 private JedisPool pool;


public void lpush(RedisPrefixKey keyPrefix,String key,T value){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        jedis.lpush(realKey, beanToString(value));
    } finally {
        jedis.close();
    }
}


public Set<T> zrange(RedisPrefixKey keyPrefix,String key,long start,long end,Class<T> tClass){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        Set<String> set = jedis.zrange(realKey, start, end);
        Iterator iterator = set.iterator();
        Set<T> resultSet = new LinkedHashSet<>();
        while (iterator.hasNext()) {
            resultSet.add(stringToBean((String) iterator.next(), tClass));
        }
        return resultSet;
    } finally {
        jedis.close();
    }
}


public void set(RedisPrefixKey keyPrefix,String key,T value){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        if (keyPrefix.expiredTime() <= 0) {
            jedis.set(realKey, beanToString(value));
        } else {
            jedis.setex(realKey, keyPrefix.expiredTime(), beanToString(value));
        }
    } finally {
        jedis.close();
    }
}


public Long zcount(RedisPrefixKey keyPrefix,String key){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        return jedis.zcount(realKey, MIN_SEED, 0);
    } finally {
        jedis.close();
    }
}


public List<T> stringToArrayBean(String value,Class<T> tClass){
    if (value == null) {
        return null;
    }
    return JSON.parseArray(value, tClass);
}


public Double zincr(RedisPrefixKey keyPrefix,String key,T member){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        return jedis.zincrby(realKey, -1d, beanToString(member));
    } finally {
        jedis.close();
    }
}


public Long incKey(RedisPrefixKey keyPrefix,String key){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        return jedis.incr(realKey);
    } finally {
        jedis.close();
    }
}


public List<T> getPipelineBatch(RedisPrefixKey keyPrefix,List<String> keyList,Class<T> clazz){
    Jedis jedis = null;
    Pipeline pipeline = null;
    List<T> valueList = null;
    HashMap<String, Response<String>> intrmMap = null;
    try {
        jedis = pool.getResource();
        valueList = new LinkedList<>();
        intrmMap = new HashMap<>();
        pipeline = jedis.pipelined();
        for (String key : keyList) {
            String finalKey = keyPrefix.getThisPrefix() + key;
            intrmMap.put(finalKey, pipeline.get(finalKey));
        }
        pipeline.sync();
        for (Map.Entry<String, Response<String>> entry : intrmMap.entrySet()) {
            String value = entry.getValue().get();
            valueList.add(stringToBean(value, clazz));
        }
        return valueList;
    } finally {
        try {
            pipeline.close();
        } catch (Exception e) {
            log.error("I/O异常");
        }
        jedis.close();
    }
}


public void del(RedisPrefixKey keyPrefix,String key){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        jedis.del(realKey);
    } finally {
        jedis.close();
    }
}


public T rpop(RedisPrefixKey keyPrefix,String key,Class<T> tClass){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        String value = jedis.rpop(realKey);
        return stringToBean(value, tClass);
    } finally {
        jedis.close();
    }
}


public boolean isExists(RedisPrefixKey keyPrefix,String key){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        return jedis.exists(realKey);
    } finally {
        jedis.close();
    }
}


public String beanToString(T value){
    if (value == null) {
        // throw new NullPointerException("value  is null");
        return null;
    }
    Class clazz = value.getClass();
    if (clazz == int.class || clazz == Integer.class || clazz == long.class || clazz == Long.class || clazz == float.class || clazz == Float.class || clazz == double.class || clazz == Double.class) {
        return value + "";
    } else if (value.getClass() == String.class) {
        return (String) value;
    }
    return JSON.toJSONString(value);
}


public List<T> getList(RedisPrefixKey keyPrefix,String key,Class<T> clazz){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String value = jedis.get(keyPrefix.getThisPrefix() + key);
        return stringToArrayBean(value, clazz);
    } finally {
        jedis.close();
    }
}


public T stringToBean(String value,Class<T> tClass){
    if (value == null) {
        // throw  new NullPointerException("value is null");
        return null;
    }
    if (tClass == String.class) {
        return (T) value;
    }
    return JSON.parseObject(value, tClass);
}


public T get(RedisPrefixKey keyPrefix,String key,Class<T> clazz){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String value = jedis.get(keyPrefix.getThisPrefix() + key);
        return stringToBean(value, clazz);
    } finally {
        jedis.close();
    }
}


public long zrem(RedisPrefixKey keyPrefix,String key,T member){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        String realMember = beanToString(member);
        return jedis.zrem(realKey, realMember);
    } finally {
        jedis.close();
    }
}


public long llength(RedisPrefixKey keyPrefix,String key){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        return jedis.llen(realKey);
    } finally {
        jedis.close();
    }
}


public void zadd(RedisPrefixKey keyPrefix,String key,RankScoreValue<T> rank){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        jedis.zadd(realKey, -Double.parseDouble(rank.getScore() + ""), beanToString(rank.getValue()));
    } finally {
        jedis.close();
    }
}


public Long decKey(RedisPrefixKey keyPrefix,String key){
    Jedis jedis = null;
    try {
        jedis = pool.getResource();
        String realKey = keyPrefix.getThisPrefix() + key;
        return jedis.decr(realKey);
    } finally {
        jedis.close();
    }
}


}
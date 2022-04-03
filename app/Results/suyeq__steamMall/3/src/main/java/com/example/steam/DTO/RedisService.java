package com.example.steam.DTO;
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
public class RedisService {

 private  double MIN_SEED;

 private Logger log;

 private JedisPool pool;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/beanToString"))

.queryParam("value",value)
;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


}
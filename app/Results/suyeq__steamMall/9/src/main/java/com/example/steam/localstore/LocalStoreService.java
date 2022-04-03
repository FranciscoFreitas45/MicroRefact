package com.example.steam.localstore;
 import com.example.steam.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class LocalStoreService {

 private Logger log;

 private  Map<String,String> map;


public void set(LocalStoreKey key,T value,String page){
    String finalValue = RedisService.beanToString(value);
    key.setExpiredTime(page);
    String finalKey = key.getKeyName() + page;
    log.info(finalKey + " " + "设置本地缓存成功");
    map.put(finalKey, finalValue);
}


public T get(LocalStoreKey key,Class<T> tClass,String page){
    long now = System.currentTimeMillis();
    String finalKey = key.getKeyName() + page;
    LocalStoreKey.ExpiredTime expiredTime = key.getExpiredTimeHashMap().get(page);
    if (expiredTime == null || (now - expiredTime.startTime >= expiredTime.expiredTime)) {
        return null;
    }
    String value = map.get(finalKey);
    log.info(finalKey + " " + "获取本地缓存成功");
    return RedisService.stringToBean(value, tClass);
}


}
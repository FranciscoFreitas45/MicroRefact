package com.dtdhehe.ptulife.util;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@Component
public class RedisUtils {

@Autowired
 private  RedisTemplate<String,Object> template;

public RedisUtils(RedisTemplate<String, Object> template) {
    this.template = template;
}
public long lRemove(String key,long count,Object value){
    try {
        Long remove = template.opsForList().remove(key, count, value);
        return remove;
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}


public long lGetListSize(String key){
    try {
        return template.opsForList().size(key);
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}


public boolean lUpdateIndex(String key,long index,Object value){
    try {
        template.opsForList().set(key, index, value);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


@SuppressWarnings("unchecked")
public void del(String key){
    if (key != null && key.length > 0) {
        if (key.length == 1) {
            template.delete(key[0]);
        } else {
            template.delete(CollectionUtils.arrayToList(key));
        }
    }
}


public boolean hset(String key,String item,Object value,long time){
    try {
        template.opsForHash().put(key, item, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public long sGetSetSize(String key){
    try {
        return template.opsForSet().size(key);
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}


public boolean hmset(String key,Map<String,Object> map,long time){
    try {
        template.opsForHash().putAll(key, map);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public long setRemove(String key,Object values){
    try {
        Long count = template.opsForSet().remove(key, values);
        return count;
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}


public void hdel(String key,Object item){
    template.opsForHash().delete(key, item);
}


public long getExpire(String key){
    return template.getExpire(key, TimeUnit.SECONDS);
}


public double hincr(String key,String item,double by){
    return template.opsForHash().increment(key, item, by);
}


public Set<Object> sGet(String key){
    try {
        return template.opsForSet().members(key);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


public Object get(String key){
    return StringUtils.isEmpty(key) ? null : template.opsForValue().get(key);
}


public long sSet(String key,Object values){
    try {
        return template.opsForSet().add(key, values);
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}


public boolean hHasKey(String key,String item){
    return template.opsForHash().hasKey(key, item);
}


public Map<Object,Object> hmget(String key){
    return template.opsForHash().entries(key);
}


public long decr(String key,long delta){
    if (delta < 0) {
        throw new RuntimeException("递减因子必须大于0");
    }
    return template.opsForValue().increment(key, -delta);
}


public Object hget(String key,String item){
    return template.opsForHash().get(key, item);
}


public boolean hasKey(String key){
    try {
        return template.hasKey(key);
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public boolean set(String key,Object value,long time){
    try {
        if (time > 0) {
            template.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public boolean lSet(String key,List<Object> value,long time){
    try {
        template.opsForList().rightPushAll(key, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public long incr(String key,long delta){
    if (delta < 0) {
        throw new RuntimeException("递增因子必须大于0");
    }
    return template.opsForValue().increment(key, delta);
}


public boolean sHasKey(String key,Object value){
    try {
        return template.opsForSet().isMember(key, value);
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public double hdecr(String key,String item,double by){
    return template.opsForHash().increment(key, item, -by);
}


public long sSetAndTime(String key,long time,Object values){
    try {
        Long count = template.opsForSet().add(key, values);
        if (time > 0) {
            expire(key, time);
        }
        return count;
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}


public boolean expire(String key,long time){
    try {
        if (time > 0) {
            template.expire(key, time, TimeUnit.SECONDS);
        }
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


public List<Object> lGet(String key,long start,long end){
    try {
        return template.opsForList().range(key, start, end);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


public Object lGetIndex(String key,long index){
    try {
        return template.opsForList().index(key, index);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


}
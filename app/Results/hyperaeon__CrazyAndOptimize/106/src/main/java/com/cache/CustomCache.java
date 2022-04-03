package com.cache;
 import lombok.Builder;
import lombok.Data;
import java.util;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
public class CustomCache {

 private  int MAXIMUM_CAPACITY;

 private  Map<String,Value> map;

 private  int cacheSize;

public CustomCache() {
    map = new ConcurrentHashMap<>(cacheSize);
}public CustomCache(int size) {
    this.cacheSize = size;
    map = new ConcurrentHashMap<>(size);
}
public String getOldestKey(){
    if (cacheSize() <= 0) {
        return null;
    }
    Comparator<Value> comparator = Comparator.comparing(Value::getLatestAccessTime);
    List<String> sortedKey = map.entrySet().stream().sorted(Map.Entry.comparingByValue(comparator)).map(e -> e.getKey()).collect(Collectors.toList());
    return sortedKey.get(0);
}


public Object getCache(String key){
    Value value = map.get(key);
    if (value == null) {
        return null;
    }
    if (value.getExpireTime() < System.currentTimeMillis()) {
        // 惰性删除
        removeCache(key);
        return null;
    }
    value.setLatestAccessTime(System.currentTimeMillis());
    return value.getValue();
}


public int cacheSize(){
    return map.size();
}


public void removeCache(String key){
    map.remove(key);
}


public void main(String[] args){
    CustomCache customCache = new CustomCache(2);
    customCache.putCache("abc", "abc");
    Thread.sleep(200L);
    customCache.putCache("bcd", "bcd");
    customCache.putCache("def", "def");
    Object obj = customCache.getCache("abc");
    System.out.println(obj);
    int c = 7;
    System.out.println(tableSizeFor(c));
}


public int tableSizeFor(int c){
    int n = c - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}


public boolean putCache(String key,Object data,long expire){
    Value oldValue = map.get(key);
    long currentTime = System.currentTimeMillis();
    if (oldValue != null) {
        // 已经存在，则直接覆盖
        oldValue.setValue(data);
        oldValue.setExpireTime(currentTime + expire);
        oldValue.setLatestAccessTime(currentTime);
        return true;
    }
    if (cacheSize() >= cacheSize) {
        removeOldestCache();
    }
    Value newValue = Value.builder().value(data).expireTime(currentTime + expire).latestAccessTime(currentTime).build();
    map.put(key, newValue);
    return true;
}


public void removeOldestCache(){
    String oldestKey = this.getOldestKey();
    if (map.get(oldestKey) != null) {
        map.remove(oldestKey);
    }
}


}
package com.example.steam.Interface;
public interface RedisService {

   public void set(RedisPrefixKey keyPrefix,String key,T value);
   public void del(RedisPrefixKey keyPrefix,String key);
   public T get(RedisPrefixKey keyPrefix,String key,Class<T> clazz);
}
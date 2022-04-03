package com.example.steam.Interface;
public interface RedisService {

   public T get(RedisPrefixKey keyPrefix,String key,Class<T> clazz);
   public void set(RedisPrefixKey keyPrefix,String key,T value);
}
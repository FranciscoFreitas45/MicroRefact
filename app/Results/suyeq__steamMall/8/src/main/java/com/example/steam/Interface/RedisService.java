package com.example.steam.Interface;
public interface RedisService {

   public void set(RedisPrefixKey keyPrefix,String key,T value);
   public List<T> getList(RedisPrefixKey keyPrefix,String key,Class<T> clazz);
}
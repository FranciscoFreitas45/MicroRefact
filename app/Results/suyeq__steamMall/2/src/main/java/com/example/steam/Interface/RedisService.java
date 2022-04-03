package com.example.steam.Interface;
public interface RedisService {

   public void lpush(RedisPrefixKey keyPrefix,String key,T value);
   public long llength(RedisPrefixKey keyPrefix,String key);
   public T rpop(RedisPrefixKey keyPrefix,String key,Class<T> tClass);
   public void del(RedisPrefixKey keyPrefix,String key);
}
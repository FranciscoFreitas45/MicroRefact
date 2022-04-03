package com.example.steam.Interface;
public interface RedisService {

   public void lpush(RedisPrefixKey keyPrefix,String key,T value);
}
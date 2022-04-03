package com.example.steam.Interface;
public interface RedisService {

   public List<T> getPipelineBatch(RedisPrefixKey keyPrefix,List<String> keyList,Class<T> clazz);
}
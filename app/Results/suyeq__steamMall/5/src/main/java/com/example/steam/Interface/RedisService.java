package com.example.steam.Interface;
public interface RedisService {

   public T get(RedisPrefixKey keyPrefix,String key,Class<T> clazz);
   public void del(RedisPrefixKey keyPrefix,String key);
   public long zrem(RedisPrefixKey keyPrefix,String key,T member);
   public Double zincr(RedisPrefixKey keyPrefix,String key,T member);
   public void set(RedisPrefixKey keyPrefix,String key,T value);
   public Set<T> zrange(RedisPrefixKey keyPrefix,String key,long start,long end,Class<T> tClass);
   public List<T> getPipelineBatch(RedisPrefixKey keyPrefix,List<String> keyList,Class<T> clazz);
   public Long incKey(RedisPrefixKey keyPrefix,String key);
   public void zadd(RedisPrefixKey keyPrefix,String key,RankScoreValue<T> rank);
}
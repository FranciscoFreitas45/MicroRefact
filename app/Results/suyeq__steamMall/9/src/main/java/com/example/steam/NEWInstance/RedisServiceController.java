package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RedisServiceController {

 private RedisService redisservice;


@GetMapping
("/get")
public T get(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key,@RequestParam(name = "clazz") Class<T> clazz){
  return redisservice.get(keyPrefix,key,clazz);
}


@PutMapping
("/set")
public void set(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key,@RequestParam(name = "value") T value){
redisservice.set(keyPrefix,key,value);
}


@PutMapping
("/lpush")
public void lpush(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key,@RequestParam(name = "value") T value){
redisservice.lpush(keyPrefix,key,value);
}


@GetMapping
("/llength")
public long llength(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key){
  return redisservice.llength(keyPrefix,key);
}


@GetMapping
("/rpop")
public T rpop(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key,@RequestParam(name = "tClass") Class<T> tClass){
  return redisservice.rpop(keyPrefix,key,tClass);
}


@PutMapping
("/del")
public void del(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key){
redisservice.del(keyPrefix,key);
}


@GetMapping
("/decKey")
public Long decKey(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key){
  return redisservice.decKey(keyPrefix,key);
}


@GetMapping
("/getList")
public List<T> getList(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key,@RequestParam(name = "clazz") Class<T> clazz){
  return redisservice.getList(keyPrefix,key,clazz);
}


@GetMapping
("/beanToString")
public String beanToString(@RequestParam(name = "value") T value){
  return redisservice.beanToString(value);
}


@GetMapping
("/zrem")
public long zrem(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key,@RequestParam(name = "member") T member){
  return redisservice.zrem(keyPrefix,key,member);
}


@PutMapping
("/zadd")
public void zadd(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key,@RequestParam(name = "rank") RankScoreValue<T> rank){
redisservice.zadd(keyPrefix,key,rank);
}


@GetMapping
("/getPipelineBatch")
public List<T> getPipelineBatch(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "keyList") List<String> keyList,@RequestParam(name = "clazz") Class<T> clazz){
  return redisservice.getPipelineBatch(keyPrefix,keyList,clazz);
}


@GetMapping
("/zincr")
public Double zincr(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key,@RequestParam(name = "member") T member){
  return redisservice.zincr(keyPrefix,key,member);
}


@GetMapping
("/zcount")
public Long zcount(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key){
  return redisservice.zcount(keyPrefix,key);
}


@GetMapping
("/zrange")
public Set<T> zrange(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key,@RequestParam(name = "start") long start,@RequestParam(name = "end") long end,@RequestParam(name = "tClass") Class<T> tClass){
  return redisservice.zrange(keyPrefix,key,start,end,tClass);
}


@GetMapping
("/incKey")
public Long incKey(@RequestParam(name = "keyPrefix") RedisPrefixKey keyPrefix,@RequestParam(name = "key") String key){
  return redisservice.incKey(keyPrefix,key);
}


}
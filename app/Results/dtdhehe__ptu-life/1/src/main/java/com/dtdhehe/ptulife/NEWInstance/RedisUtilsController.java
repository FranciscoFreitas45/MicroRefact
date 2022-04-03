package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RedisUtilsController {

 private RedisUtils redisutils;


@GetMapping
("/get")
public Object get(@RequestParam(name = "key") String key){
  return redisutils.get(key);
}


@GetMapping
("/set")
public boolean set(@RequestParam(name = "key") String key,@RequestParam(name = "value") Object value,@RequestParam(name = "time") long time){
  return redisutils.set(key,value,time);
}


}
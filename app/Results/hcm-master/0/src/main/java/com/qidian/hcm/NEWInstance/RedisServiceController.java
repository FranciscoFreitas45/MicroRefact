package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RedisServiceController {

 private RedisService redisservice;


@PutMapping
("/set")
public void set(@RequestParam(name = "key") String key,@RequestParam(name = "value") Object value,@RequestParam(name = "expireTime") Long expireTime){
redisservice.set(key,value,expireTime);
}


}
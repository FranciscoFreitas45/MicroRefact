package com.example.steam.mq;
 import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.MQKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.steam.Interface.RedisService;
@Service
public class MQProducer {

@Autowired
 private RedisService redisService;


public void productEvent(Event event){
    redisService.lpush(MQKey.MQ, Event.EVENT_KEY, event);
}


}
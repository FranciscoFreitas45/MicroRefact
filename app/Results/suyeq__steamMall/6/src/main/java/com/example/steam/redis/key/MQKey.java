package com.example.steam.redis.key;
 import com.example.steam.redis.BaseKey;
public class MQKey extends BaseKey{

 public  MQKey MQ;

public MQKey(int expiredTime) {
    super(expiredTime);
}
}
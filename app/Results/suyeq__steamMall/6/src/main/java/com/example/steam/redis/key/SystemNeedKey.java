package com.example.steam.redis.key;
 import com.example.steam.redis.BaseKey;
public class SystemNeedKey extends BaseKey{

 public  SystemNeedKey SYSTEMNEED_ID;

public SystemNeedKey(int expiredTime) {
    super(expiredTime);
}
}
package com.example.steam.redis.key;
 import com.example.steam.redis.BaseKey;
public class CookieKey extends BaseKey{

 private  int expireTime;

 public  CookieKey EMAIL;

private CookieKey(int expireTime) {
    super(expireTime);
}
}
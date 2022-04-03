package com.example.steam.redis;
 public class BaseKey implements RedisPrefixKey{

 protected  String prefixKey;

 protected  int expiredTime;

public BaseKey(int expiredTime) {
    this.prefixKey = getClass().getSimpleName();
    this.expiredTime = expiredTime;
}
@Override
public String getThisPrefix(){
    return prefixKey + ":";
}


public void setExpiredTime(int second){
    this.expiredTime = second;
}


@Override
public int expiredTime(){
    return expiredTime;
}


}
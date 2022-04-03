package com.example.steam.redis.key;
 import com.example.steam.redis.BaseKey;
public class EmailKey extends BaseKey{

 private  int VERIFICATION_CODE_TIME;

 public  EmailKey VERIFICATION_CODE;

public EmailKey(int expiredTime) {
    super(expiredTime);
}
}
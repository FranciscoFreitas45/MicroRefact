package com.example.steam.redis.key;
 import com.example.steam.redis.BaseKey;
public class SensitiveKey extends BaseKey{

 public  String SENSITIVE_KEY;

 public  SensitiveKey WORD_LIST;

public SensitiveKey(int expiredTime) {
    super(expiredTime);
}
}
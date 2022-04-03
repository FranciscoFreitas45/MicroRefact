package com.example.steam.redis.key;
 import com.example.steam.redis.BaseKey;
public class UserKey extends BaseKey{

 public  String CONTAINS_KEY;

 public  String CONTAINS_CART_KEY;

 public  String ADMIN_KEY;

 private  int expireTime;

 public  UserKey USER_ID;

 public  UserKey COOKIE_ID;

 public  UserKey CONTAINS_GAMES;

 public  UserKey CONTAINS_CART;

 public  UserKey ADMIN_EMAIL;

private UserKey(int expireTime) {
    super(expireTime);
}
}
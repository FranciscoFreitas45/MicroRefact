package com.qidian.hcm.Interface;
public interface RedisService {

   public void set(String key,Object value,Long expireTime);
}
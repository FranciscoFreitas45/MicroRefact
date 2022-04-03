package com.dtdhehe.ptulife.Interface;
public interface RedisUtils {

   public Object get(String key);
   public boolean set(String key,Object value,long time);
}
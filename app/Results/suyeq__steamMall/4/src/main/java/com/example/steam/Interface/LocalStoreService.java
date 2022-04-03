package com.example.steam.Interface;
public interface LocalStoreService {

   public T get(LocalStoreKey key,Class<T> tClass,String page);
   public void set(LocalStoreKey key,T value,String page);
}
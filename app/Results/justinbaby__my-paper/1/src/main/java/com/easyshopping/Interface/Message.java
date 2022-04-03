package com.easyshopping.Interface;
public interface Message {

   public Message error(String content,Object args);
   public Message success(String content,Object args);
}
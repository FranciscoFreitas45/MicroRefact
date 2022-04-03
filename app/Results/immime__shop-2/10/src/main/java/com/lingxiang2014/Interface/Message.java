package com.lingxiang2014.Interface;
public interface Message {

   public Message error(String content,Object args);
   public Message success(String content,Object args);
}
package com.zis.Interface;
public interface SimpleMailSender {

   public void send(String[] recipients,String subject,Object content,String filePath);
}
package com.cym.Interface;
public interface ConfService {

   public void replace(String nginxPath,String nginxContent,List<String> subContent,List<String> subName,Boolean isBak,String adminName);
   public ConfExt buildConf(Boolean decompose,Boolean check);
}
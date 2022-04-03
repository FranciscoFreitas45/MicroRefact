package com.fosun.fc.projects.creepers.Interface;
public interface ICreepersTouristBlackListService {

   public List<TCreepersTourBlackList> findListByAgentNameAndType(String merName);
   public List<TCreepersTourBlackList> findListByGuideNo(String merName);
}
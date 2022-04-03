package com.example.steam.Interface;
public interface UserGameService {

   public boolean isContains(String email,long gameId);
   public List<RecentGameVo> findThreeRecentGameVoListByEmail(String email);
}
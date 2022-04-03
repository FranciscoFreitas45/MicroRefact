package com.example.steam.Interface;
public interface LabelService {

   public int deleteGameLabelByGameId(long gameId);
   public List<String> findLabelNamesByGameId(long gameId);
}
package com.example.steam.Interface;
public interface ImageService {

   public String findImageUrlById(long id,String dataSource);
   public List<String> findGameImageUrlsByGameId(long gameId);
}